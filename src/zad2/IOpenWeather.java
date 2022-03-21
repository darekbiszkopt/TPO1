package zad2;

import java.util.List;

import net.aksingh.owmjapis.model.param.WeatherData;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IOpenWeather {
	@GET("/geo/1.0/direct")
	Call<List<GeoCoding>> geoDirect(@Query("q") String query, @Query("appid") String apiKey, @Query("limit") int limit);
	
	@GET("/data/2.5/weather")
	Call<WeatherData> weather(@Query("lat") Double lat, @Query("lon") Double lon, @Query("appid") String apiKey);
	
	@GET("/data/2.5/weather")
	Call<ResponseBody> weatherRaw(@Query("lat") Double lat, @Query("lon") Double lon, @Query("appid") String apiKey);
}