package com.hotelApp.hotel.controller;

import com.hotelApp.hotel.modal.Hotel;
import com.hotelApp.hotel.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
        this.hotelService.addHotels();
    }

    @GetMapping("/api/search/hotels")
    public ResponseEntity<List<Hotel>> searchHotels(@RequestParam String city) {
        List<Hotel> hotels = this.hotelService.findHotels(city);
        System.out.println(hotels + city);
        return ResponseEntity.ok().body(hotels);
    }
}
