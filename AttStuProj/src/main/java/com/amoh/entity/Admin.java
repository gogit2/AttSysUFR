package com.amoh.entity;

import javax.persistence.*;

@Entity
@Table(name = "adminm")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "adm_id")
    private int adm_id;

    private String username;

    private int active;

    private String password;

    public Admin() {
    }

    public Admin(int adm_id, String username, int active, String password) {
        this.adm_id = adm_id;
        this.username = username;
        this.active = active;
        this.password = password;
    }

    public int getAdm_id() {
        return adm_id;
    }

    public void setAdm_id(int namestu_id) {
        this.adm_id = namestu_id;
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
}
