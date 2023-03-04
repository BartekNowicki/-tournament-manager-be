package com.example.demo;

import com.example.demo.config.AppConfig;

import com.example.demo.repository.TesterRepository;
import com.example.demo.service.DataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class SpringBootContextIntegrationTest {

    @Autowired
    private TesterRepository testerRepository;

    @Autowired
    private DataService dataService;

    @Test
    public void whenContextLoads_thenRepositoryIsNotNull() {
        assertThat(testerRepository).isNotNull();
    }

    @Test
    public void whenContextLoads_thenDataServiceIsNotNull() {
        assertThat(dataService).isNotNull();
    }
}
