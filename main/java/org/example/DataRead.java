package org.example;

import java.io.*;
import java.util.Arrays;

public class DataRead {
    public static void readFile(WeatherData data, UserData input) {


        String file = input.getFilePath();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            reader.readLine();
            if ((line = reader.readLine()) != null) {

                String[] row = line.split(";");
                data.setTemperature(Arrays.copyOfRange(row, 1, row.length));
            }
            if ((line = reader.readLine()) != null) {

                String[] row = line.split(";");
                data.setWind(Arrays.copyOfRange(row, 1, row.length));
            }
            if ((line = reader.readLine()) != null) {

                String[] row = line.split(";");
                data.setHumidity(Arrays.copyOfRange(row, 1, row.length));
            }
            if ((line = reader.readLine()) != null) {

                String[] row = line.split(";");
                data.setPrecipitation(Arrays.copyOfRange(row, 1, row.length));
            }
            if ((line = reader.readLine()) != null) {

                String[] row = line.split(";");
                data.setLightning(Arrays.copyOfRange(row, 1, row.length));
            }
            if ((line = reader.readLine()) != null) {

                String[] row = line.split(";");
                data.setClouds(Arrays.copyOfRange(row, 1, row.length));
            }
            data.findBestDay();
        } catch (IOException ex) {
            System.out.println("File not found");;
        }
    }

    public static void writeInFile(WeatherData data) {


        String file = "src\\WeatherReport.csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("AGG/param ; MIN ; MAX ; AVERAGE ; MEDIAN ; BEST DAY\n");
            bw.write(data.getAGGTemperature());
            bw.write(data.getAGGWind());
            bw.write(data.getAGGHumidity());
            bw.write(data.getAGGPrecipitation());
            bw.write(data.getAGGLightning());
            bw.write(data.getAGGClouds());

        } catch (Exception ex) {
            System.out.println("");;

        }


    }

}
