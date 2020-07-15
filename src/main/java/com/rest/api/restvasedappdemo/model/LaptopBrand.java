package com.rest.api.restvasedappdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LaptopBrand {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String name;

    Instant dateCreated;

}
