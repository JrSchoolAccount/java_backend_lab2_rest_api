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

    List<LocationEntity> findByCategory_Id(int categoryId);

    @Query("SELECT p FROM LocationEntity p WHERE distance(p.coordinate, :center) <= :radius")
    List<LocationEntity> findAllWithinRadius(
            @Param("center") Point<G2D> center,
            @Param("radius") double radius
    );

    @Query("SELECT c FROM CategoryEntity c WHERE c.id = :categoryId")
    CategoryEntity findCategoryById(@Param("categoryId") Integer categoryId);

    @Query("SELECT l FROM LocationEntity l WHERE l.deletedAt IS NULL")
    List<LocationEntity> findAllActive();
}
