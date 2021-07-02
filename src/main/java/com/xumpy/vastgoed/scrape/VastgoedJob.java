package com.xumpy.vastgoed.scrape;

import com.xumpy.vastgoed.interfaces.VastgoedScraper;
import com.xumpy.vastgoed.interfaces.VastgoedType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class VastgoedJob {
    @Autowired VastgoedHandler vastgoedHandler;
    @Autowired List<VastgoedScraper> vastgoedScrapers;

    private List<Integer> postcodes = Collections.unmodifiableList(Arrays.asList(
            3900,
            3910,
            3920,
            3930,
            3940,
            3950,
            3990));

    private List<CompletableFuture> scrape(VastgoedScraper vastgoedScraper) throws InterruptedException {
        List<CompletableFuture> completableFutures = new ArrayList<>();

        for (Integer postcode: postcodes){
            completableFutures.add(vastgoedHandler.scrape(vastgoedScraper, VastgoedType.GROND, postcode));
            completableFutures.add(vastgoedHandler.scrape(vastgoedScraper, VastgoedType.HUIS, postcode));
        }

        return completableFutures;
    }


    @Scheduled(fixedRate = 60000)
    public void job() throws InterruptedException {
        List<CompletableFuture> completableFutures = new ArrayList<>();
        for(VastgoedScraper vastgoedScraper: vastgoedScrapers){
            completableFutures.addAll(scrape(vastgoedScraper));
        }
        completableFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}
