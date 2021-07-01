package com.xumpy.vastgoed.era;

import com.xumpy.vastgoed.Vastgoed;
import org.junit.jupiter.api.Test;

public class EraVastgoedScraperTest {

    @Test
    public void testScraper(){
        EraVastgoedScraper eraVastgoedScraper = new EraVastgoedScraper("huis", "peer");

        eraVastgoedScraper.scrape();

        for(Vastgoed vastgoed: eraVastgoedScraper.eraVastgoedList){
            System.out.println(vastgoed.getUniqueName());
            System.out.println(vastgoed.getDescription());
            System.out.println(vastgoed.getPrice());
            System.out.println(vastgoed.getAddress());
            System.out.println(vastgoed.getSize());
            System.out.println("---------------------------");
        }
    }
}