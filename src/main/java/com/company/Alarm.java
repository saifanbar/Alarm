package com.company;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;



public class Alarm {


    Pattern pattern;
    AlarmAudio audio = new AlarmAudio();
    AlarmSoundController alarmSoundController;
    LocalTime parsedAlarmTimeString;

    public Alarm() throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {

    }

    public LocalTime inputAlarmTime() {


        Scanner scanner = new Scanner(System.in);

        System.out.println("Input alarm time in HH:MM format: ");

        String alarmTimeString = scanner.nextLine();
        while (!pattern.matches("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", alarmTimeString)) {
            System.out.println("Please input time as HH:MM");

            alarmTimeString = scanner.nextLine();
        }

        LocalTime parsedAlarmTimeString = LocalTime.parse(alarmTimeString);

        return parsedAlarmTimeString;
    }

    public void setParsedAlarmTimeString(LocalTime parsedAlarmTimeString){
        this.parsedAlarmTimeString = parsedAlarmTimeString;
    }

    public LocalTime getParsedAlarmTimeString() {
        return parsedAlarmTimeString;
    }

    public LocalTime getLocalTime() {

        LocalTime time = LocalTime.now();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

        String timeString = time.format(dtf);

        LocalTime timeLabel = LocalTime.parse(timeString, dtf);

        return timeLabel;
    }

    public void runAlarm(LocalTime wakeupTime) throws UnsupportedAudioFileException, IOException, LineUnavailableException {


        boolean timeToWakeUp = getLocalTime().equals(wakeupTime);


        System.out.println("The local time is: " + getLocalTime());
        System.out.println("The wake up time is: " + wakeupTime);
        System.out.println(timeToWakeUp);

        while (!timeToWakeUp) {
            try {
                Thread.sleep(1000);
                timeToWakeUp = getLocalTime().equals(wakeupTime);
                System.out.println(LocalTime.now());
                if (timeToWakeUp) {
                    System.out.println("Time to wake up!!");


                    AlarmSoundController soundController = new AlarmSoundController();
                    soundController.play();



                }

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }



    }


}
