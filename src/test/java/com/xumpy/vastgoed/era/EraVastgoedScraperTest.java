package com.xumpy.vastgoed.era;

import org.junit.jupiter.api.Test;

public class EraVastgoedScraperTest {

    @Test
    public void testScraper(){
        EraVastgoedScraper eraVastgoedScraper = new EraVastgoedScraper();

        eraVastgoedScraper.scrape();
    }
}