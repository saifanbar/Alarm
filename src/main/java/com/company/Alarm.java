package com.company;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Alarm {


    Pattern pattern;
    AlarmAudio audio = new AlarmAudio();

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

    public LocalTime getLocalTime() {

        LocalTime time = LocalTime.now();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

        String timeString = time.format(dtf);

        LocalTime timeLabel = LocalTime.parse(timeString, dtf);

        return timeLabel;
    }

    public void runAlarm(LocalTime wakeupTime) {

        Boolean timeToWakeUp = getLocalTime().equals(wakeupTime);

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
                    break;
                }

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        audio.setAlarmOff("C:\\Users\\SA20018601\\Downloads\\BOMB_SIREN-BOMB_SIREN.wav");


    }


}
