package com.amoh.entity;


import javax.persistence.*;

//@Entity(name = "stuattendance") // value = stuatten  //@Entity \n @Table(name = "users")
@Entity
@Table(name = "stuatten")
public class Stuattendance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "namesId")
    private String namesId;

    protected Stuattendance(){
    }

    protected Stuattendance(int id, String namesId) {
        this.id = id;
        this.namesId = namesId;
    }

    public int getId() {
        return id;
    }
    public String getNamesId() {
        return namesId;
    }
    public void setNamesId(String namesId) {
        this.namesId = namesId;
    }

    @Override
    public String toString() {
        return "stuattndanceClass: {" +
                "id=" + id +
                ", namesId='" + namesId + '\'' +
                '}';
    }
}
