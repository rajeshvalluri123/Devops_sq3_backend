package com.bosch.stocktoship.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosch.stocktoship.entity.InventoryRequistionForm;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class InventoryRequisitionController {

	@Autowired
	private com.bosch.stocktoship.repository.InventoryRequisitionRepository inventoryrequisitionrepository;

	// POST request to save inventoryrequisition
	@PostMapping(value = "/inventoryrequisition")
	public ResponseEntity<InventoryRequistionForm> createinventoryrequisition(
			@RequestBody InventoryRequistionForm inventoryrequisition) {
		InventoryRequistionForm savedinventoryrequisition = inventoryrequisitionrepository.save(inventoryrequisition);
		return ResponseEntity.ok(savedinventoryrequisition);
	}

	// GET request to fetch all inventoryrequisitions
	@GetMapping(value = "/inventoryrequisition")
	public ResponseEntity<List<InventoryRequistionForm>> getAllInventoryRequisitions() {
		List<InventoryRequistionForm> inventoryrequisitions = inventoryrequisitionrepository.findAll();
		return ResponseEntity.ok(inventoryrequisitions);
	}

	// GET request to fetch a single inventoryrequisition by ID
	@GetMapping(value = "/inventoryrequisition/{id}")
	public ResponseEntity<InventoryRequistionForm> getInventoryRequisitionById(@PathVariable Long id) {
		return inventoryrequisitionrepository.findById(id).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/inventoryrequisition/{id}")
	public ResponseEntity<Void> deleteInventoryRequisitionById(@PathVariable Long id) {
		// Check if the entity exists before trying to delete it
		if (inventoryrequisitionrepository.existsById(id)) {
			inventoryrequisitionrepository.deleteById(id);
			return ResponseEntity.noContent().build(); // Return 204 No Content on successful deletion
		} else {
			return ResponseEntity.notFound().build(); // Return 404 Not Found if the entity doesn't exist
		}
	}

}
