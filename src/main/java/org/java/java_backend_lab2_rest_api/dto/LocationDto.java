package org.java.java_backend_lab2_rest_api.dto;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.java.java_backend_lab2_rest_api.entity.LocationEntity;

public record LocationDto(Integer id,
                          String name,
                          Integer categoryId,
                          String userId,
                          String status,
                          String description,
                          Double longitude,
                          Double latitude) {

    public LocationDto(Integer id, String name, Integer categoryId, String userId, String status, String description, Point<G2D> coordinate) {
        this(id,
                name,
                categoryId,
                userId,
                status,
                description,
                coordinate != null ? coordinate.getPosition().getLon() : null,
                coordinate != null ? coordinate.getPosition().getLat() : null);
    }

    public static LocationDto fromLocation(LocationEntity location) {

        return new LocationDto(
                location.getId(),
                location.getName(),
                location.getCategory().getId(),
                location.getUser(),
                location.getStatus().toString(),
                location.getDescription(),
                location.getCoordinate()
        );
    }
}
