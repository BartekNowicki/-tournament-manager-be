package com.example.demo.utils;

import com.example.demo.dataModel.Device;
import com.example.demo.dto.DeviceDTOwithTesterIDonly;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DeviceDTOMapper {
  public DeviceDTOwithTesterIDonly toDeviceDTO(Device device) {
    return new DeviceDTOwithTesterIDonly(device.getDeviceId(), device.getDescription(), device.getOwners().stream().map(owner -> owner.getTesterId()).collect(Collectors.toSet()));
  }
}
