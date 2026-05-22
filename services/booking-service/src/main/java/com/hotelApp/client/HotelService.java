package com.hotelApp.client;

import com.hotelApp.dto.HotelResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class HotelService {

    private final WebClient webClient;

    public HotelService(WebClient.Builder builder) {

        this.webClient = builder
                .baseUrl("http://localhost:8001/api/hotels/")
                .build();
    }

    public HotelResponse getHotelById(String hotelId) {

        return webClient
                .get()
                .uri(hotelId)
                .retrieve()
                .bodyToMono(HotelResponse.class)
                .block();
    }
}