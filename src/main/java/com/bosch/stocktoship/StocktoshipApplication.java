package com.bosch.stocktoship;

import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bosch.stocktoship.exception.InvalidIntegerException;
import com.bosch.stocktoship.exception.InvalidItemCodeException;



@SpringBootApplication
public class StocktoshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(StocktoshipApplication.class, args);
	}

}
