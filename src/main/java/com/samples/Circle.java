package com.samples;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by edara on 8/4/16.
 */
@Entity
public class Circle {
    @Id
    private int id;
    private String name;
    public Circle(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String toString() {
        return this.id+"-->"+this.getName();
    }
}
