package com.company;

import org.json.simple.parser.ParseException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.time.LocalTime;



public class Main {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException, ParseException, ClassNotFoundException {

        // Code to run Alarm and snoozing functionality

        Alarm objAlarm = new Alarm();

        LocalTime input = objAlarm.inputAlarmTime();
        objAlarm.setParsedAlarmTimeString(input);
        objAlarm.runAlarm(objAlarm.getParsedAlarmTimeString());


        //Code to run Saving and loading functionality
        AlarmSerialization as = new AlarmSerialization();
        as.serializeAlarm();
        as.deserializeAlarm();


    }
}

