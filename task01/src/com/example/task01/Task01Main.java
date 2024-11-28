package com.example.task01;

import java.io.*;
import java.nio.channels.Pipe;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getName());
        processBuilder.directory(file.getParentFile());


        Process process = processBuilder.start();
        process.waitFor();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            while (reader.ready()) {
                String line = reader.readLine();
                if (line.contains("format.tags.title")) {
                    line = line.split("=")[1].replace("\"", "");
                    return line;
                }
                System.out.println(line);
            }

        }
        return "Not found";
    }
}
