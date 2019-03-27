package com.company;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Init {
    public void init() throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException, ClassNotFoundException {
        Alarm objAlarm = new Alarm();
        AlarmSerialization as = new AlarmSerialization();
        //int input = 0;


        Scanner scanner = new Scanner(System.in);
        int input = 0;

        System.out.println("Select one of the options: \n");
        System.out.println(" 1) Enter alarm time.");
        System.out.println(" 2) Save alarm time");
        System.out.println(" 3) Load alarm time.");
        System.out.print(" Enter a number: ");

        do {
            try {
                input = scanner.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println("Please select one of the options by pressing the corresponding number");
                scanner.nextLine();
            }
            if(input != 1 && input != 2 && input != 3) {
                System.out.println("Please select one of the options listed below\n");
                System.out.println(" 1) Enter alarm time.");
                System.out.println(" 2) Save alarm time");
                System.out.println(" 3) Load alarm time.");
                System.out.print(" Enter a number: ");

            }
        }while (input != 1 && input != 2 && input != 3);
        if (input == 1) {
            LocalTime localTimeInput = objAlarm.inputAlarmTime();
            objAlarm.setParsedAlarmTimeString(localTimeInput);
            objAlarm.runAlarm(objAlarm.getParsedAlarmTimeString());
        } else if (input == 2) {
            as.serializeAlarm();
            init();
        } else if (input == 3) {

            objAlarm.runAlarm(as.deserializeAlarm());
        }
    }
}
