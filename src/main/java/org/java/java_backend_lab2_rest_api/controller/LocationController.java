package org.java.java_backend_lab2_rest_api.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.java.java_backend_lab2_rest_api.dto.*;
import org.java.java_backend_lab2_rest_api.service.GeocodeService;
import org.java.java_backend_lab2_rest_api.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@Slf4j
public class LocationController {

    private final LocationService locationService;
    private final GeocodeService geocodeService;

    public LocationController(LocationService locationService, GeocodeService geocodeService) {
        this.locationService = locationService;
        this.geocodeService = geocodeService;
    }

    @GetMapping
    public List<LocationDto> getAllLocations(@RequestParam(required = false) Integer categoryId) {
        if (categoryId != null) {
            return locationService.allPublicLocationsInCategory(categoryId);
        }
        return locationService.allLocations();
    }

    @GetMapping("/my-locations")
    public List<LocationDto> getMyLocations(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();

        return locationService.getMyLocations(userId);
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
    public ResponseEntity<LocationDto> createLocation(@AuthenticationPrincipal Jwt jwt,
                                                      @RequestBody @Valid LocationCreateDto locationCreateDto) {
        String userId = jwt.getSubject();

        LocationDto newLocation = locationService.addLocation(locationCreateDto, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(newLocation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationDto> updateLocation(@PathVariable Integer id,
                                                      @RequestBody @Valid LocationUpdateDto locationUpdateDto) {

        LocationDto updatedLocation = locationService.updateLocation(id, locationUpdateDto);

        return ResponseEntity.ok(updatedLocation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Integer id) {

        locationService.softDeleteLocation(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/address")
    public ResponseEntity<GeocodeResponseDto> getLocationAddress(@PathVariable Integer id) {
        LocationDto location = locationService.getPublicLocationById(id);

        GeocodeResponseDto address = geocodeService.getAddress(
                location.latitude(),
                location.longitude()
        );


        return ResponseEntity.ok(address);
    }
}