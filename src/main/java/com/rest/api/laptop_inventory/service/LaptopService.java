package com.rest.api.laptop_inventory.service;

import com.rest.api.laptop_inventory.model.LaptopBrand;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopService {
    public Optional<List<LaptopBrand>> getAllLaptopBrands() {
        return Optional.empty();
    }
}
