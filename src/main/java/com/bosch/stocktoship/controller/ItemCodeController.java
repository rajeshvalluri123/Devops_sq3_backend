package com.bosch.stocktoship.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bosch.stocktoship.entity.ItemCodeGeneration;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class ItemCodeController {
	
	@Autowired
    private com.bosch.stocktoship.repository.ItemCodeRepository itemRepository;

    // POST request to save item
    @PostMapping(value="/items")
    public ResponseEntity<ItemCodeGeneration> createItem(@RequestBody ItemCodeGeneration item) {
    	ItemCodeGeneration savedItem = itemRepository.save(item);
        return ResponseEntity.ok(savedItem);
    }

    // GET request to fetch all items
    @GetMapping(value="/items")
    public ResponseEntity<List<ItemCodeGeneration>> getAllItems() {
        List<ItemCodeGeneration> items = itemRepository.findAll();
        return ResponseEntity.ok(items);
    }

    // GET request to fetch a single item by ID
    @GetMapping(value="/items/{id}")
    public ResponseEntity<ItemCodeGeneration> getItemById(@PathVariable Long id) {
        return itemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
 // New GET request to fetch an item by uniqueItemCode
    @GetMapping(value = "/items/search")
    public ResponseEntity<?> getItemByUniqueCode(@RequestParam String itemCode) {
        Optional<ItemCodeGeneration> item = itemRepository.findByUniqueItemCode(itemCode);
        if (item.isPresent()) {
            return ResponseEntity.ok(item.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Item with code " + itemCode + " not found.");
        }
    }
    
    // DELETE request to delete an item by ID
    @DeleteMapping(value = "/items/{id}")
    public ResponseEntity<Void> deleteItemById(@PathVariable Long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content on successful deletion
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found if the item doesn't exist
        }
    }
}
