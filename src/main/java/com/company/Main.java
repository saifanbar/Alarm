package main.java.com.company;

import com.google.gson.*;
//import com.google.gson.stream.JsonReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
       /*
        // write your code here
        Alarm objAlarm = new Alarm();
        AlarmReadWrite objAlarmReadWrite = new AlarmReadWrite();
        Gson gson = new Gson();
        Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        //JsonElement je = jp.parse();



        LocalTime input = objAlarm.inputAlarmTime();

        objAlarm.setParsedAlarmTimeString(input);
        System.out.println("This is the input variable "+input);
        System.out.println("This is the get method "+objAlarm.getParsedAlarmTimeString());

        objAlarmReadWrite.alarmWriteToFile(gson.toJson(objAlarm.getParsedAlarmTimeString()));

        objAlarmReadWrite.alarmReadFromFile();

        System.out.println(objAlarm.getParsedAlarmTimeString());

        //AlarmSoundController soundController = new AlarmSoundController();

        //soundController.play();

        //objAlarm.runAlarm(objAlarm.inputAlarmTime());
*/
        File jsonInputFile = new File("C:\\Users\\Alien\\Desktop\\Alarm Folder\\Alarm_Times.txt");
        InputStream is;
        try {
            is = new FileInputStream(jsonInputFile);
            // Create JsonReader from Json.
            JsonReader reader = Json.createReader(is);
            // Get the JsonObject structure from JsonReader.
            JsonObject alarmObj = reader.readObject();
            reader.close();
            // read string data
            System.out.println("Hour: " + alarmObj.getString("hour"));
            // read integer data
            System.out.println("Minute: " + alarmObj.getString("minute"));

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    }

