package com.example.asweprj.demo.controllers;

import com.example.asweprj.demo.models.Employee;
import com.example.asweprj.demo.models.Task;
import com.example.asweprj.demo.repositories.EmployeeRepository;
import com.example.asweprj.demo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;


    @Autowired
    private EmployeeRepository employeeRepository;

        @GetMapping("/assign/{id}")
        public String showAssignForm(@PathVariable Long id, Model model) {
            Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid task ID: " + id));
            model.addAttribute("task", task);
            model.addAttribute("employees", employeeRepository.findAll());
            return "assign-task";
        }

        @PostMapping("/assign/{id}")
        public String assignTask(@PathVariable Long id, @RequestParam Long employeeId) {
            Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid task ID: " + id));
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID: " + employeeId));
            task.setEmployee(employee);
            taskRepository.save(task);
            return "redirect:/tasks/list";
        }

            @GetMapping("/create")
            public String showCreateForm(Model model) {
                model.addAttribute("task", new Task());
                return "create-task";
            }

            @PostMapping("/create")
            public String createTask(@ModelAttribute Task task) {
                taskRepository.save(task);
                return "redirect:/tasks/create?success";
            }

            @GetMapping("/list")
        public String listTasks(Model model) {
            model.addAttribute("tasks", taskRepository.findAll());
            return "task-list";
        }
        @GetMapping("/edit/{id}")
        public String editTaskForm(@PathVariable Long id, Model model) {
            Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid task ID: " + id));
            model.addAttribute("task", task);
            return "edit-task";
        }

        @PostMapping("/edit/{id}")
        public String updateTask(@PathVariable Long id, @ModelAttribute Task updatedTask) {
            Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid task ID: " + id));
            task.setDescription(updatedTask.getDescription());
            task.setStatus(updatedTask.getStatus());
            taskRepository.save(task);
            return "redirect:/tasks/list";
        }

        @GetMapping("/delete/{id}")
        public String deleteTask(@PathVariable Long id) {
            taskRepository.deleteById(id);
            return "redirect:/tasks/list";
        }

        }
