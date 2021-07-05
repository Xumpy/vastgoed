package com.xumpy.vastgoed.scrape.huiskantoor;

import com.xumpy.vastgoed.interfaces.Vastgoed;
import com.xumpy.vastgoed.interfaces.VastgoedType;
import org.junit.jupiter.api.Test;

public class HuiskantoorScraperTest {
    @Test
    public void testScraper(){
        HuiskantoorScraper huiskantoorScraper = new HuiskantoorScraper();

        for(Vastgoed vastgoed: huiskantoorScraper.getScrapedVastgoed(VastgoedType.GROND, 3920)){
            System.out.println(vastgoed.toString());
        }
    }
}