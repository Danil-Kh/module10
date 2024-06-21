package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;


public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.countWorFrequency("word.txt");
        main.userToJson("file.txt");
        main.validTelephone("file.txt");
    }
    public void validTelephone(String filePath)  {
        try(FileReader reader = new FileReader(filePath);
            BufferedReader br = new BufferedReader(reader)) {
            String line;
            String regex1 = "^\\(\\d{3}\\) \\d{3}-\\d{4}$";
            String regex2 = "^\\d{3}-\\d{3}-\\d{4}$";

            while((line= br.readLine()) != null) {

                if (line.matches(regex1)){
                    System.out.println(line);
                }
                if (line.matches(regex2)){
                    System.out.println(line);
                }
            }

        }catch (Exception e){
            System.out.println("e.getMessage() = " + e.getMessage());
        }

    }
    public void userToJson(String filePath) {
        ArrayList<String[]> arrayListAllInfo = new ArrayList<>();
        ArrayList<User> arrayListUser = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileReader reader = new FileReader(filePath);
            BufferedReader br = new BufferedReader(reader)) {
            while (true){
                String s = br.readLine();
                if (s == null)
                    break;
                arrayListAllInfo.add(s.split(" "));
            }
        }catch (Exception e){
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        for (int i = 1; i < arrayListAllInfo.size(); i++) {
            String[] name = arrayListAllInfo.get(i);
            arrayListUser.add(new User(name[0], name[1]));
        }
        try (FileWriter writer = new FileWriter("user.json")) {
            gson.toJson(arrayListUser, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void countWorFrequency(String filePath){
        StringBuilder stringBuilder = new StringBuilder(" ");
        String[] allWordsMas;
        ArrayList<Words> allWordsList = new ArrayList<>();
        try(FileReader reader = new FileReader(filePath);
            BufferedReader br = new BufferedReader(reader)) {
            while (true){
                String s = br.readLine();
                if (s == null)
                    break;
                stringBuilder.append(s).append(" ");
            }
        }catch (Exception e){
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        while (stringBuilder.toString().contains("  ")){
            stringBuilder = new StringBuilder(stringBuilder.toString().replaceAll("  ", " "));
        }
        allWordsMas = stringBuilder.toString().trim().split(" ");
        int counter = 0;
        for (String words : allWordsMas) {
            for (String wordsCompare : allWordsMas) {
                if (Objects.equals(words, wordsCompare)) {
                    counter++;
                }
            }
            Words validWord = new Words(words, counter);
            if (!allWordsList.contains(validWord)) {
                allWordsList.add(validWord);
            }
            counter = 0;
        }
        Collections.sort(allWordsList);
        System.out.println(allWordsList);
    }
}

