package com.Icwd.rating.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Icwd.rating.entities.Rating;
import com.Icwd.rating.service.RatingService;


@RestController
@RequestMapping("/ratings")
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	
	
	@PostMapping
	public ResponseEntity<Rating> create(@RequestBody Rating rating){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
		
	}

	
	@GetMapping
	public ResponseEntity<List<Rating>> getRatings(){
		return ResponseEntity.ok(ratingService.getRatings());
		
	}
	
	 @GetMapping("/users/{userId}")
	    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId) {
	        List<Rating> userRatings = ratingService.getRatingByUserId(userId);
	        return ResponseEntity.ok(userRatings);
	 }
	
	 @GetMapping("/hotels/{hotelId}")
	    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId) {
	        List<Rating> hotelRatings = ratingService.getRatingByHotelId(hotelId);
	        return ResponseEntity.ok(hotelRatings);
	    }
}
