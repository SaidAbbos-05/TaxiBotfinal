package org.example.services;

import org.example.model.Elon;
import org.example.model.Passenger;
import org.example.utils.State;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Passenger_s_Service {
   private static Passenger passenger = new Passenger();
   private static Elon elon = new Elon();


    public Passenger_s_Service(){

    }

    public String saveName(String name1 , String chatId) {
        passenger.setName(name1);
        elon.setEgasi(name1);
        passenger.setChat_Id(chatId);
        return name1;
    }

    public void saveVilFrom(String viloyat, Long chatId) {
        elon.setKetishVil(viloyat);
    }

    public void saveVilTo(String viloyat, Long chatId) {
        elon.setBorishVil(viloyat);
    }

    public void saveTumanTo(String tuman , Long chatId) {
        elon.setBorishTum(tuman);
    }
    public void saveTumanFrom(String tuman ,Long chatId) {
        elon.setKetishTum(tuman);
    }

    public  Elon getElon(Long chatId){
        return elon;
    }

    public void setState(State state1, Long chatId){
        passenger.setState( state1.toString() );
    }

    public boolean compState(State state1){
        return state1.toString().equals(passenger.getState());
    }

    public void savePhone(String phoneNumber , String chatId) {
        passenger.setTel_number(phoneNumber);
        elon.setEgaPhone(phoneNumber);
    }

    public Passenger getPassenger(Long chatId) {
        return passenger;
    }

    public  String createElon() {
        passenger.setElon(elon);
        return elon.createElon();
    }
    public  String createElon(Elon elon) {
        passenger.setElon(elon);
        return elon.createElon();
    }

    public void saveTime(String time , Long chatId) {
        elon.setTime(time);
    }

    public void saveElon( Long chatId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String root = "C:\\Pdp package\\TAXIBOT\\src\\main\\java\\org\\example\\DB\\elonlar";
                File file = Paths.get(root, chatId.toString()+".txt").toFile();
                if(!file.exists()){
                    try {
                        if(file.createNewFile()){
                            try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))){
                                outputStream.writeObject(passenger.getElonlar());
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))){
                        outputStream.writeObject(passenger.getElonlar());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

    }

    public List<Elon> getElonlarFromDb(Long chatId){
        String root = "C:\\Pdp package\\TAXIBOT\\src\\main\\java\\org\\example\\DB\\elonlar";
        File file = Paths.get(root, chatId.toString()+".txt").toFile();
        if(!file.exists()){
            return null;
        }else {
            try (Stream<Path> elons = Files.list(Paths.get(root))) {
                if (elons != null) {
                    List<Elon > elonlar = new ArrayList<>();
                    for (Path passenger1 : elons.toList()) {
                        if (Objects.equals(passenger1.toFile().getName(), chatId+".txt")) {
                            try(ObjectInputStream inputStream = new ObjectInputStream( new FileInputStream(passenger1.toFile()))) {
                                List<Elon> all = (List<Elon>)inputStream.readObject();
                                elonlar.addAll(all);
                            }
                        }
                    }
                    return elonlar;
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
       return null;
    }

    public void savePassenger(Long chatId){
        String root = "C:\\Pdp package\\TAXIBOT\\src\\main\\java\\org\\example\\DB\\passengers";
        File file = Paths.get(root, chatId.toString()+".txt").toFile();
        if(!file.exists()){
            try {
                if(file.createNewFile()){
                    try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))){
                        outputStream.writeObject(passenger);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public Passenger ifExist(Long chatId){
        String root = "C:\\Pdp package\\TAXIBOT\\src\\main\\java\\org\\example\\DB\\passengers";
        File file = Paths.get(root, chatId + ".txt").toFile();

        if (!file.exists()) {
            return null;
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            Passenger pas = (Passenger)inputStream.readObject();
            elon.setEgaPhone(pas.getTel_number());
            elon.setEgasi(pas.getName());
            passenger.setTel_number(pas.getTel_number());
            passenger.setName(pas.getName());
            return pas;
        } catch (IOException | ClassNotFoundException e) {

            throw new RuntimeException("Faylni o'qishda xatolik yuz berdi: " + e.getMessage(), e);
        }
    }


    public String getName(Long chatId) {
        return passenger.getName();
    }
}
