package com.bosch.stocktoship.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.bosch.stocktoship.entity.GenerateBOM;

public interface GenerateBomRepository extends  JpaRepository<GenerateBOM, Long> {

}
