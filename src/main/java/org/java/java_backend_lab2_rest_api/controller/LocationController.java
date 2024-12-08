package org.java.java_backend_lab2_rest_api.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.java.java_backend_lab2_rest_api.dto.LocationCreateDto;
import org.java.java_backend_lab2_rest_api.dto.LocationDto;
import org.java.java_backend_lab2_rest_api.dto.LocationUpdateDto;
import org.java.java_backend_lab2_rest_api.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
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
            return locationService.allPublicLocationsInCategory(categoryId);
        }
        return locationService.allLocations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> getPublicLocationById(@PathVariable Integer id) {
        LocationDto locationDto = locationService.getPublicLocationById(id);
        return ResponseEntity.ok(locationDto);
    }

    @GetMapping("/within-radius")
    public List<LocationDto> getPlacesWithinRadius(
            @RequestParam double longitude,
            @RequestParam double latitude,
            @RequestParam double radius) {

        return locationService.findAllLocationsWithinRadius(latitude, longitude, radius);
    }

    @PostMapping
    public ResponseEntity<LocationDto> createLocation(@RequestBody LocationCreateDto locationCreateDto) {

        String userId = "jr";

        LocationDto newLocation = locationService.addLocation(locationCreateDto, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(newLocation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationDto> updateLocation(@PathVariable Integer id,
                                                      @Valid @RequestBody LocationUpdateDto locationUpdateDto) {
        int userId = 2;

        LocationDto updatedLocation = locationService.updateLocation(id, locationUpdateDto, userId);

        return ResponseEntity.ok(updatedLocation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Integer id) throws AccessDeniedException {
        String userId = "jr";

        locationService.deleteLocation(id, userId);

        return ResponseEntity.noContent().build();
    }
}