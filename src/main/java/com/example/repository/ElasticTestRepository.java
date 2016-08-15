package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

@Repository
public interface ElasticTestRepository extends ElasticsearchRepository<User, String> {

	List<User> findByName(String name, Pageable pageable);
}
