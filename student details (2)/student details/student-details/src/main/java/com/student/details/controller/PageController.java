package com.student.details.controller;

import com.student.details.modals.StudentDetails;
import com.student.details.modals.TaskAssign;
import com.student.details.repository.StudentDAO;
import com.student.details.services.StudentInterfaceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PageController {

    @Autowired
    private StudentInterfaceService studentInterfaceService;

    @Autowired
    private StudentDAO studentDAO;

    @GetMapping("/")
    public String landing() {
        return "landingAndProfilePages/landingPage";
    }

    @GetMapping("/login")
    public String login(Model model, @ModelAttribute("signupSuccess") String signupSuccess) {
        model.addAttribute("studentDetails", new StudentDetails());
        if (signupSuccess != null && !signupSuccess.isEmpty()) {
            model.addAttribute("signupSuccess", signupSuccess);
        }
        return "index";
    }


    @PostMapping("/loginForm")
    public String processLogin(@RequestParam String studentEmailId,
                               @RequestParam String studentPassword,
                               HttpSession session,
                               Model model) {
        StudentDetails studentDetails = studentDAO.findByStudentEmailIdAndStudentPasswordAndRole(studentEmailId, studentPassword);

        if (studentDetails != null && studentDAO.existsByStudentEmailId(studentDetails.getStudentEmailId())) {
            session.setAttribute("loggedInUser", studentDetails);

            if ("Admin".equals(studentDetails.getRole())) {
                return "redirect:/adminDashboard";
            } else {
                return "redirect:/studentDashboard";
            }
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "index";
        }
    }

    @GetMapping("/signup")
    public String showSignupPage(Model model, @ModelAttribute("error") String error) {
        model.addAttribute("studentDetails", new StudentDetails());
        if (error != null && !error.isEmpty()) {
            model.addAttribute("error", error);
        }
        return "signUp";
    }


    @PostMapping("/signup")
    public String processSignup(@ModelAttribute("studentDetails") StudentDetails studentDetails,
                                RedirectAttributes redirectAttributes) {
        if (studentDAO.existsByStudentEmailId(studentDetails.getStudentEmailId())) {
            redirectAttributes.addFlashAttribute("error", "User already exists!");
            return "redirect:/signup";
        } else {
            studentInterfaceService.registerStudents(studentDetails);
            redirectAttributes.addFlashAttribute("signupSuccess", "Account created successfully! Please login.");
            return "redirect:/login";
        }
    }


    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        StudentDetails studentDetails = (StudentDetails) session.getAttribute("loggedInUser");
        if (studentDetails == null) {
            return "redirect:/login";
        }
        model.addAttribute("student", studentDetails);
        return "profile"; // the profile.html Thymeleaf template
    }

    @PostMapping("/changePassword")
    @ResponseBody
    public Map<String, String> changePassword(@RequestParam int studentId,
                                              @RequestParam String oldPassword,
                                              @RequestParam String newPassword) {
        boolean success = studentInterfaceService.changePassword(studentId, oldPassword, newPassword);

        Map<String, String> response = new HashMap<>();
        if (success) {
            response.put("status", "success");
            response.put("message", "Password changed successfully!");
        } else {
            response.put("status", "error");
            response.put("message", "Old password is incorrect!");
        }
        return response;
    }

    @PostMapping("/forgotPassword")
    @ResponseBody
    public Map<String, String> forgotPassword(@RequestParam String email,
                                              @RequestParam String newPassword) {
        boolean success = studentInterfaceService.forgotPassword(email, newPassword);

        Map<String, String> response = new HashMap<>();
        if (success) {
            response.put("status", "success");
            response.put("message", "Password reset successfully!");
        } else {
            response.put("status", "error");
            response.put("message", "Email not found!");
        }
        return response;
    }



    @GetMapping("/studentDashboard")
    public String studentDashboard(@RequestParam(required = false) String status,
                                   HttpSession session, Model model) {

        StudentDetails studentDetails = (StudentDetails) session.getAttribute("loggedInUser");
        if (studentDetails == null) {
            return "redirect:/login";
        }

        List<TaskAssign> tasks;
        if (status != null && !status.isEmpty()) {
            tasks = studentInterfaceService.getTasksByStudentAndStatus(studentDetails.getStudentName(), status);
        } else {
            tasks = studentInterfaceService.getTasksForStudent(studentDetails.getStudentName());
        }

        model.addAttribute("tasks", tasks);
        model.addAttribute("student", studentDetails);
        model.addAttribute("activeStatus", status != null ? status : "All");

        return "studentDashboard";
    }


    @PostMapping("/updateStudentTaskStatus")
    public String updateTaskStatus(@RequestParam Long taskId,
                                   @RequestParam String progress) {
        studentInterfaceService.updateTaskProgress(taskId, progress);

        // Redirect back to student dashboard
        return "redirect:/studentDashboard";
    }


    @GetMapping("/adminDashboard")
    public String adminDashboard(HttpSession session, Model model) {
        StudentDetails studentDetails = (StudentDetails) session.getAttribute("loggedInUser");
        if (studentDetails != null && "Admin".equals(studentDetails.getRole())) {
            List<StudentDetails> students = studentInterfaceService.getAllStudents();
            List<TaskAssign> tasks = studentInterfaceService.getAllTasks();

            model.addAttribute("tasks", tasks);
            model.addAttribute("students", students);
            model.addAttribute("taskAssign", new TaskAssign());
            return "adminDashboard";
        } else {
            return "access-denied";
        }
    }

    @PostMapping("/assignTask")
    public String assignTask(@ModelAttribute TaskAssign taskAssign) {
        studentInterfaceService.addTask(taskAssign);
        return "redirect:/adminDashboard";
    }

    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute TaskAssign taskAssign) {
        studentInterfaceService.updateTask(taskAssign);
        return "redirect:/adminDashboard";
    }

    @PostMapping("/deleteTask")
    public String deleteTask(@RequestParam Long taskId) {
        studentInterfaceService.deleteTask(taskId);
        return "redirect:/adminDashboard";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Remove session data
        return "redirect:/";   // Send back to landing page
    }


    @GetMapping("/taskDetails/{taskId}")
    public String showTaskDetails(@PathVariable Long taskId, HttpSession session, Model model) {
        StudentDetails loggedInUser = (StudentDetails) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        TaskAssign task = studentInterfaceService.getDetailByTaskId(taskId);
        if (task == null) {
            return "error"; // or a custom 404 page
        }

        model.addAttribute("task", task);
        model.addAttribute("role", loggedInUser.getRole());

        return "taskDetails";
    }

    @PostMapping("/taskDetails/update")
    public String updateTaskDetails(@ModelAttribute("task") TaskAssign task, HttpSession session) {
        StudentDetails loggedInUser = (StudentDetails) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        if ("Admin".equals(loggedInUser.getRole())) {
            // Admin can update everything
            studentInterfaceService.updateTask(task);
            return "redirect:/adminDashboard";
        } else {
            // Student can only update progress
            TaskAssign existing = studentInterfaceService.getDetailByTaskId(task.getTaskId());
            if (existing != null) {
                existing.setProgress(task.getProgress());
                studentInterfaceService.updateTask(existing);
            }
            return "redirect:/studentDashboard";
        }
    }



}
