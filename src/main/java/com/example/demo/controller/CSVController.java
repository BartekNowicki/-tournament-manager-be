package com.example.demo.controller;

import java.util.List;

import com.example.demo.dataModel.*;
import com.example.demo.message.ResponseMessage;
import com.example.demo.service.CSVService;
import com.example.demo.utils.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin("http://localhost:8081")
@Controller
@RequestMapping("/api/csv")
public class CSVController {

    @Autowired
    CSVService fileService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {

      System.out.println("_____________________________________________________________");
      System.out.println(file.getOriginalFilename());
      System.out.println("_____________________________________________________________");

            try {

                switch(file.getOriginalFilename()) {
                    case "bugs.csv":
                        fileService.saveBugs(file);
                        break;
                    case "devices.csv":
                        fileService.saveDevices(file);
                        break;
                    case "tester_device.csv":
                        fileService.saveTesterDevices(file);
                        break;
                    case "testers.csv":
                        fileService.saveTesters(file);
                        break;
                    case "things.csv":
                        fileService.saveThings(file);
                        break;
                    default: {}
                }

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/bugs")
    public ResponseEntity<List<Bug>> getAllBugs() {
        try {
            List<Bug> bugs = fileService.getAllBugs();

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
            List<Device> devices = fileService.getAllDevices();

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
            List<Tester> testers = fileService.getAllTesters();

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
            List<TesterDevice> testerDevices = fileService.getAllTesterDevices();

            if (testerDevices.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(testerDevices, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}