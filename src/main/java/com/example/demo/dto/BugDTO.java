package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BugDTO {
  private long bugId;
  private long deviceId;
  private long testerId;
}
