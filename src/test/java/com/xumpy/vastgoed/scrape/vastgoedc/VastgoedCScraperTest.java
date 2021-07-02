package com.xumpy.vastgoed.scrape.vastgoedc;

import com.xumpy.vastgoed.interfaces.Vastgoed;
import com.xumpy.vastgoed.interfaces.VastgoedType;
import org.junit.jupiter.api.Test;

public class VastgoedCScraperTest {
    @Test
    public void testScraper(){
        VastgoedCScraper vastgoedCScraper = new VastgoedCScraper();

        for (Vastgoed vastgoed: vastgoedCScraper.getScrapedVastgoed(VastgoedType.GROND, 3940)){
            System.out.println(vastgoed.toString());
        }
    }
}