package Controller;

import Domain.LocationWeatherConditions;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import static java.lang.Math.round;

class ErrorCode extends Exception{
    ErrorCode(String msg){
        super(msg);
    }
}

public class Controller {

    private final LocationWeatherConditions locationWeatherConditions=new LocationWeatherConditions();//We are going to have only one weather location. This object stores info regarding it.
    private boolean firstClick=false;//This boolean is kept in order to remove the text when you first click on the search bar
    /*
    Queries the OpenWeatherMapAPI for information regarding the place specified. Modifies the data stored in the locationW
    @Param:place-place to call API for
    @Return: void
     */
    void CheckWeatherLocation(String place) throws java.io.IOException, java.lang.InterruptedException, ErrorCode
    {
        //Creating the http client
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        //Creating the request for the API
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(OpenWeatherMapApi.CreateUrl(place)))
                .GET()
                .build();


        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody= response.body();

        JSONObject obj = new JSONObject(responseBody);

        //We could have also gotten this information using response.header().
        var cod = obj.getDouble("cod");
        if(cod==200){
            //If response header is 200, meaning success, we start parsing the JSON received and setting values in our object.

            locationWeatherConditions.setLongitude(obj.getJSONObject("coord").getDouble("lon"));
            locationWeatherConditions.setLatitude(obj.getJSONObject("coord").getDouble("lat"));
            locationWeatherConditions.setWeatherConditions(obj.getJSONArray("weather").getJSONObject(0).getString("main"));
            locationWeatherConditions.setWeatherDescription(obj.getJSONArray("weather").getJSONObject(0).getString("description"));
            String imageSource=obj.getJSONArray("weather").getJSONObject(0).getString("icon");
            locationWeatherConditions.setImageSource("http://openweathermap.org/img/w/"+imageSource+".png");
            locationWeatherConditions.setCurrentTemperature(obj.getJSONObject("main").getDouble("temp")-273.15);//We have to convert from Kelvin
            locationWeatherConditions.setFeelsLike(obj.getJSONObject("main").getDouble("feels_like")-273.15);
            locationWeatherConditions.setMinimumTemperature(obj.getJSONObject("main").getDouble("temp_min")-273.15);
            locationWeatherConditions.setMaximumTemperature(obj.getJSONObject("main").getDouble("temp_max")-273.15);
            locationWeatherConditions.setPressure(obj.getJSONObject("main").getDouble("pressure"));
            locationWeatherConditions.setHumidity(obj.getJSONObject("main").getDouble("humidity"));
            locationWeatherConditions.setWindSpeed(obj.getJSONObject("wind").getDouble("speed"));
            locationWeatherConditions.setCountry(obj.getJSONObject("sys").getString("country"));
            locationWeatherConditions.setName(obj.getString("name"));
            locationWeatherConditions.setTimezone(obj.getInt("timezone"));

        }
        else
        {
            //The message here is what the API returns, in case the query was not successful. E.g:"City not found", "Wrong API Key".
            String message=obj.getString("message");
            throw new ErrorCode(message);
        }

    }

    //Returns info regarding the weather. Might use it for testing.
    String LocationWeatherConditionsToString(){
        String s = locationWeatherConditions.toString();
        return s;
    }

    @FXML
    private TextField Search1;

    @FXML
    private Text City1;

    @FXML
    private Text WeatherConditions1;

    @FXML
    private Text CurrentTime1;

    @FXML
    private Text Coordinates1;

    @FXML
    private Text Temperature1;

    @FXML
    private Text FeelsLike1;

    @FXML
    private Text Pressure1;

    @FXML
    private ImageView Image1;

    @FXML
    private Text Humidity1;

    @FXML
    /*
    Sear
     */
    public void handleKeyPressedSearch1(KeyEvent keyEvent) throws Exception {

        if (keyEvent.getCode() == KeyCode.ENTER) {

            try {
                //Fills the LocationWeatherCondition instance with data(weather, temperature etc.)
                CheckWeatherLocation(Search1.getText());

                City1.setText(locationWeatherConditions.getName()+","+locationWeatherConditions.getCountry());
                ZoneOffset zoneOffset = ZoneOffset.ofTotalSeconds(locationWeatherConditions.getTimezone());//the Timezone represents the difference in seconds compared to the UTC.
                OffsetDateTime offsetDateTime = OffsetDateTime.now(zoneOffset);
                CurrentTime1.setText(offsetDateTime.truncatedTo(ChronoUnit.DAYS).toString());

                double longitude,latitude;
                longitude=locationWeatherConditions.getLongitude();
                latitude=locationWeatherConditions.getLatitude();
                String coordinates =latitude>=0?latitude+"N":latitude+"S" +", ";
                coordinates+=longitude>=0?longitude+"E":longitude+"W";
                Coordinates1.setText(coordinates);

                Temperature1.setText(round(locationWeatherConditions.getCurrentTemperature())+"°C");

                FeelsLike1.setText("Feels like:"+round(locationWeatherConditions.getFeelsLike())+"°C");

                Pressure1.setText(locationWeatherConditions.getPressure()+"hg");
                javafx.scene.image.Image image = new javafx.scene.image.Image(locationWeatherConditions.getImageSource());
                Image1.setImage(image);
                Humidity1.setText(locationWeatherConditions.getHumidity()+"% humidity");
                WeatherConditions1.setText(locationWeatherConditions.getWeatherConditions());

            } catch (ErrorCode e) {
                //If we do not make a successful query, we tell the user waht went wrong. Also, remove any info left behind.
                City1.setText(e.getMessage());
                CurrentTime1.setText("");
                Coordinates1.setText("");
                Temperature1.setText("");
                FeelsLike1.setText("");
                Pressure1.setText("");
                Humidity1.setText("");
                WeatherConditions1.setText("");
                Image1.setImage(null);
            }

        }
    }

    @FXML
    public void handleMousePressedSearch1(MouseEvent mouseEvent) {
        if(firstClick==false)
        {
            //We use this to delete the Text Field text when first clicking on it.
            Search1.clear();
            firstClick=true;
        }
    }
}
