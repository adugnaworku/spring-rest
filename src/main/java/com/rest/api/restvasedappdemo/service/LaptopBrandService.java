package com.rest.api.restvasedappdemo.service;

import com.rest.api.restvasedappdemo.model.LaptopBrand;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class LaptopBrandService {

    public Optional<List<LaptopBrand>> getAllLaptopBrands() {
        return Optional.empty();
    }

    public Optional<LaptopBrand> getById(Long id) {
        return Optional.empty();
    }

    public LaptopBrand save(LaptopBrand laptopBrand) {
        return new LaptopBrand(1L, "HP", Instant.now());
    }

    public LaptopBrand update(Long id, LaptopBrand laptopBrand) {
        return new LaptopBrand(1L, "HP2", Instant.now());
    }
}
