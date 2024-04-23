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

    @GetMapping("/teamNames/{id}")
    public String editTeamName(@PathVariable Long id, Model model) {
        // Получение информации о команде по идентификатору
        Optional<TeamName> teamName = teamNameService.findById(id);

        // Проверка, существует ли команда
        if (teamName == null) {
            return "error/404"; // Возврат страницы ошибки, если команда не найдена
        }

        model.addAttribute("teamName", teamName); // Добавление команды в модель для отображения в форме
        return "edit_teamName_form"; // Имя HTML-шаблона для редактирования команды
    }






    @RequestMapping("/{id}")
    public String deleteTeamName(@PathVariable Long id) {
        teamNameService.deleteById(id);
        return "redirect:/teamNames";  // Перенаправляем после удаления
    }
}
