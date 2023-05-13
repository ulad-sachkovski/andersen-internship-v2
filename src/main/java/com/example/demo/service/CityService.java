package com.example.demo.service;

import com.example.demo.model.City;
import com.example.demo.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<City> getCities() {
        return cityRepository.findAll();
    }

    public City addCity(City city) {
        return cityRepository.save(city);
    }

}
