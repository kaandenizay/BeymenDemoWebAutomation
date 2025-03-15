package com.utilities;

import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Log4j2
public class TextProcessManager {

    static String filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "testData", "example.txt").toString();
    static File file = new File(filePath);
    ;
    private static final Path dirPath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "testData");
    private static final String filePostfix = ".txt";

    private static void createFile() {
        try {
            if (file.createNewFile()) {
                log.info("File created: " + file.getName());
//                System.out.println("File created: " + file.getName());
            } else {
                log.info("File already exists.");
//                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            log.error("An error occurred during file creation");
            e.printStackTrace();
        }
    }

    public static void writeToFile(String text) {
        createFile();
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(text + "\n");
            writer.close();
        } catch (IOException e) {
            log.error("An error occurred during file writing");
            throw new RuntimeException(e);
        }
        log.info("Successfully wrote to the file.");
//        System.out.println("Successfully wrote to the file.");
    }

    public static String readFile(String searchText) {
        String line = "";
        LinkedList<String> lines = new LinkedList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);


            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(searchText)) {
                    System.out.println(line); // Print each line
                    lines.addFirst(line);
                }
            }
            bufferedReader.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return (Objects.requireNonNull(lines.getFirst()).split(":"))[1].trim();
    }

    public static void deleteFile() {
        File directory = dirPath.toFile();
        File[] files = directory.listFiles((dir, name) -> name.endsWith(filePostfix));

        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.delete()) {
                    System.out.println("Deleted file: " + file.getName());
                } else {
                    System.out.println("Failed to delete file: " + file.getName());
                }

            }
        } else {
            System.out.println("No files found with prefix: " + filePostfix);
        }
    }


}
