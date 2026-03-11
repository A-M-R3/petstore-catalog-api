package com.amr3.petstorecatalogapi.controller;

import com.amr3.petstorecatalogapi.model.Producer;
import com.amr3.petstorecatalogapi.repository.ProducerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producers")
public class ProducerController {

    private final ProducerRepository producerRepository;

    public ProducerController(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @GetMapping
    public List<Producer> listProducers() {
        return producerRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Producer createProducer(@RequestBody Producer producer) {
        return producerRepository.save(producer);
    }
}