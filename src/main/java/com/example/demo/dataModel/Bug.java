package com.example.demo.dataModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bugs")
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bugId")
    private long bugId;

    @Column(name = "deviceId")
    private long deviceId;

    @Column(name = "testerId")
    private long testerId;

    @Override
    public String toString() {
        return "Bug [bugId=" + bugId + ", deviceId=" + deviceId + ", testerId=" + testerId + "]";
    }
}





