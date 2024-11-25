package org.java.java_backend_lab2_rest_api.service;

import org.java.java_backend_lab2_rest_api.dto.LocationDto;
import org.java.java_backend_lab2_rest_api.entity.LocationStatus;
import org.java.java_backend_lab2_rest_api.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<LocationDto> allLocations() {
        return locationRepository.findAll().stream()
                .map(LocationDto::fromLocation)
                .toList();
    }

    public List<LocationDto> allLocationsInCategory(int categoryId) {
        return locationRepository.findByCategory_Id(categoryId).stream()
                .filter(location -> location.getStatus() == LocationStatus.PUBLIC)
                .map(LocationDto::fromLocation)
                .toList();
    }
}
