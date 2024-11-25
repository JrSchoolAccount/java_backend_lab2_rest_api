package org.java.java_backend_lab2_rest_api.repository;

import org.java.java_backend_lab2_rest_api.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {
    List<LocationEntity> findByCategory_Id(int categoryId);
}
