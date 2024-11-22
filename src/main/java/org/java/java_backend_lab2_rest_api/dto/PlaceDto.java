package org.java.java_backend_lab2_rest_api.dto;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.java.java_backend_lab2_rest_api.entity.PlaceEntity;

public record PlaceDto(String name,
                       Integer categoryId,
                       Integer userId,
                       String status,
                       String description,
                       Double longitude,
                       Double latitude) {

    public static PlaceDto fromPlace(PlaceEntity place) {
        Point<G2D> coordinate = place.getCoordinate();
        Double longitude = coordinate != null ? coordinate.getPosition().getLon() : null;
        Double latitude = coordinate != null ? coordinate.getPosition().getLat() : null;

        return new PlaceDto(
                place.getName(),
                place.getCategory().getId(),
                place.getUser(),
                place.getStatus().toString(),
                place.getDescription(),
                longitude,
                latitude
        );
    }
}
