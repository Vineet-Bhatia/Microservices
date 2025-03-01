package com.Icwd.user.service.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Icwd.rating.entities.Hotel;
import com.Icwd.user.service.entity.Rating;
import com.Icwd.user.service.entity.User;
import com.Icwd.user.service.exception.ResourceNotFoundException;
import com.Icwd.user.service.repositories.UserRepository;

import jakarta.annotation.PostConstruct;



@Service
public class UserServiceImpl implements UserServices {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User createUser(User user) {
		//generate unique userid 
		
		String randomUserId=UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String id) {
	    logger.info("Fetching user with ID: {}", id);

	    // Retrieve user from the database using the user repository
	    User user = userRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on the server: " + id));

	    // Fetch ratings of the user from the rating service
	    String url = String.format("http://localhost:8083/ratings/users/%s", user.getUserId());
	    ResponseEntity<List<Rating>> response = restTemplate.exchange(
	            url,
	            HttpMethod.GET,
	            null,
	            new ParameterizedTypeReference<List<Rating>>() {}
	    );
	    List<Rating> ratings = response.getBody();
	    logger.info("Ratings retrieved: {}", ratings);

	    // Fetch hotel details for each rating
	    ratings = ratings.stream().map(rating -> {
	        // API call to hotel service to get the hotel
	        ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8082/hotels/" + rating.getHotelId(), Hotel.class);
	        
	        Hotel hotel = forEntity.getBody();
	        
	        if (hotel != null) {
	            rating.setHotel(hotel);
	        } else {
	            logger.warn("No hotel found for ID: {}", rating.getHotelId());
	        }
	        
	        // Set the hotel to rating
	        // Return the rating
	        return rating;
	    }).collect(Collectors.toList());

	    // Set the retrieved ratings to the user object
	    user.setRating(ratings);

	    return user;
	} 


	@Override
	public User updateUserDetail(String id,User updatedUser) {
		User userData= userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on the server !! :"+ id));
		
		  if(updatedUser.getName() != null) {
			  userData.setName(updatedUser.getName());
		  }
		  if(updatedUser.getEmail() != null) {
			  userData.setEmail(updatedUser.getEmail());
		  }
		  if(updatedUser.getAbout() != null) {
		    userData.setAbout(updatedUser.getAbout());
		    }
		  
		  return userRepository.save(userData);
	}
	
	@PostConstruct
	public void testRepository() {
	    String testId = "05cf3cc1-0714-43a6-92cc-4e299166f52d";
	    userRepository.findById(testId).ifPresentOrElse(
	        user -> System.out.println("✅ Found user: " + user),
	        () -> System.out.println("❌ No user found with ID: " + testId)
	    );
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		
	}


}
