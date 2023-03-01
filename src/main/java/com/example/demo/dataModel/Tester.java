package com.example.demo.dataModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

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

    @Override
    public String toString() {
        return "Tester [testerId=" + testerId + ", firstName=" + firstName + ", lastName=" + lastName + ", country=" + country + " + lastLogin=" + lastLogin + "]";
    }
}





