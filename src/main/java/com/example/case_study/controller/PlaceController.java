package com.example.case_study.controller;

import com.example.case_study.dto.response.PlaceResponse;
import com.example.case_study.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/place")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/get-nearby-places")
    public ResponseEntity<List<PlaceResponse>> getNearbyPlaces(@RequestParam double latitude,
                                                               @RequestParam double longitude,
                                                               @RequestParam int radius){
        return ResponseEntity.ok().body(placeService.getNearbyPlaces(latitude, longitude, radius));
    }
}
