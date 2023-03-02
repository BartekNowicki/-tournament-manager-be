package com.example.demo.utils;

import com.example.demo.dataModel.Tester;
import com.example.demo.dto.TesterDTOwithDeviceNamesOnly;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TesterDTOMapper {
  public TesterDTOwithDeviceNamesOnly toTesterDTOwithDeviceNamesOnly(Tester tester) {
    return new TesterDTOwithDeviceNamesOnly(
        tester.getTesterId(),
        tester.getFirstName(),
        tester.getLastName(),
        tester.getCountry(),
        tester.getLastLogin(),
        tester.getOwnedDevices().stream().map(device -> device.getDescription()).collect(Collectors.toSet())
);
  }
}
