package com.hotelApp.hotel.modal;

public class Hotel {
    private final String name;
    private final String city;
    private final int totalRooms;
    private int availableRooms;
    private final String id;

    public Hotel(String id, String name, String city, int totalRooms) {
        this.name = name;
        this.city = city;
        this.totalRooms = totalRooms;
        this.availableRooms = totalRooms;
        this.id = id;
    }

    public boolean isInThisCity(String city) {
        return this.city.equals(city);
    }

}
