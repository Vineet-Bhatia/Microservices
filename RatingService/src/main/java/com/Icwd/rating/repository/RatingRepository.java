package com.Icwd.rating.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.Icwd.rating.entities.Rating;
import java.util.List;


public interface RatingRepository extends MongoRepository<Rating,String> {
	
	//Custom Methods
	
	//CUSTOM FINDER METHOD
	List<Rating> findByUserId(String userId);
	List<Rating> findByHotelId(String hotelId);	
	

}
