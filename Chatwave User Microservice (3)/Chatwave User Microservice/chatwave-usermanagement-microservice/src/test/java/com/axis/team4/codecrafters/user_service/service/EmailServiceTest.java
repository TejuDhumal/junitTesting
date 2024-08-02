package com.axis.team4.codecrafters.user_service.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

//@Disabled
class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    @Value("${spring.mail.username}")
    private String fromAddress = "test@domain.com";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
//        emailService = new EmailService();
//        emailService.mailSender = mailSender;
        emailService.fromAddress = fromAddress;
    }

    @Test
    void sendOtp() throws javax.mail.MessagingException, MessagingException {
        String to = "test@example.com";
        String otp = "123456";
        MimeMessage mimeMessage = mock(MimeMessage.class);

        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        emailService.sendOtp(to, otp);

        verify(mailSender, times(1)).send(mimeMessage);
    }

    @Test
    void sendResetPasswordOtp() throws MessagingException, jakarta.mail.MessagingException, javax.mail.MessagingException {
        String to = "test@example.com";
        String otp = "123456";
        MimeMessage mimeMessage = mock(MimeMessage.class);

        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        emailService.sendResetPasswordOtp(to, otp);

        verify(mailSender, times(1)).send(mimeMessage);
    }

    @Test
    void sendReactivateOtp() throws MessagingException, jakarta.mail.MessagingException, javax.mail.MessagingException {
        String to = "test@example.com";
        String otp = "123456";
        MimeMessage mimeMessage = mock(MimeMessage.class);

        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        emailService.sendReactivateOtp(to, otp);

        verify(mailSender, times(1)).send(mimeMessage);
    }

//    @Test
//    void sendEmail() throws MessagingException, jakarta.mail.MessagingException {
//        String to = "test@example.com";
//        String otp = "123456";
//        String subject = "Test Subject";
//        String htmlContent = "<html><body>Test Content</body></html>";
//        MimeMessage mimeMessage = mock(MimeMessage.class);
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
//
//        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
//
//        emailService.sendEmail(to, otp, subject, htmlContent);
//
//        verify(mailSender, times(1)).send(mimeMessage);
//    }
}