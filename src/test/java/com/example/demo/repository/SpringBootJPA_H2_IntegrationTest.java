package com.example.demo.repository;

import com.example.demo.config.AppConfig;
import com.example.demo.dataModel.Tester;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.ws.rs.core.Application;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@SpringBootTest(classes = Application.class)
public class SpringBootJPA_H2_IntegrationTest {

  @Autowired private TesterRepository testerRepository;

  @Test
  public void givenTesterRepository_SaveAndRetreiveEntity_success() {
    Tester tester =
        testerRepository.save(new Tester(1L, "Jane", "Doe", "US", "2013-07-12 13:27:18"));
    Tester foundEntity = testerRepository.findOneByTesterId(tester.getTesterId());

    assertNotNull(foundEntity);
    assertEquals(tester.getFirstName(), foundEntity.getFirstName());
    assertNotEquals(tester.getCountry(), "GB");
  }
}
