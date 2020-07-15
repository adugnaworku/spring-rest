package com.rest.api.restvasedappdemo.controller;

import com.rest.api.restvasedappdemo.exception.LaptopInventoryException;
import com.rest.api.restvasedappdemo.model.LaptopBrand;
import com.rest.api.restvasedappdemo.service.LaptopBrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/laptop-brands")
@AllArgsConstructor
public class LaptopController {

    private LaptopBrandService laptopBrandService;

    /**
     * Get all laptop brands
     * @return
     */
    @GetMapping("/getall")
    public ResponseEntity<List<LaptopBrand>> getAllLaptopBrands(){
        return ResponseEntity
                .ok()
                .body(laptopBrandService.getAllLaptopBrands()
                .orElse(Collections.emptyList()));
    }

    @GetMapping("get/{id}")
    public ResponseEntity<LaptopBrand> getLaptopBrandById(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(laptopBrandService.getById(id)
                        .orElseThrow(() -> new LaptopInventoryException("Laptop brand not found with Id:"+ id)));
    }

    @PostMapping("/")
    public ResponseEntity<LaptopBrand> save(@RequestBody LaptopBrand laptopBrand) {
        LaptopBrand savedLaptopBrand = laptopBrandService.save(laptopBrand);
        return new ResponseEntity<LaptopBrand>(savedLaptopBrand, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody LaptopBrand laptopBrand) {
        LaptopBrand savedLaptopBrand = laptopBrandService.update(id, laptopBrand);
        return new ResponseEntity<LaptopBrand>(savedLaptopBrand, HttpStatus.CREATED);
    }


}
