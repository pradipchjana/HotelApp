package com.hotelApp.booking.repository;

import com.hotelApp.booking.modal.BookingModal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends MongoRepository <BookingModal, String> {
    List<BookingModal> findByUsername(String username);
}
