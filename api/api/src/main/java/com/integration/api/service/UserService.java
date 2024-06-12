package com.integration.api.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.integration.api.entity.User;
import com.integration.api.repository.UserReposity;

@Service
public class UserService {

	@Autowired
    UserReposity userRepository;

    @Autowired
    RestTemplate restTemplate;
    

    // URL of the third-party API
    String apiUrl = "https://66684b90f53957909ff76529.mockapi.io/user/userData";


    public List<User> GetUserData() {		
		ResponseEntity<User[]> responseEntity =restTemplate.getForEntity(apiUrl,User[].class);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			return Arrays.asList(responseEntity.getBody());
		}
		return Collections.emptyList();
	}
    
    public User postUserToApi(User user) {
		  ResponseEntity<User> responseEntity = restTemplate.postForEntity(apiUrl, user, User.class);
		  if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
		      return userRepository.save(user);
		  }
		  return user;
	}
    
    public User update(Long id ,User user) {
		String PutUrl=apiUrl + "/" +id;
		ResponseEntity<User> responseEntity = restTemplate.exchange(PutUrl,HttpMethod.PUT,new HttpEntity<>(user),User.class);
		 if (responseEntity.getStatusCode() == HttpStatus.OK) {
		        User updatedUser = responseEntity.getBody();
		        return userRepository.save(updatedUser);
		    } else {
		        throw new RuntimeException("Failed to update user with id " + id);
		    }
	}
    
    public String deletedata(Long id) {
		String DeleteUrl=apiUrl + "/" +id;
		ResponseEntity<User> responseEntity = restTemplate.exchange(DeleteUrl,HttpMethod.DELETE,HttpEntity.EMPTY,User.class);
		 if (responseEntity.getStatusCode() == HttpStatus.OK) {
		        User updatedUser = responseEntity.getBody();
		        this.userRepository.delete(updatedUser);
		    } else {
		        throw new RuntimeException("User with id: " + id + " not found");
		    }
		 return id+" Id Successfully deleted....";
	}
    
    public void createAllUser() {
        List<User> AllusersData = GetUserData();
        if (!AllusersData.isEmpty()) {
            userRepository.saveAll(AllusersData);
        }
    }
    
    public User fetchUserFromApi(Long userId) {
        String url = apiUrl + "/" + userId;
        return restTemplate.getForObject(url, User.class);
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
		 
}
    

	

