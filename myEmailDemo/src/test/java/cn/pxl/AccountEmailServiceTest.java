package cn.pxl;

import cn.pxl.email.AccountEmailServiceImpl;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountEmailServiceTest {
    private GreenMail greenMail;
    @Before
    public void startMailServer(){
        greenMail = new GreenMail(ServerSetup.SMTP);
        greenMail.setUser("crenewideas@163.com","pengxiaoliang.0");
        greenMail.start();
    }

    @Test
    public void testSendEmail() throws MessagingException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("account-email.xml");
        AccountEmailServiceImpl accountEmailServiceImpl = (AccountEmailServiceImpl) classPathXmlApplicationContext.getBean("accountEmailServiceImpl");
        String subject = "Test Subject";
        String htmlText = "<h3>Test</h3>";
        accountEmailServiceImpl.sendEmail("crenewideas@163.com",subject,htmlText);
        greenMail.waitForIncomingEmail(2000,1);
        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
        assertEquals(1,receivedMessages.length);
    }

    @After
    public void stopMailServer(){
        greenMail.stop();
    }
}
