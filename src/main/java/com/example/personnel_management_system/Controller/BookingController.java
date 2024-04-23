package com.example.personnel_management_system.Controller;

import com.example.personnel_management_system.Service.BookingService;
import com.example.personnel_management_system.Service.TeamNameService;
import com.example.personnel_management_system.model.Booking;
import com.example.personnel_management_system.model.TeamName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

@Controller
@AllArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("/bookings/new")
    public String showBookingForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "booking_form"; // Имя вашего HTML-шаблона
    }

    @PostMapping("/bookings")
    public String createBooking(@Validated  @ModelAttribute Booking booking,BindingResult result) {
        if (result.hasErrors()) {
            return "booking_form"; // Возврат формы с ошибками
        }

        bookingService.save(booking); // Сохранение бронирования
        return "redirect:/bookings"; // Редирект после успешного сохранения
    }
    @GetMapping("/bookings")
    public String listBookings(Model model) {
        model.addAttribute("bookings", bookingService.findAll()); // Получение всех бронирований
        return "booking_list"; // Имя HTML-шаблона для отображения списка
    }
}



