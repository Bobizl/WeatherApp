package org.example;

import java.util.*;

public class WeatherData {
    private final Set<Integer> possibleDays;
    private final List<Integer> temperature;
    private final List<Integer> wind;
    private final List<Integer> humidity;
    private final List<Integer> precipitation;
    private final List<Boolean> lightning;
    private final List<String> clouds;

    public WeatherData() {
        possibleDays = new HashSet<>();
        temperature = new ArrayList<>();
        wind = new ArrayList<>();
        humidity = new ArrayList<>();
        precipitation = new ArrayList<>();
        lightning = new ArrayList<>();
        clouds = new ArrayList<>();
    }


    public void setTemperature(String[] temperature) {
        int minTemp = 1000;
        int maxTemp = -1000;


        for (int i = 0; i < temperature.length; ++i) {
            int b = Integer.parseInt(temperature[i]);
            this.temperature.add(b);

            if (b < minTemp) {
                minTemp = b;
            }
            if (b > maxTemp) {
                maxTemp = b;
            }
            if (b > 2 && b < 31) {
                possibleDays.add(i);
            }

        }
        this.temperature.add(minTemp);
        this.temperature.add(maxTemp);
        addAverage(this.temperature);
        addMedian(this.temperature);


    }

    public void setWind(String[] wind) {
        int minWind = 1000;
        int maxWind = -1000;

        for (int i = 0; i < wind.length; ++i) {
            int b = Integer.parseInt(wind[i]);
            this.wind.add(b);
            if (b > 10) {
                possibleDays.remove(i);
            }
            if (b < minWind) {
                minWind = b;
            }
            if (b > maxWind) {
                maxWind = b;
            }
        }
        this.wind.add(minWind);
        this.wind.add(maxWind);
        addAverage(this.wind);
        addMedian(this.wind);

    }

    public void setHumidity(String[] humidity) {
        int minHumidity = 1000;
        int maxHumidity = -1000;

        for (int i = 0; i < humidity.length; ++i) {
            int b = Integer.parseInt(humidity[i]);
            this.humidity.add(b);
            if (b > 60) {
                possibleDays.remove(i);
            }
            if (b < minHumidity) {
                minHumidity = b;
            }
            if (b > maxHumidity) {
                maxHumidity = b;
            }
        }
        this.humidity.add(minHumidity);
        this.humidity.add(maxHumidity);
        addAverage(this.humidity);
        addMedian(this.humidity);

    }

    public void setPrecipitation(String[] precipitation) {
        int minPrecipitation = 1000;
        int maxPrecipitation = -1000;
        for (int i = 0; i < precipitation.length; ++i) {
            int b = Integer.parseInt(precipitation[i]);
            this.precipitation.add(b);
            if (b > 0) {
                possibleDays.remove(i);
            }
            if (b < minPrecipitation) {
                minPrecipitation = b;
            }
            if (b > maxPrecipitation) {
                maxPrecipitation = b;
            }
        }
        this.precipitation.add(minPrecipitation);
        this.precipitation.add(maxPrecipitation);
        addAverage(this.precipitation);
        addMedian(this.precipitation);

    }

    public void setLightning(String[] lightning) {
        for (int i = 0; i < lightning.length; ++i) {
            Boolean b = Boolean.parseBoolean(lightning[i]);
            this.lightning.add(b);
            if (b) {
                possibleDays.remove(i);
            }
        }

    }

    public void setClouds(String[] clouds) {
        for (int i = 0; i < clouds.length; ++i) {

            this.clouds.add(clouds[i]);
            if (clouds[i].equals("Cumulus") || clouds[i].equals("Nimbus")) {
                possibleDays.remove(i);
            }
        }

    }

    public void addBestDayInfo(int index) {
        this.temperature.add(this.temperature.get(index));
        this.humidity.add(this.humidity.get(index));
        this.wind.add(this.wind.get(index));
        this.precipitation.add(this.precipitation.get(index));
        this.lightning.add(this.lightning.get(index));
        this.clouds.add(this.clouds.get(index));
    }


    public String getAGGValuesNumerical(List<Integer> data) {

        StringBuilder result = new StringBuilder();
        for (int i = data.size() - 5; i < data.size(); i++) {
            result.append(data.get(i)).append(";");

        }
        return result.toString();
    }

    public String getAGGLightning() {
        return "Lightning; " + "; ; ; ;" + lightning.get(lightning.size() - 1) + ";\n";

    }

    public String getAGGClouds() {

        return "Clouds; " + "; ; ; ;" + clouds.get(clouds.size() - 1) + ";\n";
    }

    public String getAGGTemperature() {
        return ("Temperature ( C ) ;" + getAGGValuesNumerical(this.temperature)) + "\n";

    }

    public String getAGGWind() {
        return ("Wind (m/s) ;" + getAGGValuesNumerical(this.wind)) + "\n";

    }

    public String getAGGHumidity() {
        return ("Humidity (%) ;" + getAGGValuesNumerical(this.humidity)) + "\n";

    }

    public String getAGGPrecipitation() {
        return ("Precipitation (%) ;" + getAGGValuesNumerical(this.precipitation)) + "\n";

    }

    public void findBestDay() {
        if (possibleDays.isEmpty()) {
            return;
        }
        if (possibleDays.size() == 1) {
            addBestDayInfo(possibleDays.iterator().next());
            return;
        }
        int bestDayIndex = possibleDays.iterator().next();
        for (int currDay : possibleDays) {
            int bestDayWind = wind.get(bestDayIndex);
            int bestDayHumidity = humidity.get(bestDayIndex);
            int currDayWind = wind.get(currDay);
            int currDayHumidity = humidity.get(currDay);
            if (bestDayWind > currDayWind && bestDayHumidity > currDayHumidity) {
                bestDayIndex = currDay;
            } else if (bestDayWind == currDayWind && bestDayHumidity > currDay) {
                bestDayIndex = currDay;
            } else if (bestDayHumidity == currDayHumidity && bestDayWind > currDay) {
                bestDayIndex = currDay;
            } else if (bestDayWind > currDayWind) {
                bestDayIndex = currDay;
            }
        }
        addBestDayInfo(bestDayIndex);
    }

    public void addAverage(List<Integer> data) {
        if (data.isEmpty()) {
            return;
        }
        int sum = 0;
        for (int i = 0; i < data.size() - 2; ++i) {
            sum += data.get(i);
        }
        sum /= (data.size() - 2);

        data.add(sum);
    }

    public void addMedian(List<Integer> data) {
        Integer[] arr = new Integer[data.size()];
        data.toArray(arr);
        Arrays.sort(arr, 0, arr.length - 2);
        data.add(arr[(arr.length + 1) / 2]);
    }
}
