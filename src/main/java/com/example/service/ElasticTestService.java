package com.example.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.domain.User;

public interface ElasticTestService {

	public void saveUser(User user);

	public List<User> getUsersByName(String name, Pageable pageable);

}
