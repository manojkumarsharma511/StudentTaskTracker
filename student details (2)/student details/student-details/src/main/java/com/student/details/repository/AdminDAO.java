package com.student.details.repository;

import com.student.details.modals.StudentDetails;
import com.student.details.modals.TaskAssign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminDAO extends JpaRepository<TaskAssign, Long> {

    List<TaskAssign> findByStudentName(String studentName);

    List<TaskAssign> findByStudentNameAndProgress(String studentName, String progress);


    TaskAssign findByTaskId(Long taskId);


//    List<TaskAssign> findByStudentId(Long studentId);



}
