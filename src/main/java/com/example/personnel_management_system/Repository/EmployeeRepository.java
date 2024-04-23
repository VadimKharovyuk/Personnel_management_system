package com.example.personnel_management_system.Repository;

import com.example.personnel_management_system.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee,Long> {
    // Метод поиска сотрудников по имени
    List<Employee> findByName(String name);

    List<Employee> findByNameIgnoreCase(String lowerCaseName);

}
