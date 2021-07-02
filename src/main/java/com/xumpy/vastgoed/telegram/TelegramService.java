package com.xumpy.vastgoed.telegram;

import com.xumpy.vastgoed.interfaces.Vastgoed;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class TelegramService extends TelegramLongPollingBot {
    @Value("${telegram.bot.token}") private String telegramBotToken;
    private String chat_id;

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
        System.out.println(update.getMessage().getChat().getUserName());
    }

    public void sendNewVastgoed(Vastgoed vastgoed){
        String message_text = "--------- nieuw vastgoed gevonden -------\n";
        message_text  = message_text + vastgoed.toString();
        SendMessage message = new SendMessage(chat_id, message_text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendUpdateVastgoed(Vastgoed vastgoedNew, Vastgoed vastgoedOld){
        String message_text = "--------- vastgoed veranderd -------\n";
        message_text = message_text + "--------- oude waarden -------------\n";
        message_text = message_text + vastgoedOld.toString() + "\n";
        message_text = message_text + "--------- nieuwe waarden -----------\n";
        message_text = message_text + vastgoedNew.toString();

        SendMessage message = new SendMessage(chat_id, message_text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
