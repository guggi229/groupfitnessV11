package ch.guggisberg.stefan.groupfitness.sec;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@ManagedBean

public class MySetting {

	@Resource(name="java:jboss/mail/BFHMail")
	private javax.mail.Session sessionobj;
	 String sendrmailid = "guggs2@bfh.ch";	
	 String destmailid = "guggs2@bfh.ch";
	
	public String sayHello() throws MessagingException {
		 try { //message.setContent("<h1>This is actual message</h1>", "text/html");
			   //Create MimeMessage object & set values
			   Message messageobj = new MimeMessage(sessionobj);
			   messageobj.setFrom(new InternetAddress(sendrmailid));
			   messageobj.setRecipients(Message.RecipientType.TO,InternetAddress.parse(destmailid));
			   messageobj.setSubject("This is test Subject");
			   messageobj.setText("Checking sending emails by using JavaMail APIs");
			   messageobj.setContent("<h1>This is actual message</h1>", "text/html");
			  //Now send the message
			   Transport.send(messageobj);
			   System.out.println("Your email sent successfully....");
		      } catch (MessagingException exp) {
		         throw new RuntimeException(exp);
		      }
		return null;
	}
}
