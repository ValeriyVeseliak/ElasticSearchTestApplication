package com.example.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.SearchResponse;
import com.example.domain.User;
import com.example.service.ElasticTestService;

@Controller
@RequestMapping(value = "/ex-1")
public class TestController {

	@Autowired
	private ElasticTestService testService;

	@RequestMapping(value = "/alumni", method = RequestMethod.POST)
	public ResponseEntity<String> call1(@RequestBody User user) {
		if (!StringUtils.isAlpha(user.getName())
				|| user.getAddresses().stream().anyMatch(addr -> !StringUtils.isNumeric(addr.getNumber()))
				|| user.getAddresses().stream().anyMatch(address -> StringUtils.isEmpty(address.getCountry())
						|| StringUtils.isEmpty(address.getNumber()) || StringUtils.isEmpty(address.getStreet()))) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		testService.saveUser(user);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "/alumni", method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity<SearchResponse> call2(@RequestParam(name = "name") String name, Pageable pageable) {
		List<User> users = testService.getUsersByName(name, pageable);
		if (users.isEmpty()) {
			return new ResponseEntity<SearchResponse>(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(new SearchResponse(users));
	}

}
