package cn.pxl.email;

import javax.mail.MessagingException;

public interface AccountEmailService {
    void sendEmail(String to,String subject,String htmlText) throws MessagingException;
}
