package com.example.personnel_management_system.Controller;

import com.example.personnel_management_system.Service.TeamNameService;
import com.example.personnel_management_system.model.Employee;
import com.example.personnel_management_system.model.TeamName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/teams")
@AllArgsConstructor
public class TeamController {


    private final TeamNameService teamNameService;

    @GetMapping
    public String getAllTeams(Model model) {
        List<TeamName> teams = teamNameService.findAll();
        model.addAttribute("teams", teams);
        return "teams";
    }

    @GetMapping("/{id}/employees")
    public String getEmployeesByTeamId(@PathVariable Long id, Model model) {
        List<Employee> employees = teamNameService.findEmployeesByTeamId(id);
        model.addAttribute("employees", employees);
        return "employees";
    }
}
