package com.company;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        // write your code here

        Alarm objAlarm = new Alarm();

        //AlarmSoundController soundController = new AlarmSoundController();

        //soundController.play();

        objAlarm.runAlarm(objAlarm.inputAlarmTime());


    }
}
