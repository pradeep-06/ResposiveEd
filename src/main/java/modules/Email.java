package modules;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.StringUtils;

import io.quickchart.QuickChart;



public class Email {
	static PropertyManager prop=new PropertyManager("execution.properties");
	
	public static void send(String to, String from, String cc, String bcc,String subject,String body,Map<String, String> inlineImages, String password) {
		String host="smtp-mail.outlook.com";
//	    inlineImages= new HashMap<String, String>();
//		inlineImages.put("Content-Type", "image/jpeg; name=image.jpg");
//		inlineImages.put("Content-ID", "<image>");
		
		int totalTests=(CommonBean.testcasecountAPI+CommonBean.testcasecountUI);
		System.out.println("Total Tests:"+totalTests);
		int passedTestCount=CommonBean.TotalpassAPI+CommonBean.TotalpassUI;
		System.out.println("passedTestCount:"+passedTestCount);
		int failedTestCount=CommonBean.TotalfailAPI+CommonBean.TotalfailUI;
		System.out.println("failededTestCount:"+failedTestCount);
		int skippedTestCount=0;
		int successPercentage=(int) Math.round(((((double)passedTestCount/((double)totalTests))*100)));
		
		String pieChart="https://quickchart.io/chart?c={type:'pie',data:{labels:['Pass','Fail'],fillColor:'rgba(255,89,114,0.6)',datasets:[backgroundColor:['rgba(0,176,80,2.0)','rgba(255,51,51,2.0)']]}}"+"data:["+totalTests+","+failedTestCount+"]}]}}";
		String qmetryReport="";
		if(!Global.ciPipelineUrl.equals(null) && !Global.ciPipelineUrl.equals("")) {//Qmetry report url will be generated only in pipeline
			String gitURL=Global.ciPipelineUrl.replace("https://gitlab.com","");
			String url1[]=gitURL.split("/");
			qmetryReport="https://"+url1[0]+".gitlabpages.com";
			
			for(int i=1; i<=url1.length; i++) {
				if(!url1[i].equals("-")) {
					qmetryReport=qmetryReport+url1[i]+"/";
				}
				else {
					break;
				}
			}
			qmetryReport="<br>Qmetry Reports with Screenshots="+qmetryReport+"-/jobs/"+Global.ciJobid.replace("null","")+"/articrafts/dashboard.htm";//formed Qmetry url for all types of projects
		}
		
		/**Generate Piechart
		try {
			QuickChart chart = new QuickChart();
	        chart.setWidth(100);
	        chart.setHeight(100);
	        chart.setVersion("2");
	        chart.setConfig("{\r\n"
	        		+ "  type: 'pie',\r\n"
	        		+ "  data: {\r\n"
	        		+ "    datasets: [\r\n"
	        		+ "      {\r\n"
	        		+ "        data: ["+passedTestCount+", "+failedTestCount+"],\r\n"
	        		+ "        backgroundColor: ['rgb(75, 192, 192)', 'rgb(255, 99, 132)'],\r\n"
	        		+ "        label: 'test',\r\n"
	        		+ "      },\r\n"
	        		+ "    ],\r\n"
	        		+ "    labels: ['Green', 'Red'],\r\n"
	        		+ "  },\r\n"
	        		+ "}");

	        chart.toFile("target/piechart/chart.png");
		} catch (Exception e) {
			
		}**/
		
		body= "<!DOCTYPE html>"
				+"<html>"
			 	+"<head>"
			 		+"<style>"
			 			+"table, th,td {"+"padding: 1px;"+"border:1px solid #000000;"+" border-collapse:collapse;"+"width:700px;"+" text-align: center;"+" vertical-align:middle;"+"}"
					+"</style>"
				+"</head>"
				+"<body>"
					+"<table>"
						+"<tr>"+
							"<th colspan=\"5\"style=\"background-color:#6a5acd;\">"+"<font color=\"white\">Test Execution Report</font></th>"
						+"</tr>"
						+"<tr>"+
							"<td align=\"center\">Total Tests Executed</td>"
							+"<td align=\"center\">Passed</td>"
							+"<td align=\"center\">Failed</td>"
							+"<td align=\"center\">Skipped</td>"
							+"<td align=\"center\">Pass Percentage</td>"
						+"</tr>"
						+"<tr>"
							+"<td align=\"center\">" + totalTests+"</td>"
							+"<td align=\"center\">" + passedTestCount+"</td>"
							+"<td align=\"center\">" + failedTestCount+"</td>"
							+"<td align=\"center\">" + skippedTestCount+"</td>"+"<td align=\"center\">" 
							+ successPercentage+"%"+"</td>"
						+"</tr>"
						+"<tr>"+"</tr>"
					+"</table>"
				 +"</body>"
			 +"</html>";
        
	
			
		try {
			if(subject==null || subject.trim().equals("")) {
				System.out.println("email error The subject is undefined null");
				return;
			}else if(body==null || body.trim().equals("")) {
				System.out.println("email errorThe body is undefined null");
				return;
			}
			
			to=to.trim();
			from=from.trim();
			subject=subject.trim();
			body=body.trim();
			//get Qunatum project info to add to the email body
			String qcProject=Global.QC_PROJECT_NAME;  //default to the project for JMeter
			String gitProject=Global.ciProjecteName;
			String gitBranch="";
			String jobNumber=Global.ciJobid.replace("null","");
			String quantumJar="";
			String testID="";
			String runtest="";
			int iteration=1;
			String computer=StringUtils.left(String.valueOf(System.getenv().get("COMPUTERNAME")).replace("null",""),35).trim();
			String tester=System.getProperty("user.name");
			String featureFile="";
			String debug="0";
			
			if(to.equals("")) {//default to the email address of the person runing the test
				if(Global.ciUserEmail!=null && !Global.ciUserEmail.trim().equals("")) {
					to=Global.ciUserEmail;
				}
			}
			
			String gitURL=StringUtils.left(Global.ciPipelineUrl.replace("null",""),125).trim(); //125 char length in database		
			String rslt="<br><br><br>Report info:<br><br>LOB = " +qcProject+"<br>Git Project="+gitProject+"<br>Git Branch="+gitBranch+"<br>Job Number="+jobNumber+"<br>Git URL="+gitURL+"<br>Machine="+computer+
					"<br>Tester="+tester+"<br>FeatureFile="+featureFile+"<br>TestID="+testID+"<br>Test Name="+runtest+"<br>Iteration="+String.valueOf(iteration);
			
			
			Properties properties=System.getProperties();//Get system properties
			properties.setProperty("mail.smtp.host", host);//setup mail server
	        properties.put("mail.transport.protocol", "smtp");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.smtp.port", "587");
//	        Session session = Session.getDefaultInstance(properties, null);
			/* 
			 * properties.put("mail.smtp.auth","true");   properties.put("mail.smtp.starttls.enable","true");
			 */
			Session session=Session.getDefaultInstance(properties);//get a deafult session object
			MimeMessage message= new MimeMessage(session);//Create a deafult MimeMessage object
			message.setFrom(new InternetAddress(from));//set from:header eild of the header
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));//Set to:header feild of hte header
			
