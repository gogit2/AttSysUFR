package com.amoh.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(name="seq", initialValue=2021404000, allocationSize=300)
public class Student {

    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "namestu_id")
    private int stu_id;

    private int active;

    private String password;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
            , fetch = FetchType.LAZY)
    @JoinTable(name = "student_course"
            ,joinColumns = @JoinColumn(name = "fk_namestu_id")
            ,inverseJoinColumns = @JoinColumn(name = "fk_cour_id")
    )
    private List<Course> courses;

    @OneToMany(mappedBy = "student")
    private List<Attendance> attendances;

    public Student() {
    }

    public Student(int stu_id, int active, String password) {
        this.stu_id = stu_id;
        this.active = active;
        this.password = password;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int namestu_id) {
        this.stu_id = namestu_id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
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

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public void addAttendance(Attendance attendance){
        if(this.attendances == null)
            this.attendances = new ArrayList<>();
        else
            this.attendances.add(attendance);
    }


}
