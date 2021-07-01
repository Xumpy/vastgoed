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

    @Override
    public void scrape() {
        try {
            Document doc = Jsoup.connect("https://www.era.be/nl/te-koop/lommel/grond").get();
            Elements selectedVastgoed = doc.select(".group-main");
            for (Element vastgoed : selectedVastgoed) {
                Vastgoed eraVastgoed = new EraVastgoed();
                eraVastgoed.build(vastgoed);

                System.out.println(eraVastgoed.getAddress());
                System.out.println(eraVastgoed.getSize());

                System.out.println("----------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getLocation() {
        return null;
    }

    @Override
    public List<Vastgoed> getScrapedVastgoed() {
        return eraVastgoedList;
    }

    @Override
    public String getWebUrl() {
        return null;
    }
}
