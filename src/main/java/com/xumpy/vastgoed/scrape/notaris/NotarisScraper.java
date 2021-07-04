package com.xumpy.vastgoed.scrape.notaris;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xumpy.vastgoed.interfaces.Vastgoed;
import com.xumpy.vastgoed.interfaces.VastgoedScraper;
import com.xumpy.vastgoed.interfaces.VastgoedType;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NotarisScraper extends VastgoedScraper {

    @Override
    public Boolean sentTelegram() {
        return true;
    }

    @Override
    protected String getCity(Integer postcode){
        Map<Integer, String> aMap = new HashMap<>();
        aMap.put(3900, "51.1913215_5.3966251_10");
        aMap.put(3910, "51.252553_5.434349699999999_10");
        aMap.put(3920, "51.2167985_5.298356699999999_10");
        aMap.put(3930, "51.26976680000001_5.5154222_10");
        aMap.put(3940, "51.1105222_5.3295958_10");
        aMap.put(3950, "51.1829141_5.5758576_10");
        aMap.put(3990, "51.1156185_5.4463713_10");

        return aMap.get(postcode);
    }

    @Override
    protected String getType(VastgoedType type){
        if (type == VastgoedType.GROND){
            return "LAND";
        }
        if (type == VastgoedType.HUIS){
            return "HOUSE";
        }
        throw new RuntimeException("Unknown TYpe");
    }

    @Override
    public List<Vastgoed> getScrapedVastgoed(VastgoedType type, Integer postcode) {
        List<Vastgoed> vastgoeds = new ArrayList<>();
        String webUrl =  "https://immo.notaris.be/immoplatform-public-service_v1/api/properties?location=" + getCity(postcode) + "&propertyType=" + getType(type) + "&superSType=SALE";

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = Jsoup.connect(webUrl).ignoreContentType(true).execute().body();
            Map<String, Map> map = mapper.readValue(json, Map.class);

            List<Map<String, Object>> responses = (List<Map<String, Object>>)map.get("response");

            for(Map<String, Object> response: responses){
                NotarisVastgoed vastgoed = new NotarisVastgoed();

                vastgoed.setProvider("NOTARIS");
                vastgoed.setUniqueName(response.get("id").toString());
                vastgoed.setType(super.getType(type));

                try{
                    Map<String, String> municipality = (Map)response.get("municipality");
                    vastgoed.setLocation(municipality != null ? municipality.get("municipality_Nl") : null);

                    Map<String, String> description = (Map)response.get("desc");
                    vastgoed.setDescription(description != null ? description.get("freeText_Nl") : null);

                    Map<String, String> street = (Map)response.get("street");
                    vastgoed.setAddress(street != null ? street.get("street_Nl") : null);
                    vastgoed.setPrice(response.get("price") != null ? response.get("price").toString() : null);
                    vastgoed.setSize(response.get("searchSurface") != null ? response.get("searchSurface").toString() : null);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
                vastgoeds.add(vastgoed);
            }
        } catch (IOException e) { }

        return vastgoeds;
    }
}
