package com.example.asweprj.demo.controllers;

import com.example.asweprj.demo.models.Employee;
import com.example.asweprj.demo.models.Task;
import com.example.asweprj.demo.models.MenuItem; // Make sure this exists
import com.example.asweprj.demo.repositories.EmployeeRepository;
import com.example.asweprj.demo.repositories.TaskRepository;
import com.example.asweprj.demo.services.MenuService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class DashboardController {

    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;
    private final MenuService menuService; // ✅ Inject MenuService

    public DashboardController(EmployeeRepository employeeRepository, TaskRepository taskRepository, MenuService menuService) {
        this.employeeRepository = employeeRepository;
        this.taskRepository = taskRepository;
        this.menuService = menuService; // ✅ Assign it
    }

    @GetMapping("/dashboard")
    public String showManagerDashboard(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        List<Task> tasks = taskRepository.findAll();
        List<MenuItem> menuItems = menuService.getMenuItems(); // ✅ Get menu items

        model.addAttribute("employees", employees);
        model.addAttribute("tasks", tasks);
        model.addAttribute("menuItems", menuItems); // ✅ Add to model

        return "manager_dashboard";
    }
}




