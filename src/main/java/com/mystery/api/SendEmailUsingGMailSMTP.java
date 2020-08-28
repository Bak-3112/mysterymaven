package com.mystery.api;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.URLDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.beans.factory.annotation.Value;

import com.mystery.beans.MysteryShoppingVisitsBean;

public class SendEmailUsingGMailSMTP {
	
	
	ResourceBundle resource = ResourceBundle.getBundle("resources/web");
	final String dashboardURL = resource.getString("dashboardURL");
	String smtpsetting=resource.getString("smtpsetting");
	

	public void forgotPassword(String email, String link, String emailfrom,String emailusername,String emailpassword){
		
		System.out.println("link in forgotpassword"+link);
		 
		// Recipient's email ID needs to be mentioned.
		String to = email;
		
		String emailBody = "To change your password, click the below link:" +link;
		// Sender's email ID needs to be mentioned
		String from = emailfrom;// change accordingly
		final String username = emailusername; // change accordingly
		final String password = emailpassword; // change accordingly
         System.out.println("");
		String result = null;

		try {

			Properties props = System.getProperties();
			props.put("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.host", smtpsetting);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.debug", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");

			Session emailSession = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			emailSession.setDebug(true);
			Message msg = new MimeMessage(emailSession);

			msg.setFrom(new InternetAddress(username));
			InternetAddress[] toAddresses = { new InternetAddress(email) };
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject("Forgot Password");
			// msg.setSentDate(new Date());

			// creates message part
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(emailBody, "text/html");

			// creates multi-part
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// adds attachments

			// sets the multi-part as e-mail's content
			msg.setContent(multipart);

			// sends the e-mail
			Transport.send(msg);

			System.out.println("Successfully sent email");
			// result = "Successfully sent email";

		} catch (MessagingException e) {
			System.out.println("Unable to send email");
		}

	}
	
	
	public void Eymail(String email1, String url,  String encrypted_shopper_id,String emailfrom,String emailusername,String emailpassword, MysteryShoppingVisitsBean mvBean) throws Exception {
		//String email="ashlekha@codebele.com ";
		
		System.out.println("url in ey email"+url);
		String email=email1;
		String emailBody="<html>"
	    		+ "<head>"
	    		+ "<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>"
	    				+ "<meta name='viewport' content='width=device-width'>"
	    						+ "<title>Basic Email Template Subject</title>"
	    						+ "<!-- <style> -->"
	    						+ "<style>#outlook a {padding:0;}.ExternalClass {width:100% !important;}.ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font,.ExternalClass td, .ExternalClass div {line-height: 100%;}img {outline:none; text-decoration:none; -ms-interpolation-mode: bicubic; margin:0 0 0 0 !important;border:0;} a img {border:none; margin:0 0 0 0 !important;} .applelinks a {color:#222222; text-decoration: none;}.ExternalClass img[class^=Emoji] { width: 10px !important; height: 10px !important; display: inline !important; } .tpl-content { display:inline !important;}body{background-color: #ffff;font-family: arial; line-height: 1.7; color: black; font-size: 20px;}.font{font-size: 14px;font-weight: 100;color: #615252;}.img-fluid{max-width: 100%;height: 75px;vertical-align: bottom;}.font span{font-weight: 700;text-decoration: underline;color: #615252;}.font label{font-weight: 700;font-size: 15px;}.dealership{ background-color: #b0daea; font-size: 14px;font-weight: 400;padding:15px 5px 15px 5px;border-left: 1px solid black;border-top:1px solid black;border-bottom: 1px solid black;}.dealer-table{border-spacing: 0;}.cnt{border-right: 1px solid black;}  .dealer-table td{border: 1px solid black;border-right: 0;border-top:0;}.dealer-table td:last-child{border-right: 1px solid black;}ul{    padding: 0px 0 0 30px; font-size: 14px; margin: 0;color: #615252;}li span{font-weight: 600;color: #615252;}.paddingtop20{padding-top:20px;}.second-table td{font-size:14px;color: #615252;}.w-100{width:100%;}.second-table .pad{padding: 5px 15px 5px 15px;}.b-0{border-right: 0!important;}.admin td{font-size:14px; padding: 5px 15px 5px 15px;}.tq{font-size:14px;color: #615252;}.m-0{margin: -10px 0 0 0;}.first td{padding: 5px 15px 5px 15px;}</style>"
	    						+ "</head>"
	    						+ "<body style='margin:0;'><table class='body' width='100%' cellspacing='0' cellpadding='0'><tr><td class='center'  align='center' valign='middle'>"
	    						+ "<table class='body' width='100%'><tr><td class='font'><p>Hi "+mvBean.getName()+",</p>"
	    						+ "<p>Thank you for choosing to be a part of the BMW Mystery Shopping Program!</p>"
	    						+ "<p>Once enrolled, you will be first be contacted by the BMW dealership to fixing an appointment, during which you will be required to confirm a time when you will be visiting the dealership.</p>"
	    						+ "<p>  Once you complete the visit, the PMO team will contact you to take detailed feedback of your visit.</p></td></tr><tr>  "
	    						+ "<td class='font'>"
	    						+ "<span>  Please retain all documentation shared with you during the visit</span> , including any written correspondence done through your mobile phone, as we would require evidence of your feedback.<br>"
	    						+ "You can use the " + url+ "upload/" + encrypted_shopper_id+" link to upload all evidences and give us your feedback.</td></tr><tr>"
	    						+ "<td class='font paddingtop20'><label>Dealership Details:</label>"
	    						+ "<table class='dealer-table'><thead><tr>"
	    						+ "<th class='dealership' >Brand</th>"
	    						+ "<th class='dealership' >Model</th>"
	    						+ "<th class='dealership' >Dealership</th>"
	    						+ "<th class='dealership' >Address</th>"
	    						+ "<th class='dealership' >Contact Number</th>"
	    						+ "<th class='dealership' >Timings of Dealership</th>"
	    						+ "<th class='dealership' >City</th>"
	    						+ "<th class='dealership' style='white-space: nowrap;'>Visit Date</th>"
	    						+ "<th class='dealership cnt' >Mode of contact</th>"
	    						+ "</tr>"
	    						+ "</thead>"
	    						+ "<tbody>"
	    						+ "<tr class='first'>"
	    						+ "<td>"+mvBean.getBrand_name()+"</td>"
	    						+ "<td>"+mvBean.getModel_name()+"</td>"
	    						+ "<td>"+mvBean.getDealer_name()+"</td>"
	    						+ "<td>"+mvBean.getAddress()+"</td>"
	    						+ "<td>"+mvBean.getContact_number()+"</td>"
	    						+ "<td style='text-align: center;'>9:00 AM<br> to <br>9:00 PM</td>"
	    						+ "<td>"+mvBean.getCity_name()+"</td>"
	    						+ "<td style='white-space: nowrap;'>"+mvBean.getVisit_date()+"</td>"
	    						+ "<td class='cnt' style='border-right: 1px solid black;'>"+mvBean.getMode_of_contact()+"</td>"
	    						+ "</tr></tbody>"
	    						+ "</table> </td></tr><tr><td class='font paddingtop20'><label>Key Pointers to be noted during the visit:</label></tr><tr><td>"
	    						+ "<ul><li>If you are asked about trade-in of your current vehicle, please suggest that you are looking to exchange your existing (additional) car</li>"
	    						+ "<li><span>Probe about competition </span>for the target model and remember the response.</li>"
	    						+ "<li>Ask for <span>flexibility on the price or discounts </span> during offer discussion</li>"
	    						+ "<li>Ensure to <span>retain ALL documentation </span>shared by the dealership (including business card, brochure, price list, proforma invoice etc.)</li>"
	    						+ "<li><span>You need not probe for a test drive. </span>However, if offered it is not mandatory for you to take it up</li>"
	    						+ "<li>If the sales consultant <span>proposes to schedule a follow-up call,</span> do agree to do the same at a convenient time within 24hours of your visit.</li>"
	    						+ "<li>In case of a used car inquiry, please observe the state of the dealership, including whether all used cards are clearly segregated, the nameplates are as per BMW guidelines etc.</li>"
	    						+ "</ul>"
	    						+ "</td></tr><tr><td>"
	    						+ "<table class='dealer-table paddingtop20 w-100'><thead><tr><th class='dealership' >Current car</th><th class='dealership' >Year of purphase</th><th class='dealership w-100' >Timeline of new purphase</th><th class='dealership cnt w-100' >Occupation</th></tr></thead><tbody class='second-table'><tr><td class='pad'>Renault Koleos</td>"
	    						+ "<td class='pad' rowspan='8'>Example : 2015/2016/2017(depending on the car and model in discussion)</td><td class='pad' rowspan='8'>Within 1 Month</td><td class='pad cnt' style='border-right: 1px solid black;' rowspan='8'>Entrepreneur/ Professional e.g. Chartered Accountant, Lawyer, Doctor/Tax Consultant etc.</td></tr><tr>"
	    						+ "<td  class='pad b-0'>Honda CRV</td></tr><tr><td  class='pad b-0'>Mitsubishi Pajero</td></tr><tr>"
	    						+ "<td  class='pad b-0'>Toyota Land Cruiser Prado</td></tr><tr><td  class='pad b-0'>Ford Endeavour</td></tr>"
	    						+ "<tr><td  class='pad b-0'>Hyundai Santa Fe</td></tr><tr><td class='pad b-0'>Ssangyong Rexton</td></tr><tr><td  class='pad b-0'>Skoda Laura</td></tr></tbody></table></td></tr><tr><td class='font paddingtop20'><label>Visit Mandates</label></tr><tr>"
	    						+ "<td><ul>"
	    						+ "<li>You would be required to inquire about the model mentioned above during your visit.</li>"
	    						+ "<li><span>Do not disclose your EY Identity at any point. </span>Mention a different employer name/state that you are self-employed.</li>"
	    						+ "<li>Share your personal <span>email ID </span>with the dealer staff which has been <span>shared by EY Team </span>at the time of briefing call.</li>"
	    						+ "<li>Please ensure that the cab availed for reaching the dealership is a Sedan model and not a Hatchback.</li>"
	    						+ "<li>Please do not conduct the BMW visit on a Sunday or on Public Holidays.</li>"
	    						+ "<li>We would encourage you not to carry your <span>laptop </span>on the day of your visit. However, if you choose to, then please carry it inside the dealership, while conducting the visit. Please do not leave it in the car.</li>"
	    						+ "<li>Kindly ensure that your responses at the dealership are in line with the target customer segment for such luxury vehicles, a few examples are given below for your reference:</li>"
	    						+ "</ul></td></tr><tr><td class='font'>"
	    						+ "<p> In case you are unable to perform the visit on or before the aforementioned date(s) please inform us at the earliest.</p>"
	    						+ "</td></tr><tr><td class='font paddingtop20'><label>Admin related information:</label></tr><tr><td>"
	    						+ "<ul>"
	    						+ "<li>For charging expenses, use the code as applicable – For EYI resources -  41226522 – BMW Mystery Shopping (Activity code-000)</li>"
	    						+ "<li> Please find below, the details of the EY Team Member, who would be guiding you and shall be in touch with you for this Mystery Shopping visit.</li>"
	    						+ "<li>EY Team Member Contact Details:</li>"
	    						+ "</ul></td></tr><tr><td><table class='dealer-table w-100 admin'><thead><tr><th class='dealership' >Name</th>"
	    						+ "<th class='dealership cnt' >Contact details</th></tr></thead><tbody>"
	    						+ "<tr><td  class='last-table' >Yashu Gupta</td><td  class='last-table' style='border-right: 1px solid black;'>8130035848</td></tr>"
	    						+ "</tbody></table></td></tr>"
	    						+ "<tr><td class='paddingtop20'><p class='tq'>Thanks in advance for your help!</p></td></tr>"
	    						+ "<tr><td class=''><p class='tq m-0' >Please feel free to reach out to us in case of any queries.</p></td></tr><tr><td class='font'><label>Warm Regards,</label><br><label>BMW PMO team</label></td></tr></table></td></tr></table>"
	    						+ "<div style'display:none; white-space:nowrap; font:15px courier; line-height:0;'> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </div"
	    						+ "</body></html>";
		
		
		
			// Recipient's email ID needs to be mentioned.
			String to = email;

		//	String emailBody = "To take test, click the below link:" + link;
			// Sender's email ID needs to be mentioned
			String from = emailfrom;// change accordingly
			final String username = emailusername; // change accordingly
			final String password = emailpassword; // change accordingly

			String result = null;

			try {

				Properties props = System.getProperties();
				props.put("mail.smtp.starttls.enable", "true");
				props.setProperty("mail.transport.protocol", "smtp");
				props.setProperty("mail.host", smtpsetting);
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.port", "465");
				props.put("mail.debug", "true");
				props.put("mail.smtp.socketFactory.port", "465");
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.socketFactory.fallback", "false");

				Session emailSession = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
				
				
				

				emailSession.setDebug(true);
				Message msg = new MimeMessage(emailSession);

				msg.setFrom(new InternetAddress(username));
				InternetAddress[] toAddresses = { new InternetAddress(email) };
				msg.setRecipients(Message.RecipientType.TO, toAddresses);
				msg.setSubject("Welcome to BMW Mystery Shopping Program");
				// msg.setSentDate(new Date());

				// creates message part
				MimeBodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(emailBody, "text/html");

				// creates multi-part
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);

				// adds attachments
				// doTrustToCertificates();//  
				 URL url1 = new URL("https://mys.dqi.co.in:8443/Mystery_documents/uploads/documents/pdf/Declaration.pdf") ;
			     HttpURLConnection conn = (HttpURLConnection)url1.openConnection(); 
			     
				    Part attachment = new MimeBodyPart();
			        URLDataSource uds = new URLDataSource(url1);
			       attachment.setDataHandler(new DataHandler(uds));
			        attachment.setDisposition(Part.ATTACHMENT);
			        attachment.setFileName("Declaration.pdf");
			       multipart.addBodyPart((BodyPart) attachment);
		        
				
				
				// sets the multi-part as e-mail's content
				msg.setContent(multipart);

				// sends the e-mail
				Transport.send(msg);

				System.out.println("Successfully sent email");
				// result = "Successfully sent email";

			} catch (MessagingException e) {
				System.out.println("Unable to send email");
			}

		}
		
		
		


	private String URLEncoder(String string) {
		// TODO Auto-generated method stub
		return null;
	}


	public void FandFemail(String email1, String url,  String encrypted_shopper_id,String emailfrom,String emailusername,String emailpassword, MysteryShoppingVisitsBean mvBean) throws Exception {
			//String email="ashlekha@codebele.com";
		String email=email1;
		String emailBody="<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>"
	    		+ "<html xmlns='http://www.w3.org/1999/xhtml' lang='en' xml:lang='en'>"
	    		+ "<head>"
	    		+ "<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>"
	    				+ "<meta name='viewport' content='width=device-width'>"
	    						+ "<title>Basic Email Template Subject</title>"
	    						+ "<!-- <style> -->"
	    						+ "<style>#outlook a {padding:0;}.ExternalClass {width:100% !important;}.ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font,.ExternalClass td, .ExternalClass div {line-height: 100%;}img {outline:none; text-decoration:none; -ms-interpolation-mode: bicubic; margin:0 0 0 0 !important;border:0;} a img {border:none; margin:0 0 0 0 !important;} .applelinks a {color:#222222; text-decoration: none;}.ExternalClass img[class^=Emoji] { width: 10px !important; height: 10px !important; display: inline !important; } .tpl-content { display:inline !important;}body{background-color: #ffff;font-family: arial; line-height: 1.7; color: black; font-size: 20px;}.font{font-size: 14px;font-weight: 100;color: #615252;}.img-fluid{max-width: 100%;height: 75px;vertical-align: bottom;}.font span{font-weight: 700;text-decoration: underline;color: #615252;}.font label{font-weight: 700;font-size: 15px;}.dealership{ background-color: #b0daea; font-size: 14px;font-weight: 400;padding:15px 5px 15px 5px;border-left: 1px solid black;border-top:1px solid black;border-bottom: 1px solid black;}.dealer-table{border-spacing: 0;}.cnt{border-right: 1px solid black;}  .dealer-table td{border: 1px solid black;border-right: 0;border-top:0;}.dealer-table td:last-child{border-right: 1px solid black;}ul{    padding: 0px 0 0 30px; font-size: 14px; margin: 0;color: #615252;}li span{font-weight: 600;color: #615252;}.paddingtop20{padding-top:20px;}.second-table td{font-size:14px;color: #615252;}.w-100{width:100%;}.second-table .pad{padding: 5px 15px 5px 15px;}.b-0{border-right: 0!important;}.admin td{font-size:14px; padding: 5px 15px 5px 15px;}.tq{font-size:14px;color: #615252;}.m-0{margin: -10px 0 0 0;}.first td{padding: 5px 15px 5px 15px;}</style>"
	    						+ "</head>"
	    						+ "<body style='margin:0;'><table class='body' width='100%' cellspacing='0' cellpadding='0'><tr><td class='center'  align='center' valign='middle'>"
	    						+ "<table class='body' width='100%'><tr><td class='font'><p>Hi "+mvBean.getName()+",</p>"
	    						+ "<p>Thank you for choosing to be a part of the BMW Mystery Shopping Program!</p>"
	    						+ "<p>  Once you complete the visit, the PMO team will contact you to take detailed feedback of your visit.</p></td></tr><tr>  "
	    						+ "<td class='font'>"
	    						+""
	    						
	    						+ "<span>  Please retain all documentation shared with you during the visit</span> , including any written correspondence done through your mobile phone, as we would require evidence of your feedback"
	    						+"You can use the " + url+ "upload/" + encrypted_shopper_id+" link to upload all evidences and give us your feedback.</td></tr><tr>"
	    						+ "<td class='font'>Do note: you would not be required to fill any documentation.</td>"
	    						+ "</tr>"
	    						+ "<tr>"
	    						+ "<td class='font paddingtop20'><label>Dealership Details:</label>"
	    						+ "<table class='dealer-table'><thead><tr>"
	    						+ "<th class='dealership' >Brand</th>"
	    						+ "<th class='dealership' >Model</th>"
	    						+ "<th class='dealership' >Dealership</th>"
	    						+ "<th class='dealership' >Address</th>"
	    						+ "<th class='dealership' >Contact Number</th>"
	    						+ "<th class='dealership' >Timings of Dealership</th>"
	    						+ "<th class='dealership' >City</th>"
	    						+ "<th class='dealership' style='white-space: nowrap;'>Visit Date</th>"
	    						+ "<th class='dealership cnt' >Mode of contact</th>"
	    						+ "</tr>"
	    						+ "</thead>"
	    						+ "<tbody>"
	    						+ "<tr class='first'>"
	    						+ "<td>"+mvBean.getBrand_name()+"</td>"
	    						+ "<td>"+mvBean.getModel_name()+"</td>"
	    						+ "<td>"+mvBean.getDealer_name()+"</td>"
	    						+ "<td>"+mvBean.getAddress()+"</td>"
	    						+ "<td>"+mvBean.getContact_number()+"</td>"
	    						+ "<td style='text-align: center;'>9:00 AM<br> to <br>9:00 PM</td>"
	    						+ "<td>"+mvBean.getCity_name()+"</td>"
	    						+ "<td style='white-space: nowrap;'>"+mvBean.getVisit_date()+"</td>"
	    						+ "<td class='cnt' style='border-right: 1px solid black;'>"+mvBean.getMode_of_contact()+"</td>"
	    						+ "</tr></tbody>"
	    						+ "</table> </td></tr><tr><td class='font paddingtop20'><label>Key Pointers to be noted during the visit:</label></tr><tr><td>"
	    						+ "<ul><li>If you are asked about trade-in of your current vehicle, please suggest that you are looking to exchange your existing (additional) car</li>"
	    						+ "<li><span>Probe about competition </span>for the target model and remember the response.</li>"
	    						+ "<li>Ask for <span>flexibility on the price or discounts </span> during offer discussion</li>"
	    						+ "<li>Ensure to <span>retain ALL documentation </span>shared by the dealership (including business card, brochure, price list, proforma invoice etc.)</li>"
	    						+ "<li><span>You need not probe for a test drive. </span>However, if offered it is not mandatory for you to take it up</li>"
	    						+ "<li>If the sales consultant<span>proposes to schedule a follow-up call,</span> do agree to do the same at a convenient time within 24 hours of your visit.</li>"
	    						+ "<li>In case of a used car inquiry, please observe the state of the dealership, including whether there is all used cards are clearly segregated, the name plates are as per BMW guidelines etc.</li>"
	    						+ "</ul>"
	    						+ "</td></tr>"
	    						+ "<tr>"
	    						+ "<td>"
	    						+"<tr><td class='font paddingtop20'><label>Visit Mandates</label></td></tr>"
	    						//+ "<td class='font paddingtop20'><label>Visit Mandates</label></tr><tr><td><ul>"
	    						+ "<li>You would be required to inquire about the model mentioned above during your visit.</li>"
	    						+ "<li><span><u>Do not disclose your association with EY at any point</u>. </span></li>"
	    						+ "<li>Share your personal <span>email ID </span>with the dealer staff which has been <span>shared by EY Team </span>at the time of briefing call.</li>"
	    						+ "<li>Please ensure that the cab availed for reaching the dealership is a Sedan model and not a Hatchback.</li>"
	    						+ "<li>Please do not conduct the BMW visit on a Sunday or on Public Holidays.</li>"
	    						+ "<li>Kindly ensure that your responses at the dealership are in line with the target customer segment for such luxury vehicles, a few examples are given below for your reference:</li>"
	    						+ "</ul></td></tr><tr><td class='font'>"
	    						+ "<p> In case you are unable to perform the visit on or before the aforementioned date(s) please inform us at the earliest.</p>"
	    						+ "</td></tr><tr><td class='font paddingtop20'><label>Admin related information:</label></tr><tr><td>"
	    						+ "<ul>"
	    						+ "<p>Please find below, the details of the EY Team Member, who would be guiding you and shall be in touch with you for this Mystery Shopping visit. EY Team Member Contact Details:</p>"
	    						+ "</ul></td></tr><tr><td><table class='dealer-table w-100 admin'><thead><tr><th class='dealership' >Name</th>"
	    						+ "<th class='dealership cnt' >Contact details</th></tr></thead><tbody>"
	    						+ "<tr><td  class='last-table' >Yashu Gupta</td><td  class='last-table' style='border-right: 1px solid black;'>8130035848</td></tr>"
	    						+ "</tbody></table></td></tr>"
	    						+ "<tr><td class='paddingtop20'><p class='tq'>Thanks in advance for your help!</p></td></tr>"
	    						+ "<tr><td class=''><p class='tq m-0' >Please feel free to reach out to us in case of any queries.</p></td></tr><tr><td class='font'><label>Warm Regards,</label><br><label>BMW PMO team</label></td></tr></table></td></tr></table>"
	    						+ "<div style'display:none; white-space:nowrap; font:15px courier; line-height:0;'> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </div>"
	    						+ "</body></html>";
		
		
		
		
			// Recipient's email ID needs to be mentioned.
			String to = email;

		//	String emailBody = "To take test, click the below link:" + link;
			// Sender's email ID needs to be mentioned
			String from = emailfrom;// change accordingly
			final String username = emailusername; // change accordingly
			final String password = emailpassword; // change accordingly

			String result = null;

			try {

				Properties props = System.getProperties();
				props.put("mail.smtp.starttls.enable", "true");
				props.setProperty("mail.transport.protocol", "smtp");
				props.setProperty("mail.host", smtpsetting);
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.port", "465");
				props.put("mail.debug", "true");
				props.put("mail.smtp.socketFactory.port", "465");
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.socketFactory.fallback", "false");

				Session emailSession = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

				emailSession.setDebug(true);
				Message msg = new MimeMessage(emailSession);

				msg.setFrom(new InternetAddress(username));
				InternetAddress[] toAddresses = { new InternetAddress(email) };
				msg.setRecipients(Message.RecipientType.TO, toAddresses);
				msg.setSubject("Welcome to BMW Mystery Shopping Program");
				// msg.setSentDate(new Date());

				// creates message part
				MimeBodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(emailBody, "text/html");

				// creates multi-part
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);

				// adds attachments
				

				// doTrustToCertificates();//  
				 URL url1 = new URL("https://mys.dqi.co.in:8443/Mystery_documents/uploads/documents/pdf/Declaration.pdf") ;
			     HttpURLConnection conn = (HttpURLConnection)url1.openConnection(); 
			     
				    Part attachment = new MimeBodyPart();
				    
			        URLDataSource uds = new URLDataSource(url1);
			       attachment.setDataHandler(new DataHandler(uds));
			        attachment.setDisposition(Part.ATTACHMENT);
			        attachment.setFileName("Declaration.pdf");
			       multipart.addBodyPart((BodyPart) attachment);
				
				
				
				
				//Part attachment = new MimeBodyPart();
		       // URL url1 = new URL("https://mys.dqi.co.in:8443/Mystery_documents/uploads/documents/pdf/Declaration.pdf");
		      //  URLDataSource uds = new URLDataSource(url1);
		       // attachment.setDataHandler(new DataHandler(uds));
		       // attachment.setDisposition(Part.ATTACHMENT);
		       // attachment.setFileName("Declaration.pdf");
		      // multipart.addBodyPart((BodyPart) attachment);
				 
			
		        
		        

				// sets the multi-part as e-mail's content
				msg.setContent(multipart);

				// sends the e-mail
				Transport.send(msg);

				System.out.println("Successfully sent email");
				// result = "Successfully sent email";

			} catch (MessagingException e) {
				System.out.println("Unable to send email");
			}

		}
	
	public void doTrustToCertificates() throws Exception {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                        return;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                        return;
                    }
                }
        };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                if (!urlHostName.equalsIgnoreCase(session.getPeerHost())) {
                    System.out.println("Warning: URL host '" + urlHostName + "' is different to SSLSession host '" + session.getPeerHost() + "'.");
                }
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }
	

}
