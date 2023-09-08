package com.devmireski.cardapioDB.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devmireski.cardapioDB.DTO.FoodDTO;
import com.devmireski.cardapioDB.entity.Food;
import com.devmireski.cardapioDB.service.FoodService;

@RestController
@RequestMapping(value = "/food")
public class FoodResources {

	@Autowired
	private FoodService service;

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping()
	public ResponseEntity<List<FoodDTO>> findAll() {
		List<Food> obj = service.findAll();
		List<FoodDTO> foodDto = obj.stream().map(x -> new FoodDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(foodDto);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/{id}")
	public ResponseEntity<FoodDTO> findById(@PathVariable Long id) {
		Food obj = service.findById(id);
		return ResponseEntity.ok().body(new FoodDTO(obj));
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody FoodDTO food) {
		Food obj = service.fromDTO(food);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping(value = "/{id}")
 	public ResponseEntity<Void> update(@RequestBody FoodDTO objDto, @PathVariable Long id) {
		Food obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

}
