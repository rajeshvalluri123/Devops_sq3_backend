package com.bosch.stocktoship.controllerTest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bosch.stocktoship.entity.InventoryRequistionForm;
import com.bosch.stocktoship.repository.InventoryRequisitionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(com.bosch.stocktoship.controller.InventoryRequisitionController.class)
class InventoryRequisitionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryRequisitionRepository inventoryRequisitionRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateInventoryRequisition() throws Exception {
    	InventoryRequistionForm requisition = new InventoryRequistionForm();
        requisition.setId(1L);
        requisition.setRequisitionerdepartment("IT");
        requisition.setUniqueItemCode("ITEM001");
        requisition.setQuantity(10);

        when(inventoryRequisitionRepository.save(any(InventoryRequistionForm.class))).thenReturn(requisition);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/inventoryrequisition")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requisition)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.requisitionerdepartment").value("IT"))
                .andExpect(jsonPath("$.uniqueItemCode").value("ITEM001"))
                .andExpect(jsonPath("$.quantity").value(10));
    }

    @Test
    void testGetAllInventoryRequisitions() throws Exception {
    	InventoryRequistionForm requisition1 = new InventoryRequistionForm();
        requisition1.setId(1L);
        requisition1.setRequisitionerdepartment("IT");

        InventoryRequistionForm requisition2 = new InventoryRequistionForm();
        requisition2.setId(2L);
        requisition2.setRequisitionerdepartment("Finance");

        when(inventoryRequisitionRepository.findAll()).thenReturn(Arrays.asList(requisition1, requisition2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/inventoryrequisition")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].requisitionerdepartment").value("IT"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].requisitionerdepartment").value("Finance"));
    }

    @Test
    void testGetInventoryRequisitionById() throws Exception {
    	InventoryRequistionForm requisition = new InventoryRequistionForm();
        requisition.setId(1L);
        requisition.setRequisitionerdepartment("IT");

        when(inventoryRequisitionRepository.findById(1L)).thenReturn(Optional.of(requisition));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/inventoryrequisition/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.requisitionerdepartment").value("IT"));
    }

    @Test
    void testGetInventoryRequisitionByIdNotFound() throws Exception {
        when(inventoryRequisitionRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/inventoryrequisition/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteInventoryRequisitionById() throws Exception {
        when(inventoryRequisitionRepository.existsById(1L)).thenReturn(true);
        doNothing().when(inventoryRequisitionRepository).deleteById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/inventoryrequisition/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteInventoryRequisitionByIdNotFound() throws Exception {
        when(inventoryRequisitionRepository.existsById(1L)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/inventoryrequisition/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
