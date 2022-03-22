/**
 *
 *  @author Dajcz Dariusz S21522
 *
 */

package zad2;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import javafx.fxml.FXML;
import retrofit2.http.Url;

public class Service {
	private static final String String = null;
	String country;
	public static double counterRate = 0;
	
String kraj = null;
	
		HashMap<String, String> cities = new HashMap<String, String>() {	
		{
			put("Warsaw", "lat=52.23&lon=21.01");
			put("London", "lat=51.50&lon=-0.11");
			put("Paris", "lat=48.86&lon=2.34");
			put("Rome", "lat=41.90&lon=12.49");
		}
	};
	
	public Service() {
	}
	
	public Service(String country) {
		this.country = country;
	}
	
	//------------------------------------------------------------------------------------------



	public Double getRateFor(String rate) throws JsonIOException, JsonSyntaxException, IOException {
		
		String exchangeUrl = "https://api.exchangerate.host/latest";
		
	
		int httpResponse = 0;
		String responseMessage = "";
		try {
			URL url = new URL(exchangeUrl);
			HttpURLConnection conection = (HttpURLConnection) url.openConnection();
			conection.setRequestProperty("accept", "application/json");
			conection.setRequestMethod("GET");
			conection.connect();
			
			 httpResponse = conection.getResponseCode();
			
			 Gson gson = new Gson();
			 
			 if(httpResponse != 200) {
				    throw new RuntimeException("Error: " + httpResponse);
				}else{
				    Scanner sc = new Scanner(url.openStream());
				    while(sc.hasNext())
				    {
				    	responseMessage+=sc.nextLine();
				    }
		}} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sformatujWaluty(responseMessage, rate);
	      
    }
	
	   public Double sformatujWaluty(String tekstNiesformatowany, String rate) {
	    	
	    	String tekstSformatowany = "";
	    	Double exchangeRate = 0.;
	    	Double countryRate = 0.;
	    	try {
	    	
	    			JSONObject jsonObj = new JSONObject(tekstNiesformatowany);
	    			
	    			if (!jsonObj.isNull("rates")) {
	    				if(rate.equals( "GBP")) {
	    					tekstSformatowany += "Funt: " + jsonObj.getJSONObject("rates").get("GBP") + "GBP";
	    					exchangeRate = (Double) jsonObj.getJSONObject("rates").get("GBP");
	    				} else if (rate.equals( "USD")) {
	    					tekstSformatowany += "Dolar: " + jsonObj.getJSONObject("rates").get("USD")+ "USD";
	    					exchangeRate = (Double) jsonObj.getJSONObject("rates").get("USD");
	    				} else if (rate.equals( "PLN")) {
	    					tekstSformatowany += "Polski Złoty: " + jsonObj.getJSONObject("rates").get("PLN")+ "PLN";
	    					exchangeRate = (Double) jsonObj.getJSONObject("rates").get("PLN");
	    				} else if (rate.equals( "EUR")) {
	    					
	    					exchangeRate = (Double) jsonObj.getJSONObject("rates").get("EUR");
	    				} else if (rate.equals( "CHF")) {
	    					tekstSformatowany += "Frank: " + jsonObj.getJSONObject("rates").get("CHF")+ "CHF" ;
	    					exchangeRate = (Double) jsonObj.getJSONObject("rates").get("CHF");
	    				} 
	    	} 
	    			
	    			
	    			if (!jsonObj.isNull("rates")) {
	    				if(country.equals( "England")) {
	    					countryRate = (Double) jsonObj.getJSONObject("rates").get("GBP");
	    				} else if (country.equals( "USA")) {
	    					countryRate = (Double) jsonObj.getJSONObject("rates").get("USD");
	    				} else if (country.equals( "Poland")) {
	    					countryRate = (Double) jsonObj.getJSONObject("rates").get("PLN");
	    				} else if (country.equals( "Italy")) {
	    					countryRate = (Double) jsonObj.getJSONObject("rates").get("EUR");
	    				} else if (country.equals( "Switzerland")) {
	    					countryRate = (Double) jsonObj.getJSONObject("rates").get("CHF");
	    				} 
	    	}

	    	} catch (Exception ex) {}
	    	
	    	return countryRate/exchangeRate;
	    }
	
	//------------------------------------------------------------------------------------------

