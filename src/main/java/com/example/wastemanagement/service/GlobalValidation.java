package com.example.wastemanagement.service;

import org.springframework.validation.FieldError;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GlobalValidation {

    // This function is used to validate form data to make sure the contents are within set parameters!
    public List<FieldError> Validation(String data, String field, String objectName){
        final char COMMA_DELIMITER = ',';
        ArrayList<String> bannedWords;
        boolean answer = false;
        bannedWords = new ArrayList<String>();
        List<FieldError> errors = new ArrayList<FieldError>();
        Pattern pattern = Pattern.compile("[!#$%&*()_+=|<>?{}\\[\\]~]", Pattern.CASE_INSENSITIVE);
        Matcher match = pattern.matcher(data);

        // This gets the file called Terms-to-Block from the resources folder.
        File bW = new File("src/main/resources", "Terms-to-Block.csv");
        // This code reads the csv file from the bW file path and then the while loop formats the data to the correct form
        // and adds them to the list called bannedWords.
        try(BufferedReader bR = new BufferedReader(new FileReader(bW.getAbsolutePath()))){
            String line;
            String word = null;
            int num = 0;
            while((line = bR.readLine()) != null){
                word = Arrays.toString(line.split(String.valueOf(COMMA_DELIMITER))).trim();
                if (word.endsWith(", \"]")){
                    word = word.substring(2, word.length()-4);
                }else{
                    word = word.substring(1, word.length()-1).toLowerCase();
                }
                bannedWords.add(num, word.toLowerCase());
                num += 1;
            }
            // This checks if the data variable contains any of the banned words in bannedWords.
            for (int i = 0; i < bannedWords.size(); i++){
                if(data.toLowerCase().equals(bannedWords.get(i))){
                    answer = true;
                }
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        // These if statements adds errors to the list errors if the statements if true.
        if (answer == true){
            errors.add(new FieldError(objectName,field,"Banned words found!"));
        }
        else if (data.length() >= 500) {
            errors.add(new FieldError(objectName, field, "Word limit exceeded!"));
        }
//        else if(data.length() <= 5){
//            errors.add(new FieldError(objectName,field,"Length of input insufficient!"));
//        }
//        else if(match.find()){
//            errors.add(new FieldError(objectName,field,"Special characters detected!"));
//        }
        return errors;
    }

    // This function is used to validate data
    public boolean Validate(String data) {
        ArrayList<String> dataWords = new ArrayList<String>();
        dataWords.addAll(Arrays.asList(data.split(" ")));

        final char COMMA_DELIMITER = ',';
        ArrayList<String> bannedWords;
        bannedWords = new ArrayList<>();

        // This gets the file called Terms-to-Block from the resources folder.
        File bW = new File("src/main/resources", "Terms-to-Block.csv");
        // This code reads the csv file from the bW file path and then the while loop formats the data to the correct form
        // and adds them to the list called bannedWords.
        try (BufferedReader bR = new BufferedReader(new FileReader(bW.getAbsolutePath()))) {
            String line;
            String word = null;
            int num = 0;
            while ((line = bR.readLine()) != null) {
                word = Arrays.toString(line.split(String.valueOf(COMMA_DELIMITER))).trim();
                if (word.endsWith(", \"]")) {
                    word = word.substring(2, word.length() - 4);
                } else {
                    word = word.substring(1, word.length() - 1).toLowerCase();
                }
                bannedWords.add(num, word.toLowerCase());
                num += 1;
            }
            // This checks if the data variable contains any of the banned words in bannedWords.
            for (int i = 0; i < bannedWords.size(); i++) {
                if (data.toLowerCase().contains(bannedWords.get(i))) {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    // validate no special characters
    public boolean ValidateSpecialCharacters(String data) {
        Pattern pattern = Pattern.compile("[!#$%&*()_+=|<>?{}\\[\\]~]", Pattern.CASE_INSENSITIVE);
        Matcher match = pattern.matcher(data);
        if(match.find()){
            return false;
        }
        return true;
    }
}
