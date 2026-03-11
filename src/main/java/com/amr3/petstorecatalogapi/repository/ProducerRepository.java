package com.amr3.petstorecatalogapi.repository;

import com.amr3.petstorecatalogapi.model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
}