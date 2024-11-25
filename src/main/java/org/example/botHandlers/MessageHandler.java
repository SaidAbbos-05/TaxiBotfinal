package org.example.botHandlers;

import org.example.TaxiBot;
import org.example.model.Elon;
import org.example.model.Passenger;
import org.example.services.Driver_s_Service;
import org.example.services.Passenger_s_Service;
import org.example.utils.IconButton;
import org.example.utils.State;
import org.example.utils.TextMaker;
import org.example.utils.ViloyatTuman;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessageHandler {


    private final Passenger_s_Service passengerSService = new Passenger_s_Service();
    private final Driver_s_Service driverSService = new Driver_s_Service();
    private TaxiBot bot;
    public MessageHandler(){

    }

    public void handler(Message message0) {
        bot = new TaxiBot();
        String text = message0.getText();
        Long chatId = message0.getChatId();

        if (passengerSService.compState(State.TelefonJonatdi)  || passengerSService.compState(State.BoshMenyuda) && message0.hasContact()) {
            String phoneNumber = message0.getContact().getPhoneNumber();
            passengerSService.savePhone(phoneNumber , chatId.toString());
            passengerSService.savePassenger(chatId);
            mainButtons(chatId);

        } else if ( message0.hasText() && text.equals("/start")) {
            passengerSService.setState(State.Kirdi , chatId);
            String name = check_in(chatId);
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            if(!Objects.nonNull(name)) {
                message.setText(TextMaker.ismJonat);
                passengerSService.setState(State.IsmYozdi, chatId);
            }else {
                message.setText(TextMaker.oldingiMijoz.formatted(name));
                passengerSService.setState(State.TelefonJonatdi, chatId);
                mainButtons(chatId);
            }
            bot.toBot(message);

        } else if(passengerSService.compState(State.BoshMenyuda) &&message0.hasText() && text.equals(IconButton.yolovchilar)) {
            yolovchiButton(chatId);

        } else if (passengerSService.compState(State.BoshMenyuda) &&message0.hasText() && text.equals(IconButton.xaydovchilar)) {
            xaydovchiButton(chatId);

        } else if (passengerSService.compState(State.XaydovchiMenu) &&message0.hasText() && text.equals(IconButton.barchaelonlar)) {
            barchaElonlar(chatId);

        } else if (passengerSService.compState(State.XaydovchiMenu) &&message0.hasText() && text.equals(IconButton.saralash)) {
            manzilTanlash(chatId , IconButton.xaydovchiManzil , 2);

        } else if(passengerSService.compState(State.YolovchiMenyu) &&message0.hasText() && text.equals(IconButton.elonyarat)){
            manzilTanlash(chatId , IconButton.qayerdan , 1);

        } else if (passengerSService.compState(State.BoshMenyuda) && message0.hasText() && text.equals(IconButton.BoshQaytish)) {
            mainButtons(chatId);

        } else if (text.equals(IconButton.meningElonlarim)) {
            showAllElon(chatId);

        } else if (text.contains(":")) {
            passengerSService.saveTime(text.trim(),chatId);
            elon(chatId);

        } else if( passengerSService.compState(State.IsmYozdi) ) {

            if(Objects.equals(passengerSService.getPassenger(chatId).getChat_Id(), chatId.toString())) {
                mainButtons(chatId);
                passengerSService.setState(State.BoshMenyuda, chatId);
            }else {
                passengerSService.saveName(text.trim() , chatId.toString());
                passengerSService.setState(State.TelefonJonatdi, chatId);
                telefonJonatButton(chatId);
            }
        }else{
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(TextMaker.notogriurinish);
            bot.toBot(message);

        }
    }

    private void barchaElonlar(Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.enableHtml(true);
        message.setText(TextMaker.kanalHaqida);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Kanalga o'tish");
        button.setUrl("https://t.me/reserveTaxi298");

        row.add(button);
        markup.setKeyboard(List.of(row));
        message.setReplyMarkup(markup);

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException();
        }

    }

    private void xaydovchiButton(Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(TextMaker.xaydovchilaruchun);

        KeyboardButton button = new KeyboardButton();
        KeyboardButton button1 = new KeyboardButton();
        button.setText(IconButton.barchaelonlar);
        button1.setText(IconButton.saralash);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);

        KeyboardRow row = new KeyboardRow();
        row.add(button);
        row.add(button1);

        replyKeyboardMarkup.setKeyboard(List.of(row));
        message.setReplyMarkup(replyKeyboardMarkup);
        passengerSService.setState(State.XaydovchiMenu, chatId);
        bot.toBot(message);

    }


    private void showAllElon(Long chatId) {
        List<Elon> elonlarFromDb = passengerSService.getElonlarFromDb(chatId);
        for (int i = 0; i < elonlarFromDb.size(); i++) {
            if(elonlarFromDb.get(i).getEgaPhone().equals(passengerSService.getPassenger(chatId).getTel_number())) {
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                String elon = passengerSService.createElon(elonlarFromDb.get(i));
                message.setText(elon);
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
                passengerSService.setState(State.ElonOldi, chatId);
                bot.toBot(message);
            }

        }


    }

    private String check_in(Long chatId) {
        Passenger passenger = passengerSService.ifExist(chatId);
        if(Objects.nonNull(passenger)) {
            passenger.setElon(passengerSService.getElonlarFromDb(chatId));
            return passenger.getName();
        }
        return  null;
    }

    private void elon(Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        String elon =passengerSService.createElon();
        passengerSService.saveElon(chatId);
        message.setText(elon);
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
        passengerSService.setState(State.ElonOldi, chatId);
        bot.toBot(message);

    }

    private void manzilTanlash(Long chatId , String str , int num) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(str.formatted("viloyat"));
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List< List<InlineKeyboardButton> > rows = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();
        String[] allViloyat = ViloyatTuman.getAllViloyat();
        String st;
        if(num==1){
            st = "vil:";
        }else{
            st = "vilX:";
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

    public void yolovchiButton(Long chatId) {
        passengerSService.setState(State.YolovchiMenyu ,chatId);
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

    private void mainButtons(Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(TextMaker.yoriqnoma);

        KeyboardButton button = new KeyboardButton();
        KeyboardButton button1 = new KeyboardButton();
        KeyboardButton button2 = new KeyboardButton();
        button.setText(IconButton.yolovchilar);
        button1.setText(IconButton.xaydovchilar);
        button2.setText(IconButton.qaytish);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);

        KeyboardRow row = new KeyboardRow();
        KeyboardRow row1 = new KeyboardRow();
        row.add(button);
        row.add(button1);
        row1.add(button2);
        replyKeyboardMarkup.setKeyboard(List.of(row,row1));
        message.setReplyMarkup(replyKeyboardMarkup);
        passengerSService.setState(State.BoshMenyuda, chatId);
        bot.toBot(message);

    }

    private void telefonJonatButton(Long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(TextMaker.telefonJonat.formatted(passengerSService.getName(chatId)));

        KeyboardButton button = new KeyboardButton();
        button.setText(IconButton.telefonRaqamJonat);
        button.setRequestContact(true);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);

        KeyboardRow row = new KeyboardRow();
        row.add(button);
        replyKeyboardMarkup.setKeyboard(List.of(row));
        message.setReplyMarkup(replyKeyboardMarkup);
        bot.toBot(message);

    }


}
