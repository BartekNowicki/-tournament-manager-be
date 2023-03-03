package com.example.demo.service;

import com.example.demo.dataModel.*;
import com.example.demo.dto.TesterDTOwithDeviceNamesOnly;
import com.example.demo.repository.*;

import com.example.demo.utils.TesterDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DataService {
  @Autowired BugRepository bugRepository;
  @Autowired DeviceRepository deviceRepository;
  @Autowired TesterRepository testerRepository;
  @Autowired TesterDeviceRepository testerDeviceRepository;
  @Autowired TesterDTOMapper testerDTOMapper;

  public List<Bug> getAllBugs() {
    return bugRepository.findAll();
  }

  public List<Bug> getAllBugsByTesterId(Long testerId, Long deviceId) {
    return bugRepository.findAllByTesterIdAndDeviceId(testerId, deviceId);
  }

  public int totalBugsFiled(Long testerId, List<String> deviceNames) {
    return deviceNames.stream()
        .map(deviceName -> getAllBugsByTesterId(testerId, getDeviceIdFrom(deviceName)).size())
        .reduce(0, (subtotal, n) -> subtotal + n);
  }

  private Long getDeviceIdFrom(String deviceName) {
    return deviceRepository.findByDescription(deviceName).getDeviceId();
  }

  public List<Device> getAllDevices() {
    return deviceRepository.findAll();
  }

  public List<Tester> getAllTesters() {
    return testerRepository.findAll();
  }

  public List<TesterDevice> getAllTesterDevices() {
    return testerDeviceRepository.findAll();
  }

  public Set<String> getAllCountries() {
    return getAllTesters().stream().map(Tester::getCountry).collect(Collectors.toSet());
  }

  public List<TesterDTOwithDeviceNamesOnly> findTestersByCountry(String country) {
    return testerRepository.findTestersByCountry(country).stream()
        .map(t -> testerDTOMapper.toTesterDTOwithDeviceNamesOnly(t))
        .collect(Collectors.toList());
  }
}
