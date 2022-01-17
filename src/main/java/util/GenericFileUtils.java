package util;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GenericFileUtils {

    /**
     * Recibe los datos del usuario y los valida.
     *
     * @return Devuelve los datos del usuario ya validado.
     */
    public static String[] validatedData(String city, String datetime) {

        String[] informationSupplied = new String[2];
        informationSupplied[0] = city.trim();
        informationSupplied[1] = verifyDatetime(datetime.trim());

        return informationSupplied;
    }

    /**
     * Si el usuario no ingresa una fecha se da por sentado que la fecha que pide es la actual, si ingresa se revisa que
     * sea correcta.
     *
     * @param datetime
     * @return
     */
    public static String verifyDatetime(String datetime) {

        if (StringUtils.isBlank(datetime)) {
            return datetime;
        } else {
            String[] date = StringUtils.split(datetime, "/");
            return dateLessthan(date);
        }
    }

    /**
     * Se revisa que la fecha no sea mayor a la fecha actual más 5 días.
     *
     * @param date Array en string de la fecha ingresada por el usuario
     * @return Devuelve una fecha correcta o avisa al usuario que es incorrecta.
     */
    public static String dateLessthan(String[] date) {
        Calendar afterFiveDays = Calendar.getInstance();
        afterFiveDays.add(Calendar.DAY_OF_MONTH, 5);

        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);

        if (year > 2012 && year < 2023) {
            if (month > 0 && month < 13) {
                Calendar datetime = Calendar.getInstance();
                datetime.set(Calendar.DATE, day);
                datetime.set(Calendar.MONTH, month - 1);
                datetime.set(Calendar.YEAR, year);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

                if (datetime.before(afterFiveDays)) {
                    return dateFormat.format(datetime.getTime());
                } else {
                    return "Inválido";
                }
            } else {
                return "Inválido";
            }
        } else {
            return "Inválido";
        }
    }

    /**
     * Muestra la información recibida desde la API
     *
     * @param data     Información de API
     * @param withDate Si se muetra la previsión de un día específico o de los 5 días siguientes.
     */
    public static boolean showInfo(String data, boolean withDate) {
        boolean show;
        if (withDate) {
            JSONArray oneDay = new JSONArray(data);
            String date = oneDay.getJSONObject(0).get("applicable_date").toString();
            String weather = oneDay.getJSONObject(0).get("weather_state_name").toString();
            System.out.println(date + ":   " + weather);

            show = true;
            return show;

        } else {
            JSONArray fiveDay = new JSONObject(data).getJSONArray("consolidated_weather");
            for (int i = 0; i < fiveDay.length() - 1; i++) {
                String date = fiveDay.getJSONObject(i).get("applicable_date").toString();
                String weather = fiveDay.getJSONObject(i).get("weather_state_name").toString();
                System.out.println(date + ":   " + weather);
            }

            show = true;
            return show;

        }
    }
}
