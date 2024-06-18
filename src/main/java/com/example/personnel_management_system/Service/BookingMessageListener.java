package com.example.personnel_management_system.Service;
import com.example.personnel_management_system.model.Booking;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingMessageListener {
    private final EmailService emailService;

    @RabbitListener(queues = "bookingQueue")
    public void receiveMessage(Booking booking) {
        // Отправка электронного письма с подтверждением бронирования
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(booking.getEmail());
        message.setSubject("Подтверждение бронирования");
        message.setText(
                "Уважаемый(ая) " + booking.getFirstName() + ",\n\n" +
                        "Спасибо за ваше бронирование. Мы рады подтвердить ваше бронирование на " + booking.getBookingDate() + ".\n\n" +
                        "Если у вас есть вопросы, пожалуйста, свяжитесь с нами по адресу support@example.com.\n\n" +
                        "С наилучшими пожеланиями,\nВаша компания"
        );

        emailService.sendEmail(message);
    }
}
