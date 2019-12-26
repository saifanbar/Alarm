package com.company;



import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;

public class DynamicAlarm {

    Alarm alarm = new Alarm();

    public DynamicAlarm() throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {
    }

    public LocalTime totalTimeCalculation (long normalDrive, long durationInTraffic) {
        long getReadyTime = 45;
        long drivingDuration;
        LocalTime arrivalTime;
        LocalTime totalTime;
        LocalTime totalTimeCalculated = null;
        LocalTime hour;
        LocalTime leaveTime;

        totalTime = LocalTime.of(0,0);
        arrivalTime = LocalTime.of(10,37);
        hour = LocalTime.of(1,0);


        if(durationInTraffic > normalDrive){
            drivingDuration = (((durationInTraffic - normalDrive) + normalDrive) + getReadyTime);
            totalTimeCalculated = totalTime.plusMinutes(drivingDuration);
         } else if(durationInTraffic <= normalDrive){


            drivingDuration = (normalDrive + getReadyTime);
            totalTimeCalculated = totalTime.plusMinutes(drivingDuration);
        }

        int totalTimeCalculatedMinute = totalTimeCalculated.getMinute();
        int totalTimeCalculatedHour = totalTimeCalculated.getHour();


        if (totalTimeCalculated.isAfter(hour)){

            leaveTime = arrivalTime.minusMinutes(totalTimeCalculatedMinute);
            leaveTime = leaveTime.minusHours(totalTimeCalculatedHour);

        } else {
            leaveTime = arrivalTime.minusMinutes(totalTimeCalculatedMinute);
        }

            System.out.println("The alarm time is: " + leaveTime);
            System.out.println("The get ready time is: " + getReadyTime);
            System.out.println("The drive time is: " + durationInTraffic);

        return leaveTime;

    }

}
