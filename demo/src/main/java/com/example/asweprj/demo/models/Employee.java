package com.example.asweprj.demo.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;



@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Employee extends User {
    private int workload;
    
    @OneToMany(mappedBy = "employee")
    private List<TimeTracking> timeTrackings;
    
    @OneToOne(mappedBy = "employee")
    private Dashboard dashboard;
}
