package com.example.personnel_management_system.Service;

import com.example.personnel_management_system.model.Booking;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
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
        message.setSubject("Booking Confirmation");
        message.setText(
                "Dear " + booking.getFirstName() + ",\n\n" +
                        "Thank you for booking with us. We are pleased to confirm your booking on " + booking.getBookingDate() + ".\n\n" +
                        "If you have any questions, please contact us at support@example.com.\n\n" +
                        "Best regards,\nYour Company Name"
        );

        emailService.sendEmail(message);
    }
}
