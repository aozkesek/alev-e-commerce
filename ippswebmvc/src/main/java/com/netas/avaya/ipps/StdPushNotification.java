package com.netas.avaya.ipps;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StdPushNotification {
	
	static String pushToplineData = 
			"<?xml version=\"1.0\"?>"
			+ "\n\n<Push alert=\"2\" type=\"topline\" mode=\"barge\">"
			+ "\n<go href=\"http://10.254.63.12:80/ippswebmvc/pushTopline.wml\" method=\"get\">"
			+ "\n<postfield name=\"notification\" value=\"$NOTIFICATION\"/>"
			+ "\n</go>"
			+ "\n</Push>";

	static String pushDisplayData = 
			"<?xml version=\"1.0\"?>"
			+ "\n\n<Push alert=\"2\" type=\"display\" mode=\"barge\">"
			+ "\n<go href=\"http://10.254.63.12:80/ippswebmvc/$PUSHDISPLAY\" method=\"get\">"
			+ "\n<postfield name=\"queryString\" value=\"$QUERYSTRING\"/>"
			+ "\n</go>"
			+ "\n</Push>";

	@Scheduled(fixedRate=1000*60*2,initialDelay=1000*3)
	public void pushTopline() {
		
		
		try {
			String postString = pushToplineData
					.replace("$NOTIFICATION", URLEncoder.encode("<Topline>topline push - message</Topline>", "UTF-8"));
			
			postString = "XMLData=" + URLEncoder.encode(postString, "UTF-8");
			postData("47.168.249.5:80", postString.getBytes());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Scheduled(fixedRate=1000*60*3,initialDelay=1000*6)
	public void pushNotificationDisplay() {
		
		
		try {
			String postString = pushDisplayData
					.replace("$PUSHDISPLAY", "exchangeRates.wml")
					.replace("$QUERYSTRING", URLEncoder.encode("title=Exhange Rates", "UTF-8"));
			
			postString = "XMLData=" + URLEncoder.encode(postString, "UTF-8");
			postData("47.168.249.5:80", postString.getBytes());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
	}
	
	protected void postData(String phoneIp, byte[] postBytes) {
		
		HttpURLConnection httpConn = null;
		
		try {
			URL phoneUrl = new URL("http://" + phoneIp + "/forms/push");
			httpConn = (HttpURLConnection) phoneUrl.openConnection();
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setRequestProperty("Content-Lenght", Integer.toString(postBytes.length));
			httpConn.getOutputStream().write(postBytes);
			
			byte[] responseMessage = new byte[httpConn.getInputStream().available()];
			httpConn.getInputStream().read(responseMessage);
			
			int httpResponseCode = httpConn.getResponseCode();
			String avayaPushStatus = httpConn.getHeaderField("x-Avaya-Push-Status");
			
			System.out.println(new String(postBytes));
			System.out.println(new String(responseMessage));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (httpConn != null)
				httpConn.disconnect();
		}
	}
}
