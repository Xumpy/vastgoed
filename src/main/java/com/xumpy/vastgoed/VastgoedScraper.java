package com.xumpy.vastgoed;

import java.util.List;

public abstract class VastgoedScraper {
    protected String location;
    protected String type;

    public VastgoedScraper(String type, String location){
        this.location = location;
        this.type = type;
    }

    public abstract List<Vastgoed> getScrapedVastgoed();
    public abstract void scrape();
}
