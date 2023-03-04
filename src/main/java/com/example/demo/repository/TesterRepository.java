package com.example.demo.repository;

import com.example.demo.dataModel.Tester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TesterRepository extends JpaRepository<Tester, Long> {

  @Query(value = "SELECT * FROM demo_db.testers t WHERE country = ?1", nativeQuery = true)
  List<Tester> findTestersByCountry(String country);

  Tester findOneByTesterId(long testerId);
}
