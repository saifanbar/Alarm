package com.company;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.MINUTES;


public class AlarmSoundController implements LineListener {
    // to store current position
    Long currentFrame;
    Clip clip;
    boolean playCompleted;
    Alarm alarm;

    AudioInputStream audioInputStream;

    // constructor to initialize streams and clip
    File audioFile = new File("Sounds/analog-watch-alarm_daniel-simion.wav");


    public AlarmSoundController() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {


        // create AudioInputStream object
        audioInputStream = AudioSystem.getAudioInputStream(audioFile);

        System.out.println(audioFile);

        AudioFormat format = audioInputStream.getFormat();

        DataLine.Info info = new DataLine.Info(Clip.class, format);

        clip = (Clip) AudioSystem.getLine(info);

        clip.addLineListener(this);

        // open audioInputStream to the clip
        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    // Method to play the audio

    public void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {


        clip.start();
        Scanner scanner = new Scanner(System.in);
        while (!playCompleted) {
            System.out.println("Would you like to snooze for 10 minutes? 'Y/N'");
            String snoozeAnswer = scanner.nextLine().toUpperCase();
            //boolean check = snoozeAnswer.equals("N") || snoozeAnswer.equals("Y");

            while (!snoozeAnswer.equals("N") && !snoozeAnswer.equals("Y")) {

                System.out.println("Please enter 'Y' for yes or 'N' for no. ");
                snoozeAnswer = scanner.nextLine().toUpperCase();
            }
            gotoChoice(snoozeAnswer);

            scanner.close();
        }

    }


    // Method to stop the audio
    public void stop() {

        currentFrame = 0L;
        clip.stop();
        clip.close();
    }


    public void snooze() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {

        alarm = new Alarm();

        long snoozeTime = 1;
        LocalTime currentTime = alarm.getLocalTime();
        LocalTime snoozePlusCurrentTime = currentTime.plus(snoozeTime, MINUTES);
        boolean timeToWakeUp = alarm.getLocalTime().equals(snoozePlusCurrentTime);

        stop();
        System.out.println("The current time is: " + currentTime);
        System.out.println("The snooze time is: " + snoozePlusCurrentTime);
        while (!timeToWakeUp) {
            try {
                Thread.sleep(1000);
                timeToWakeUp = alarm.getLocalTime().equals(snoozePlusCurrentTime);

                System.out.println(LocalTime.now());
                if (timeToWakeUp) {
                    System.out.println("Snooze time is up, playing alarm!!");
                    AlarmSoundController soundController = new AlarmSoundController();
                    soundController.play();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void gotoChoice(String answer) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        switch (answer) {
            case "Y":
                snooze();
                break;
            case "N":
                stop();
                break;
        }
    }


    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();

        if (type == LineEvent.Type.START) {
            System.out.println("Playback started.");

        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
            System.out.println("Playback completed.");
        }
    }
}
