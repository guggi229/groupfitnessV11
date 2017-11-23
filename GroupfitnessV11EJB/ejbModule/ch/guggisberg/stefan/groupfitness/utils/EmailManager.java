package ch.guggisberg.stefan.groupfitness.utils;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Hilfsklasse alles Rund um Email. 
 * @author guggi229
 *
 */

public class EmailManager {

	@Resource(name="java:jboss/mail/BFHMail")
	private javax.mail.Session sessionobj;
	String sendrmailid = "guggs2@bfh.ch";	
	String destmailid = "guggs2@bfh.ch";

	public String sayHello() throws MessagingException {
		try { 
			Message messageobj = new MimeMessage(sessionobj);
			messageobj.setFrom(new InternetAddress(sendrmailid));
			messageobj.setRecipients(Message.RecipientType.TO,InternetAddress.parse(destmailid));
			messageobj.setSubject("This is test Subject");
			messageobj.setText("Checking sending emails by using JavaMail APIs");
			messageobj.setContent("<h1>This is actual message</h1>", "text/html");
			Transport.send(messageobj);
			System.out.println("Your email sent successfully....");
		} catch (MessagingException exp) {
			throw new RuntimeException(exp);
		}
		return null;
	}
	public void sendEmail(String destmailid, String subject, String text, String content) {
		try { 
			Message messageobj = new MimeMessage(sessionobj);
			messageobj.setFrom(new InternetAddress(this.sendrmailid));
			messageobj.setRecipients(Message.RecipientType.TO,InternetAddress.parse(destmailid));
			messageobj.setSubject(subject);
			messageobj.setText(text);
			messageobj.setContent(content, "text/html");
			Transport.send(messageobj);
			System.out.println("Your email sent successfully....");
		} catch (MessagingException exp) {
			throw new RuntimeException(exp);
		}
	}
}
