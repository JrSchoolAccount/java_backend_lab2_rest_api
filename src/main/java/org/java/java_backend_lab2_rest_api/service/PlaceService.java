package org.java.java_backend_lab2_rest_api.service;

import org.java.java_backend_lab2_rest_api.dto.PlaceDto;
import org.java.java_backend_lab2_rest_api.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<PlaceDto> allPlaces() {
        return placeRepository.findAll().stream()
                .map(PlaceDto::fromPlace)
                .toList();
    }
}
