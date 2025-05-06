package com.example.case_study.dto.response;

import lombok.Data;

@Data
public class PlaceResponse {

    private String name;

    private double latitude;

    private double longitude;

    private String address;

    private String placeId;
}
