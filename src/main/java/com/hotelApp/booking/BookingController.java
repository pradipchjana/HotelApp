package com.hotelApp.booking;

import com.hotelApp.booking.exception.HotelAppException;
import com.hotelApp.booking.request.BookingRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest details) {
        try {
            BookingModal jana = this.bookingService.createBooking("Jana", details);
            return ResponseEntity.ok(jana);
        } catch (HotelAppException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
