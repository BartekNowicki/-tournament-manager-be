package com.example.demo.utils;

import com.example.demo.dataModel.TesterDevice;
import com.example.demo.dto.TesterDeviceDTO;
import org.springframework.stereotype.Component;

@Component
public class TesterDeviceDTOMapper {
  public TesterDeviceDTO toTesterDeviceDTO(TesterDevice testerDevice) {
    return new TesterDeviceDTO(testerDevice.getTesterId(), testerDevice.getDeviceId());
  }
}
