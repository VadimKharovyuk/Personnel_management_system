package com.example.personnel_management_system.Controller;

import com.example.personnel_management_system.Service.TeamNameService;
import com.example.personnel_management_system.model.TeamName;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/teamNames")
@AllArgsConstructor
public class TeamNameController {

    private final TeamNameService teamNameService;

    @GetMapping
    public String listTeamNames(Model model) {
        model.addAttribute("teamNames", teamNameService.findAll());
        return "teamname_list";  // Предполагается, что это шаблон списка команд
    }

    @GetMapping("/new")
    public String newTeamNameForm(Model model) {
        model.addAttribute("teamName", new TeamName());
        return "teammate_form";  // Шаблон формы команды
    }

    @PostMapping
    public String saveTeamName(@ModelAttribute TeamName teamName) {
        teamNameService.save(teamName);
        return "redirect:/teamNames";  // Перенаправляем после сохранения
    }

    @GetMapping("/{id}")
    public String editTeamNameForm(@PathVariable Long id, Model model) {
        Optional<TeamName> teamName = teamNameService.findById(id);
        if (teamName.isPresent()) {
            model.addAttribute("teamName", teamName.get());
            return "";  // Используем ту же форму для редактирования
        } else {
            return "redirect:/teamNames";  // Если команда не найдена, перенаправляем
        }
    }

    @DeleteMapping("/{id}")
    public String deleteTeamName(@PathVariable Long id) {
        teamNameService.deleteById(id);
        return "redirect:/teamNames";  // Перенаправляем после удаления
    }
}
