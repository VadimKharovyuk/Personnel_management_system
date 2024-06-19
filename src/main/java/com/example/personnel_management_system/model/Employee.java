package com.example.personnel_management_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Автоматически генерируемый идентификатор
    private Long id;

    private String name;
    private String lastname;
    private double salary;
    private String hireDate;
    private String position;
    private String phone;  // Может быть null

    @ManyToOne(cascade = CascadeType.PERSIST) // Изменим каскадирование, если необходимо
    @JoinColumn(name = "team_id")
    @JsonIgnore  // игнорируем сериализацию team при выводе в JSON
    private TeamName team;
    }