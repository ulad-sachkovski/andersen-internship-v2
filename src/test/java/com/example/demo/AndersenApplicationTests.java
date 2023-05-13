package com.example.demo;

import com.example.demo.model.City;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver",
                "spring.datasource.url=jdbc:tc:postgresql:15.2-alpine:///testing"
        }
)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AndersenApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(25)
    void createFirstCity() {
        var response = restTemplate.postForEntity(
                "/cities",
                Map.of(
                        "name", "Minsk"
                ),
                City.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isEqualTo(1);
    }

    @Test
    @Order(50)
    void createSecondCity() {
        var response = restTemplate.postForEntity(
                "/cities",
                Map.of(
                        "name", "Mogilev"
                ),
                City.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isEqualTo(2);
    }



    @Test
    @Order(75)
    void listCities() {
        var response = restTemplate.exchange(
                RequestEntity.get("/cities").build(),
                new ParameterizedTypeReference<Collection<City>>() {
                }
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).first()
                .isEqualTo(
                        new City(1L, "Minsk")
                );
        assertThat(response.getBody()).hasSize(2);
    }

}
