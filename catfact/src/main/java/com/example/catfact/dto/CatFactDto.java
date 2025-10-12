package com.example.catfact.dto;

public class CatFactDto {
    private String fact;
    private int length;

    public CatFactDto() {}
    public CatFactDto(String fact, int length) {
        this.fact = fact;
        this.length = length;
    }

    public String getFact() { return fact; }
    public void setFact(String fact) { this.fact = fact; }

    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }
}
