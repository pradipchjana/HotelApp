package com.hotelApp.booking.controller;

import com.hotelApp.booking.exception.HotelAppException;
import com.hotelApp.booking.modal.BookingModal;
import com.hotelApp.booking.request.BookingRequest;
import com.hotelApp.booking.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest details, Authentication authentication) {
        try {
            BookingModal jana = this.bookingService.createBooking(authentication.getName(), details);
            return ResponseEntity.ok(jana);
        } catch (HotelAppException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<BookingModal>> listMyBookings(Authentication authentication) {
        List<BookingModal> bookings = this.bookingService.listMyBookings(authentication.getName());

        return ResponseEntity.ok().body(bookings);
    }
}
