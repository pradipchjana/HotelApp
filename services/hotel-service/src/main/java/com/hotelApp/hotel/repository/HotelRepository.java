package com.hotelApp.hotel.repository;

import com.hotelApp.hotel.modal.Hotel;
import org.jspecify.annotations.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends MongoRepository <Hotel, String> {
    List<Hotel> findAllByCity(String city);
    Optional<Hotel> findById(@NonNull String id);

}
