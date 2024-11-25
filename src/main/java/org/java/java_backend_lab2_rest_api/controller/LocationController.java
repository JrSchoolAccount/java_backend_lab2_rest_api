package org.java.java_backend_lab2_rest_api.controller;

import lombok.extern.slf4j.Slf4j;
import org.java.java_backend_lab2_rest_api.dto.LocationDto;
import org.java.java_backend_lab2_rest_api.service.LocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@Slf4j
public class LocationController {

    LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<LocationDto> getAllLocations(@RequestParam(required = false) Integer categoryId) {
        if (categoryId != null) {
            return locationService.allLocationsInCategory(categoryId);
        }
        return locationService.allLocations();
    }

    @GetMapping("/within-radius")
    public List<LocationDto> getPlacesWithinRadius(
            @RequestParam double longitude,
            @RequestParam double latitude,
            @RequestParam double radius) {

        return locationService.findAllLocationsWithinRadius(latitude, longitude, radius);
    }
}
