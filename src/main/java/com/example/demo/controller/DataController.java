package com.example.demo.controller;

import com.example.demo.dataModel.Bug;
import com.example.demo.dataModel.Device;
import com.example.demo.dataModel.Tester;
import com.example.demo.dataModel.TesterDevice;
import com.example.demo.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("http://localhost:8081")
@Controller
@RequestMapping("/api/data")
public class DataController {

    @Autowired
    DataService dataService;

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
    public ResponseEntity<List<Bug>> getAllBugs() {
        try {
            List<Bug> bugs = dataService.getAllBugs();

            if (bugs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(bugs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/devices")
    public ResponseEntity<List<Device>> getAllDevices() {
        try {
            List<Device> devices = dataService.getAllDevices();

            if (devices.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(devices, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/testers")
    public ResponseEntity<List<Tester>> getAllTesters() {
        try {
            List<Tester> testers = dataService.getAllTesters();

            if (testers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(testers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tester_devices")
    public ResponseEntity<List<TesterDevice>> getAllTesterDevices() {
        try {
            List<TesterDevice> testerDevices = dataService.getAllTesterDevices();

            if (testerDevices.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(testerDevices, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
