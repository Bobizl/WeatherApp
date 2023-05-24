package org.example;

public class Main {
    public static void main(String[] args)  {

        UserData users = new UserData();
        WeatherData weather = new WeatherData();
        DataRead.readFile(weather,users);
        DataRead.writeInFile(weather);
        SendEmail.sendMail(users.getSenderEmail(), users.getPassword(), users.getReceiverEmail());

    }
}