package com.devmireski.cardapioDB.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.devmireski.cardapioDB.DTO.FoodDTO;
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
		} catch (EmptyResultDataAccessException e) {
			e.getMessage();
		}
	}
	
	public Food update(Food obj) {
		Food newObj= findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void updateData(Food newObj, Food obj) {
		newObj.setName(obj.getName());
		newObj.setImage(obj.getImage());
		newObj.setPrice(obj.getPrice());
	}

	public Food fromDTO(FoodDTO objDto) {
		return new Food(objDto.getId(), objDto.getName(), objDto.getImage(), objDto.getPrice());
	}
}
