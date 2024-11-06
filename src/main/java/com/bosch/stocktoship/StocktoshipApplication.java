package com.bosch.stocktoship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bosch.stocktoship.entity.ItemCodeGeneration;
import com.bosch.stocktoship.service.BOMMain;


@SpringBootApplication
public class StocktoshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(StocktoshipApplication.class, args);
		
        // Create a BOMMain instance to manage BOM processes
        BOMMain bomMain = new BOMMain();

        try {
            // Collect general item details and store them in an ItemCodeGeneration object
            ItemCodeGeneration item = bomMain.collectItemDetails();

            // Collect specific BOM details (dimensions, quantity, etc.) for the item
            bomMain.collectBOMDetails(item);

            // Display the collected BOM details in a formatted table
            bomMain.displayBOMDetails(item);

        } catch (Exception e) {
            // Print any error that occurs during BOM collection or display
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
		
	}

}
