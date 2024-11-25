package org.example.model;

import java.io.Serializable;

public class Driver implements Serializable {

    private String name;
    private String model;
    private String tel_number;
    private String carNum;
    private String code;

    public Driver (){}

    public Driver(String name, String model, String tel_number, String carNum) {
        this.name = name;
        this.model = model;
        this.tel_number = tel_number;
        this.carNum = carNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
