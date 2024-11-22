package org.example.botHandlers;

import org.example.TaxiBot;
import org.example.services.Driver_s_Service;
import org.example.services.Passenger_s_Service;
import org.example.utils.IconButton;
import org.example.utils.TextMaker;
import org.example.utils.ViloyatTuman;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MessageHandler {

    private Vector<Integer> messages = new Vector<Integer>();

    private final Passenger_s_Service passengerSService = new Passenger_s_Service();
    private final Driver_s_Service driverSService = new Driver_s_Service();
    private TaxiBot bot;
    public MessageHandler(){

    }

    public void handler(Message message0) {
        bot = new TaxiBot();
        String text = message0.getText();
        Long chatId = message0.getChatId();
        if (message0.hasContact()) {
            String phoneNumber = message0.getContact().getPhoneNumber();
            passengerSService.savePhone(phoneNumber);
            mainButtons(chatId);

        } else if (message0.hasText() && text.equals("/start")) {
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(TextMaker.ismJonat);
            messages.add(bot.toBot(message));

        } else if(message0.hasText() && text.equals(IconButton.yolovchilar)) {
            yolovchiButton(chatId);


        }else if(message0.hasText() && text.equals(IconButton.elonyarat)){
            elonyaratishButton(chatId);

        }else if(message0.hasText()) {
            passengerSService.saveName(text.trim());
            telefonJonatButton(chatId);

        }else{
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(TextMaker.notogriurinish);
            messages.add(bot.toBot(message));
        }

    }

    private void elonyaratishButton(Long chatId) {
        deleteMessages(chatId.toString() , 0);
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(IconButton.qayerdan.formatted("viloyat"));
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
            button.setCallbackData("vil:"+allViloyat[i]);
            row.add(button);

            if( i % 2 == 1 || i == allViloyat.length-1){
               rows.add(row);
            }
        }
        markup.setKeyboard(rows);
        message.setReplyMarkup(markup);

        bot.toBot(message);
    }

    private void yolovchiButton(Long chatId) {
        deleteMessages(chatId.toString() , 0);
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(TextMaker.yoriqnomaYolovchi);

        KeyboardButton button = new KeyboardButton();
        KeyboardButton button1 = new KeyboardButton();
        button.setText(IconButton.elonyarat);
        button1.setText(IconButton.meningElonlarim);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);

        KeyboardRow row = new KeyboardRow();
        row.add(button);
        row.add(button1);
        replyKeyboardMarkup.setKeyboard(List.of(row));
        message.setReplyMarkup(replyKeyboardMarkup);
        messages.add(bot.toBot(message));
    }

    private void mainButtons(Long chatId) {
        deleteMessages(chatId.toString() , 0);
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
        messages.add(bot.toBot(message));
    }

    public  void deleteMessages(String chatId , int lastId) {
        for (int i = 0; i < messages.size()-lastId; i++) {
            bot.deleteMessage(chatId, messages.get(i));
            messages.remove(i);
        }
    }


    private void telefonJonatButton(Long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(TextMaker.telefonJonat.formatted(passengerSService.getPassenger().getName()));

        KeyboardButton button = new KeyboardButton();
        button.setText(IconButton.telefonRaqamJonat);
        button.setRequestContact(true);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);

        KeyboardRow row = new KeyboardRow();
        row.add(button);
        replyKeyboardMarkup.setKeyboard(List.of(row));
        message.setReplyMarkup(replyKeyboardMarkup);
        messages.add(bot.toBot(message));
    }

    public void addToMessages(int id){
        messages.add(id);
    }
}
