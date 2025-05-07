package com.example.case_study.controller;

import com.example.case_study.dto.request.PlaceRequest;
import com.example.case_study.dto.response.PlaceResponse;
import com.example.case_study.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/place")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/get-nearby-places")
    public ResponseEntity<List<PlaceResponse>> getNearbyPlaces(@RequestBody PlaceRequest placeRequest){
        return ResponseEntity.ok().body(placeService.getNearbyPlaces(placeRequest));
    }
}
