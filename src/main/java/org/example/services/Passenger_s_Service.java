package org.example.services;

import org.example.model.Passenger;

public class Passenger_s_Service {
private final Passenger passenger = new Passenger();

    public String saveName(String name) {
        passenger.setName(name);
        return name;
    }


    public void savePhone(String phoneNumber) {
        passenger.setTel_number(phoneNumber);
    }

    public Passenger getPassenger() {
        return this.passenger;
    }
}
