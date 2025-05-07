package com.example.case_study.service;

import com.example.case_study.dto.response.PlaceResponse;
import com.example.case_study.mapper.PlaceMapper;
import com.example.case_study.model.PlaceSearch;
import com.example.case_study.repository.PlaceRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${apiKey}")
    private String apiKey;

    public List<PlaceResponse> getNearbyPlaces(double latitude, double longitude, int radius) {
        String requestKey = generateRequestKey(latitude, longitude, radius);

        Optional<PlaceSearch> savedRequest = placeRepository.findByRequestKey(requestKey);

        if (savedRequest.isPresent()) {
            return savedRequest.get().getPlaceResponseList();
        }

        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" +
                "?location=" + latitude + "," + longitude +
                "&radius=" + radius +
                "&key=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        List<PlaceResponse> placeResponse = parsePlacesFromJson(response);

        placeRepository.save(placeMapper.toPlaceSearch(requestKey,placeResponse, response));

        return placeResponse;
    }

    private String generateRequestKey(double latitude, double longitude, int radius) {
        return latitude + "," + longitude + "," + radius;
    }

    private List<PlaceResponse> parsePlacesFromJson(String json) {
        List<PlaceResponse> result = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(json);
            JsonNode results = root.path("results");

            for (JsonNode node : results) {
                String name = node.path("name").asText();
                String address = node.path("vicinity").asText();
                String placeId = node.path("place_id").asText();
                double lat = node.path("geometry").path("location").path("lat").asDouble();
                double lng = node.path("geometry").path("location").path("lng").asDouble();

                PlaceResponse place = new PlaceResponse();
                place.setName(name);
                place.setAddress(address);
                place.setPlaceId(placeId);
                place.setLatitude(lat);
                place.setLongitude(lng);

                result.add(place);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;


    }
}
