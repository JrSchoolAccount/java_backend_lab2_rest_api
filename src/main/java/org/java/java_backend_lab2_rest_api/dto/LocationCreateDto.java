package org.java.java_backend_lab2_rest_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.builder.DSL;
import org.geolatte.geom.crs.CoordinateReferenceSystems;
import org.java.java_backend_lab2_rest_api.entity.CategoryEntity;
import org.java.java_backend_lab2_rest_api.entity.LocationEntity;
import org.java.java_backend_lab2_rest_api.entity.LocationStatus;

public record LocationCreateDto(String name,
                                @NotNull Integer categoryId,
                                @NotBlank @Pattern(regexp = "PUBLIC|PRIVATE", message = "Status must be either PUBLIC or PRIVATE") String status,
                                @Size(max = 255) String description,
                                @NotNull Double longitude,
                                @NotNull Double latitude) {

    public LocationEntity toLocationEntity(CategoryEntity category, String userId) {
        LocationEntity location = new LocationEntity();
        location.setName(name());
        location.setCategory(category);
        location.setUserId(userId);
        location.setStatus(LocationStatus.valueOf(status().toUpperCase()));
        location.setDescription(description());
        location.setCoordinate(createPoint(longitude(), latitude()));
        return location;
    }

    private Point<G2D> createPoint(Double longitude, Double latitude) {
        if (longitude == null || latitude == null) {
            throw new IllegalArgumentException("Longitude and latitude cannot be null");
        }

        return DSL.point(CoordinateReferenceSystems.WGS84, DSL.g(longitude, latitude));
    }
}
