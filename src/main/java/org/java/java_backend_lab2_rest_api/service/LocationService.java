package org.java.java_backend_lab2_rest_api.service;

import jakarta.persistence.EntityNotFoundException;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometries;
import org.geolatte.geom.Point;
import org.geolatte.geom.crs.CoordinateReferenceSystems;
import org.java.java_backend_lab2_rest_api.dto.LocationCreateDto;
import org.java.java_backend_lab2_rest_api.dto.LocationDto;
import org.java.java_backend_lab2_rest_api.dto.LocationUpdateDto;
import org.java.java_backend_lab2_rest_api.entity.CategoryEntity;
import org.java.java_backend_lab2_rest_api.entity.LocationEntity;
import org.java.java_backend_lab2_rest_api.entity.LocationStatus;
import org.java.java_backend_lab2_rest_api.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
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

    public List<LocationDto> findAllLocationsWithinRadius(double longitude, double latitude, double radius) {
        Point<G2D> center = Geometries.mkPoint(new G2D(longitude, latitude), CoordinateReferenceSystems.WGS84);

        List<LocationEntity> locations = locationRepository.findAllWithinRadius(center, radius);

        return locations.stream()
                .map(LocationDto::fromLocation)
                .toList();
    }

    public LocationDto addLocation(LocationCreateDto locationCreateDto, Integer userId) {
        CategoryEntity categoryEntity = locationRepository.findCategoryById(locationCreateDto.categoryId());

        LocationEntity location = locationCreateDto.toLocationEntity(categoryEntity, userId);

        location = locationRepository.save(location);

        return LocationDto.fromLocation(location);
    }

    public LocationDto updateLocation(Integer id, LocationUpdateDto locationUpdateDto, Integer userId) {
        LocationEntity location = locationRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        location.setName(locationUpdateDto.name());
        location.setStatus(LocationStatus.valueOf(locationUpdateDto.status().toUpperCase()));
        location.setDescription(locationUpdateDto.description());

        location = locationRepository.save(location);

        return LocationDto.fromLocation(location);
    }

    public void deleteLocation(Integer id, Integer userId) throws AccessDeniedException {
        LocationEntity location = locationRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if (!location.getUser().equals(userId)) {
            throw new AccessDeniedException("User not authorized to delete this location");
        }

        location.setDeletedAt(LocalDateTime.now());
        locationRepository.save(location);
    }
}
