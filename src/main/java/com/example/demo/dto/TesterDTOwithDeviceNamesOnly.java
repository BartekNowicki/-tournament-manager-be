package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class TesterDTOwithDeviceNamesOnly {
  private long testerId;
  private String firstName;
  private String lastName;
  private String country;
  private String lastLogin;
  private Set<String> ownedDeviceNames;
}
