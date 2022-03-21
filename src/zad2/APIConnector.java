package zad2;



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public abstract class APIConnector {

	protected <T> Response<T> makeCall(Call<T> call) {
		Response<T> response = null;
		try {
			response = call.execute();
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return response;
	}
}