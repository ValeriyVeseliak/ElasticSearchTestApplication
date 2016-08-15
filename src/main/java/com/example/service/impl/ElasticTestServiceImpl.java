package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.ElasticTestRepository;
import com.example.service.ElasticTestService;

@Service
public class ElasticTestServiceImpl implements ElasticTestService {

	@Autowired
	private ElasticTestRepository testRepository;

	@Override
	public void saveUser(User user) {
		testRepository.save(user);
	}

	@Override
	public List<User> getUsersByName(String name, Pageable pageable) {
		return testRepository.findByName(name, pageable);
	}

}
