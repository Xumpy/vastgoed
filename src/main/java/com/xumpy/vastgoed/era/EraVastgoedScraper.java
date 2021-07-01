package com.xumpy.vastgoed.era;

import com.xumpy.vastgoed.Vastgoed;
import com.xumpy.vastgoed.VastgoedScraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EraVastgoedScraper extends VastgoedScraper {
    List<Vastgoed> eraVastgoedList = new ArrayList<>();

    public EraVastgoedScraper(String type, String location) {
        super(type, location);
    }

    public String buildWebUrl() {
        return "https://www.era.be/nl/te-koop/" + this.location + "/" + this.type;
    }

    @Override
    public void scrape() {
        try {
            Document doc = Jsoup.connect(buildWebUrl()).get();
            Elements selectedVastgoed = doc.select(".group-main");
            for (Element vastgoed : selectedVastgoed) {
                Vastgoed eraVastgoed = new EraVastgoed();
                eraVastgoedList.add(eraVastgoed.build(buildWebUrl(), vastgoed));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Vastgoed> getScrapedVastgoed() {
        return eraVastgoedList;
    }
}
