package com.example.case_study.model;

import com.example.case_study.dto.response.PlaceResponse;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("place_search")
public class PlaceSearch {

    @Id
    private Long id;

    private String requestKey;

    private List<PlaceResponse> placeResponseList;

    private String googleResponse;
}
