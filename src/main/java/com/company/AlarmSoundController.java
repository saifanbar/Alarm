package com.company;
import java.io.File;
import java.io.IOException;


import javax.sound.sampled.*;


//https://www.geeksforgeeks.org/play-audio-file-using-java/

/*
    Create a while loop to allow snooze functionality such as adding time to alarm and stopping alarm.
    A switch statement will be created to facilitate the snooze functionality
    The user should be able to input what they want to do when the alarm goes off.
    Stopping the alarm will break the while loop and stop the sound from playing.
    When given input, the program should then call the pertaining function i.e. pause, stop using a switch statement

        - User inputs "Y" for snooze -> call the pause() -> add snooze time to LocalTime.now() -> prompt play/restart of alarm
        - All sound funtionality is in "AlarmSoundController.java"

    refer to the while and switch statements in: //https://www.geeksforgeeks.org/play-audio-file-using-java/

 */

public class AlarmSoundController implements LineListener{
    // to store current position
    Long currentFrame;
    Clip clip;
    boolean playCompleted;

    // current status of clip
    String status;

    AudioInputStream audioInputStream;

    // constructor to initialize streams and clip

    //static String filePath = "C:\\Users\\SA20018601\\Downloads\\analog-watch-alarm_daniel-simion.wav";
    File audioFile = new File("C:\\Users\\SA20018601\\Downloads\\analog-watch-alarm_daniel-simion.wav");

    public AlarmSoundController() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException, InterruptedException{
        // create AudioInputStream object
        audioInputStream =
                AudioSystem.getAudioInputStream(audioFile);

        System.out.println(audioFile);

        AudioFormat format = audioInputStream.getFormat();

        DataLine.Info info = new DataLine.Info(Clip.class, format);

        clip = (Clip) AudioSystem.getLine(info);

        clip.addLineListener(this);

        // create clip reference
        //clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    // Method to play the audio

    public void play()
    {
        //start the clip
        clip.start();
        while(!playCompleted) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

    }

    // Method to pause the audio
    public void pause()
    {

        this.currentFrame =
                this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    // Method to resume the audio
    public void resumeAudio() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {

        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    // Method to restart the audio
    public void restart() throws IOException, LineUnavailableException,
            UnsupportedAudioFileException
    {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }

    // Method to stop the audio
    public void stop() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    // Method to jump over a specific part
    public void jump(long c) throws UnsupportedAudioFileException, IOException,
            LineUnavailableException
    {
        if (c > 0 && c < clip.getMicrosecondLength())
        {
            clip.stop();
            clip.close();
            resetAudioStream();
            currentFrame = c;
            clip.setMicrosecondPosition(c);
            this.play();
        }
    }

    // Method to reset audio stream
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException
    {
        audioInputStream = AudioSystem.getAudioInputStream(
                audioFile);
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
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
