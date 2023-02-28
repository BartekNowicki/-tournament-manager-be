package com.example.demo.repository;

import com.example.demo.dataModel.Thing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThingRepository extends JpaRepository<Thing, Long> {}


