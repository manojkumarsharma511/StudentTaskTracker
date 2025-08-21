package com.student.details.repository;

import com.student.details.modals.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDAO extends JpaRepository<StudentDetails, Long> {
//accessing data from database using jpql
    @Query("SELECT s FROM StudentDetails s WHERE s.studentEmailId = ?1 AND s.studentPassword = ?2" )
    StudentDetails findByStudentEmailIdAndStudentPasswordAndRole(String studentEmailId, String studentPassword);

    @Query("SELECT COUNT(s) > 0 FROM StudentDetails s WHERE s.studentEmailId = :studentEmailId")
    boolean existsByStudentEmailId(@Param("studentEmailId") String studentEmailId);

    StudentDetails findByStudentEmailId(String studentEmailId);

    StudentDetails findByStudentEmailIdAndStudentPassword(String email, String password);

//    List<StudentDetails> findAll();


}
