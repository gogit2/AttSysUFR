package com.amoh.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "doct_id")
    private int doc_id;

    private String username;

    private int active;

    private String password;

    @OneToMany(mappedBy = "theDoctor")
    private List<Course> courses;

    public Doctor() {
    }

    public Doctor(int doc_id, String username, int active, String password) {
        this.doc_id = doc_id;
        this.username = username;
        this.active = active;
        this.password = password;
    }

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int namestu_id) {
        this.doc_id = namestu_id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
