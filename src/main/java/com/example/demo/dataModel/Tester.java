package com.example.demo.dataModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "testers")
public class Tester {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "testerId")
    private long testerId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "country")
    private String country;

    @Column(name = "lastLogin")
    //private LocalDateTime lastLogin;
    private String lastLogin;

    @ManyToMany
    @JoinTable(
            name = "tester_device",
            joinColumns = @JoinColumn(name = "testerId"),
            inverseJoinColumns = @JoinColumn(name = "deviceId"))
    Set<Device> ownedDevices;

    //constructor needed by the CSVHelper
    public Tester(long testerId, String firstName, String lastName, String country, String lastLogin) {
        this.testerId = testerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "Tester [testerId=" + testerId + ", firstName=" + firstName + ", lastName=" + lastName + ", country=" + country + " + lastLogin=" + lastLogin + "]";
    }
}





