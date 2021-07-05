package com.xumpy.vastgoed.scrape.huiskantoor;

import com.xumpy.vastgoed.interfaces.Vastgoed;
import com.xumpy.vastgoed.interfaces.VastgoedScraper;
import com.xumpy.vastgoed.interfaces.VastgoedType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HuiskantoorScraper extends VastgoedScraper {
    private String baseUrl = "https://huiskantoor.be";

    @Override
    public Boolean sentTelegram() {
        return false;
    }

    @Override
    protected String getType(VastgoedType type){
        if (type == VastgoedType.GROND){
            return "3";
        }
        if (type == VastgoedType.HUIS){
            return "5";
        }
        throw new RuntimeException("Unknown TYpe");
    }

    @Override
    public List<Vastgoed> getScrapedVastgoed(VastgoedType type, Integer postcode) {
        String webUrl =  baseUrl + "/nl/te-koop/?type%5B%5D=" + getType(type) + "&city%5B%5D=" + postcode + "&price-min=&price-max=";
        List<Vastgoed> vastgoedList = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(webUrl).get();
            Elements selectedVastgoed = doc.select(".spotlight");
            for (Element vastgoed : selectedVastgoed) {
                vastgoedList.add(buildVastgoed(type, postcode, vastgoed));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vastgoedList;
    }

    private Vastgoed buildVastgoed(VastgoedType type, Integer postcode, Element webContent){
        HuiskantoorVastgoed vastgoed  = new HuiskantoorVastgoed();

        vastgoed.setUniqueName(webContent.select(".spotlight__image > a").first().attr("href"));
        vastgoed.setProvider("HUISKANTOOR");
        vastgoed.setType(super.getType(type));
        vastgoed.setLocation(super.getCity(postcode));
        vastgoed.setState(webContent.select(".spotlight__image__sticker").text());
        try {
            Document doc = Jsoup.connect(baseUrl + vastgoed.getUniqueName()).get();

            vastgoed.setDescription(doc.select(".property__details__block__description").text());
            vastgoed.setAddress(doc.select(".property__header-block__adress__street").text());
            vastgoed.setPrice(doc.select(".financial").select(".even").select(".value").text());
            vastgoed.setSize(doc.select(".construction").select(".odd").select(".value").text());
        } catch (Exception e) {

        }

        return vastgoed;
    }
}
