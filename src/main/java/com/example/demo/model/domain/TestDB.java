package com.example.demo.model.domain;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "testdb")
@Data

public class TestDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String name;
}
