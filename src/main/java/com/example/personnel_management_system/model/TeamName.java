package com.example.personnel_management_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)  // Все работники этой команды
    private List<Employee> employees;  // Список работников в команде



}
