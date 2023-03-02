package com.example.demo.dto;

import com.example.demo.dataModel.Tester;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class BugDTO {
    private long bugId;
    private long deviceId;
    private long testerId;
}
