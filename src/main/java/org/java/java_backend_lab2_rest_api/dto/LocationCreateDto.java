package org.java.java_backend_lab2_rest_api.dto;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.builder.DSL;
import org.geolatte.geom.crs.CoordinateReferenceSystems;
import org.java.java_backend_lab2_rest_api.entity.CategoryEntity;
import org.java.java_backend_lab2_rest_api.entity.LocationEntity;
import org.java.java_backend_lab2_rest_api.entity.LocationStatus;

public record LocationCreateDto(String name,
                                Integer categoryId,
                                Integer userId,
                                String status,
                                String description,
                                Double longitude,
                                Double latitude) {

    // Converts this DTO to a LocationEntity
    public LocationEntity toLocationEntity(CategoryEntity category, Integer userId) {
        LocationEntity location = new LocationEntity();
        location.setName(name());
        location.setCategory(category);
        location.setUser(userId);
        location.setStatus(LocationStatus.valueOf(status().toUpperCase()));
        location.setDescription(description());
        location.setCoordinate(createPoint(longitude(), latitude()));
        return location;
    }

    // Helper method to create a Point<G2D> object
    private Point<G2D> createPoint(Double longitude, Double latitude) {
        if (longitude == null || latitude == null) {
            throw new IllegalArgumentException("Longitude and latitude cannot be null");
        }

        return DSL.point(CoordinateReferenceSystems.WGS84, DSL.g(longitude, latitude));
    }
}
