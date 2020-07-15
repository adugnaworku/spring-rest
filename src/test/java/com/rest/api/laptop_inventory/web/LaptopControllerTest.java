package com.rest.api.laptop_inventory.web;

import com.rest.api.laptop_inventory.model.LaptopBrand;
import com.rest.api.laptop_inventory.service.LaptopBrandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LaptopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LaptopBrandService mockLaptopBrandService;

    private List<LaptopBrand> laptopBrandList;

    LaptopBrand laptopBrand;

    @BeforeEach
    public void setUp() {
        laptopBrandList = new ArrayList();
        laptopBrandList.add(new LaptopBrand(1L, "HP", Instant.now()));
        laptopBrandList.add(new LaptopBrand(2L, "DELL", Instant.now()));

        laptopBrand = new LaptopBrand(1L, "HP", Instant.now());

    }

    @Test
    public void shouldReturnEmptyWhenLaptopBrandIsNotDefined() throws Exception {
        when(mockLaptopBrandService.getAllLaptopBrands())
                .thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders
            .get("/api/laptop-brands/getall")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$",is(empty())));
    }

    @Test
    public void shouldReturnListOfLaptopBrands() throws Exception {
        when(mockLaptopBrandService.getAllLaptopBrands())
                .thenReturn(Optional.ofNullable(laptopBrandList));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/laptop-brands/getall")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(1)))
                .andExpect(jsonPath("$.[0].name", is("HP")))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldReturnSingleLaptopBrandById() throws Exception {
        when(mockLaptopBrandService.getById(1L))
                .thenReturn(Optional.ofNullable(laptopBrand));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/laptop-brands/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("HP")));
    }

    @Test
    public void shouldThrowExceptionIfLaptopBrandNoFoundById() throws Exception {
        when(mockLaptopBrandService.getById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/laptop-brands/get/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message",
                        is("Laptop brand not found with Id:1")));
    }

    @Test
    public void shouldSaveLaptopBrand() throws Exception {
        when(mockLaptopBrandService
                .save(LaptopBrand
                        .builder()
                        .name("HP")
                        .build()))
                .thenReturn(laptopBrand);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/laptop-brands/")
                .content("{ \"name\" : \"HP\" }")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("HP")));
    }

    @Test
    public void shouldUpdateLaptopBrand() throws Exception {
        when(mockLaptopBrandService
                .update(1L, LaptopBrand.builder().name("HP").build()))
                .thenReturn(laptopBrand);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/laptop-brands/1")
                .content("{ \"name\" : \"HP\" }")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("HP")));
    }
}
