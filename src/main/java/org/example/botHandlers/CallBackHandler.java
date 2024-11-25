package org.example.botHandlers;

import org.example.TaxiBot;
import org.example.model.Elon;
import org.example.services.Driver_s_Service;
import org.example.services.Passenger_s_Service;
import org.example.utils.IconButton;
import org.example.utils.State;
import org.example.utils.TextMaker;
import org.example.utils.ViloyatTuman;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CallBackHandler {

    private TaxiBot bot;
    Driver_s_Service driverService = new Driver_s_Service();

    public CallBackHandler(){}
    Passenger_s_Service passengerSService = new Passenger_s_Service();

    public void handler(CallbackQuery callBack){
        this.bot = new TaxiBot();
        Long chatId = callBack.getMessage().getChatId();
        String data = callBack.getData();

        if(data.startsWith("vil:")){
            String viloyat = data.substring(4);
            passengerSService.saveVilFrom(viloyat, chatId);
            elonyaratishButtonTuman(chatId , viloyat);

        } else if (data.startsWith("tum:")) {
            String tuman = data.substring(4);
            passengerSService.saveTumanFrom(tuman, chatId);
            lokatsiyaJonat(chatId , tuman , false);

        } else if (data.equals(IconButton.skip)) {
            manzilYaratishButton(chatId , IconButton.qayerga , 1);

        } else if (data.startsWith("vilm:")) {
            String viloyat = data.substring(5);
            passengerSService.saveVilTo(viloyat,chatId);
            manzilYaratishTumanButton(chatId, viloyat);

        } else if (data.startsWith("vilX:")) {
            String viloyat = data.substring(4);
            manzilYaratishButton(chatId , IconButton.xaydovchiManzil1 , 2);

        } else if (data.startsWith("vilx:")) {
            String viloyat = data.substring(4);
            saeralanganElon(chatId);

        } else if (data.startsWith(IconButton.faollashtirish)) {
            sendChannel();

        } else if (data.startsWith("tumm:")) {
            String tuman = data.substring(5);
            passengerSService.saveTumanTo(tuman ,chatId);
            lokatsiyaJonat(chatId , tuman , true);

        } else if (data.equals(IconButton.tugadi)) {
            vaqtniAyt(chatId);

        } else if (data.startsWith("soat:")) {
            passengerSService.saveTime(data.substring(4).trim(), chatId);
            elon(chatId);
            passengerSService.setState(State.ElonOldi, chatId);

        }else if(data.equals(IconButton.boshqaVaqtniTanla)){
            boshqaVaqt(chatId);

        } else if (data.equals(IconButton.qaytish)) {
            passengerSService.setState(State.YolovchiMenyu, chatId);
            yolovchiButton(chatId);
        }
    }

    private void sendChannel() {
        SendMessage message = new SendMessage();
        message.setChatId("@reserveTaxi298");
        message.enableHtml(true);
        message.setText(passengerSService.createElon());
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button.setText(IconButton.faollashtirish);
        button1.setText(IconButton.qaytish);
        button.setCallbackData(IconButton.faollashtirish);
        button1.setCallbackData(IconButton.qaytish);
        row.add(button);
        row.add(button1);
        markup.setKeyboard(List.of(row));
        message.setReplyMarkup(markup);
        bot.toBot(message);
    }


    private void saeralanganElon(Long chatId) {
        List<Elon> elons = driverService.showsExactElonlar(chatId);
        if(!elons.isEmpty()) {
            for (int i = 0; i < elons.size(); i++) {
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                message.setText(passengerSService.createElon(elons.get(i)));
                InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
                List<InlineKeyboardButton> row = new ArrayList<>();
                InlineKeyboardButton button = new InlineKeyboardButton();
                InlineKeyboardButton button1 = new InlineKeyboardButton();
                button.setText(IconButton.faollashtirish);
                button1.setText(IconButton.qaytish);
                button.setCallbackData(IconButton.faollashtirish);
                button1.setCallbackData(IconButton.qaytish);
                row.add(button);
                row.add(button1);
                markup.setKeyboard(List.of(row));
                message.setReplyMarkup(markup);
                bot.toBot(message);
            }
        }else {
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(TextMaker.bundayElonYoq);
            bot.toBot(message);
        }

    }

    private void yolovchiButton(Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(TextMaker.yoriqnomaYolovchi);

        KeyboardButton button = new KeyboardButton();
        KeyboardButton button1 = new KeyboardButton();
        KeyboardButton button2 = new KeyboardButton();
        button.setText(IconButton.elonyarat);
        button1.setText(IconButton.meningElonlarim);
        button2.setText(IconButton.BoshQaytish);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);

        KeyboardRow row = new KeyboardRow();
        KeyboardRow row1 = new KeyboardRow();
        row.add(button);
        row.add(button1);
        row1.add(button2);
        replyKeyboardMarkup.setKeyboard(List.of(row,row1));
        message.setReplyMarkup(replyKeyboardMarkup);
        bot.toBot(message);

    }

    private void boshqaVaqt(Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(TextMaker.boshqaVaqtnikiriting);
        bot.toBot(message);
    }

    private void vaqtniAyt(Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(IconButton.vaqtniTanla);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List< List<InlineKeyboardButton> > rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        for (int i = 0; i < 25; i++) {
            if( i !=0 && i % 4 == 0 ){
                row = new ArrayList<>();
            }
            if(i==24) {
                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setText(IconButton.boshqaVaqtniTanla);
                button.setCallbackData(IconButton.boshqaVaqtniTanla);
                row.add(button);
                rows.add(row);
            }else{
                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setText("%s : 00".formatted(i + 1));
                button.setCallbackData("soat:" + "%s : 00".formatted(i + 1));
                row.add(button);
            }
            if( i % 4 == 1  ){
                rows.add(row);
            }
        }

        markup.setKeyboard(rows);
        message.setReplyMarkup(markup);

        bot.toBot(message);

    }

    private void elon(Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.enableHtml(true);
        message.setText(passengerSService.createElon());
        passengerSService.saveElon(chatId);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button.setText(IconButton.faollashtirish);
        button1.setText(IconButton.qaytish);
        button.setCallbackData(IconButton.faollashtirish+message.getReplyToMessageId());
        button1.setCallbackData(IconButton.qaytish);
        row.add(button);
        row.add(button1);
        markup.setKeyboard(List.of(row));
        message.setReplyMarkup(markup);
        bot.toBot(message);
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
        InlineKeyboardButton button = new InlineKeyboardButton();
        if(isDest) {
            message.setText(TextMaker.lokatsiyaBorish.formatted(tuman));
            button.setCallbackData(IconButton.tugadi);
        }else{
            message.setText(TextMaker.lokatsiyaJonash.formatted(tuman));
            button.setCallbackData(IconButton.skip);
        }
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> row = new ArrayList<>();
        button.setText(IconButton.skip);
        row.add(button);
        markup.setKeyboard(List.of(row));
        message.setReplyMarkup(markup);
        bot.toBot(message);
    }

    private void manzilYaratishButton(Long chatId , String str , int num) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(str.formatted("viloyat"));
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List< List<InlineKeyboardButton> > rows = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();
        String[] allViloyat = ViloyatTuman.getAllViloyat();
        String st;
        if(num == 1){
            st = "vilm:";
        }else{
            st = "vilx:";
        }
        for (int i = 0; i < allViloyat.length; i++) {
            if( i !=0 && i % 2 == 0 ){
                row = new ArrayList<>();
            }

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(allViloyat[i]);
            button.setCallbackData(st+allViloyat[i]);
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
