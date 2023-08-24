package com.devmireski.cardapioDB.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.devmireski.cardapioDB.entity.Food;
import com.devmireski.cardapioDB.exception.ResourceNotFoundException;
import com.devmireski.cardapioDB.repository.FoodRepository;

@Service
public class FoodService {

	@Autowired
	private FoodRepository repository;

	public List<Food> findAll() {
		return repository.findAll();
	}
	
	public Food findById(Long id) {
		Optional<Food> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Food insert(Food food) {
		return repository.save(food);
	}
	
	public void delete(Long id) {
		try {
		repository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			e.getMessage();
		}
	}
}
