package com.example.personnel_management_system.Controller;
import com.example.personnel_management_system.Service.BookingService;
import com.example.personnel_management_system.Service.EmailService;
import com.example.personnel_management_system.model.Booking;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private RabbitTemplate rabbitTemplate;


    @GetMapping("/bookings/new")
    public String showBookingForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "booking_form"; // Имя вашего HTML-шаблона
    }

    @PostMapping("/bookings")
    public String createBooking(@Validated @ModelAttribute("booking") Booking booking, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "booking_form";
        }

        bookingService.save(booking);

        // Отправка сообщения в очередь RabbitMQ
        rabbitTemplate.convertAndSend("bookingQueue", booking);

        return "redirect:/bookings";
    }

    @GetMapping("/bookings")
    public String listBookings(Model model) {
        model.addAttribute("bookings", bookingService.findAll()); // Получение всех бронирований
        return "booking_list";
    }

    @PostMapping("/bookings/{id}/delete")
    public String deleteBooking(@PathVariable("id") Long id) {
        bookingService.deleteById(id);
        return "redirect:/bookings";
    }
}



