import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherAPI {
    public static City getWeather(String cityName) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&units=metric&appid=fdec5664d19caf22895d3aaf207d3d43");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        Gson gson = new Gson();
        City city = gson.fromJson(br, City.class);

        br.close();
        inputStream.close();
        urlConnection.disconnect();

        return city;
    }
}
