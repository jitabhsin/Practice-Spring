package com.example.catfact.controller;

import com.example.catfact.dto.CatFactDto;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.catfact.service.CatFactService;

@RestController
public class CatFactController {
    private final CatFactService service;
    
    public CatFactController(CatFactService service) {
    	this.service=service;
    }
    
    @GetMapping("/fetch-fact")
    public ResponseEntity<CatFactDto> fetchFact() throws Exception{
    	return ResponseEntity.ok(service.fetchAndSaveFact());
    }
    
    @GetMapping("/facts")
    public ResponseEntity<List<CatFactDto>> getAllFacts() {
        return ResponseEntity.ok(service.getAllFacts());
    }
}
