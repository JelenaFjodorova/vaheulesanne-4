package ee;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.api.Forecast;
import ee.api.WeatherForecast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class WeatherForecastService {

    public static URL url;

    public static final String API_KEY = "02de4f9608f50b67b16dbb2370997657";

    public static final String httpCurrent = "http://api.openweathermap.org/data/2.5/weather?q=";

    public static final String httpForecast = "http://api.openweathermap.org/data/2.5/forecast?q=";


    private static final ObjectMapper mapper = new ObjectMapper();


    public WeatherForecast getCurrentWeather(WeatherForecast request, String cityName) throws Exception {
        String url = appendApiKey(httpCurrent, cityName);
        WeatherForecast weatherForecast = mapper.readValue(new URL(url), WeatherForecast.class);
        return weatherForecast;
    }

    public WeatherForecast getOfflineCurrentWeather(WeatherForecast request, String cityName) throws IOException {
        FileReader in = new FileReader(System.getProperty("user.dir") + "/src/ee/" + cityName + "_weatherAPI.txt");
        BufferedReader br = new BufferedReader(in);
        String offlineApi = br.readLine();
        WeatherForecast weatherForecast = mapper.readValue(offlineApi, WeatherForecast.class);
        return weatherForecast;
    }

    public Forecast getForecastForThreeDays(WeatherForecast request, String cityName) throws Exception {
        String url = appendApiKey(httpForecast, cityName);
        Forecast forecast = mapper.readValue(new URL(url), Forecast.class);
        return forecast;

    }

    public Forecast getOfflineForecastForThreeDays(WeatherForecast request, String cityName) throws Exception {
        FileReader in = new FileReader(System.getProperty("user.dir") + "/src/ee/" + cityName + "_forecastAPI.txt");
        BufferedReader br = new BufferedReader(in);
        String offlineApi = br.readLine();
        Forecast forecast = mapper.readValue(offlineApi, Forecast.class);
        return forecast;

    }


    public Coordinates getGeoCoordinates(String cityName) throws Exception {
        String url = appendApiKey(httpCurrent, cityName);
        WeatherForecast weatherForecast = mapper.readValue(new URL(url), WeatherForecast.class);
        Coordinates coordinates = new Coordinates();
        coordinates.lat = weatherForecast.getCoord().getLat();
        coordinates.lon = weatherForecast.getCoord().getLon();
        return coordinates;
    }

    public Coordinates getOfflineGeoCoordinates(String cityName) throws Exception {
        FileReader in = new FileReader(System.getProperty("user.dir") + "/src/ee/" + cityName + "_weatherAPI.txt");
        BufferedReader br = new BufferedReader(in);
        String offlineApi = br.readLine();
        WeatherForecast weatherForecast = mapper.readValue(offlineApi, WeatherForecast.class);
        Coordinates coordinates = new Coordinates();
        coordinates.lat = weatherForecast.getCoord().getLat();
        coordinates.lon = weatherForecast.getCoord().getLon();
        return coordinates;
    }

    private String appendApiKey(String url, String cityName) {
        return url + cityName + "&units=metric" + "&appid=" + API_KEY;
    }

}
