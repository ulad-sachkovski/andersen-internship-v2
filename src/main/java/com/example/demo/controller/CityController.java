package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    @GetMapping
    public ResponseEntity<List<City>> getCityList() {
        return new ResponseEntity<>(cityService.getCities(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<City> addCity(@RequestBody Map<String, String> body, @Value("${kafka.topic}") String topic) {
        String cityName = body.get("name");
        City city = cityService.addCity(City.builder()
                .name(cityName)
                .build());
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

}
