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

import com.bosch.stocktoship.entity.ItemCodeGeneration;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(com.bosch.stocktoship.controller.ItemCodeController.class)
class ItemCodeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private com.bosch.stocktoship.repository.ItemCodeRepository itemCodeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateItem() throws Exception {
    	ItemCodeGeneration item = new ItemCodeGeneration();
        item.setId(1L);
        item.setUniqueItemCode("ITEM123");
        item.setDescription("Test Item");
        item.setCategory("Category A");
        item.setDimensions("10x10x10");
        item.setManufacturer("Test Manufacturer");
        item.setCountryOfOrigin("USA");

        when(itemCodeRepository.save(any(ItemCodeGeneration.class))).thenReturn(item);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.uniqueItemCode").value("ITEM123"))
                .andExpect(jsonPath("$.description").value("Test Item"))
                .andExpect(jsonPath("$.category").value("Category A"))
                .andExpect(jsonPath("$.dimensions").value("10x10x10"))
                .andExpect(jsonPath("$.manufacturer").value("Test Manufacturer"))
                .andExpect(jsonPath("$.countryOfOrigin").value("USA"));
    }

    @Test
    void testGetAllItems() throws Exception {
    	ItemCodeGeneration item1 = new ItemCodeGeneration();
        item1.setId(1L);
        item1.setUniqueItemCode("ITEM123");

        ItemCodeGeneration item2 = new ItemCodeGeneration();
        item2.setId(2L);
        item2.setUniqueItemCode("ITEM456");

        when(itemCodeRepository.findAll()).thenReturn(Arrays.asList(item1, item2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/items")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].uniqueItemCode").value("ITEM123"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].uniqueItemCode").value("ITEM456"));
    }

    @Test
    void testGetItemById() throws Exception {
    	ItemCodeGeneration item = new ItemCodeGeneration();
        item.setId(1L);
        item.setUniqueItemCode("ITEM123");

        when(itemCodeRepository.findById(1L)).thenReturn(Optional.of(item));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/items/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.uniqueItemCode").value("ITEM123"));
    }

    @Test
    void testGetItemByIdNotFound() throws Exception {
        when(itemCodeRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/items/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetItemByUniqueCode() throws Exception {
    	ItemCodeGeneration item = new ItemCodeGeneration();
        item.setId(1L);
        item.setUniqueItemCode("ITEM123");

        when(itemCodeRepository.findByUniqueItemCode("ITEM123")).thenReturn(Optional.of(item));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/items/search")
                .param("itemCode", "ITEM123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.uniqueItemCode").value("ITEM123"));
    }

    @Test
    void testGetItemByUniqueCodeNotFound() throws Exception {
        when(itemCodeRepository.findByUniqueItemCode("ITEM123")).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/items/search")
                .param("itemCode", "ITEM123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Item with code ITEM123 not found."));
    }
}
