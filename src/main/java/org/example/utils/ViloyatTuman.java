package org.example.utils;

import java.util.ArrayList;
import java.util.Objects;

public enum ViloyatTuman {
    Andijon(new String[]{
            "Andijon tumani",
            "Asaka tumani",
            "Baliqchi tumani",
            "Boʻston tumani",
            "Buloqboshi tumani",
            "Izboskan tumani",
            "Jalaquduq tumani",
            "Xoʻjaobod tumani",
            "Qoʻrgʻontepa tumani",
            "Marhamat tumani",
            "Oltinkoʻl tumani",
            "Paxtaobod tumani",
            "Shahrixon tumani",
            "Ulugʻnor tumani",
            "Xonobod shahri",
            "Andijon shahri"
    }),
    Buxoro(new String[]{
            "Olot tumani",
            "Buxoro tumani",
            "Gʻijduvon tumani",
            "Jondor tumani",
            "Kogon tumani",
            "Qorakoʻl tumani",
            "Qorovulbozor tumani",
            "Peshku tumani",
            "Romitan tumani",
            "Shofirkon tumani",
            "Vobkent tumani",
            "Buxoro shahri",
            "Kogon shahri"
    }),
    Fargona(new String[]{
            "Oltiariq tumani",
            "Bagʻdod tumani",
            "Beshariq tumani",
            "Buvayda tumani",
            "Dangʻara tumani",
            "Fargʻona tumani",
            "Furqat tumani",
            "Qoʻshtepa tumani",
            "Rishton tumani",
            "Soʻx tumani",
            "Toshloq tumani",
            "Uchkoʻprik tumani",
            "Yozyovon tumani",
            "Margʻilon shahri",
            "Quvasoy shahri",
            "Qoʻqon shahri",
            "Fargʻona shahri"
    }),
    Jizzax(new String[]{
            "Arnasoy tumani",
            "Baxmal tumani",
            "Doʻstlik tumani",
            "Forish tumani",
            "Gʻallaorol tumani",
            "Sharof Rashidov tumani",
            "Mirzachoʻl tumani",
            "Paxtakor tumani",
            "Yangiobod tumani",
            "Zarbdor tumani",
            "Zafarobod tumani",
            "Zomin tumani",
            "Jizzax shahri"
    }),
    Namangan(new String[]{
            "Chortoq tumani",
            "Chust tumani",
            "Kosonsoy tumani",
            "Mingbuloq tumani",
            "Namangan tumani",
            "Norin tumani",
            "Pop tumani",
            "Toʻraqoʻrgʻon tumani",
            "Uchqoʻrgʻon tumani",
            "Yangiqoʻrgʻon tumani",
            "Namangan shahri"
    }),
    Navoiy(new String[]{
            "Karmana tumani",
            "Konimex tumani",
            "Qiziltepa tumani",
            "Navbahor tumani",
            "Nurota tumani",
            "Tomdi tumani",
            "Uchquduq tumani",
            "Xatirchi tumani",
            "Zarafshon shahri",
            "Navoiy shahri"
    }),
    Qashqadaryo(new String[]{
            "Chiroqchi tumani",
            "Dehqonobod tumani",
            "Gʻuzor tumani",
            "Kasbi tumani",
            "Kitob tumani",
            "Koson tumani",
            "Mirishkor tumani",
            "Muborak tumani",
            "Nishon tumani",
            "Qamashi tumani",
            "Shahrisabz tumani",
            "Yakkabogʻ tumani",
            "Qarshi shahri",
            "Shahrisabz shahri"
    }),
    Qoraqalpogiston(new String[]{
            "Amudaryo tumani",
            "Beruniy tumani",
            "Chimboy tumani",
            "Ellikqalʼa tumani",
            "Kegeyli tumani",
            "Moʻynoq tumani",
            "Nukus tumani",
            "Qoʻngʻirot tumani",
            "Qoraoʻzak tumani",
            "Shumanay tumani",
            "Taxiatosh tumani",
            "Toʻrtkoʻl tumani",
            "Xoʻjayli tumani",
            "Nukus shahri"
    }),
    Samarqand(new String[]{
            "Bulungʻur tumani",
            "Ishtixon tumani",
            "Jomboy tumani",
            "Kattaqoʻrgʻon tumani",
            "Narpay tumani",
            "Oqdaryo tumani",
            "Payariq tumani",
            "Pastdargʻom tumani",
            "Paxtachi tumani",
            "Samarqand tumani",
            "Toyloq tumani",
            "Urgut tumani",
            "Samarqand shahri",
            "Kattaqoʻrgʻon shahri"
    }),
    Sirdaryo(new String[]{
            "Boyovut tumani",
            "Guliston tumani",
            "Mirzaobod tumani",
            "Oqoltin tumani",
            "Sayxunobod tumani",
            "Sardoba tumani",
            "Xovos tumani",
            "Yangiyer shahri",
            "Shirin shahri",
            "Guliston shahri"
    }),
    Surxondaryo(new String[]{
            "Angor tumani",
            "Bandixon tumani",
            "Boysun tumani",
            "Denov tumani",
            "Jarqoʻrgʻon tumani",
            "Muzrabot tumani",
            "Oltinsoy tumani",
            "Qiziriq tumani",
            "Qumqoʻrgʻon tumani",
            "Sariosiyo tumani",
            "Sherobod tumani",
            "Shoʻrchi tumani",
            "Termiz tumani",
            "Termiz shahri"
    }),
    Toshkent(new String[]{
            "Bekobod tumani",
            "Boʻstonliq tumani",
            "Chinoz tumani",
            "Ohangaron tumani",
            "Oʻrtachirchiq tumani",
            "Parkent tumani",
            "Piskent tumani",
            "Quyi Chirchiq tumani",
            "Toshkent tumani",
            "Yangiyoʻl tumani",
            "Bekobod shahri",
            "Angren shahri",
            "Chirchiq shahri",
            "Olmaliq shahri",
            "Ohangaron shahri",
            "Toshkent shahri",
            "Yangiyoʻl shahri"
    }),
    Xorazm(new String[]{
            "Bogʻot tumani",
            "Gurlan tumani",
            "Qoʻshkoʻpir tumani",
            "Shovot tumani",
            "Urganch tumani",
            "Xiva tumani",
            "Xonqa tumani",
            "Yangiariq tumani",
            "Yangibozor tumani",
            "Urganch shahri",
            "Xiva shahri"
    });

    private final String[] tumanlar;

    ViloyatTuman(String[] tumanlar) {
        this.tumanlar = tumanlar;
    }

    public String[] getTumanlar(){
        return tumanlar;
    }

    public static String[] getAllViloyat(){
        ViloyatTuman[] vil = values();
        String[] res = new String[vil.length];
        for (int i = 0; i < vil.length; i++) {
            res[i] = vil[i].name();
        }
        return res;
    }

    public static String[] getAllTuman(String viloyatnomi){
            for (ViloyatTuman viloyat : values()){
                if(Objects.equals(viloyat.name(), viloyatnomi)){
                    return viloyat.getTumanlar();
                }
            }
            return  null;
    }
}
