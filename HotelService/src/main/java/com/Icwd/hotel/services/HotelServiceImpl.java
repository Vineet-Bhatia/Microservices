package com.Icwd.hotel.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Icwd.hotel.entities.Hotel;
import com.Icwd.hotel.exception.ResourceNotFoundException;
import com.Icwd.hotel.repositories.HotelRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel create(Hotel hotel) {
		String hotelId=UUID.randomUUID().toString();
		hotel.setId(hotelId);
	
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAll() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel get(String id) {
		
		return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotels with given id not found !!"));
	}
	
	

}
