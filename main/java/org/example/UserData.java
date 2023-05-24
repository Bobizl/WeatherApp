package org.example;

import java.util.Scanner;

public class UserData {
    private  String filePath;
    private  String senderEmail;
    private  String password;
    private  String receiverEmail;

    UserData(){
        setFilePath();
        setSenderEmail();
        setPassword();
        setReceiverEmail();
    }
    public void setFilePath (){
        Scanner sc = new Scanner(System.in);
        System.out.print("Input filePath = ");
        filePath = sc.nextLine();
    }
    public void setSenderEmail(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Input email = ");
        senderEmail = sc.nextLine();
    }
    public void setPassword(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Input password = ");
        password = sc.nextLine();
    }
    public void setReceiverEmail(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Input receiver = ");
        receiverEmail = sc.nextLine();
    }
    public String getFilePath(){
        return filePath;
    }
    public String getSenderEmail(){
        return senderEmail;
    }
    public String getPassword(){
        return password;
    }
    public String getReceiverEmail(){
        return receiverEmail;
    }



}
