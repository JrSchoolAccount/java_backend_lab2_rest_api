package org.java.java_backend_lab2_rest_api.repository;

import org.java.java_backend_lab2_rest_api.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
}
