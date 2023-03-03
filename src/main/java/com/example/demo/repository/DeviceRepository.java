package com.example.demo.repository;

import com.example.demo.dataModel.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
  Device findByDescription(String deviceName);
}
