package com.example.task02;

import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(listFiles(Paths.get("task02/src/main/resources/")));
    }

    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException {
        List<Path> output = new ArrayList<>();

        try(Stream<Path> stream = Files.walk(rootDir)) {

            stream.forEach(p -> {
                if (!p.toFile().isDirectory())
                    output.add(p);
            });
        }
        return output;
    }
}
