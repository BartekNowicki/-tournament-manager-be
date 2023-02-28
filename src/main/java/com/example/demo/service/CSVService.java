package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import com.example.demo.dataModel.Thing;
import com.example.demo.repository.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.utils.CSVHelper;

@Service
public class CSVService {
  @Autowired ThingRepository repository;

  public void save(MultipartFile file) {
    try {
      List<Thing> things = CSVHelper.csvToThings(file.getInputStream());
      repository.saveAll(things);
    } catch (IOException e) {
      throw new RuntimeException("failed to save csv data: " + e.getMessage());
    }
  }

  public List<Thing> getAllThings() {
    return repository.findAll();
  }
}
