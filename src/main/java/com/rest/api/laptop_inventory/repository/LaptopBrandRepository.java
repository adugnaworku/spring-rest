package com.rest.api.laptop_inventory.repository;

import com.rest.api.laptop_inventory.model.LaptopBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LaptopBrandRepository extends JpaRepository<LaptopBrand, Long> {
}
