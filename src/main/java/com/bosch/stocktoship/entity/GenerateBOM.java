package com.bosch.stocktoship.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GenerateBOM{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String uniqueItemCode;
    private String description;
    private int quantity;
    private String dimensions;
    private String unitsofmeasure;
    private String manufacturer;
    private int suppliercode;
    private int subassembly;
    private String notesandcomments;
    
    
    @Override
	public String toString() {
		return "GenerateBom [id=" + id + ", uniqueItemCode=" + uniqueItemCode + ", description=" + description
				+ ", quantity=" + quantity + ", dimensions=" + dimensions + ", unitsofmeasure=" + unitsofmeasure
				+ ", manufacturer=" + manufacturer + ", suppliercode=" + suppliercode + ", subassembly=" + subassembly
				+ ", notesandcomments=" + notesandcomments + "]";
	}
    
   
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUniqueItemCode() {
		return uniqueItemCode;
	}

	public void setUniqueItemCode(String uniqueItemCode) {
		this.uniqueItemCode = uniqueItemCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUnitsofmeasure() {
		return unitsofmeasure;
	}

	public void setUnitsofmeasure(String unitsofmeasure) {
		this.unitsofmeasure = unitsofmeasure;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getSuppliercode() {
		return suppliercode;
	}

	public void setSuppliercode(int suppliercode) {
		this.suppliercode = suppliercode;
	}

	public int getSubassembly() {
		return subassembly;
	}

	public void setSubassembly(int subassembly) {
		this.subassembly = subassembly;
	}

	public String getNotesandcomments() {
		return notesandcomments;
	}

	public void setNotesandcomments(String notesandcomments) {
		this.notesandcomments = notesandcomments;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}    

}
