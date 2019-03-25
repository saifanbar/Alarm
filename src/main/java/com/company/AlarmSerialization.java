package com.company;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Scanner;

import static java.nio.file.Files.walk;

public class AlarmSerialization {

    Alarm alarm = new Alarm();


    public AlarmSerialization() throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {
    }

    LocalTime input = alarm.inputAlarmTime();
    String filename;
    String pathName = "C:\\Users\\Alien\\Desktop\\Alarm\\serFiles\\";

    public void serializeAlarm() {
        try {
            filename = "alarmTime.ser";
            //Saving of object in a file
            if (filename.equalsIgnoreCase(filename)) {
                filename = "alarmTime_" + input.getHour() + input.getMinute() + ".ser";
                System.out.println(filename);
            }
            FileOutputStream file = new FileOutputStream(pathName + filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            System.out.println(filename);
            // Method for serialization of object
            out.writeObject(input);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void deserializeAlarm() throws IOException, ClassNotFoundException {
        File root = new File(pathName);
        File[] list = root.listFiles();
        int i = 0;
        if (list == null) return;

        System.out.println("What time would you like to wake up? Press the number corresponding to the time.");

        for (File f : list) {

            if (f.isDirectory()) {
                walk(Paths.get(f.getAbsolutePath()));
                System.out.println("Dir:" + f.getAbsoluteFile());
            } else {
                i++;
                System.out.println(i + ") " + f.getName().replaceAll("[^0-9]", ""));

            }
        }
        Scanner scanner = new Scanner(System.in);

        int choiceInput = scanner.nextInt();

        FileInputStream fileInputStream = new FileInputStream(list[choiceInput - 1]);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();

        System.out.println(object);

    }
}
