package com.student.details.services;

import com.student.details.modals.StudentDetails;
import com.student.details.modals.TaskAssign;
import com.student.details.repository.AdminDAO;
import com.student.details.repository.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService implements StudentInterfaceService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private AdminDAO adminDAO;


// to save provided information while signing up or registering the user
    @Override
    public StudentDetails registerStudents(StudentDetails studentDetails) {
        try {
            return studentDAO.save(studentDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Exception Handled
        }
    }

    @Override
    public TaskAssign addTask(TaskAssign taskAssign){

        TaskAssign tasksave    = adminDAO.save(taskAssign);

        return tasksave;

    }

    @Override
    public TaskAssign updateTask(TaskAssign taskAssign){
        TaskAssign updatetask = adminDAO.save(taskAssign);
        return updatetask;
    }

    @Override
    public void deleteTask(Long taskId) {
        adminDAO.deleteById(taskId);
    }

    @Override
    public List<TaskAssign> getAllTasks() {
        return adminDAO.findAll();
    }

    @Override
    public TaskAssign getDetailByTaskId(Long taskId) {
        try {
            return this.adminDAO.findByTaskId(taskId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<TaskAssign> getTasksForStudent(String studentName) {
        return adminDAO.findByStudentName(studentName);
    }

    @Override
    public void updateTaskProgress(Long taskId, String progress) {
        TaskAssign task = adminDAO.findById(taskId).orElse(null);
        if (task != null) {
            task.setProgress(progress);
            adminDAO.save(task);
        }
    }

    @Override
    public List<TaskAssign> getTasksByStudentAndStatus(String studentName, String status) {
        return adminDAO.findByStudentNameAndProgress(studentName, status);
    }

    @Override
    public List<StudentDetails> getAllStudents() {
        return studentDAO.findAll();
    }


    @Override
    public boolean changePassword(int studentId, String oldPassword, String newPassword) {
        try {
            StudentDetails student = studentDAO.findById((long) studentId).orElse(null);
            if (student != null && student.getStudentPassword().equals(oldPassword)) {
                student.setStudentPassword(newPassword);
                studentDAO.save(student);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean forgotPassword(String email, String newPassword) {
        try {
            StudentDetails student = studentDAO.findByStudentEmailId(email);
            if (student != null) {
                student.setStudentPassword(newPassword);
                studentDAO.save(student);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


//    @Override
//    public TaskAssign getTaskById(Long taskId) {
//        return adminDAO.findById(taskId).orElse(null);
//    }
//
//    @Override
//    public List<TaskAssign> getTasksForStudent(Long studentId) {
//        return adminDAO.findByStudentId(studentId);
//    }

}