	String getWeather(String cityy) throws IOException {
		
		
		String watherForCity = "lat=52.23&lon=21.01";
		
		String city = cities.get(cityy);
		
		if(!city.isEmpty()) {
			watherForCity = city;
		}
		
		String responseMessage = "";
	
		
		String weatherAPI = "https://api.openweathermap.org/data/2.5/weather?" ;
		String apiKey = "&appid=b9912a4fe9976e6f8750f815d9e44a8d";
		String finalURL = weatherAPI + watherForCity + apiKey;
	
		int httpResponse = 0;
		
		try {
			URL url = new URL(finalURL);
			HttpURLConnection conection = (HttpURLConnection) url.openConnection();
			conection.setRequestProperty("accept", "application/json");
			conection.setRequestMethod("GET");
			conection.connect();
			
			 httpResponse = conection.getResponseCode();
			
			 Gson gson = new Gson();
			 
			 if(httpResponse != 200) {
				    throw new RuntimeException("Error: " + httpResponse);
				}else{
				    Scanner sc = new Scanner(url.openStream());
				    while(sc.hasNext())
				    {
				    	responseMessage+=sc.nextLine();
				    }
		}} catch (ProtocolException e) {
			
			e.printStackTrace();
		}
		
		return sformatujPogode(responseMessage);
		

        
    }
	
	
	   public String sformatujPogode(String tekstNiesformatowany) {
	    	
	    	String tekstSformatowany = "";
	    	
	    	try {
	    	
	    			JSONObject jsonObj = new JSONObject(tekstNiesformatowany);
	    			
	    			if (!jsonObj.isNull("main")) {
	    				
	    				tekstSformatowany += "Temperature: " + jsonObj.getJSONObject("main").get("temp") + "F" + "\n";
	    				tekstSformatowany += "Temperature min: " + jsonObj.getJSONObject("main").get("temp_min")+ "F" + "\n";
	    				tekstSformatowany += "Temperature max: " + jsonObj.getJSONObject("main").get("temp_max")+ "F" + "\n";
	    				tekstSformatowany += "Humidity: " + jsonObj.getJSONObject("main").get("humidity") + "\n";
	    				tekstSformatowany += "Pressure: " + jsonObj.getJSONObject("main").get("pressure");
	    			}

	    	} catch (Exception ex) {}
	    	
	    	return tekstSformatowany;
	    }
	   
	   
	   
	   Double getNBPRate() throws InvocationTargetException {
			
			String[] BASE_URL = {"http://www.nbp.pl/kursy/kursya.html", "http://www.nbp.pl/kursy/kursyb.html"};
			Double exchange = null;
			
			for (int i = 0; i < 2 && exchange == null; i++) {
				
				HttpGet get = new HttpGet(BASE_URL[i]);
			
				try (CloseableHttpClient httpClient = HttpClients.createDefault(); CloseableHttpResponse response =  httpClient.execute(get);) {

					HttpEntity entity = response.getEntity();
					
					
					String exchangeRates = EntityUtils.toString(entity);
					
					int indeksKoduWaluty = exchangeRates.indexOf("USD");
					
					
					if(this.country.equals( "England")) {
						indeksKoduWaluty = exchangeRates.indexOf("GBP");
    				} else if (this.country.equals( "USA")) {
    					indeksKoduWaluty = exchangeRates.indexOf("USD");
    				} else if (this.country.equals( "Poland")) {
    					indeksKoduWaluty = exchangeRates.indexOf("PLN");
    				} else if (this.country.equals( "Italy")) {
    					indeksKoduWaluty = exchangeRates.indexOf("EUR");
    				} else if (this.country.equals( "Switzerland")) {
    					indeksKoduWaluty = exchangeRates.indexOf("CHF");
    				} 
					
					if (indeksKoduWaluty != -1 ) {
	            	
						
						int indeksKursu = exchangeRates.indexOf(">", indeksKoduWaluty + 10) + 1;
						exchange = Double.valueOf(exchangeRates.substring(indeksKursu, indeksKursu + 6).replace(',', '.'));
						this.counterRate = exchange;
						System.out.print(exchange);
					} 
				} catch (IOException e) {
	        	
					e.printStackTrace();
				} 
			}
			
			return this.counterRate;
		}

}  
