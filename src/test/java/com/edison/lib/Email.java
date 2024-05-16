package com.edison.lib;

import java.util.HashMap;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.poi.xddf.usermodel.XDDFEffectContainer;

public class Email {
	
	public static void main(String[]args) {
		
		
		 String from = "qateamadvancetesting@sapphirus.in";
         String to = "prashanthi.k@sapphirus.in,testingtrainee@sapphirus.in,suresh.r@sapphirus.in,deepthi.y@sapphirus.in,"
         		+ "srinivas.t@sapphirus.in,pakkirareddy.y@sapphirus.in";
//         String subject = ".....Wishes.....";
//         String text = ".....Wish you a many more happy return of the day......";
//
//         // A properties to store mail server smtp information such as the host
//         // name and the port number. With this properties we create a Session
//         // object from which we'll create the Message object.
//         
         Properties properties = new Properties();
         properties.put("mail.smtp.host", "smtp-mail.outlook.com");
         properties.put("mail.transport.protocol", "smtp");
         properties.put("mail.smtp.starttls.enable", "true");
         properties.put("mail.smtp.port", "587");
         Session session = Session.getDefaultInstance(properties, null);

         try {
             // Message is a mail msg to be send through the Transport object.
             // In the Message object we set the sender address and the
             // recipient address. Both of this address is a type of
             // InternetAddress. For the recipient address we can also set the
             // type of recipient, the value can be TO, CC or BCC. In the next
             // two lines we set the email subject and the content text.
        	 
//             Message msg = new MimeMessage(session);
//             msg.setFrom(new InternetAddress(from));
//             msg.setRecipients(Message.RecipientType.TO,  InternetAddress.parse(to));
//             msg.setSubject(subject);
//             msg.setText(text);
//
//             // Send the msg to the recipient.
//             Transport.send(msg, from, "Pradeep@06$06");
         }catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
