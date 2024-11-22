package org.example.model;

import java.util.UUID;

public class Passenger {

    private String id;
    private String name;
    private String from;
    private String to;
    private String tel_number;

    public Passenger(){}

    public Passenger(String name, String from, String to, String tel_number) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.tel_number = tel_number;
        this.id = UUID.randomUUID().toString();
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
    }
}
