package org.example;

import org.example.botHandlers.CallBackHandler;
import org.example.botHandlers.MessageHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendContact;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramBot;


import java.util.HashSet;
import java.util.List;
import java.util.Vector;


public class TaxiBot extends TelegramLongPollingBot {

    private MessageHandler messageHandler = new MessageHandler();
    private CallBackHandler callBackHandler = new CallBackHandler();
    private static Vector<Integer> messages = new Vector<>();

    public TaxiBot(){
        super("7628684296:AAEW4cxbZ5EFUqjTASl_cyY5OcCwpUi8PrI");
    }

    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage()){
            messageHandler.handler(update.getMessage());
            deleting(update.getMessage().getMessageId());
            //deleteMessages(update.getMessage().getChatId());

        }else if(update.hasCallbackQuery()){
            callBackHandler.handler(update.getCallbackQuery());
            deleting(update.getCallbackQuery().getMessage().getMessageId());
            //deleteMessages(update.getMessage().getChatId());
        }
    }

    public void deleting( int id){
        messages.add(id);
    }
/*

    public void deleteMessages(Long chatId) {
        if (messages.size()>1) {
            for (int i = messages.size()-1; i >0; i--) {
                int in = messages.get(i);
                deleteMessage(chatId.toString(), in);
            }
           messages.clear();
        }
    }
*/

    @Override
    public String getBotUsername() {
        return "ReserveTaxi_bot";
    }

    //oddiy message jo'natadi
    public void toBot(SendMessage message){
        try {
             deleting(execute(message).getMessageId());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    //rasm jo'natadi
    public int toBot(SendPhoto message){
        try {
            int id = execute(message).getMessageId();
            messages.add(id);
            return id;
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
