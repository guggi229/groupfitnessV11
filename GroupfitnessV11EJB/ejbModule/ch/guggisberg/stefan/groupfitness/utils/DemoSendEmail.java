package ch.guggisberg.stefan.groupfitness.utils;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
// https://linux.bfh.ch/services/mail/mail.html


public class DemoSendEmail {
   public static void main(String[] args) {
      //Declare recipient's & sender's e-mail id.
      String destmailid = "guggs2@bfh.ch";
      String sendrmailid = "guggs2@bfh.ch";	  
     //Mention user name and password as per your configuration
      final String uname = "guggs2";
      final String pwd = "";
      //We are using relay.jangosmtp.net for sending emails
      String smtphost = "smtp.bfh.ch";
     //Set properties and their values
      Properties propvls = new Properties();
      propvls.put("mail.smtp.auth", "true");
      propvls.put("mail.smtp.starttls.enable", "true");
      propvls.put("mail.smtp.host", smtphost);
      propvls.put("mail.smtp.port", "587");
      //Create a Session object & authenticate uid and pwd
      Session sessionobj = Session.getInstance(propvls,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(uname, pwd);
	   }
         });
 

      
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
   }
}