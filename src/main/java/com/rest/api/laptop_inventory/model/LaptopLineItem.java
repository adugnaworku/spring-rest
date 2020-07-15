package com.rest.api.laptop_inventory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LaptopLineItem {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    LaptopBrand brand;

    Integer ramSize;

    Integer screenSize;

    boolean isConvertible;



}
