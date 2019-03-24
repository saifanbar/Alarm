package main.java.com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.*;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.Gson;


import static com.sun.activation.registries.LogSupport.log;



public class AlarmReadWrite {

    private static Gson gson = new Gson();



    public static void alarmWriteToFile (String myData){
        String alarm_file_location = "C:\\Users\\Alien\\Desktop\\Alarm Folder\\Alarm_Times.txt";
        File alarmFile = new File(alarm_file_location);
        if (!alarmFile.exists()) {
            try {
                File directory = new File(alarmFile.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                alarmFile.createNewFile();
            } catch (IOException e) {
                log("Excepton Occured: " + e.toString());
            }
        }
        try {
            // Convenience class for writing character files
            FileWriter alarmWriter;
            alarmWriter = new FileWriter(alarmFile.getAbsoluteFile(), true);


            // Writes text to a character-output stream
            BufferedWriter bufferWriter = new BufferedWriter(alarmWriter);
            bufferWriter.write(myData.toString());
            bufferWriter.close();

            log("Alarm data saved at file location: " + alarm_file_location + " Data: " + myData + "\n");
        } catch (IOException e) {
            log("Hmm.. Got an error while saving Alarm data to file " + e.toString());
        }
    }

    public static void alarmReadFromFile() {
        String alarm_file_location = "C:\\Users\\Alien\\Desktop\\Alarm Folder\\Alarm_Times.txt";
        File alarmFile = new File(alarm_file_location);
        if (!alarmFile.exists())
            log("File doesn't exist");

        InputStreamReader isReader;
        try {
            isReader = new InputStreamReader(new FileInputStream(alarmFile), "UTF-8");

            JsonReader myReader = new JsonReader(isReader);
            Alarm alarm = gson.fromJson(myReader, Alarm.class);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(myReader);

            String prettyJsonString = gson.toJson(je);

            System.out.println(prettyJsonString);

            log("Alarm Hour is: " + alarm.getParsedAlarmTimeString());


        } catch (Exception e) {
            log("error load cache from file " + e.toString());
        }

        log("\nAlarm Data loaded successfully from file " + alarm_file_location);

    }


    private static void log(String string) {
        System.out.println(string);
    }
}

