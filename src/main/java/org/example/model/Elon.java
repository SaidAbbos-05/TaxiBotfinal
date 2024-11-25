package org.example.model;

import org.example.utils.TextMaker;

import java.io.Serializable;

public class Elon implements Serializable {
    private String ketishVil;
    private String ketishTum;
    private String borishVil;
    private String borishTum;
    private String time;
    private String egasi;
    private String egaPhone;

    public Elon(){}

    public Elon(String ketishVil, String ketishTum, String borishVil, String borishTum, String time, String egasi, String egaPhone) {
        this.ketishVil = ketishVil;
        this.ketishTum = ketishTum;
        this.borishVil = borishVil;
        this.borishTum = borishTum;
        this.time = time;
        this.egasi = egasi;
        this.egaPhone = egaPhone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEgasi() {
        return egasi;
    }

    public void setEgasi(String egasi) {
        this.egasi = egasi;
    }

    public String getEgaPhone() {
        return egaPhone;
    }

    public void setEgaPhone(String egaPhone) {
        this.egaPhone = egaPhone;
    }

    public String getKetishVil() {
        return ketishVil;
    }

    public String getKetishTum() {
        return ketishTum;
    }

    public void setKetishTum(String ketishTum) {
        this.ketishTum = ketishTum;
    }

    public String getBorishVil() {
        return borishVil;
    }

    public void setBorishVil(String borishVil) {
        this.borishVil = borishVil;
    }

    public String getBorishTum() {
        return borishTum;
    }

    public void setBorishTum(String borishTum) {
        this.borishTum = borishTum;
    }

    public void setKetishVil(String ketishVil) {
        this.ketishVil = ketishVil;
    }

    public  String createElon(){
        return TextMaker.elon.formatted(egasi , ketishVil , ketishTum , borishVil, borishTum, time , egaPhone);
    }



}
