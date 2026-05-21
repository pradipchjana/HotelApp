package com.hotelApp.booking;

import com.hotelApp.booking.exception.HotelAppException;
import com.hotelApp.booking.exception.RoomsNotAvailableException;
import com.hotelApp.booking.request.BookingRequest;
import com.hotelApp.hotel.modal.Hotel;
import com.hotelApp.hotel.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {
    private final HotelRepository hotelRepository;
    private final BookingRepository bookingRepository;

    public BookingService(HotelRepository hotelRepository, BookingRepository bookingRepository) {
        this.hotelRepository = hotelRepository;
        this.bookingRepository = bookingRepository;
    }

    public BookingModal createBooking(String username, BookingRequest details) throws HotelAppException {
        String hotelId = details.hotel_id();
        int rooms = details.rooms();
        Optional<Hotel> hotel = this.hotelRepository.findById(hotelId);

        if(hotel.get().availableRooms < rooms){
            throw new RoomsNotAvailableException("Rooms are not available");
        }

        return this.bookingRepository.insert(new BookingModal(null, username, hotelId, rooms));
    }
}
