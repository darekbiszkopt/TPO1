/**
 *
 *  @author Dajcz Dariusz S21522
 *
 */

package zad2;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

import com.google.gson.Gson;

import javafx.fxml.FXML;
import retrofit2.http.Url;

public class Service {
	private static final String String = null;
	String country;
	
	public Service() {
	}
	
	public Service(String country) {
		this.country = country;
	}
	
	//------------------------------------------------------------------------------------------


	public String getWeather(String string) {
	
		return "In Warsaw is rainy";
	}

	public Double getRateFor(String string) {
		return 4.5;
	}
	
	
	//------------------------------------------------------------------------------------------

	String GetTodaysWeatherInformation() throws IOException {
		
		
		String weatherAPI = "https://api.openweathermap.org/data/2.5/weather?lat=52.23&lon=21.01&appid=b9912a4fe9976e6f8750f815d9e44a8d";
		
	
		int httpResponse = 0;
		String responseMessage = "";
		try {
			URL url = new URL(weatherAPI);
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
		 System.out.print(responseMessage);
		return sformatujPogode(responseMessage);
		

		
//        APIConnector apiConnectorWeather = new APIConnector(weatherAPI);
//        
//        System.out.println(apiConnectorWeather.toString());
//
//        JSONObject weatherJSONObject = apiConnectorWeather.getJSONObject(weatherAPI);
//        
//        System.out.println(weatherJSONObject);
//
//        JSONArray weatherArray = (JSONArray) weatherJSONObject.get("consolidated_weather");
        
    }
	
	   public String sformatujPogode(String tekstNiesformatowany) {
	    	
	    	String tekstSformatowany = "";
	    	
	    	try {
	    	
	    			JSONObject jsonObj = new JSONObject(tekstNiesformatowany);
	    			
	    			if (!jsonObj.isNull("main")) {
	    				
	    				tekstSformatowany += "Temperatura: " + jsonObj.getJSONObject("main").get("temp") + "F" + "\n";
	    				tekstSformatowany += "Temperatura minimalna: " + jsonObj.getJSONObject("main").get("temp_min")+ "F" + "\n";
	    				tekstSformatowany += "Temperatura maksymalna: " + jsonObj.getJSONObject("main").get("temp_max")+ "F" + "\n";
	    				tekstSformatowany += "Wilgotność: " + jsonObj.getJSONObject("main").get("humidity") + "\n";
	    				tekstSformatowany += "Ciœnienie: " + jsonObj.getJSONObject("main").get("pressure");
	    			}

	    	} catch (Exception ex) {}
	    	
	    	return tekstSformatowany;
	    }

	public Double getNBPRate() {
		// TODO Auto-generated method stub
		return 55.55;
	}
}  
