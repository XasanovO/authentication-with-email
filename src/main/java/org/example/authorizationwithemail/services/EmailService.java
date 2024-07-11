package org.example.authorizationwithemail.services;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Async
    public void sendConfirmLink(String username, String token) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        String confirmUrl = "http://localhost:8080/api/auth/activate?token=" + token;
        String htmlContent = "<p>Click the link below to confirm your authorization:</p>" +
                "<a href=\"" + confirmUrl + "\">Confirm Authorization</a>";

        helper.setTo(username);
        helper.setSubject("Confirm authorization");
        helper.setText(htmlContent, true); // true indicates that the text is HTML

        mailSender.send(mimeMessage);
    }
}
