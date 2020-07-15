package com.rest.api.laptop_inventory.controller;

import com.rest.api.laptop_inventory.exception.LaptopInventoryException;
import com.rest.api.laptop_inventory.model.LaptopBrand;
import com.rest.api.laptop_inventory.service.LaptopBrandService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
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


    @ApiOperation(value = "Gets all available brands", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping("/getall")
    public ResponseEntity<List<LaptopBrand>> getAllLaptopBrands(){
        return ResponseEntity
                .ok()
                .body(laptopBrandService.getAllLaptopBrands()
                .orElse(Collections.emptyList()));
    }

    @ApiOperation(value = "Gets aa single laptop brand by id", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping("/{id}")
    public ResponseEntity<LaptopBrand> getLaptopBrandById(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(laptopBrandService.getById(id)
                        .orElseThrow(() -> new LaptopInventoryException("Laptop brand not found with Id:"+ id)));
    }

    @ApiOperation(value = "Insert a new laptop brand", authorizations = { @Authorization(value="jwtToken") })
    @PostMapping("/")
    public ResponseEntity<LaptopBrand> save(@RequestBody LaptopBrand laptopBrand) {
        LaptopBrand savedLaptopBrand = laptopBrandService.save(laptopBrand);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLaptopBrand);
    }

    @ApiOperation(value = "Update existing laptop brand", authorizations = { @Authorization(value="jwtToken") })
    @PutMapping("/{id}")
    public ResponseEntity<LaptopBrand> update(@PathVariable Long id,
                                              @RequestBody LaptopBrand laptopBrand) {
        LaptopBrand savedLaptopBrand = laptopBrandService.update(id, laptopBrand);
        return new ResponseEntity<LaptopBrand>(savedLaptopBrand, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a laptop brand by id", authorizations = { @Authorization(value="jwtToken") })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
         laptopBrandService.delete(id);
         return ResponseEntity.ok().body("Laptop brand with deleted with id: "+id);
    }

}
