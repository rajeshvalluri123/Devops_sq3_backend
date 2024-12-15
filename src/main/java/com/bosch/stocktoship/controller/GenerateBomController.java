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


import com.bosch.stocktoship.entity.GenerateBOM;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class GenerateBomController {

	@Autowired
    private com.bosch.stocktoship.repository.GenerateBomRepository generatebomrepository;

    // POST request to save bom
    @PostMapping(value="/bom")
    public ResponseEntity<GenerateBOM> createbom(@RequestBody GenerateBOM bom) {
        System.out.println("saved");

        GenerateBOM savedbom = generatebomrepository.save(bom);
        return ResponseEntity.ok(savedbom);
    }

    // GET request to fetch all Boms
    @GetMapping(value="/bom")
    public ResponseEntity<List<GenerateBOM>> getAllBoms() {
        List<GenerateBOM> boms = generatebomrepository.findAll();
        return ResponseEntity.ok(boms);
    }

    // GET request to fetch a single Bom by ID
    @GetMapping(value="/bom/{id}")
    public ResponseEntity<GenerateBOM> getBomById(@PathVariable Long id) {
        return generatebomrepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/bom/{id}")
    public ResponseEntity<Void> deleteBomById(@PathVariable Long id) {
        // Check if the entity exists before trying to delete it
        if (generatebomrepository.existsById(id)) {
            generatebomrepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Return 204 No Content on successful deletion
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if the entity doesn't exist
        }
    }

}
