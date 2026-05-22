package com.hotelApp.hotel.modal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "hotels")
public final class Hotel {
    @Id
    public final String id;
    public final String name;
    public final String city;
    public final int totalRooms;
    public final int availableRooms;

    public Hotel(String id, String name, String city, int totalRooms) {
        this.name = name;
        this.city = city;
        this.totalRooms = totalRooms;
        this.id = id;
        this.availableRooms = this.totalRooms;
    }

}
