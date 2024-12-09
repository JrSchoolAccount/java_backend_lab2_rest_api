package org.java.java_backend_lab2_rest_api.repository;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.java.java_backend_lab2_rest_api.entity.CategoryEntity;
import org.java.java_backend_lab2_rest_api.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {

    List<LocationEntity> findLocationByCategory_IdAndDeletedAtIsNull(int categoryId);

    List<LocationEntity> findAllByUserId(String userId);

    Optional<LocationEntity> findByIdAndDeletedAtIsNull(Integer id);

    @Query("SELECT l FROM LocationEntity l WHERE l.deletedAt IS NULL AND distance(l.coordinate, :center) <= :radius")
    List<LocationEntity> findActiveLocationsWithinRadius(
            @Param("center") Point<G2D> center,
            @Param("radius") double radius
    );

    @Query("SELECT c FROM CategoryEntity c WHERE c.id = :categoryId")
    CategoryEntity findActiveLocationsByCategory(@Param("categoryId") Integer categoryId);

    @Query("SELECT l FROM LocationEntity l WHERE l.deletedAt IS NULL")
    List<LocationEntity> findAllActiveLocations();

    @Query("SELECT l FROM LocationEntity l WHERE l.id = :id AND l.status = 'PUBLIC' AND l.deletedAt IS NULL")
    Optional<LocationEntity> findActivePublicLocationById(@Param("id") Integer id);
}
