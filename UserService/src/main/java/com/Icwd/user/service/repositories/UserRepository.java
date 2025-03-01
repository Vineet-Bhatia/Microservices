package com.Icwd.user.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Icwd.user.service.entity.User;




@Repository
public interface UserRepository extends  JpaRepository<User,String>{
	
	//If you want to introduce any custom method or query
	//write

}
