package com.example.demo.repository;

import com.example.demo.dataModel.Bug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BugRepository extends JpaRepository<Bug, Long> {
  List<Bug> findAllByTesterIdAndDeviceId(Long testerId, Long deviceId);
}
