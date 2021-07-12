package com.amoh.entity;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "att_id")
    private int att_id;

    @Column(name = "section")
    private String section;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="namestu_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cour_id")
    private Course course;

    public Attendance() {
    }

    public Attendance(String section, Student student, Course course) {
        this.section = section;
        this.student = student;
        this.course = course;
    }

    public int getAtt_id() {
        return att_id;
    }

    public void setAtt_id(int att_id) {
        this.att_id = att_id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
