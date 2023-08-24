package com.devmireski.cardapioDB.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devmireski.cardapioDB.entity.Food;
import com.devmireski.cardapioDB.service.FoodService;

@RestController
@RequestMapping(value = "/food")
public class FoodResources {

	@Autowired
	private FoodService service;
	
	@GetMapping()
	public ResponseEntity<List<Food>> findAll(){
		List<Food> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Food> findById(@PathVariable Long id) {
		Food fd = service.findById(id);
		return ResponseEntity.ok().body(fd);
	}
	
	@PostMapping
	public ResponseEntity<Food> insert(@RequestBody Food food) {
		food = service.insert(food);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(food.getId()).toUri();
		return ResponseEntity.created(uri).body(food);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
