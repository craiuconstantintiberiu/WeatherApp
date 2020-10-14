package Controller;

public class OpenWeatherMapApi {
    private static final String myApiKey= "Input your own key";

    /*
    Create the url for which to call the API, using the place
    Params: cityName- city for which to query, can also take the form of "{cityName}{Country Code}"
    Return : String containing the URL for which to create our HTTP request.
     */
    public static String CreateUrl(String cityName){
        return "http://api.openweathermap.org/data/2.5/weather?q="+cityName+"&appid="+myApiKey;
    }
}
