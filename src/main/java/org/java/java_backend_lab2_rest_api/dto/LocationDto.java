package org.java.java_backend_lab2_rest_api.dto;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.java.java_backend_lab2_rest_api.entity.LocationEntity;

public record LocationDto(String name,
                          Integer categoryId,
                          Integer userId,
                          String status,
                          String description,
                          Double longitude,
                          Double latitude) {

    public static LocationDto fromLocation(LocationEntity location) {
        Point<G2D> coordinate = location.getCoordinate();
        Double longitude = coordinate != null ? coordinate.getPosition().getLon() : null;
        Double latitude = coordinate != null ? coordinate.getPosition().getLat() : null;

        return new LocationDto(
                location.getName(),
                location.getCategory().getId(),
                location.getUser(),
                location.getStatus().toString(),
                location.getDescription(),
                longitude,
                latitude
        );
    }
}
