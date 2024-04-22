package com.example.personnel_management_system.Controller;

import com.example.personnel_management_system.Service.EmployeeService;
import com.example.personnel_management_system.Service.PositionService;
import com.example.personnel_management_system.model.Employee;
import com.example.personnel_management_system.model.Position;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;
    private final PositionService positionService ;

    @GetMapping("/employees")
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.findAll();

        // Проверка данных перед отправкой в шаблон
        for (Employee employee : employees) {
            if (employee.getPosition() == null) {
                System.out.println("Employee ID " + employee.getId() + " has no position assigned.");
            }
        }

        model.addAttribute("employees", employees);
        return "employees";
    }


    // Метод для отображения формы добавления нового сотрудника
    @GetMapping("/employees/new")
    public String newEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee()); // Новый объект Employee
        List<Position> positions = positionService.findAll(); // Получаем все позиции
        model.addAttribute("positions", positions); // Передаем список позиций в модель
        return "employee_form"; // Имя шаблона
    }

    // Метод для обработки POST-запроса на добавление сотрудника
    @PostMapping("/employees-add")
    public String saveEmployee(Employee employee) { // Spring привяжет данные формы к объекту Employee
        employeeService.save(employee); // Сохраняем сотрудника
        return "redirect:/employees"; // Перенаправляем на список сотрудников
    }

    @GetMapping("/employees/edit/{id}")
    public String editEmployeeForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        return "employee_form";
    }

    @PostMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
        return "redirect:/employees";
    }
}
