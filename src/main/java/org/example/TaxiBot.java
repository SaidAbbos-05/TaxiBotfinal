package org.example;

import org.example.botHandlers.CallBackHandler;
import org.example.botHandlers.MessageHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendContact;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Vector;


public class TaxiBot extends TelegramLongPollingBot {

    private MessageHandler messageHandler = new MessageHandler();
    private CallBackHandler callBackHandler = new CallBackHandler();

    public TaxiBot(){
        super("7628684296:AAEW4cxbZ5EFUqjTASl_cyY5OcCwpUi8PrI");
    }

    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage()){
            messageHandler.addToMessages(update.getMessage().getMessageId());
            messageHandler.handler(update.getMessage());
        }else{
            callBackHandler.handler(update.getCallbackQuery());
        }

    }

    @Override
    public String getBotUsername() {
        return "ReserveTaxi_bot";
    }

    //oddiy message jo'natadi
    public int toBot(SendMessage message){
        try {
            return execute(message).getMessageId();
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    //rasm jo'natadi
    public int toBot(SendPhoto message){
        try {
            return execute(message).getMessageId();
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    //kontakt nomer jo'natadi
    public int toBot(SendContact message){
        try {
            return execute(message).getMessageId();
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    //lokatsiya jo'natadi
    public int toBot(SendLocation message){
        try {
            return execute(message).getMessageId();
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteMessage(String chatId, int messageId){
        DeleteMessage message = new DeleteMessage(chatId, messageId);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


}
