package com.xumpy.vastgoed.scrape.vastgoedc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xumpy.vastgoed.interfaces.Vastgoed;
import com.xumpy.vastgoed.interfaces.VastgoedScraper;
import com.xumpy.vastgoed.interfaces.VastgoedType;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class VastgoedCScraper extends VastgoedScraper {
    @Override
    public Boolean sentTelegram() {
        return true;
    }

    @Override
    protected String getType(VastgoedType type){
        if (type == VastgoedType.GROND){
            return "3";
        }
        if (type == VastgoedType.HUIS){
            return "1";
        }
        throw new RuntimeException("Unknown TYpe");
    }

    @Override
    public List<Vastgoed> getScrapedVastgoed(VastgoedType type, Integer postcode) {
        List<Vastgoed> vastgoeds = new ArrayList<>();
        String webUrl =  "https://vastgoedc.be/api/v1/estates?cities=" + postcode + "&types=" + getType(type);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = Jsoup.connect(webUrl).ignoreContentType(true).execute().body();
            Map<String, Map> map = mapper.readValue(json, Map.class);

            Map<String, List> data = map.get("data");
            List<Map<String, Object>> estates = data.get("estates");

            for(Map<String, Object> estate: estates){
                VastgoedCVastgoed vastgoed = new VastgoedCVastgoed();

                vastgoed.setProvider("VASTGOED-C");
                vastgoed.setUniqueName(estate.get("EstateID").toString());
                vastgoed.setType(super.getType(type));
                vastgoed.setLocation(super.getCity(postcode));

                vastgoed.setDescription((String)estate.get("Name"));
                vastgoed.setAddress((String)estate.get("Address1"));
                vastgoed.setPrice((String)estate.get("Price"));
                vastgoed.setSize(estate.get("GroundArea") != null ? estate.get("GroundArea").toString() : null);

                List purpuses = ((List)estate.get("Purposes"));
                if (purpuses != null){
                    Map<String, String> purpuse = (Map<String, String>)purpuses.get(0);

                    vastgoed.setState((String)purpuse.get("PurposeStatus"));
                }
                if (vastgoed.getState().equals("te koop")){
                    vastgoeds.add(vastgoed);
                }
            }

        } catch (IOException e) { }

        return vastgoeds;
    }
}
