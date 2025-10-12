package com.example.catfact.entity;

import jakarta.persistence.*;

@Entity
public class CatFact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fact;
    private int length;

    public CatFact() {}

    public CatFact(Long id, String fact, int length) {
        this.id = id;
        this.fact = fact;
        this.length = length;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFact() { return fact; }
    public void setFact(String fact) { this.fact = fact; }

    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }
}
