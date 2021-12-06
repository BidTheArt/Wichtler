//EN: This source-code is licensend under the GNU General Public License, version 2
//DE: Dieser Sourcecode ist unter der GNU General Public Licence, Version 2
//FR: Ce code source est sous licence GNU General Public Licence, version 2
package main;

import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.Authenticator;
import jakarta.activation.*;

public class Wichtelmail {

	private Sender sender;
	String wichtlerName;
	String wichtelName;
	String mailTo;

	public Wichtelmail(Sender sender, String wichtlerName, String wichtelName, String mailTo) {
		this.sender = sender;
		this.wichtlerName = wichtlerName;
		this.wichtelName = wichtelName;
		this.mailTo = mailTo;
	}

	public void sendMail() {

		// configure SMTP server details
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", sender.getHost());
		props.put("mail.smtp.port", sender.getPort());

		Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender.getUsername(), sender.getPassword());
			}
		});

		try {
			Message wichtelmail = new MimeMessage(session);
			wichtelmail.setFrom(new InternetAddress(sender.getMailAdress()));
			wichtelmail.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
			wichtelmail.setSubject("Dein Wichtel");
			wichtelmail.setText("Hallo " + wichtlerName + "!" + "\r\n\r\nDu bewichtelst: " + wichtelName + "\r\n\r\n" + "          (+)\n"
					+ "          /=\\\n"
					+ "         / + \\\n"
					+ "        /=-*-=\\\n"
					+ "        // i \\\\\n"
					+ "       / o ° o \\ \n"
					+ "      /=*=-*-=-*\\\n"
					+ "      // + ° + \\\\\n"
					+ "     / o  \"i\"  O \\\n"
					+ "    /=*=-=-*-=-=*=\\\n"
					+ "    // °  \"i\"  ° \\\\ \n"
					+ "   / +   o ° o   + \\\n"
					+ "  /=*=-=*=-*-=*=-=*=\\\n"
					+ "         |-|~|\n"
					+ "  _.~^~._|~|-|_.~^~._\n"
					+ "========================\n"
					+ "FROHE WEIHNACHTEN\n"
					+ "wuenscht der Wichtler\n"
					+ "========================" + "\r\n\r\nWichtler ist ein FREIES Programm unter der GPLv2-Lizenz" + "\r\nEs gibt KEINERLEI Gewaehrleistung!");
			Transport.send(wichtelmail);

			System.out.println("-> E-Mail gesendet an: " + wichtlerName);

		} catch (MessagingException e) {
			System.out.println("<!> Fehler beim Senden an: " + wichtlerName);
			throw new RuntimeException(e);
		}
	}
}