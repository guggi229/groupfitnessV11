package ch.guggisberg.stefan.groupfitness.utils;

import java.text.MessageFormat;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 * Hilfsklasse alles Rund um Email. 
 * @author guggi229
 *
 */
@Stateless
public class EmailManager {

	@Resource(name="java:jboss/mail/HostfactoryMail")
	private javax.mail.Session sessionobj;
	private final String PROPERTY_EMAIL_SENDER=PropertiesExporter.getPropertyEmailSender();
	private static Logger log = Logger.getLogger(EmailManager.class);
	
	public void sendEmail(String destmailid, String subject, String text, String content) throws MessagingException, MessagingException {
			Message messageobj = new MimeMessage(sessionobj);
			messageobj.setFrom(new InternetAddress(PROPERTY_EMAIL_SENDER));	
			destmailid ="guggs2@bfh.ch"; // Damit man Testemails verwenden kann. --> Dev Filter wäre schöner
			messageobj.setRecipients(Message.RecipientType.TO,InternetAddress.parse(destmailid));
			messageobj.setSubject(subject);
			messageobj.setText(text);
			messageobj.setContent(content, "text/html");
			Transport.send(messageobj);
			log.info( MessageFormat.format("Mail an {0} versendet.", destmailid));
	}
}
