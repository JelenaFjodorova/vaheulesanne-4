/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee;


import ee.api.Forecast;
import ee.api.WeatherForecast;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


import static ee.TxtReader.inputPath;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;



public class WeatherForecastServiceTest {
    private static WeatherForecast request;
    ArrayList<String> cityList = new TxtReader().CityList();

    public WeatherForecastServiceTest() throws FileNotFoundException {
    }


    @Test
    public void testIfOfflineCurrentWeatherIsNotCriticalAndCityNameControl() throws Exception {
        for (int n = 0; n < cityList.size(); n++) {
            String cityName = cityList.get(n);
            WeatherForecastService getCurrentWeather = new WeatherForecastService();
            WeatherForecast response = getCurrentWeather.getOfflineCurrentWeather(request, cityName);
            Assert.assertNotNull(getCurrentWeather);
            assertTrue(response.getMain().getTemp() < 50 && response.getMain().getTemp() > -50);
            assertTrue(response.getName().toString().matches(cityName));
            System.out.println(response.getMain().getTemp());
            System.out.println(cityName);
        }
    }


    @Test
    public void getCurrentWeatherTest() throws Exception {
        for (int n = 0; n < cityList.size(); n++) {
            String cityName = cityList.get(n);
            WeatherForecastService service = new WeatherForecastService();
            WeatherForecast currentWeather = service.getCurrentWeather(request, cityName);
            Assert.assertNotNull(currentWeather);
        }
    }

    @Test
    public void getForecastForThreeDays() throws Exception {
        for (int n = 0; n < cityList.size(); n++) {
            String cityName = cityList.get(n);
            WeatherForecastService service = new WeatherForecastService();
            Forecast forecast = service.getForecastForThreeDays(request, cityName);
            Assert.assertNotNull(forecast);
        }
    }

    @Test
    public void getGeoCoordinates() throws Exception {
        for (int n = 0; n < cityList.size(); n++) {
            String cityName = cityList.get(n);
            WeatherForecastService service = new WeatherForecastService();
            Coordinates coordinates = service.getGeoCoordinates(cityName);
            Assert.assertNotNull(coordinates);
        }
    }
    @Test
    public void testIfCurrentTemperatureIsNotCritical() throws Exception {
        for (int n = 0; n < cityList.size(); n++) {
            String cityName = cityList.get(n);
            WeatherForecastService getCurrentWeather = new WeatherForecastService();
            WeatherForecast response = getCurrentWeather.getCurrentWeather(request, cityName);
            assertTrue(response.getMain().getTemp() < 50 && response.getMain().getTemp() > -50);
        }
    }

    @Test
    public void testIfCityNameIsCorrect() throws Exception {
        for (int n = 0; n < cityList.size(); n++) {
            String cityName = cityList.get(n);
            WeatherForecastService service = new WeatherForecastService();
            WeatherForecast response = service.getCurrentWeather(request, cityName);
            assertTrue(response.getName().toString().matches(cityName));
            System.out.println(cityName);
        }
    }
    @Test
    public void testIfCountryCodeIsCorrectLength() throws Exception {
        for (int n = 0; n < cityList.size(); n++) {
            String cityName = cityList.get(n);
            WeatherForecastService service = new WeatherForecastService();
            Forecast response = service.getForecastForThreeDays(request, cityName);
            assertTrue(response.getCity().getCountry().toString().length()==2);
            System.out.println(response.getCity().getCountry().toString());
            System.out.println(cityName);
        }

    }


}

