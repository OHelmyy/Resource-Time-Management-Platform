package com.example.asweprj.demo.models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
// import java.util.List;
// import java.util.Set;
@Entity
@Data
@NoArgsConstructor

public class WorkLoadBalancer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long balancerId;
    private int time;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}