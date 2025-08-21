package com.student.details.modals;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @Id
    private long studentId;
    @Column(name="studentName")
    private String studentName;
    @Column( unique = true)
    private String studentEmailId;
    @Column(name="studentDOB")
    private String studentDOB;
    @Column(name="studentContactNo")
    private String studentContactNo;
    @Column(name="studentPassword")
    private String studentPassword;
    @Column(name="role")
    private String role;


    public void getStudentId(String studentDetails, StudentDetails studentDetails1) {
    }
}
