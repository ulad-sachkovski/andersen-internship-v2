package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<City> addCity(@RequestBody Map<String, String> body) {
        return new ResponseEntity<>(
                cityService.addCity(City.builder()
                        .name(body.get("name"))
                        .build())
                , HttpStatus.OK);
    }

}