			if(cc!=null&&!cc.trim().equals("")) {
				message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));//set to:header eild of the header
						
			}
			if(bcc!=null&&!bcc.trim().equals("")) {
				message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(cc));//set to eild of the header
						
			}
			//set subject :header
			message.setSubject(subject);
			message.setSentDate(new Date());
			MimeBodyPart messageBodyPart=new MimeBodyPart();//created message part
			messageBodyPart.setContent(body+rslt,"text/html");
			Multipart multipart =new MimeMultipart();//created mutltplart
			multipart.addBodyPart(messageBodyPart);
		
		
			BodyPart messageBodyPart2=new MimeBodyPart();
		
	/**Adding Excel Sheet*/
		
		File excelfile=ExcelWrite.getLastModifiedFile("src\\main\\resources\\ResultStorage\\");
		String excelSource=excelfile.toString();
		System.out.println(excelfile.toString().replace("src\\main\\resources\\ResultStorage\\", ""));
		DataSource source1=new FileDataSource(excelSource);
		messageBodyPart2.setDataHandler(new DataHandler(source1));
		messageBodyPart2.setFileName(excelfile.toString().replace("src\\main\\resources\\ResultStorage\\", ""));
		multipart.addBodyPart(messageBodyPart2);
		
   /**Adding HTML report*/	
		
		BodyPart messageBodyPart3=new MimeBodyPart();
		File htmlfile=ExcelWrite.getLastModifiedFile("src\\main\\resources\\HTML_Result\\");
		String htmlSource=htmlfile.toString();	
		System.out.println(htmlfile.toString().replace("src\\main\\resources\\HTML_Result\\", ""));
		DataSource source2=new FileDataSource(htmlSource);
		messageBodyPart3.setDataHandler(new DataHandler(source2));
		messageBodyPart3.setFileName(htmlfile.toString().replace("src\\main\\resources\\HTML_Result\\", ""));
		multipart.addBodyPart(messageBodyPart3);
		
		
		
		
		if(inlineImages!=null&&inlineImages.size()>0) {
	Set<String> setImageID=inlineImages.keySet();		
		
	for(String contentid:setImageID) {
		MimeBodyPart imagePart=new MimeBodyPart();
		imagePart.setHeader("Content-ID","<"+contentid+">");
		imagePart.setDisposition(MimeBodyPart.INLINE);
		String imageFilePath=inlineImages.get(contentid);
		try {
			imagePart.attachFile(imageFilePath);
		}catch (IOException ex) {
			// TODO: handle exception
			System.out.println("Email info ,IOException ,Unable to send email to="+to+",from"+from+",subject="+subject+",body="+body+".Error:"+ex.getMessage());
		return;
		}catch (Throwable e) {
			// TODO: handle exception
			System.out.println("Email failed ,Unexpected Error attching an image ,Please contact the selenium Administrator. to="+to+",from"+from+",subject="+subject+",body="+body+".Error:"+e.getMessage());
		return;
		}
	
	multipart.addBodyPart(imagePart);
	}
		}	
	message.setContent(multipart);
	Transport.send(message, from, password);
	System.out.println("Email trigerred Succesfully");
	
}catch (AddressException e) {
	// TODO: handle exception
	System.out.println("Email failed ,AddressEception Email address missing or invalid.to , to="+to+",from"+from+",subject="+subject+",body="+body+".Error:"+e);
	return;
}catch (SendFailedException e) {//includes SMTP Send failed Exception
	// TODO: handle exception
	System.out.println("Email info ,SendFailedException unable to send the email address may be invalid.to , to="+to+",from"+from+",subject="+subject+",body="+body+".Error:"+e);
	return;
}catch (AuthenticationFailedException e) {//includes SMTP Send failed Exception
	// TODO: handle exception
	System.out.println("Email info ,AuthenticationFailedException incorrect usename or password.to , to="+to+",from"+from+",subject="+subject+",body="+body+".Error:"+e);
	return;
}catch (MessagingException e) {//includes SMTP Send failed Exception
	// TODO: handle exception
	System.out.println("Email failed  unable to send the email,inValid host address:"+host+". to="+to+",from"+from+",subject="+subject+",body="+body+".Error:"+e);
	return;
}catch (Throwable e) {
	// TODO: handle exception
	System.out.println("Email failed ,Unexpected Error,Please contact the selenium Administrator. to="+to+",from"+from+",subject="+subject+",body="+body+".Error:"+e.getMessage());
return;
}
		
		
		
		
		
	}

	

}
