package org.java.java_backend_lab2_rest_api.repository;

import org.java.java_backend_lab2_rest_api.entity.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<PlaceEntity, Integer> {
}
