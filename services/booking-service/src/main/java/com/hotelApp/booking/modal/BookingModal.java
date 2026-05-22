package com.hotelApp.booking.modal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookings")
public record BookingModal (@Id String id, String username, String hotelId, int rooms) {
}

