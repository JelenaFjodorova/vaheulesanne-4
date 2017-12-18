package ee;

import ee.api.Forecast;
import ee.api.List;
import ee.api.WeatherForecast;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Runner {
    public static void main(String[] args) throws Exception {
        TxtWriter writer = new TxtWriter();

        //Write forecast for city online
        //writer.ForecastWriter();

        //Write forecast api
        writer.OutputForecastApiWriter();

        //Write weather api
        writer.OutputWeatherApiWriter();

        //Write forecast for city offline
        //writer.OfflineForecastWriter();
    }
}


