package com.rest.api.laptop_inventory.service;

import com.rest.api.laptop_inventory.exception.LaptopInventoryException;
import com.rest.api.laptop_inventory.model.LaptopBrand;
import com.rest.api.laptop_inventory.repository.LaptopBrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LaptopBrandService {

    LaptopBrandRepository laptopBrandRepository;

    public Optional<List<LaptopBrand>> getAllLaptopBrands() {
        return Optional.of(laptopBrandRepository.findAll());
    }

    public Optional<LaptopBrand> getById(Long id) {

        return Optional.ofNullable(laptopBrandRepository.findById(id)
                .orElseThrow(() -> new LaptopInventoryException("Invalid laptop brand id:")));
    }

    public LaptopBrand update(Long id, LaptopBrand laptopBrand) {
        laptopBrand.setId(id);

        LaptopBrand existingBrand = laptopBrandRepository.findById(id)
                .orElseThrow(() ->
                        new LaptopInventoryException("Invalid laptop brand id: "+id));

        return laptopBrandRepository.save(laptopBrand);
    }

    public LaptopBrand save(LaptopBrand laptopBrand) {
        return laptopBrandRepository.save(laptopBrand);
    }

    public void delete(Long id) {
        LaptopBrand existingBrand = Optional.of(laptopBrandRepository.getOne(id))
                .orElseThrow(() ->
                        new LaptopInventoryException("Invalid laptop brand id: "+id));

         laptopBrandRepository.deleteById(id);
    }
}
