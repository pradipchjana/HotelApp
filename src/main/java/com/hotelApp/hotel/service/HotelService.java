package com.hotelApp.hotel.service;

import com.hotelApp.hotel.modal.Hotel;
import com.hotelApp.hotel.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    private final IdGenerator idGenerator;
    private final HotelRepository hotelRepository;

    public HotelService(IdGenerator idGenerator, HotelRepository hotelRepository) {
        this.idGenerator = idGenerator;
        this.hotelRepository = hotelRepository;
    }

    public void addHotels() {
        String id = idGenerator.generate();
        Hotel hotel = new Hotel(id, "Grand Plaza", "New York",10);
        this.hotelRepository.insert(hotel);
    }

    public List<Hotel> findHotels(String city) {
        return this.hotelRepository.findAllByCity(city);
    }
}
