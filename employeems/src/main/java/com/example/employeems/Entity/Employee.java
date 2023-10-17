package com.example.employeems.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor // create constructor without argument
@Data // to define getters and setters
@Table(name = "Employee") //define table name
public class Employee {
    @Id // to define ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)//to set auto generating for id
    int empID;
    String empName;
    String empAddress;
    String empMNumber;
}
