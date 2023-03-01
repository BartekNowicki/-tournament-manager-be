package com.example.demo.dataModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "deviceId")
    private long deviceId;

    @Column(name = "description")
    private String description;

    @Override
    public String toString() {
        return "Device [deviceId=" + deviceId + ", description=" + description + "]";
    }
}





