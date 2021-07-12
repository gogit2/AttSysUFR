package com.amoh.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cour_id")
    private int cour_id;

    @Column(name = "cour_name")
    private String courname;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
                , fetch = FetchType.LAZY)
    @JoinColumn(name = "Fk_Doct_Id")
    private Doctor theDoctor;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
            , fetch = FetchType.LAZY)
    @JoinTable(name = "student_course"
            ,joinColumns = @JoinColumn(name = "fk_cour_id")
            ,inverseJoinColumns = @JoinColumn(name = "fk_namestu_id")
    )
    private List<Student> students;

    @OneToMany(mappedBy = "course")
    private List<Attendance> attendances;

    public Course() {
    }

    public Course(int cour_id, String courname) {
        this.cour_id = cour_id;
        this.courname = courname;
    }

    public int getCour_id() {
        return cour_id;
    }

    public void setCour_id(int cour_id) {
        this.cour_id = cour_id;
    }

    public String getCourname() {
        return courname;
    }

    public void setCourname(String courname) {
        this.courname = courname;
    }

    public Doctor getTheDoctor() {
        return theDoctor;
    }

    public void setTheDoctor(Doctor theDoctor) {
        this.theDoctor = theDoctor;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
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
