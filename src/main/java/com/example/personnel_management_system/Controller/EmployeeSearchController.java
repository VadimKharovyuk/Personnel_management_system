package com.example.personnel_management_system.Controller;

import com.example.personnel_management_system.Service.EmployeeService;
import com.example.personnel_management_system.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@Controller
public class EmployeeSearchController {
    private final EmployeeService employeeService;
    @GetMapping("/search")
    public String searchEmployees(@RequestParam("name") String name, Model model) {
        // Используем сервис для поиска сотрудников по имени
        List<Employee> employees = employeeService.searchByName(name);

        // Добавляем список найденных сотрудников в модель
        model.addAttribute("employees", employees);

        // Возвращаем представление для отображения результатов
        return "employee_search_resault"; // Имя HTML-шаблона, который будет отображать результаты
    }
}
