package com.example.demo.dataModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
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

    public Thing() {}

    public Thing(long id, String title, String description, boolean good) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.good = good;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isGood() {
        return good;
    }

    public void setGood(boolean isPublished) {
        this.good = isPublished;
    }

    @Override
    public String toString() {
        return "Thing [id=" + id + ", title=" + title + ", desc=" + description + ", good=" + good + "]";
    }
}





