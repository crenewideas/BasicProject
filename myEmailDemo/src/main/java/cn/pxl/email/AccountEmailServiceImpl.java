package cn.pxl.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountEmailServiceImpl implements AccountEmailService {
    private JavaMailSender javaMailSender;
    private String systemEmail;
    @Override
    public void sendEmail(String to, String subject, String htmlText) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom(systemEmail);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(htmlText,true);
        javaMailSender.send(mimeMessage);
    }
}
