package com.company;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        // write your code here

            Scanner scanner = new Scanner(System.in);
            AlarmSoundController audioPlayer = new AlarmSoundController();

            String input = scanner.nextLine();

            audioPlayer.play();
            System.out.println("type pause to pause");
            if(input == "pause"){

                audioPlayer.pause();
                System.out.println("User has paused");
            }






        //objAlarmSoundController.resetAudioStream();



        //objAlarm.runAlarm(objAlarm.inputAlarmTime());


    }
}
