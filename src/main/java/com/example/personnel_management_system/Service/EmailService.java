package com.example.personnel_management_system.Service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;





@Service
@AllArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage(); // Создаем объект SimpleMailMessage
        message.setTo(to); // Устанавливаем получателя
        message.setSubject(subject); // Устанавливаем тему
        message.setText(text); // Устанавливаем текст

        mailSender.send(message); // Отправляем сообщение через mailSender
    }

    public void sendEmail(SimpleMailMessage message) {
        mailSender.send(message); // Новый метод для отправки SimpleMailMessage
    }
}

