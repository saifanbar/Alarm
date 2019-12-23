package com.company;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DynamicAlarm {

    Alarm alarm = new Alarm();

    public DynamicAlarm() throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {
    }

    public void totalTimeCalculation (int normalDrive, int durationInTraffic) {
        int getReadyTime = 0;
        int drivingDuration;
        LocalTime arrivalTime;
        int totalTime;

        if(durationInTraffic > normalDrive){
            drivingDuration = ((durationInTraffic - normalDrive) + normalDrive);
            totalTime = (getReadyTime + drivingDuration);

            alarm.getLocalTime();

        } else if(durationInTraffic <= normalDrive){
            totalTime = (normalDrive + getReadyTime);

        }

    }

}
