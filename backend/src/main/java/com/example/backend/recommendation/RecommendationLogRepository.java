package com.example.backend.recommendation;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecommendationLogRepository extends MongoRepository<RecommendationLogEntity, String>  {
}
