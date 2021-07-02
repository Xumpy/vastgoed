package com.xumpy.vastgoed.interfaces;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class VastgoedScraper {
    public abstract Boolean sentTelegram();

    protected String getCity(Integer postcode){
        Map<Integer, String> aMap = new HashMap<>();
        aMap.put(3900, "pelt");
        aMap.put(3910, "pelt");
        aMap.put(3920, "lommel");
        aMap.put(3930, "hamont-achel");
        aMap.put(3940, "hechtel-eksel");
        aMap.put(3950, "bochelt");
        aMap.put(3990, "peer");

        return aMap.get(postcode);
    }
    protected String getType(VastgoedType type){
        if (type == VastgoedType.GROND){
            return "grond";
        }
        if (type == VastgoedType.HUIS){
            return "huis";
        }
        throw new RuntimeException("Unknown TYpe");
    }

    public abstract List<Vastgoed> getScrapedVastgoed(VastgoedType type, Integer postcode);
}
