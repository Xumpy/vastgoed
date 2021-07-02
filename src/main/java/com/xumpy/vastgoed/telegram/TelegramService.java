package com.xumpy.vastgoed.telegram;

import com.xumpy.vastgoed.db.TelegramDBPojo;
import com.xumpy.vastgoed.db.TelegramRepo;
import com.xumpy.vastgoed.interfaces.Vastgoed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class TelegramService extends TelegramLongPollingBot {
    @Value("${telegram.bot.token}") private String telegramBotToken;

    @Autowired TelegramRepo telegramRepo;

    @Override
    public String getBotUsername() {
        return "VastgoedScraper";
    }

    @Override
    public String getBotToken() {
        return telegramBotToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        for (TelegramDBPojo telegramDBPojo: telegramRepo.findAll()){
            if (telegramDBPojo.getChatId().equals(update.getMessage().getChatId())) return;
        }
        TelegramDBPojo telegramDBPojo = new TelegramDBPojo();
        telegramDBPojo.setChatId(update.getMessage().getChatId());
        telegramRepo.save(telegramDBPojo);
    }

    private void sendMessage(String message_text){
        for (TelegramDBPojo telegramDBPojo: telegramRepo.findAll()){
            SendMessage message = new SendMessage(String.valueOf(telegramDBPojo.getChatId()), message_text);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                telegramRepo.delete(telegramDBPojo);
            }
        }
    }

    public void sendNewVastgoed(Vastgoed vastgoed){
        String message_text = "--------- nieuw vastgoed gevonden -------\n";
        message_text  = message_text + vastgoed.toString();
        sendMessage(message_text);
    }

    public void sendUpdateVastgoed(Vastgoed vastgoedNew, Vastgoed vastgoedOld){
        String message_text = "--------- vastgoed veranderd -------\n";
        message_text = message_text + "--------- oude waarden -------------\n";
        message_text = message_text + vastgoedOld.toString() + "\n";
        message_text = message_text + "--------- nieuwe waarden -----------\n";
        message_text = message_text + vastgoedNew.toString();

        sendMessage(message_text);
    }
}
