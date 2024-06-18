package com.example.personnel_management_system.Service;

import com.example.personnel_management_system.Repository.EmployeeRepository;
import com.example.personnel_management_system.model.Employee;
import com.example.personnel_management_system.model.TeamName;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    @Cacheable(value = "findById::EmployeeService", key = "#id")
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }


    @CachePut(value = "save::EmployeeService", key = "#employee.id")
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }



    // Метод поиска сотрудников по имени, игнорируя регистр (без учета camelCase)
    @Cacheable(value = "searchByName::EmployeeService", key = "#name")
    public List<Employee> searchByName(String name) {
        String lowerCaseName = name.toLowerCase(); // Преобразование имени в нижний регистр
        return employeeRepository.findByNameIgnoreCase(lowerCaseName);
    }
}
