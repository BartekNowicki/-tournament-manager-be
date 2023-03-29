package com.example.tmbe.enumConverter;

public enum TournamentType {
  SINGLES("singles"), DOUBLES("doubles");

  private String naming;

  private TournamentType(String naming) {
    this.naming = naming;
  }

  public String getNaming() {
    return naming;
  }
}