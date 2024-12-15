package com.bosch.stocktoship.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bosch.stocktoship.entity.ItemCodeGeneration;



public interface ItemCodeRepository  extends  JpaRepository<ItemCodeGeneration, Long>{
	Optional<ItemCodeGeneration> findByUniqueItemCode(String uniqueItemCode);
}
