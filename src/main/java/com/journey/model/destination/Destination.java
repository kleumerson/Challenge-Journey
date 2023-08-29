package com.journey.model.destination;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "DESTINATIONS", schema = "jornada")
public class Destination{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "photo", nullable = false, length = 255)
    private String photo;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "photo1", nullable = false, length = 255)
    private String photo1;

    @Column(name = "photo2", nullable = false, length = 255)
    private String photo2;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "meta", nullable = false, length = 160)
    private String meta;

    @Column(name = "description", nullable = false, length = 255)
    private String description;
}
