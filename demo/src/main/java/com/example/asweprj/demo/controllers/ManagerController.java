
package com.example.asweprj.demo.controllers;

import com.example.asweprj.demo.models.Employee;
import com.example.asweprj.demo.services.WorkloadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final WorkloadService workloadService;

    public ManagerController(WorkloadService workloadService) {
        this.workloadService = workloadService;
    }

    // Show all employees with their current workload
    @GetMapping("/view-workload")
    public String viewWorkload(Model model) {
        List<Employee> employees = workloadService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "view-workload";  // View name where you display the employee's workload
    }

    // Show the form to edit the workload of a specific employee
    @GetMapping("/edit-workload/{employeeId}")
    public String editWorkload(@PathVariable Long employeeId, Model model) {
        Employee employee = workloadService.getAllEmployees().stream()
                .filter(e -> e.getUserId().equals(employeeId))
                .findFirst()
                .orElseThrow();  // Find the employee or throw exception if not found

        model.addAttribute("employee", employee);
        return "edit-workload";  // A form to edit the employee's workload
    }

    // Save the updated workload
    @PostMapping("/update-workload/{employeeId}")
public String updateWorkload(@PathVariable Long employeeId, @RequestParam int workload) {
    workloadService.updateWorkload(employeeId, workload);  // Update the employee's workload
    return "redirect:/manager/view-workload";  // Correct redirect URL
}

}
