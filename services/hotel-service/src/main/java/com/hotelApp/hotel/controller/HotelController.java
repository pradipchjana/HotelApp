package com.hotelApp.hotel.controller;

import com.hotelApp.hotel.modal.Hotel;
import com.hotelApp.hotel.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HotelController {
  private final HotelService hotelService;

  public HotelController(HotelService hotelService) {
    this.hotelService = hotelService;
  }

  @GetMapping("/api/search/hotels")
  public ResponseEntity<List<Hotel>> searchHotels(@RequestParam String city) {
    List<Hotel> hotels = this.hotelService.findHotels(city);
    return ResponseEntity.ok().body(hotels);
  }

  @GetMapping("/api/hotels/{id}")
  public ResponseEntity<Optional<Hotel>> findHotel(@PathVariable String id) {
    return ResponseEntity.ok().body(hotelService.findHotel(id));
  }

  @PatchMapping("/api/update/{id}")
  public ResponseEntity<Boolean> post(@PathVariable String id, @RequestParam int rooms) {
    try {
      hotelService.updateRooms(id, rooms);
      return ResponseEntity.ok(true);
    } catch (Exception e) {

      return ResponseEntity.internalServerError().body(false);
    }
  }
}
