package com.xumpy.vastgoed.scrape;

import com.xumpy.vastgoed.db.VastgoedDBPojo;
import com.xumpy.vastgoed.db.VastgoedRepo;
import com.xumpy.vastgoed.interfaces.Vastgoed;
import com.xumpy.vastgoed.interfaces.VastgoedScraper;
import com.xumpy.vastgoed.interfaces.VastgoedType;
import com.xumpy.vastgoed.scrape.vastgoedc.VastgoedCScraper;
import com.xumpy.vastgoed.telegram.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class VastgoedHandler {
    @Autowired VastgoedRepo vastgoedRepo;
    @Autowired TelegramService telegramService;

    private void saveVastgoed(VastgoedScraper vastgoedScraper, Vastgoed vastgoed){
        vastgoedRepo.save(new VastgoedDBPojo(vastgoed));
        if (vastgoedScraper.sentTelegram()) telegramService.sendNewVastgoed(vastgoed);
    }

    private void updateVastgoed(VastgoedScraper vastgoedScraper, Vastgoed vastgoedNew, VastgoedDBPojo vastgoedOld){
        if (!vastgoedNew.equals(vastgoedOld)){
            vastgoedRepo.save(new VastgoedDBPojo(vastgoedNew));
            if (vastgoedScraper.sentTelegram()) telegramService.sendUpdateVastgoed(vastgoedNew, vastgoedOld);
        }
    }

    @Async
    public CompletableFuture<List> scrape(VastgoedScraper vastgoedScraper, VastgoedType vastgoedType, Integer postcode) throws InterruptedException {
        List<Vastgoed> vastgoedScraped = vastgoedScraper.getScrapedVastgoed(vastgoedType, postcode);

        for(Vastgoed vastgoed: vastgoedScraped){
            List<VastgoedDBPojo> vastgoedDBPojos = vastgoedRepo.selectVastgoedUniqueName(vastgoed.getUniqueName());
            if (vastgoedDBPojos.isEmpty()){
                saveVastgoed(vastgoedScraper, vastgoed);
            } else {
                updateVastgoed(vastgoedScraper, vastgoed, vastgoedDBPojos.get(0));
            }
        }
        return CompletableFuture.completedFuture(vastgoedScraped);
    }
}
