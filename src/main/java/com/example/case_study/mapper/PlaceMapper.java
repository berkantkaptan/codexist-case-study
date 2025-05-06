package com.example.case_study.mapper;

import com.example.case_study.dto.response.PlaceResponse;
import com.example.case_study.model.PlaceSearch;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlaceMapper {

    public PlaceSearch ToPlaceSearch(String key, List<PlaceResponse> responseList, String googleResponse){
        PlaceSearch placeSearch = new PlaceSearch();
        placeSearch.setPlaceResponseList(responseList);
        placeSearch.setRequestKey(key);
        placeSearch.setGoogleResponse(googleResponse);
        return placeSearch;
    }
}
