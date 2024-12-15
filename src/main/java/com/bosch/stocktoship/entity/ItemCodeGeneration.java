package com.bosch.stocktoship.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ItemCodeGeneration {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String uniqueItemCode;
    private String description;
    private String category;
    private String dimensions;
    private String manufacturer;
    private String countryOfOrigin;
    
    @Override
   	public String toString() {
   		return "ItemCode [id=" + id + ", uniqueItemCode=" + uniqueItemCode + ", description=" + description
   				+ ", category=" + category + ", dimensions=" + dimensions + ", manufacturer=" + manufacturer
   				+ ", countryOfOrigin=" + countryOfOrigin + "]";
   	}


    // Getters and Setters
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }
}