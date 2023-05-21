package weather;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherData {

    private List<Integer> temperature;
    private List<Integer> wind;
    private List<Integer> humidity;
    private List<Integer> precipitation;
    private List<Boolean> lightning;
    private List<String> clouds;

    WeatherData(List<Integer> temperature,List<Integer> wind,List<Integer> humidity,List<Integer> precipitation,List<Boolean> lightning,List<String> clouds){
        this.temperature = temperature;
        this.wind = wind;
        this.humidity = humidity;
        this.precipitation = precipitation;
        this.lightning = lightning;
        this.clouds = clouds;
    }
    public void setTemperature(String[] temperature){
        this.temperature =  Arrays
                .stream(temperature)
                .map(Integer::valueOf)
                .collect(Collectors.toList());//list of integers
    }
    public void setWind(String[] wind) {
        this.wind = Arrays
                .stream(wind)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
    public void setHumidity(String[] humidity){
        this.humidity = Arrays
                .stream(humidity)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
    public void setPrecipitation(String[] precipitation){
        this.precipitation = Arrays
                .stream(precipitation)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
    public void setLightning(String[] lightning){
        this.lightning = Arrays
                .stream(lightning)
                .map(Boolean::valueOf)
                .collect(Collectors.toList());
    }
    public void setClouds(String[] clouds){
        this.clouds = Arrays.asList(clouds);
    }

    public void addMin(List<Integer> data){
        data.add(Collections.min(data));
    }
    public void addMax(List<Integer> data){
        data.add(Collections.max(data));
    }
    public void addAverage(List<Integer> data){
        if(data.isEmpty()){
            return;
        }
        int sum = 0;
        for(Integer value : data){
            sum+= value;
        }
        sum/=data.size();
        data.add(sum);
    }
    public <T> void addMedian(List<T> data){
        data.add(data.get((data.size() + 1) / 2));
    }


}
