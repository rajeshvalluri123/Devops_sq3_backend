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

import com.bosch.stocktoship.controller.GenerateBomController;
import com.bosch.stocktoship.entity.GenerateBOM;
import com.bosch.stocktoship.repository.GenerateBomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(GenerateBomController.class)
class GenerateBomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenerateBomRepository generateBomRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateBom() throws Exception {
        GenerateBOM bom = new GenerateBOM();
        bom.setId(1L);
        bom.setUniqueItemCode("ITEM001");
        bom.setDescription("Test BOM");
        bom.setQuantity(5);
        bom.setDimensions("10x10x10");
        bom.setUnitsofmeasure("cm");
        bom.setManufacturer("Test Manufacturer");
        bom.setSuppliercode(1001);
        bom.setSubassembly(0);
        bom.setNotesandcomments("Sample notes");

        when(generateBomRepository.save(any(GenerateBOM.class))).thenReturn(bom);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/bom")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bom)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.uniqueItemCode").value("ITEM001"))
                .andExpect(jsonPath("$.description").value("Test BOM"))
                .andExpect(jsonPath("$.quantity").value(5))
                .andExpect(jsonPath("$.dimensions").value("10x10x10"))
                .andExpect(jsonPath("$.unitsofmeasure").value("cm"))
                .andExpect(jsonPath("$.manufacturer").value("Test Manufacturer"))
                .andExpect(jsonPath("$.suppliercode").value(1001))
                .andExpect(jsonPath("$.subassembly").value(0))
                .andExpect(jsonPath("$.notesandcomments").value("Sample notes"));
    }

    @Test
    void testGetAllBoms() throws Exception {
        GenerateBOM bom1 = new GenerateBOM();
        bom1.setId(1L);
        bom1.setUniqueItemCode("ITEM001");

        GenerateBOM bom2 = new GenerateBOM();
        bom2.setId(2L);
        bom2.setUniqueItemCode("ITEM002");

        when(generateBomRepository.findAll()).thenReturn(Arrays.asList(bom1, bom2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/bom")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].uniqueItemCode").value("ITEM001"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].uniqueItemCode").value("ITEM002"));
    }

    @Test
    void testGetBomById() throws Exception {
        GenerateBOM bom = new GenerateBOM();
        bom.setId(1L);
        bom.setUniqueItemCode("ITEM001");

        when(generateBomRepository.findById(1L)).thenReturn(Optional.of(bom));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/bom/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.uniqueItemCode").value("ITEM001"));
    }

    @Test
    void testGetBomByIdNotFound() throws Exception {
        when(generateBomRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/bom/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
