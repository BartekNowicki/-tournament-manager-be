package com.example.demo.service;

import com.example.demo.dataModel.*;
import com.example.demo.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
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


  public List<Thing> getAllThings() {
    return thingRepository.findAll();
  }

  public List<Bug> getAllBugs() { return bugRepository.findAll();
  }

  public List<Device> getAllDevices() { return deviceRepository.findAll();
  }

  public List<Tester> getAllTesters() { return testerRepository.findAll();
  }

  public List<TesterDevice> getAllTesterDevices() { return testerDeviceRepository.findAll();
  }

  public List<String> getAllCountries() {
    return List.of("aaa", "bbb", "ccc");
  }

}
