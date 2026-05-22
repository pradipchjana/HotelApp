package com.hotelApp.booking.service;

import com.hotelApp.client.HotelService;
import com.hotelApp.dto.HotelResponse;
import com.hotelApp.booking.exception.HotelAppException;
import com.hotelApp.booking.exception.RoomsNotAvailableException;
import com.hotelApp.booking.modal.BookingModal;
import com.hotelApp.booking.repository.BookingRepository;
import com.hotelApp.booking.request.BookingRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final HotelService hotelService;

    public BookingService(
            BookingRepository bookingRepository,
            HotelService hotelClient
    ) {
        this.bookingRepository = bookingRepository;
        this.hotelService = hotelClient;
    }

    public BookingModal createBooking(
            String username,
            BookingRequest details
    ) throws HotelAppException {

        String hotelId = details.hotel_id();
        int rooms = details.rooms();

        HotelResponse hotel =
                this.hotelService.getHotelById(hotelId);

        if (hotel == null) {
            throw new HotelAppException("Hotel not found");
        }

        if (hotel.getAvailableRooms() < rooms) {
            throw new RoomsNotAvailableException(
                    "Rooms are not available"
            );
        }

        return this.bookingRepository.insert(
                new BookingModal(
                        null,
                        username,
                        hotelId,
                        rooms
                )
        );
    }

    public List<BookingModal> listMyBookings(
            String username
    ) {
        return this.bookingRepository.findByUsername(username);
    }
}