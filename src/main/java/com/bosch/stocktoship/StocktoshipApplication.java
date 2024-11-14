package com.bosch.stocktoship;

import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bosch.stocktoship.exception.InvalidIntegerException;
import com.bosch.stocktoship.exception.InvalidItemCodeException;
import com.bosch.stocktoship.service.BOMMain;
import com.bosch.stocktoship.service.InventoryRequisitionFormService;


@SpringBootApplication
public class StocktoshipApplication {

	public static void main(String[] args) throws SQLException, InvalidIntegerException, InvalidItemCodeException {
		SpringApplication.run(StocktoshipApplication.class, args);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Stock Vault");
		while(true) {
			System.out.println("\n1. Item code \t2. Inventory Requisition \t3. BOM \t4. Exit");
			int input = Integer.parseInt(scanner.nextLine());
			BOMMain bomMain = new BOMMain();
			if(input == 1) {
				bomMain.generateItemCode(scanner);
			}
			
			if(input == 2) {				
				InventoryRequisitionFormService service = new InventoryRequisitionFormService();
				service.manageIRF(scanner);
			}
			
			if(input == 3) {
				bomMain.collectBOMDetails(scanner);
			}
			
			if(input == 4) {
				break;
			}
		}
	}

}
