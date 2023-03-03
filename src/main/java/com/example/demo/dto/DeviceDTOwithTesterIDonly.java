package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class DeviceDTOwithTesterIDonly {
  private long deviceId;
  private String description;
  private Set<Long> owners;
}
