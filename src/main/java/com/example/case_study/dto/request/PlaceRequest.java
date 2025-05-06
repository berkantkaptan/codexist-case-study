package com.example.case_study.dto.request;

import lombok.Data;

@Data
public class PlaceRequest {

    private double latitude;

    private double longitude;

    private int radius;
}
