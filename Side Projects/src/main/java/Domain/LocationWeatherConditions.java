package Domain;

public class LocationWeatherConditions{
    private double longitude;
    private double latitude ;
    private String weatherConditions;
    private String weatherDescription;
    private String imageSource;
    private double currentTemperature;
    private double feelsLike;
    private double minimumTemperature;
    private String name;
    private String country;
    private int timezone;//This holds the difference of seconds compared to the UTC

    @Override
    public String toString() {
        return "LocationWeatherConditions{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", weatherConditions='" + weatherConditions + '\'' +
                ", weatherDescription='" + weatherDescription + '\'' +
                ", imageSource='" + imageSource + '\'' +
                ", currentTemperature=" + currentTemperature +
                ", feelsLike=" + feelsLike +
                ", minimumTemperature=" + minimumTemperature +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", timezone=" + timezone +
                ", maximumTemperature=" + maximumTemperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                '}';
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setWeatherConditions(String weatherConditions) {
        this.weatherConditions = weatherConditions;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public void setMinimumTemperature(double minimumTemperature) {
        this.minimumTemperature = minimumTemperature;
    }

    public void setMaximumTemperature(double maximumTemperature) {
        this.maximumTemperature = maximumTemperature;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    private double maximumTemperature;
    private double pressure;
    private double humidity;
    private double windSpeed;

    //private LocationWeatherConditions()throws Exception{
      //  throw new Exception("There should be only one of these.");
    //}

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getWeatherConditions() {
        return weatherConditions;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public String getImageSource() {
        return imageSource;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public double getMinimumTemperature() {
        return minimumTemperature;
    }

    public double getMaximumTemperature() {
        return maximumTemperature;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

}


//public class LocationWeatherConditions {


//}
