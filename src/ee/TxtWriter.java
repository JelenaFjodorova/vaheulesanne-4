package ee;

import ee.api.Forecast;
import ee.api.List;
import ee.api.WeatherForecast;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class TxtWriter {

    String outputPath;

    public String ForecastWriter() throws Exception {

        ArrayList<String> cityList = new TxtReader().CityList();
        for (int n = 0; n < cityList.size(); n++) {
            String cityName = cityList.get(n);
            WeatherForecastService service = new WeatherForecastService();
            WeatherForecast request = new WeatherForecast();
            WeatherForecast currentWeather = service.getCurrentWeather(request, cityName);
            Forecast forecast = service.getForecastForThreeDays(request, cityName);

            LocalDateTime currentDate = LocalDateTime.now();
            double firstDayMaxTemp = -100;
            double firstDayMinTemp = 100;
            LocalDate firstDayDate = null;
            double secondDayMaxTemp = -100;
            double secondDayMinTemp = 100;
            LocalDate secondDayDate = null;
            double thirdDayMaxTemp = -100;
            double thirdDayMinTemp = 100;
            LocalDate thirdDayDate = null;
            for (List list : forecast.getList()) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(list.getDtTxt(), formatter);

                if (dateTime.getDayOfMonth() == currentDate.getDayOfMonth()) {
                    continue;
                }

                if (dateTime.getDayOfMonth() == currentDate.getDayOfMonth() + 1) {
                    if (list.getMain().getTempMax() > firstDayMaxTemp) {
                        firstDayMaxTemp = list.getMain().getTempMax();

                    }
                    if (list.getMain().getTempMin() < firstDayMaxTemp) {
                        firstDayMinTemp = list.getMain().getTempMin();
                    }
                    firstDayDate = LocalDate.from(dateTime);

                }

                if (dateTime.getDayOfMonth() == currentDate.getDayOfMonth() + 2) {
                    if (list.getMain().getTempMax() > secondDayMaxTemp) {
                        secondDayMaxTemp = list.getMain().getTempMax();

                    }
                    if (list.getMain().getTempMin() < secondDayMaxTemp) {
                        secondDayMinTemp = list.getMain().getTempMin();
                    }
                    secondDayDate = LocalDate.from(dateTime);

                }

                if (dateTime.getDayOfMonth() == currentDate.getDayOfMonth() + 3) {
                    if (list.getMain().getTempMax() > thirdDayMaxTemp) {
                        thirdDayMaxTemp = list.getMain().getTempMax();

                    }
                    if (list.getMain().getTempMin() < thirdDayMinTemp) {
                        thirdDayMinTemp = list.getMain().getTempMin();
                    }
                    thirdDayDate = LocalDate.from(dateTime);

                }


            }

            outputPath = (System.getProperty("user.dir") + "//src//ee//" + cityName + ".txt");

            BufferedWriter bw = null;
            FileWriter fw = null;

            try {

                Coordinates coordinates = service.getGeoCoordinates(cityName);
                String content = (
                        ("Today is: " + currentDate) + "\r" +
                                ("Сurrent temperature is: " + currentWeather.getMain().getTemp()) + "\n" +
                                ("Forecast for three days: ") + "\r" +
                                ("The date is " + firstDayDate.toString() + " Maximum temperature is: " + firstDayMaxTemp +
                                        " Minimum temperature is: " + firstDayMinTemp) + "\n" +
                                ("The date is " + secondDayDate.toString() + " Maximum temperature is: " + secondDayMaxTemp +
                                        " Minimum temperature is: " + secondDayMinTemp) + "\n" +
                                ("The date is " + thirdDayDate.toString() + " Maximum temperature is: " + thirdDayMaxTemp +
                                        " Minimum temperature is: " + thirdDayMinTemp) + "\n" +
                                ("Latitude: " + coordinates.lat + " Longitude: " + coordinates.lon));

                fw = new FileWriter(outputPath);
                bw = new BufferedWriter(fw);
                bw.write(content);

            } catch (IOException e) {

                e.printStackTrace();

            } finally {

                try {

                    if (bw != null)
                        bw.close();

                    if (fw != null)
                        fw.close();

                } catch (IOException ex) {

                    ex.printStackTrace();

                }
            }
        }

        return outputPath;
    }

    public String OutputWeatherApiWriter() throws Exception {
        ArrayList<String> cityNamesList = new TxtReader().CityList();
        for (int n = 0; n < cityNamesList.size(); n++) {
            String cityName = cityNamesList.get(n);
            outputPath = (System.getProperty("user.dir") + "//src//ee//" + cityName + "_weatherAPI.txt");
            String forecastText = new Scanner(new URL("http://api.openweathermap.org/data/2.5/weather?q=" +
                    cityName + "&APPID=02de4f9608f50b67b16dbb2370997657&units=metric").openStream(), "UTF-8").useDelimiter("\\A").next();
            BufferedWriter bw = null;
            FileWriter fw = null;

            try {
                String content = forecastText;
                fw = new FileWriter(outputPath);
                bw = new BufferedWriter(fw);
                bw.write(content);

            } catch (IOException e) {
                e.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bw != null)
                        bw.close();

                    if (fw != null)
                        fw.close();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return outputPath;
    }

    public String OfflineForecastWriter() throws Exception {

        ArrayList<String> cityList = new TxtReader().CityList();
        for (int n = 0; n < cityList.size(); n++) {
            String cityName = cityList.get(n);
            WeatherForecastService service = new WeatherForecastService();
            WeatherForecast request = new WeatherForecast();
            WeatherForecast currentWeather = service.getOfflineCurrentWeather(request, cityName);
            Forecast forecast = service.getOfflineForecastForThreeDays(request, cityName);

            LocalDateTime currentDate = LocalDateTime.now();
            double firstDayMaxTemp = -100;
            double firstDayMinTemp = 100;
            LocalDate firstDayDate = null;
            double secondDayMaxTemp = -100;
            double secondDayMinTemp = 100;
            LocalDate secondDayDate = null;
            double thirdDayMaxTemp = -100;
            double thirdDayMinTemp = 100;
            LocalDate thirdDayDate = null;
            for (List list : forecast.getList()) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(list.getDtTxt(), formatter);

                if (dateTime.getDayOfMonth() == currentDate.getDayOfMonth()) {
                    continue;
                }

                if (dateTime.getDayOfMonth() == currentDate.getDayOfMonth() + 1) {
                    if (list.getMain().getTempMax() > firstDayMaxTemp) {
                        firstDayMaxTemp = list.getMain().getTempMax();

                    }
                    if (list.getMain().getTempMin() < firstDayMaxTemp) {
                        firstDayMinTemp = list.getMain().getTempMin();
                    }
                    firstDayDate = LocalDate.from(dateTime);

                }

                if (dateTime.getDayOfMonth() == currentDate.getDayOfMonth() + 2) {
                    if (list.getMain().getTempMax() > secondDayMaxTemp) {
                        secondDayMaxTemp = list.getMain().getTempMax();

                    }
                    if (list.getMain().getTempMin() < secondDayMaxTemp) {
                        secondDayMinTemp = list.getMain().getTempMin();
                    }
                    secondDayDate = LocalDate.from(dateTime);

                }

                if (dateTime.getDayOfMonth() == currentDate.getDayOfMonth() + 3) {
                    if (list.getMain().getTempMax() > thirdDayMaxTemp) {
                        thirdDayMaxTemp = list.getMain().getTempMax();

                    }
                    if (list.getMain().getTempMin() < thirdDayMinTemp) {
                        thirdDayMinTemp = list.getMain().getTempMin();
                    }
                    thirdDayDate = LocalDate.from(dateTime);

                }


            }


            outputPath = (System.getProperty("user.dir") + "//src//ee//" + cityName + ".txt");


            BufferedWriter bw = null;
            FileWriter fw = null;

            try {

                Coordinates coordinates = service.getOfflineGeoCoordinates(cityName);
                String content = (
                        ("Today is: " + currentDate) + "\r" +
                                ("Сurrent temperature is: " + currentWeather.getMain().getTemp()) + "\n" +
                                ("Forecast for three days: ") + "\r" +
                                ("The date is " + firstDayDate.toString() + " Maximum temperature is: " + firstDayMaxTemp +
                                        " Minimum temperature is: " + firstDayMinTemp) + "\n" +
                                ("The date is " + secondDayDate.toString() + " Maximum temperature is: " + secondDayMaxTemp +
                                        " Minimum temperature is: " + secondDayMinTemp) + "\n" +
                                ("The date is " + thirdDayDate.toString() + " Maximum temperature is: " + thirdDayMaxTemp +
                                        " Minimum temperature is: " + thirdDayMinTemp) + "\n" +
                                ("Latitude: " + coordinates.lat + " Longitude: " + coordinates.lon));

                fw = new FileWriter(outputPath);
                bw = new BufferedWriter(fw);
                bw.write(content);

            } catch (IOException e) {

                e.printStackTrace();

            } finally {

                try {

                    if (bw != null)
                        bw.close();

                    if (fw != null)
                        fw.close();

                } catch (IOException ex) {

                    ex.printStackTrace();

                }

            }
        }

        return outputPath;
    }

    public String OutputForecastApiWriter() throws Exception {
        ArrayList<String> cityNamesList = new TxtReader().CityList();
        for (int n = 0; n < cityNamesList.size(); n++) {
            String cityName = cityNamesList.get(n);
            outputPath = (System.getProperty("user.dir") + "//src//ee//" + cityName + "_forecastAPI.txt");
            String forecastText = new Scanner(new URL("http://api.openweathermap.org/data/2.5/forecast?q=" +
                    cityName + "&APPID=02de4f9608f50b67b16dbb2370997657&units=metric").openStream(), "UTF-8").useDelimiter("\\A").next();
            BufferedWriter bw = null;
            FileWriter fw = null;

            try {
                String content = forecastText;
                fw = new FileWriter(outputPath);
                bw = new BufferedWriter(fw);
                bw.write(content);

            } catch (IOException e) {
                e.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bw != null)
                        bw.close();

                    if (fw != null)
                        fw.close();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return outputPath;
    }



}
