package com.arteco.arteco;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import util.GenericFileUtils;
import weatherAPI.WeatherForecast;

import java.io.IOException;


@SpringBootTest
class ArtecoApplicationTests {

    @Test
    void checkUserDate() {
        GenericFileUtils genericFileUtils = new GenericFileUtils();
        String[] arrayDatetime = {"2022", "01", "10"};
        String data = genericFileUtils.dateLessthan(arrayDatetime);
        Assertions.assertEquals("2022/01/10", data);
    }


    @Test
    void verifyDate() {
        GenericFileUtils genericFileUtils = new GenericFileUtils();
        String date = "2022/01/10";
        String data = genericFileUtils.verifyDatetime(date);
        Assert.assertEquals("2022/01/10", data);
    }

    @Test
    void enteredData() {
        GenericFileUtils genericFileUtils = new GenericFileUtils();
        String[] data = genericFileUtils.validatedData("London", "2013/12/08");
        System.out.println(data[0] + " - " + data[1]);
        Assert.assertFalse("No contiene el texto Inválido", ArrayUtils.contains(data, "Inválido"));
    }

    @Test
    void getCityFromAPI() throws IOException {
        WeatherForecast weatherForecast = new WeatherForecast();
        String city = "Madrid";
        String data = weatherForecast.getWeatherForecast(city);
        Assert.assertTrue("Contiene información de la ciudad", StringUtils.contains(data, "woeid"));

    }

    @Test
    void getWeather() throws IOException, JSONException {
        WeatherForecast weatherForecast = new WeatherForecast();
        String city = "Madrid";
        String date = "2021/12/12";
        String data = weatherForecast.getWeatherForecast(city);
        Assert.assertTrue("Contiene información de la ciudad", StringUtils.contains(data, "woeid"));
        JSONArray arrayData = new JSONArray(data);
        String isWoeid = arrayData.getJSONObject(0).get("woeid").toString();
        String weather = weatherForecast.DayWeatherForecast(isWoeid, date);
        System.out.println(weather);
        Assert.assertTrue("Contiene 2021-12-12", StringUtils.contains(weather, "2021-12-12"));
    }

    @Test
    void getFiveWeather() throws IOException, JSONException {
        WeatherForecast weatherForecast = new WeatherForecast();
        String city = "Madrid";
        String data = weatherForecast.getWeatherForecast(city);
        Assert.assertTrue("Contiene información de la ciudad", StringUtils.contains(data, "woeid"));
        JSONArray arrayData = new JSONArray(data);
        String isWoeid = arrayData.getJSONObject(0).get("woeid").toString();
        String weather = weatherForecast.fiveDayWeatherForecast(isWoeid);
        Assert.assertTrue("Contiene consolidated_weather", StringUtils.contains(weather, "consolidated_weather"));
    }

    @Test
    void WeatherForecast() throws IOException {
        WeatherForecast weatherForecast = new WeatherForecast();
        String city = "Madrid";
        String date = "2021/12/12";
        String data = weatherForecast.getWeather(city, date);
        Assert.assertTrue("Contiene 2021-12-12", StringUtils.contains(data, "2021-12-12"));
        String city2 = "Madrid";
        String date2 = "";
        String data2 = weatherForecast.getWeather(city2, date2);
        Assert.assertTrue("Contiene consolidated_weather", StringUtils.contains(data2, "consolidated_weather"));

    }

    @Test
    void checkShowInfo() throws IOException, JSONException {
        WeatherForecast weatherForecast = new WeatherForecast();
        GenericFileUtils genericFileUtils = new GenericFileUtils();
        String city = "Madrid";
        String data = weatherForecast.getWeatherForecast(city);
        Assert.assertTrue("Contiene información de la ciudad", StringUtils.contains(data, "woeid"));
        JSONArray arrayData = new JSONArray(data);
        String isWoeid = arrayData.getJSONObject(0).get("woeid").toString();
        String weather = weatherForecast.fiveDayWeatherForecast(isWoeid);
        //JSONArray arrayWeather = new JSONObject(weather).getJSONArray("consolidated_weather");
        genericFileUtils.showInfo(weather, true);

    }

    @Test
    void checkshowWeatherForecast() throws IOException, JSONException {
        WeatherForecast weatherForecast = new WeatherForecast();
        weatherForecast.showWeatherForecast();

    }

}
