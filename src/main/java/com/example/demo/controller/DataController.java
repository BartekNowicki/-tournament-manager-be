package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.DataService;
import com.example.demo.utils.BugDTOMapper;
import com.example.demo.utils.DeviceDTOMapper;
import com.example.demo.utils.TesterDTOMapper;
import com.example.demo.utils.TesterDeviceDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@CrossOrigin("http://localhost:8081")
@Controller
@RequestMapping("/api/data")
public class DataController {

  @Autowired DataService dataService;
  @Autowired private DeviceDTOMapper deviceDTOmapper;
  @Autowired private BugDTOMapper bugDTOmapper;
  @Autowired private TesterDTOMapper testerDTOmapper;
  @Autowired private TesterDeviceDTOMapper testerDeviceDTOmapper;

  @GetMapping("/countries")
  public ResponseEntity<List<String>> getAllCountries() {
    try {
      List<String> countries = dataService.getAllCountries();

      if (countries.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(countries, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/bugs")
  public ResponseEntity<List<BugDTO>> getAllBugs() {
    try {
      List<BugDTO> bugs =
          dataService.getAllBugs().stream().map(bugDTOmapper::toBugDTO).collect(toList());
      ;

      if (bugs.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(bugs, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/devices")
  public ResponseEntity<List<DeviceDTOwithTesterIDonly>> getAllDevices() {
    try {
      List<DeviceDTOwithTesterIDonly> devices =
          dataService.getAllDevices().stream().map(deviceDTOmapper::toDeviceDTO).collect(toList());
      if (devices.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(devices, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/testers")
  public ResponseEntity<List<TesterDTOwithDeviceNamesOnly>> getAllTesters() {
    try {
      List<TesterDTOwithDeviceNamesOnly> testers =
          dataService.getAllTesters().stream().map(testerDTOmapper::toTesterDTO).collect(toList());
      ;

      if (testers.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(testers, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/tester_devices")
  public ResponseEntity<List<TesterDeviceDTO>> getAllTesterDevices() {
    try {
      List<TesterDeviceDTO> testerDevices =
          dataService.getAllTesterDevices().stream()
              .map(testerDeviceDTOmapper::toTesterDeviceDTO)
              .collect(toList());
      ;

      if (testerDevices.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(testerDevices, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
