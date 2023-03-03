package com.example.demo.dataModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tester_device")
public class TesterDevice {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private long id;

  @Column(name = "testerId")
  private long testerId;

  @Column(name = "deviceId")
  private long deviceId;

  @Override
  public String toString() {
    return "TesterDevice [testerId=" + testerId + ", deviceId=" + deviceId + "]";
  }
}
