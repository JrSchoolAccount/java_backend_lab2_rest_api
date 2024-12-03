package org.java.java_backend_lab2_rest_api.repository;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.java.java_backend_lab2_rest_api.entity.CategoryEntity;
import org.java.java_backend_lab2_rest_api.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {

    List<LocationEntity> findByCategory_IdAndDeletedAtIsNull(int categoryId);

    @Query("SELECT l FROM LocationEntity l WHERE l.deletedAt IS NULL AND distance(l.coordinate, :center) <= :radius")
    List<LocationEntity> findActiveLocationsWithinRadius(
            @Param("center") Point<G2D> center,
            @Param("radius") double radius
    );

    @Query("SELECT c FROM CategoryEntity c WHERE c.id = :categoryId")
    CategoryEntity findActiveLocationsByCategory(@Param("categoryId") Integer categoryId);

    @Query("SELECT l FROM LocationEntity l WHERE l.deletedAt IS NULL")
    List<LocationEntity> findAllActiveLocations();
}
