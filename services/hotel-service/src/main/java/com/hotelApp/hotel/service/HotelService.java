package com.hotelApp.hotel.service;

import com.hotelApp.hotel.modal.Hotel;
import com.hotelApp.hotel.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    private final IdGenerator idGenerator;
    private final HotelRepository hotelRepository;

    public HotelService(IdGenerator idGenerator, HotelRepository hotelRepository) {
        this.idGenerator = idGenerator;
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> findHotels(String city) {
        return this.hotelRepository.findAllByCity(city);
    }

    public Optional<Hotel> findHotel(String id) {
        return hotelRepository.findById(id);
    }

  public void updateRooms(String id, int rooms) {
    System.out.println("It came here");
    this.hotelRepository.updateRoomsById(id, -rooms);
  }
}
