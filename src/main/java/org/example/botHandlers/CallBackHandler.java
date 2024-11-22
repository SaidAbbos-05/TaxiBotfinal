package org.example.botHandlers;

import org.example.TaxiBot;
import org.example.utils.IconButton;
import org.example.utils.TextMaker;
import org.example.utils.ViloyatTuman;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CallBackHandler {

    private TaxiBot bot;

    public CallBackHandler(){}

    public void handler(CallbackQuery callBack){
        this.bot = new TaxiBot();
        Long chatId = callBack.getMessage().getChatId();
        String data = callBack.getData();

        if(data.startsWith("vil:")){
            String viloyat = data.substring(4);
            elonyaratishButtonTuman(chatId , viloyat);
        } else if (data.startsWith("tum:")) {
            String tuman = data.substring(4);
            lokatsiyaJonat(chatId , tuman , false);
        } else if (data.equals(IconButton.skip)) {
            manzilYaratishButton(chatId);
        } else if (data.startsWith("vilm:")) {
            String viloyat = data.substring(5);
            manzilYaratishTumanButton(chatId, viloyat);
        } else if (data.startsWith("tumm:")) {
            String tuman = data.substring(5);
            lokatsiyaJonat(chatId , tuman , true);
        }
    }

    private void manzilYaratishTumanButton(Long chatId, String viloyat) {

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(IconButton.qayerga.formatted("tuman"));
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();
        String[] allTuman = ViloyatTuman.getAllTuman(viloyat);

        for (int i = 0; i < Objects.requireNonNull(allTuman).length; i++) {
            if( i !=0 && i % 2 == 0 ){
                row = new ArrayList<>();
            }

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(allTuman[i]);
            button.setCallbackData("tumm:"+allTuman[i]);
            row.add(button);

            if( i % 2 == 1 || i == allTuman.length-1){
                rows.add(row);
            }
        }
        markup.setKeyboard(rows);
        message.setReplyMarkup(markup);

        bot.toBot(message);
    }

    private void lokatsiyaJonat(Long chatId, String tuman, boolean isDest ) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        if(isDest) {
            message.setText(TextMaker.lokatsiyaBorish.formatted(tuman));
        }else{
            message.setText(TextMaker.lokatsiyaJonash.formatted(tuman));
        }
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(IconButton.skip);
        button.setCallbackData(IconButton.skip);
        row.add(button);
        markup.setKeyboard(List.of(row));
        message.setReplyMarkup(markup);
        bot.toBot(message);
    }


    private void manzilYaratishButton(Long chatId) {
        MessageHandler.deleteMessages(chatId.toString() , 0);
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(IconButton.qayerga.formatted("viloyat"));
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List< List<InlineKeyboardButton> > rows = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();
        String[] allViloyat = ViloyatTuman.getAllViloyat();

        for (int i = 0; i < allViloyat.length; i++) {
            if( i !=0 && i % 2 == 0 ){
                row = new ArrayList<>();
            }

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(allViloyat[i]);
            button.setCallbackData("vilm:"+allViloyat[i]);
            row.add(button);

            if( i % 2 == 1 || i == allViloyat.length-1){
                rows.add(row);
            }
        }
        markup.setKeyboard(rows);
        message.setReplyMarkup(markup);

        bot.toBot(message);
    }


    private void elonyaratishButtonTuman(Long chatId , String viloyat) {

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(IconButton.qayerdan.formatted("tuman"));
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();
        String[] allTuman = ViloyatTuman.getAllTuman(viloyat);

        for (int i = 0; i < Objects.requireNonNull(allTuman).length; i++) {
            if( i !=0 && i % 2 == 0 ){
                row = new ArrayList<>();
            }

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(allTuman[i]);
            button.setCallbackData("tum:"+allTuman[i]);
            row.add(button);

            if( i % 2 == 1 || i == allTuman.length-1){
                rows.add(row);
            }
        }
        markup.setKeyboard(rows);
        message.setReplyMarkup(markup);

        bot.toBot(message);
    }


}
