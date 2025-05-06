package com.example.case_study.repository;

import com.example.case_study.model.PlaceSearch;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends MongoRepository<PlaceSearch, String> {
    Optional<PlaceSearch> findByRequestKey(String requestKey);
}
