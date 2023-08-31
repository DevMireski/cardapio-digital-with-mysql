package com.devmireski.cardapioDB.DTO;

import java.io.Serializable;

import com.devmireski.cardapioDB.entity.Food;

public class FoodDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String image;
	private Double price;

	public FoodDTO() {
	}

	public FoodDTO(Food obj) {
		id = obj.getId();
		name = obj.getName();
		image = obj.getImage();
		price = obj.getPrice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}