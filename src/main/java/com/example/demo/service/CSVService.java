package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import com.example.demo.dataModel.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.utils.CSVHelper;

@Service
public class CSVService {
  @Autowired
  ThingRepository thingRepository;
  @Autowired
  BugRepository bugRepository;
  @Autowired
  DeviceRepository deviceRepository;
  @Autowired
  TesterRepository testerRepository;
  @Autowired
  TesterDeviceRepository testerDeviceRepository;

  public void saveThings(MultipartFile file) {
    try {
      List<Thing> things = CSVHelper.csvToThings(file.getInputStream());
      thingRepository.saveAll(things);
    } catch (IOException e) {
      throw new RuntimeException("failed to save csv data: " + e.getMessage());
    }
  }

  public void saveBugs(MultipartFile file) {
    try {
      List<Bug> bugs = CSVHelper.csvToBugs(file.getInputStream());
      bugRepository.saveAll(bugs);
    } catch (IOException e) {
      throw new RuntimeException("failed to save csv data: " + e.getMessage());
    }
  }

  public void saveDevices(MultipartFile file) {
    try {
      List<Device> devices = CSVHelper.csvToDevices(file.getInputStream());
      deviceRepository.saveAll(devices);
    } catch (IOException e) {
      throw new RuntimeException("failed to save csv data: " + e.getMessage());
    }
  }

  public void saveTesters(MultipartFile file) {
    try {
      List<Tester> testers = CSVHelper.csvToTesters(file.getInputStream());
      testerRepository.saveAll(testers);
    } catch (IOException e) {
      throw new RuntimeException("failed to save csv data: " + e.getMessage());
    }
  }

  public void saveTesterDevices(MultipartFile file) {
    try {
      List<TesterDevice> testerDevices = CSVHelper.csvToTesterDevices(file.getInputStream());
      testerDeviceRepository.saveAll(testerDevices);
    } catch (IOException e) {
      throw new RuntimeException("failed to save csv data: " + e.getMessage());
    }
  }
}
