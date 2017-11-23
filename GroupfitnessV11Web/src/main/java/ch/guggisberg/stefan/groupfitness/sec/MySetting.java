package ch.guggisberg.stefan.groupfitness.sec;

import java.text.MessageFormat;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.mail.MessagingException;
import ch.guggisberg.stefan.groupfitness.utils.EmailManager;
import ch.guggisberg.stefan.groupfitness.utils.PropertiesExporter;

@ManagedBean

public class MySetting {

	@EJB
	EmailManager emailManager;

	
	public String sayHello() throws MessagingException {
		
		
	//	emailManager.sendEmail("guggs2@bfh.ch", "This is test Subject", "Checking sending emails by using JavaMail APIs", MessageFormat.format("<h1>Hallo {0}</h1><br>","Thomas"));

		System.out.println(PropertiesExporter.getPropertyEmailSender());

		return null;
		
		
	}
}
