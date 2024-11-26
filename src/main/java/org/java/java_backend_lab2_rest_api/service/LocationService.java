package org.java.java_backend_lab2_rest_api.service;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometries;
import org.geolatte.geom.Point;
import org.geolatte.geom.crs.CoordinateReferenceSystems;
import org.java.java_backend_lab2_rest_api.dto.LocationDto;
import org.java.java_backend_lab2_rest_api.entity.LocationEntity;
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

    public List<LocationDto> findAllLocationsWithinRadius(double longitude, double latitude, double radius) {
        Point<G2D> center = Geometries.mkPoint(new G2D(longitude, latitude), CoordinateReferenceSystems.WGS84);

        List<LocationEntity> locations = locationRepository.findAllWithinRadius(center, radius);

        return locations.stream()
                .map(LocationDto::fromLocation)
                .toList();
    }

    public LocationDto addLocation(LocationCreateDto locationCreateDto) {
        LocationEntity location = locationCreateDto.toLocationEntity();

        location = locationRepository.save(location);

        return new LocationDto(location.getName(),
                location.getCategory().getId(),
                location.getUser(),
                location.getStatus().toString(),
                location.getDescription(),
                location.getCoordinate());
    }
}
