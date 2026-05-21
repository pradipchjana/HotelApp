package com.hotelApp.hotel.repository;

import com.hotelApp.hotel.modal.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository <Hotel, String> {
    List<Hotel> findAllByCity(String city);

}
