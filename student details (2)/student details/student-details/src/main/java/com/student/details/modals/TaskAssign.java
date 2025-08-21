package com.student.details.modals;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TaskAssign" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskAssign {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @Id
    private long taskId;
    @Column(name="studentName")
    private String studentName;
    @Column(name="taskName")
    private String taskName;
    @Column(name="deadline")
    private String deadline;
    @Column(name="progress")
    private String progress;

}
