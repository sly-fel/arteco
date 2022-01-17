package weatherAPI;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import util.GenericFileUtils;

import java.io.IOException;
import java.util.Scanner;

public class WeatherForecast {

    private HttpRequestFactory requestFactoryAPI = new NetHttpTransport().createRequestFactory();
    private String[] enteredData;
    private String isWoeid;

    /**
     * Muestra la información de la previsión de acuerdo a la fecha
     * @throws IOException
     */
    public void showWeatherForecast() throws IOException {
        boolean withDate;
        Scanner enterScanner = new Scanner(System.in);

        String city ;
        String datetime ;

        System.out.println("Empezamos el programa");
        System.out.println("Puede introducir solo la ciudad y el pronóstico que verá será de la fecha actual. " +
                "Si ingresa la ciudad y una fecha, ésta última debe ser mayor al año 2013 y solo 5 días después " +
                "de la fecha actual");
        System.out.println("Por favor introduzca una ciudad:");
        city = enterScanner.nextLine();
        while (StringUtils.isEmpty(city)) {
            System.out.println("Por favor introduzca una ciudad válida como London, Madrid, etc... :");
            city = enterScanner.nextLine();
        }

        System.out.println("Por favor introduzca una fecha de esta forma 2021/4/1 (opcional):");
        datetime = enterScanner.nextLine();

        if (StringUtils.isBlank(datetime)) {
            withDate = false;
        } else {
            withDate = true;
        }

        String data = getWeather(city, datetime);
        GenericFileUtils.showInfo(data, withDate);

    }


    /**
     * Busca la previsión del tiempo para un día o para cinco días en adelante desde la fecha actual dependiendo si
     * se ingresa una fecha o no.
     *
     * @param EnteredCity     Ciudad a buscar.
     * @param EnteredDatetime Fecha ingresa, puede estar vacía.
     * @return Devuelve el previsión del tiempo.
     * @throws IOException
     */
    public String getWeather(String EnteredCity, String EnteredDatetime) throws IOException {

        enteredData = GenericFileUtils.validatedData(EnteredCity, EnteredDatetime);
        String city = enteredData[0];
        String datetime = enteredData[1];
        String data = getWeatherForecast(city);

        if (StringUtils.isEmpty(data)) {
            return "Aún no se ha actualizado la previsión para esta ciudad";
        } else {
            JSONArray arrayData = new JSONArray(data);
            isWoeid = arrayData.getJSONObject(0).get("woeid").toString();
            if (StringUtils.isBlank(datetime)) {
                return fiveDayWeatherForecast(isWoeid);
            } else if (!StringUtils.isAlpha(datetime)) {
                return DayWeatherForecast(isWoeid, datetime);
            } else {
                return "Puedes ingresar Madrid y la fecha 2020/12/12";
            }
        }
    }

    /**
     * Busca los datos de la ciudad ingresada.
     *
     * @param city Ciudad a buscar.
     * @return Devuelve los datos de la ciudad.
     * @throws IOException
     */
    public String getWeatherForecast(String city) throws IOException {

        HttpRequest request = requestFactoryAPI
                .buildGetRequest(new GenericUrl("https://www.metaweather.com/api/location/search/?query=" + city));
        String cityData = request.execute().parseAsString();
        if (StringUtils.isEmpty(cityData)) {
            return "";
        } else {
            return cityData;
        }
    }

    /**
     * Busca el pronóstico de 5 días en adelante desde la fecha actual.
     *
     * @param woeid Código de localización
     * @return Devuelve los datos del pronóstico para 5 días en adelante.
     * @throws IOException
     */
    public String fiveDayWeatherForecast(String woeid) throws IOException {
        HttpRequest request = requestFactoryAPI
                .buildGetRequest(new GenericUrl("https://www.metaweather.com/api/location/" + woeid));
        String cityData = request.execute().parseAsString();
        return cityData;
    }

    /**
     * Busca el pronóstico de un día específico.
     *
     * @param woeid    Código de localización
     * @param datetime Fecha que se pide
     * @return Devuelve los datos del pronóstico de un día.
     * @throws IOException
     */
    public String DayWeatherForecast(String woeid, String datetime) throws IOException {
        HttpRequest request = requestFactoryAPI
                .buildGetRequest(new GenericUrl("https://www.metaweather.com/api/location/" + woeid + "/" + datetime + "/"));
        String cityData = request.execute().parseAsString();
        return cityData;
    }

}
