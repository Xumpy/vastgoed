package com.xumpy.vastgoed.era;

import com.xumpy.vastgoed.Vastgoed;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class EraVastgoed extends Vastgoed {
    private String uniqueName;
    private String price;
    private String address;
    private String size;
    private String description;

    @Override
    public String getUniqueName() {
        return uniqueName;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getPrice() {
        return price;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getSize() {
        return size;
    }

    private String getTextInFieldItem(Elements field){
        if (field.select(".field-item").hasText()){
            return field.select(".field-item").text();
        } else {
            return field.text();
        }
    }

    @Override
    public Vastgoed build(String baseUrl, Element webContent) {
        try {
            this.description = getTextInFieldItem(webContent.select(".field-name-title-field"));
            this.uniqueName = DescriptionToUrl.transform(baseUrl, this.description);
            this.address = getTextInFieldItem(webContent.select(".field-name-era-adres--c"));

            Document doc = Jsoup.connect(uniqueName).get();

            this.price = getTextInFieldItem(doc.select(".field-name-era-actuele-vraagprijs--c"));
            this.size = getTextInFieldItem(doc.select(".field-name-era-oppervlakte-grond--c"));
        } catch (Exception e) {

        }

        return this;
    }
}
