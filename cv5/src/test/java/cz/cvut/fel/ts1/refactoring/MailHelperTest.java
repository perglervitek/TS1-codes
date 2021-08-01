package cz.cvut.fel.ts1.refactoring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions*;


public class MailHelperTest {
    @Test
    public void createAndSendMail_validInput(){
        //ararnge
        TestableMailHelper helper = new TestableMailHelper();
        String to = "to@fel.cvut.cz";
        String subject = "subject test";
        String body = "body test";
        int expectedSendEmailCount = 1;
        Mail excpectedMail = new Mail();
        excpectedMail.setTo(to);
        excpectedMail.setSubject(subject);
        excpectedMail.setBody(body);
        //act
        helper.createAndSendMail(to,subject,body);
        //assert
        assertTrue(helper.mailWasStored(expectedMail));
    }
}
