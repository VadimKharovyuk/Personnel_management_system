package com.example.personnel_management_system.Controller;
import com.example.personnel_management_system.Service.BookingService;
import com.example.personnel_management_system.Service.EmailService;
import com.example.personnel_management_system.model.Booking;
import lombok.AllArgsConstructor;
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
    private final EmailService emailService; // Подключение сервиса для отправки email


    @GetMapping("/bookings/new")
    public String showBookingForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "booking_form"; // Имя вашего HTML-шаблона
    }

    @PostMapping("/bookings")
    public String createBooking(@Validated @ModelAttribute("booking") Booking booking, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "booking_form"; // Возврат формы с ошибками
        }

        bookingService.save(booking); // Сохранение бронирования

        // Отправка электронного письма с подтверждением бронирования
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(booking.getEmail());
        message.setSubject("Booking Confirmation");
        message.setText(
                "Dear " + booking.getFirstName() + ",\n\n" +
                        "Thank you for booking with us. We are pleased to confirm your booking on " + booking.getBookingDate() + ".\n\n" +
                        "If you have any questions, please contact us at support@example.com.\n\n" +
                        "Best regards,\nYour Company Name"
        );

        emailService.sendEmail(message); // Используем новый метод для отправки SimpleMailMessage

        return "redirect:/bookings"; // Редирект после успешного сохранения
    }

    @GetMapping("/bookings")
    public String listBookings(Model model) {
        model.addAttribute("bookings", bookingService.findAll()); // Получение всех бронирований
        return "booking_list"; // Имя HTML-шаблона для отображения списка
    }

    @PostMapping("/bookings/{id}/delete")
    public String deleteBooking(@PathVariable("id") Long id) {
        bookingService.deleteById(id); // Удаление бронирования по ID
        return "redirect:/bookings";
    }
}



