package com.rest.api.restvasedappdemo.service;

import com.rest.api.restvasedappdemo.model.LaptopBrand;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LaptopService {
    public Optional<List<LaptopBrand>> getAllLaptopBrands() {
        return Optional.empty();
    }
}
