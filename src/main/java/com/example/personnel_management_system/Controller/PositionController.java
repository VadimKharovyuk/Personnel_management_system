package com.example.personnel_management_system.Controller;

import com.example.personnel_management_system.Service.PositionService;
import com.example.personnel_management_system.model.Position;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class PositionController {
    private  final PositionService positionService;

    @GetMapping("/positions")
    public String listPositions(Model model) {
        model.addAttribute("positions", positionService.findAll());
        return "positions";
    }

    @GetMapping("/positions/new")
    public String newPositionForm(Model model) {
        model.addAttribute("position", new Position());
        return "position_form";
    }

    @PostMapping("/positions")
    public String savePosition(@ModelAttribute Position position) {
        positionService.save(position);
        return "redirect:/positions";
    }

    @GetMapping("/positions/edit/{id}")
    public String editPositionForm(@PathVariable Long id, Model model) {
        model.addAttribute("position", positionService.findById(id));
        return "position_form";
    }

    @PostMapping("/positions/delete/{id}")
    public String deletePosition(@PathVariable Long id) {
        positionService.deleteById(id);
        return "redirect:/positions";
    }
}
