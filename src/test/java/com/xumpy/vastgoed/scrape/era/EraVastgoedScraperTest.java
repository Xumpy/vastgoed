package com.xumpy.vastgoed.scrape.era;

import com.xumpy.vastgoed.interfaces.Vastgoed;
import com.xumpy.vastgoed.interfaces.VastgoedType;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;

@Ignore
public class EraVastgoedScraperTest {

    @Test
    public void testScraper(){
        EraVastgoedScraper eraVastgoedScraper = new EraVastgoedScraper();

        for(Vastgoed vastgoed: eraVastgoedScraper.getScrapedVastgoed(VastgoedType.GROND, 3920)){
            System.out.println(vastgoed.toString());
        }
    }
}