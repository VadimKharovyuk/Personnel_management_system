package com.example.personnel_management_system.Controller;

import com.example.personnel_management_system.Service.EmployeeService;
import com.example.personnel_management_system.Service.TeamNameService;
import com.example.personnel_management_system.model.Employee;
import com.example.personnel_management_system.model.TeamName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final TeamNameService teamNameService;

    @PostMapping
    public String saveEmployee(@ModelAttribute Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "employee_form";  // Возвращаем форму с ошибками
        }

        // Проверяем, что `team` не `null`
        if (employee.getTeam() == null || employee.getTeam().getId() == null) {
            result.rejectValue("team", "error.employee", "Team must be selected.");
            return "employee_form";
        }

        // Получаем объект TeamName
        Optional<TeamName> teamOptional = teamNameService.findById(employee.getTeam().getId());

        if (teamOptional.isPresent()) {
            employee.setTeam(teamOptional.get());  // Извлекаем значение из Optional
        } else {
            throw new IllegalArgumentException("Team with given ID not found.");
        }

        employeeService.save(employee);  // Сохраняем сотрудника
        return "redirect:/employees/new";  // Перенаправление после успешного сохранения
    }
    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll()); // Получаем список всех сотрудников
        return "employee_list"; // Возвращаем HTML-шаблон с таблицей сотрудников
    }

    @GetMapping("/new")
    public String newEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("teamNames", teamNameService.findAll());
        return "employee_form";
    }

    @GetMapping("/edit/{id}")
    public String editEmployeeForm(@PathVariable Long id, Model model) {
        Optional<Employee> employeeOptional = employeeService.findById(id);

        if (employeeOptional.isPresent()) {
            model.addAttribute("employee", employeeOptional.get());
            model.addAttribute("teamNames", teamNameService.findAll());
            return "employee_form";
        } else {
            model.addAttribute("error", "Employee not found.");
            return "employee_list";  // Переход к списку сотрудников
        }
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
        return "redirect:/employees";  // Перенаправление после удаления
    }
}
