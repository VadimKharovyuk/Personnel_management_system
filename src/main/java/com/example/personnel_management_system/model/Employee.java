package com.example.personnel_management_system.model;

import jakarta.persistence.*;  // Импорт всех необходимых аннотаций
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Автоматически генерируемый идентификатор
    private Long id;

    private String name;
    private String lastname;
    private double salary;
    private String hireDate;
    private String position;
    private String phone;  // Может быть null

    @ManyToOne(cascade = CascadeType.ALL)  // Если команда удаляется, значение становится null
    @JoinColumn(name = "team_id")  // Имя столбца для связи с TeamName
    private TeamName team;  // Ссылка на команду
}