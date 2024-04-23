package com.example.personnel_management_system.Service;

import com.example.personnel_management_system.Repository.EmployeeRepository;
import com.example.personnel_management_system.model.Employee;
import com.example.personnel_management_system.model.TeamName;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
    // Метод поиска сотрудников по имени
    public List<Employee> searchByName(String name) {
        return employeeRepository.findByName(name);
    }
}
