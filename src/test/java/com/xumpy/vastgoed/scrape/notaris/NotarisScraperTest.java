package com.xumpy.vastgoed.scrape.notaris;

import com.xumpy.vastgoed.interfaces.Vastgoed;
import com.xumpy.vastgoed.interfaces.VastgoedType;
import org.junit.jupiter.api.Test;

public class NotarisScraperTest {
    @Test
    public void testScraper(){
        NotarisScraper notarisScraper = new NotarisScraper();

        for (Vastgoed vastgoed: notarisScraper.getScrapedVastgoed(VastgoedType.GROND, 3940)){
            System.out.println(vastgoed.toString());
        }
    }
}