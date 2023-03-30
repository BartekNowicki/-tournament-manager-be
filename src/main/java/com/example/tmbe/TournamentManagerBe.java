package com.example.tmbe;

import com.example.tmbe.dataModel.Tournament;
import com.example.tmbe.enumConverter.TournamentType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TournamentManagerBe {

  public static void main(String[] args) {
    SpringApplication.run(TournamentManagerBe.class, args);
  }
}
