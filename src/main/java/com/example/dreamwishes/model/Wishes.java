package com.example.dreamwishes.model;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
@Primary
public class Wishes {

    private int id;
    private String name;
    private String description;
    private String city;
    private String tags;
    private String location;

    // Constructor for adding attractions without ID

    public Wishes() {
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;
        this.location = location;
    }


    // Constructor for editing attractions with ID
    public Wishes(int id, String name, String description, String tags, String location, String city) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.location = location;
        this.city = city;
    }



    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCity(String city) {
        this.city = city;
    }

   /* public void setTags(String tags) {
        this.tags = tags;
    }

    */

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCity() {
        return city;
    }

    public String getTags() {
        return tags;
    }

    public String getPrice() {
        return location;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setPrice(String defaultLocation) {
        this.location = defaultLocation;
    }

    public void setId(int id) {
        this.id = id;
    }
}

