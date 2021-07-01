package com.xumpy.vastgoed;

import java.util.List;

public abstract class VastgoedScraper {
    public abstract void scrape();

    public abstract String getLocation();
    public abstract List<Vastgoed> getScrapedVastgoed();
    public abstract String getWebUrl();

    public String executeWebCall(){
        return null;
    }
}
