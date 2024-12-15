package com.bosch.stocktoship.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.bosch.stocktoship.entity.InventoryRequistionForm;

public interface InventoryRequisitionRepository extends  JpaRepository<InventoryRequistionForm, Long>{
	


}
