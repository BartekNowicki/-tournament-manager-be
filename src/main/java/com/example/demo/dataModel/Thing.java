package com.example.demo.dataModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "things")
public class Thing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "good")
    private boolean good;

    public Thing(long id, String title, String description, boolean good) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.good = good;
    }

    @Override
    public String toString() {
        return "Thing [id=" + id + ", title=" + title + ", desc=" + description + ", good=" + good + "]";
    }
}





