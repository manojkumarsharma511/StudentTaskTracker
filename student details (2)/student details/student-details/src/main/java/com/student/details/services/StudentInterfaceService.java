package com.student.details.services;

import com.student.details.modals.StudentDetails;
import com.student.details.modals.TaskAssign;

import java.util.List;

public interface StudentInterfaceService {
    StudentDetails registerStudents(StudentDetails studentDetails);

    TaskAssign addTask(TaskAssign taskAssign);

    TaskAssign updateTask(TaskAssign taskAssign);

    void deleteTask(Long taskId);

    List<TaskAssign> getAllTasks();

    TaskAssign getDetailByTaskId(Long taskId);

    List<TaskAssign> getTasksForStudent(String studentName);

    List<TaskAssign> getTasksByStudentAndStatus(String studentName, String status);

    void updateTaskProgress(Long taskId, String progress);

    List<StudentDetails> getAllStudents();

    boolean changePassword(int studentId, String oldPassword, String newPassword);

    boolean forgotPassword(String email, String newPassword);


//
//    TaskAssign getTaskById(Long taskId);
//
//    List<TaskAssign> getTasksForStudent(Long studentId);


}
