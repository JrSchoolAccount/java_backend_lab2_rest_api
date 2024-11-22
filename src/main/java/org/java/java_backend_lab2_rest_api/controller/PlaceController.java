package org.java.java_backend_lab2_rest_api.controller;

import lombok.extern.slf4j.Slf4j;
import org.java.java_backend_lab2_rest_api.dto.PlaceDto;
import org.java.java_backend_lab2_rest_api.service.PlaceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/places")
@Slf4j
public class PlaceController {

    PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping
    public List<PlaceDto> getAllPlaces() {
        return placeService.allPlaces();
    }
}
