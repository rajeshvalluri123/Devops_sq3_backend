package com.bosch.stocktoship.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class InventoryRequistionForm{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String requisitionerdepartment;
	private String uniqueItemCode;
	private int quantity;
	private String purposeofrequistions;
	private Date materialrequireddate;
	private Date dateofrequistions;
    private String itemdescription;
    private String unitsofmeasure;
    private String deliverydepartment;
    private String notesandcomments;
    
	@Override
	public String toString() {
		return "InventoryRequisition [id=" + id + ", requisitionerdepartment=" + requisitionerdepartment
				+ ", uniqueItemCode=" + uniqueItemCode + ", quantity=" + quantity + ", purposeofrequistions="
				+ purposeofrequistions + ", materialrequireddate=" + materialrequireddate + ", dateofrequistions="
				+ dateofrequistions + ", itemdescription=" + itemdescription + ", unitsofmeasure=" + unitsofmeasure
				+ ", deliverydepartment=" + deliverydepartment + ", notesandcomments=" + notesandcomments + "]";
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRequisitionerdepartment() {
		return requisitionerdepartment;
	}

	public void setRequisitionerdepartment(String requisitionerdepartment) {
		this.requisitionerdepartment = requisitionerdepartment;
	}

	public String getUniqueItemCode() {
		return uniqueItemCode;
	}

	public void setUniqueItemCode(String uniqueItemCode) {
		this.uniqueItemCode = uniqueItemCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPurposeofrequistions() {
		return purposeofrequistions;
	}

	public void setPurposeofrequistions(String purposeofrequistions) {
		this.purposeofrequistions = purposeofrequistions;
	}

	public Date getMaterialrequireddate() {
		return materialrequireddate;
	}

	public void setMaterialrequireddate(Date materialrequireddate) {
		this.materialrequireddate = materialrequireddate;
	}

	public Date getDateofrequistions() {
		return dateofrequistions;
	}

	public void setDateofrequistions(Date dateofrequistions) {
		this.dateofrequistions = dateofrequistions;
	}

	public String getItemdescription() {
		return itemdescription;
	}

	public void setItemdescription(String itemdescription) {
		this.itemdescription = itemdescription;
	}

	public String getUnitsofmeasure() {
		return unitsofmeasure;
	}

	public void setUnitsofmeasure(String unitsofmeasure) {
		this.unitsofmeasure = unitsofmeasure;
	}

	public String getDeliverydepartment() {
		return deliverydepartment;
	}

	public void setDeliverydepartment(String deliverydepartment) {
		this.deliverydepartment = deliverydepartment;
	}

	public String getNotesandcomments() {
		return notesandcomments;
	}

	public void setNotesandcomments(String notesandcomments) {
		this.notesandcomments = notesandcomments;
	}


}
