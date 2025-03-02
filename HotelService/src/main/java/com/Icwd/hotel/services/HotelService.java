package com.Icwd.hotel.services;

import java.util.List;

import com.Icwd.hotel.entities.Hotel;

public interface HotelService {

	Hotel create(Hotel hotel);
	
	List<Hotel> getAll();
	
	Hotel get(String id);
	
	
}
