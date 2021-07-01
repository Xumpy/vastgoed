package com.xumpy.vastgoed.era;

import com.xumpy.vastgoed.Vastgoed;
import org.jsoup.nodes.Element;

import javax.lang.model.util.Elements;

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

    @Override
    public Vastgoed build(Element webContent) {
        Element titleField = webContent.select(".field-name-title-field").select(".field-item").first();
        Element addressField = webContent.select(".field-name-era-adres--c").select(".field-item").first();

        this.description = titleField.text();
        this.address = addressField.text();

        System.out.println(DescriptionToUrl.transform("https://www.era.be/nl/te-koop/lommel/grond", this.description));

        return this;
    }
}
