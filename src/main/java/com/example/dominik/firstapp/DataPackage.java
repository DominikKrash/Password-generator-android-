package com.example.dominik.firstapp;

import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataPackage  {
    private final String password;
    private String website;
    private static final String urlLocation = "base.txt";

    public static String getUrlLocation(){
        return urlLocation;
    }
    public String getPassword() {
        return password;
    }

    public String getWebsite() {
        return website;
    }

    public DataPackage(final String w, final String p){
        this.password = p;
        this.website = w;
    }
    public void setWebsite(final String name){
        website = name;
    }

    static public String[] getWebsiteArray(DataPackage[] arr){
        String[] temp = new String[arr.length];
        for(int i = 0;i < arr.length;i++ ){
            temp[i] = new String(arr[i].getWebsite());
            System.out.println(temp[i]);
        }
        return temp;
    }

    public static boolean searchForWebsite(final String name, Context c){
        File dir = c.getFilesDir();
        File file = new File(dir,getUrlLocation());

        try(Scanner sc = new Scanner(file)){
            while(sc.hasNext()){
                String next;
                next = sc.next();
                if(next.compareTo(name) == 0)return true;
                sc.next();
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }

    public static String getRawName(final String name){
        StringBuilder sb = new StringBuilder("");
        for(int i = 0;i<name.length();i++){
            if(name.charAt(i)== '(') break;
            sb.append(name.charAt(i));
        }
        return sb.toString();
    }

    public static int checkDataNumber(final String name){
        StringBuilder sb = new StringBuilder("");
        boolean inNumber = false;
        for(int i = 0;i<name.length();i++){
            if(name.charAt(i) == '(') {
                inNumber = true;
                continue;
            }
            if(name.charAt(i) == ')' && inNumber == true){
                inNumber = false;
            }
            else if(inNumber == true){
                sb.append(name.charAt(i));
            }
        }
        String result = sb.toString();
        if(result.compareTo("")==0) return 0;
        else return Integer.parseInt(result);
    }


    public static String fixSpaces(final String name){
        StringBuilder sb = new StringBuilder("");
        for(int i = 0;i < name.length();i++){
            if(name.charAt(i) != ' ') sb.append(name.charAt(i));
        }
        return sb.toString();
    }

    public static void addPackage(DataPackage data, Context c){
        if(searchForWebsite(data.getWebsite(),c)){
            String num = Integer.toString((checkDataNumber(data.getWebsite())+1));
            data.setWebsite(getRawName(data.getWebsite())+"("+num+")");
            addPackage(data,c);
            return;
        }

        File directory = c.getFilesDir();
        File file = new File(directory,getUrlLocation());

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file,true);
            fos.write((data.getWebsite()+ " "+data.getPassword()+"\n").getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<DataPackage> getPackages(Context c){
        ArrayList<DataPackage> list = new ArrayList<>();
        File file = new File(c.getFilesDir(),getUrlLocation());
        try(Scanner sc = new Scanner(file)){
           while(sc.hasNext()){
               String website,password;
               website = sc.next();
               password = sc.next();
               DataPackage temp = new DataPackage(website,password);
               list.add(temp);
           }
           return list;
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
    public static void deleteRecord(final String name,Context c){
        File temp = new File(c.getFilesDir(),"temp.txt");
        File base = new File(c.getFilesDir(), getUrlLocation());

        Scanner sc = null;
        FileOutputStream foo = null;
        try{
            sc = new Scanner(base);
            foo = new FileOutputStream(temp,true);
            while(sc.hasNext()){
                String website,password;
                website = sc.next();
                if(website.compareTo(name) == 0){
                    sc.next();
                    continue;
                }
                password = sc.next();
                foo.write((website+ " "+password+"\n").getBytes());
            }
            foo.close();
            sc.close();

            base.delete();
            temp.renameTo(base);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
