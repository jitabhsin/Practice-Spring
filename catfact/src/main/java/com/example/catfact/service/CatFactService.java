package com.example.catfact.service;

import com.example.catfact.dto.CatFactDto;
import com.example.catfact.entity.CatFact;
import com.example.catfact.repository.CatFactRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatFactService {

    private final CatFactRepository repository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public CatFactService(CatFactRepository repository) {
        this.repository = repository;
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    // Fetch a cat fact from API, save to DB, return DTO
    public CatFactDto fetchAndSaveFact() throws Exception {
        String response = restTemplate.getForObject("https://catfact.ninja/fact?max_length=140", String.class);

        JsonNode jsonNode = objectMapper.readTree(response);
        String fact = jsonNode.get("fact").asText();
        int length = jsonNode.get("length").asInt();

        CatFact catFact = new CatFact();
        catFact.setFact(fact);
        catFact.setLength(length);

        repository.save(catFact);

        return new CatFactDto(fact, length);
    }

    // Retrieve all saved cat facts as DTOs
    public List<CatFactDto> getAllFacts() {
        List<CatFact> catFacts = repository.findAll();   // fetch all entities from DB
        List<CatFactDto> dtoList = new ArrayList<>();    // create an empty DTO list

        for (CatFact cf : catFacts) {
            CatFactDto dto = new CatFactDto();
            dto.setFact(cf.getFact());      
            dto.setLength(cf.getLength()); 
            dtoList.add(dto);              
        }

        return dtoList;  // return list of DTOs
    }

}
