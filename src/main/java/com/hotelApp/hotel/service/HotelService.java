package com.hotelApp.hotel.service;

import com.hotelApp.hotel.modal.Hotel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HotelService {
    private final IdGenerator idGenerator;
    private final Map<String, Hotel> hotels;

    public HotelService(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
        this.hotels = new HashMap<String, Hotel>();
    }

    public void addHotels() {
        String id = idGenerator.generate();
        Hotel hotel = new Hotel(id, "Grand Plaza", "New York",10);
        this.hotels.put(id, hotel);
    }

    public List<Hotel> findHotels(String city) {
        List<Hotel> searchResult = this.hotels.values().stream().filter(hotel -> hotel.isInThisCity(city)).toList();

        return searchResult;
    }
}
