package com.rest.api.laptop_inventory.service;

import com.rest.api.laptop_inventory.exception.LaptopInventoryException;
import com.rest.api.laptop_inventory.model.LaptopBrand;
import com.rest.api.laptop_inventory.repository.LaptopBrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LaptopBrandService {

    LaptopBrandRepository laptopBrandRepository;

    /**
     * Get all laptop brands
     * @return list of {@link List<LaptopBrand>}
     */
    public Optional<List<LaptopBrand>> getAllLaptopBrands() {
        return Optional.of(laptopBrandRepository.findAll());
    }

    /**
     * Get laptop brand by id
     * @param id
     * @return a single instance of {@link LaptopBrand}
     * @throws {@link LaptopInventoryException}
     */
    public Optional<LaptopBrand> getById(Long id) {

        return Optional.ofNullable(laptopBrandRepository.findById(id)
                .orElseThrow(() -> new LaptopInventoryException("Invalid laptop brand id:")));
    }

    /**
     * Update exisint laptop brand
     * @param id
     * @param {@link LaptopBrand}
     * @return updated data of type {@link LaptopBrand}
     * @throws {@link LaptopInventoryException}
     */
    @Transactional
    public LaptopBrand update(Long id, LaptopBrand laptopBrand) {
        laptopBrand.setId(id);

        LaptopBrand existingBrand = laptopBrandRepository.findById(id)
                .orElseThrow(() ->
                        new LaptopInventoryException("Invalid laptop brand id: "+id));

        return laptopBrandRepository.save(laptopBrand);
    }

    /**
     * inserts a new laptop brand
     * @param {@link LaptopBrand}
     * @return inserted data of type {@link LaptopBrand}
     */
    @Transactional
    public LaptopBrand save(LaptopBrand laptopBrand) {
        return laptopBrandRepository.save(laptopBrand);
    }

    /**
     * Deletes laptop brand by id
     * @param id
     * @throws {@link LaptopInventoryException}
     */
    @Transactional
    public void delete(Long id) {
        LaptopBrand existingBrand = Optional.of(laptopBrandRepository.getOne(id))
                .orElseThrow(() ->
                        new LaptopInventoryException("Invalid laptop brand id: "+id));

         laptopBrandRepository.deleteById(id);
    }
}
