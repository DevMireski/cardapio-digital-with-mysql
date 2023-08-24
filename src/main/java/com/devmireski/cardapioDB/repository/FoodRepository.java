package com.devmireski.cardapioDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devmireski.cardapioDB.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long>{

}
