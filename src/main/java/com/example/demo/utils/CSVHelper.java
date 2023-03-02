package com.example.demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.dataModel.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

public class CSVHelper {
    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Bug> csvToBugs(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Bug> bugs = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Bug bug = new Bug(
                        Long.parseLong(csvRecord.get("bugId")),
                        Long.parseLong(csvRecord.get("deviceId")),
                        Long.parseLong(csvRecord.get("testerId"))
                );

                bugs.add(bug);
            }

            return bugs;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static List<Device> csvToDevices(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Device> devices = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Device device = new Device(
                        Long.parseLong(csvRecord.get("deviceId")),
                        csvRecord.get("description")
                );

                devices.add(device);
            }

            return devices;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static List<Tester> csvToTesters(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Tester> testers = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Tester tester = new Tester(
                        Long.parseLong(csvRecord.get("testerId")),
                        csvRecord.get("firstName"),
                        csvRecord.get("lastName"),
                        csvRecord.get("country"),
                        //LocalDateTime.parse(csvRecord.get("lastLogin"))
                        csvRecord.get("lastLogin")
                );

                testers.add(tester);
            }

            return testers;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static List<TesterDevice> csvToTesterDevices(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<TesterDevice> testerDevices = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            int id = 0;

            for (CSVRecord csvRecord : csvRecords) {
                TesterDevice testerDevice = new TesterDevice(
                        id++,
                        Long.parseLong(csvRecord.get("testerId")),
                        Long.parseLong(csvRecord.get("deviceId"))
                );

                testerDevices.add(testerDevice);
            }

            return testerDevices;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}