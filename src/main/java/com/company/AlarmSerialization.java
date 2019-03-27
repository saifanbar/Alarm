package com.company;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Scanner;

import static java.nio.file.Files.walk;

public class AlarmSerialization {

    public AlarmSerialization() throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {
    }


    Alarm alarm = new Alarm();
    String filename;
    String pathName = "C:\\Users\\SA20018601\\Desktop\\TempAlarm\\serFiles\\";


    public void serializeAlarm() {
        System.out.println(" --- Save alarm time ---\n");
        LocalTime input = alarm.inputAlarmTime();
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

    public LocalTime deserializeAlarm() throws IOException, ClassNotFoundException {
        File root = new File(pathName);
        File[] list = root.listFiles();
        int i = 0;
        if (list == null) return null;

        System.out.println("\nWhat time would you like to wake up? Press the number corresponding to the time.\n");

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

        return (LocalTime) object;

    }
}
