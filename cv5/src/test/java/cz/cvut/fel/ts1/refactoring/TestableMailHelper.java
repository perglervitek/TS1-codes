package cz.cvut.fel.ts1.refactoring;

import java.util.ArrayList;
import java.util.List;

public class TestableMailHelper extends MailHelper{
    private final List<Mail> mailStorage = new ArrayList<Mail>();

    public TestableMailHelper(){
        super(new DBManager());
    }
    @Override
    public void saveMail(Mail mail){

    }
    @Override
    public void sendMail(Mail mail){

    }

    public boolean mailWasStroed(Mail mail){
        return mailStorage.contains(mail);
    }
}
