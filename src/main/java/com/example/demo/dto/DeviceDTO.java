package com.example.demo.dto;

import com.example.demo.dataModel.Tester;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class DeviceDTO {
  private long deviceId;
  private String description;
  private Set<Tester> owners;
}
