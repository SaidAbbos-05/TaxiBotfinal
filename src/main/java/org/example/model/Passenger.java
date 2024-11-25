package org.example.model;

import org.example.utils.State;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Passenger implements Serializable {

    private String state;
    private String id;
    private String name;
    private List<Elon> elonlar = new ArrayList<>();
    private String tel_number;
    private String chat_Id;

    public Passenger(){
        this.state = String.valueOf(State.Kirdi);
    }

    public String getState() {
        return state;
    }

    public void setState(String state1) {
        state = state1;
    }

    public Passenger(String name, String tel_number , String chat_Id ) {
        this.name = name;
        this.tel_number = tel_number;
        this.chat_Id= chat_Id;
        this.id = UUID.randomUUID().toString();
    }

    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
    }

    public String getChat_Id() {
        return chat_Id;
    }

    public void setChat_Id(String chat_Id) {
        this.chat_Id = chat_Id;
    }

    public void addElon(Elon elon){
        elonlar.add(elon);
    }

    public List<Elon> getElonlar(){
        return elonlar;
    }

    public String getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setElon(Elon elon) {
        elonlar.add(elon);
    }
    public void setElon(List<Elon> elon) {
        elonlar = elon;
    }
}
