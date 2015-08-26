package com.netas.avaya.ipps;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netas.avaya.AbstractBaseServlet;

@Component("/exchangeRates.wml")
public class ExchangeRatesServlet extends AbstractBaseServlet {

	@Override
	public String getServletInfo() {
		return "</exchangeRates.wml>servlet";
	}
	
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
		Date now = new Date();
		
		response.setContentType("text/vnd.wap.wml");
		String wml = readAllText("/exchangeRates-wml.xml").replace("/_DATE_/", now.toString());
		
		RestTemplate restTemp = new RestTemplate();
		String xrates = restTemp.getForObject("http://www.tcmb.gov.tr/kurlar/today.xml", String.class);
		
		String strippedRates = xrates.replace("\r", " ").replace("\n", "");
		int sPos = strippedRates.indexOf("<CurrencyName>US DOLLAR</CurrencyName>");
		int ePos = strippedRates.indexOf("<CrossRateUSD>", sPos);
		xrates = strippedRates.substring(sPos, ePos)
				.replace("CurrencyName", "p")
				.replace("<ForexBuying>", "<p>FxBuy/Sell ").replace("</ForexBuying>", "")
				.replace("<ForexSelling>", " / ").replace("</ForexSelling>", "</p>")
				.replace("<BanknoteBuying>", "<p>BnBuy/Sell ").replace("</BanknoteBuying>", "")
				.replace("<BanknoteSelling>", " / ").replace("</BanknoteSelling>", "</p>");
		
		sPos = strippedRates.indexOf("<CurrencyName>EURO</CurrencyName>");
		ePos = strippedRates.indexOf("<CrossRateUSD>", sPos);
		xrates += strippedRates.substring(sPos, ePos)
				.replace("CurrencyName", "p")
				.replace("<ForexBuying>", "<p>FxBuy/Sell ").replace("</ForexBuying>", "")
				.replace("<ForexSelling>", " / ").replace("</ForexSelling>", "</p>")
				.replace("<BanknoteBuying>", "<p>BnBuy/Sell ").replace("</BanknoteBuying>", "")
				.replace("<BanknoteSelling>", " / ").replace("</BanknoteSelling>", "</p>");
		
		sb.append(wml.replace("/_XR_/", xrates));
		
		response.getOutputStream().println(sb.toString());
	}

	
	
}
