package com.xumpy.vastgoed.scrape.era;

import com.xumpy.vastgoed.interfaces.Vastgoed;
import com.xumpy.vastgoed.interfaces.VastgoedScraper;
import com.xumpy.vastgoed.interfaces.VastgoedType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class EraVastgoedScraper extends VastgoedScraper {
    @Override
    public Boolean sentTelegram() {
        return true;
    }

    @Override
    public List<Vastgoed> getScrapedVastgoed(VastgoedType type, Integer postcode) {
        String webUrl =  "https://www.era.be/nl/te-koop/" + getCity(postcode) + "/" + getType(type);
        List<Vastgoed> eraVastgoedList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(webUrl).get();
            Elements selectedVastgoed = doc.select(".group-main");
            for (Element vastgoed : selectedVastgoed) {
                eraVastgoedList.add(buildVastgoed(type, postcode, webUrl, vastgoed));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return eraVastgoedList;
    }

    private String transformDescription(String description){
        return description.replace(".", "")
                .replace(",", "")
                .replace("(", "")
                .replace(")", "")
                .replace("!", "")
                .replace(" in ", "-")
                .replace(" ", "-")
                .replace("²", "2")
                .toLowerCase(Locale.ROOT);
    }

    private String getTextInFieldItem(Elements field){
        if (field.select(".field-item").hasText()){
            return field.select(".field-item").text();
        } else {
            return field.text();
        }
    }

    private Vastgoed buildVastgoed(VastgoedType type, Integer postcode, String baseUrl, Element webContent) {
        EraVastgoed eraVastgoed = new EraVastgoed();

        eraVastgoed.setProvider("ERA");
        eraVastgoed.setType(super.getType(type));
        eraVastgoed.setLocation(super.getCity(postcode));
        eraVastgoed.setDescription(getTextInFieldItem(webContent.select(".field-name-title-field")));
        eraVastgoed.setAddress(getTextInFieldItem(webContent.select(".field-name-era-adres--c")));
        eraVastgoed.setUniqueName(eraVastgoed.getAddress());
        try {
            Document doc = Jsoup.connect(baseUrl + "/" + transformDescription(eraVastgoed.getDescription())).get();

            eraVastgoed.setPrice(getTextInFieldItem(doc.select(".field-name-era-actuele-vraagprijs--c")));
            eraVastgoed.setSize(getTextInFieldItem(doc.select(".field-name-era-oppervlakte-grond--c")));
            eraVastgoed.setState(getTextInFieldItem(doc.select(".group-prices").select(".property-state")));
        } catch (Exception e) {

        }

        return eraVastgoed;
    }
}
