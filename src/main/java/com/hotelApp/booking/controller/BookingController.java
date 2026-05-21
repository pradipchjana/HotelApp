package com.hotelApp.booking.controller;

import com.hotelApp.booking.modal.BookingModal;
import com.hotelApp.booking.service.BookingService;
import com.hotelApp.booking.exception.HotelAppException;
import com.hotelApp.booking.request.BookingRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<BookingModal>> listMyBookings() {
        List<BookingModal> bookings = this.bookingService.listMyBookings("Jana");

        return ResponseEntity.ok().body(bookings);
    }
}
