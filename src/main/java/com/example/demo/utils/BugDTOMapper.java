package com.example.demo.utils;

import com.example.demo.dataModel.Bug;
import com.example.demo.dto.BugDTO;
import org.springframework.stereotype.Component;

@Component
public class BugDTOMapper {
  public BugDTO toBugDTO(Bug bug) {
    return new BugDTO(bug.getBugId(), bug.getDeviceId(), bug.getTesterId());
  }
}
