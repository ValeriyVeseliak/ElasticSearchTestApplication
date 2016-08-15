package com.example;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.configs.ElasticTestApplication;
import com.example.configs.WebConfigs;
import com.example.domain.Address;
import com.example.domain.Degree;
import com.example.domain.User;
import com.example.repository.ElasticTestRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ElasticTestApplication.class, WebConfigs.class })
public class ElasticTestApplicationIntegrationTests {

	@Autowired
	private ElasticTestRepository testRepository;

	private static final String TEST_USERNAME = "unique_username";

	private List<User> users = new ArrayList<>();

	@Before
	public void before() {
		createUsers();
	}

	private void createUsers() {
		User user = new User();
		user.setName(TEST_USERNAME);
		Address address = new Address();
		address.setCountry("country");
		address.setNumber("25");
		Map<String, Degree> education = new HashMap<>();
		Degree degree = new Degree();
		degree.setUniversity("university");
		degree.setYear(2015);
		education.put("bachelor", degree);
		user.setEducation(education);
		testRepository.save(user);
		users.add(user);
	}

	@Test
	public void testFindByName() {
		assertArrayEquals(users.toArray(), testRepository.findByName(TEST_USERNAME, new PageRequest(0, 20)).toArray());
	}

	@After
	public void afer() {
		testRepository.delete(users);
	}
}
