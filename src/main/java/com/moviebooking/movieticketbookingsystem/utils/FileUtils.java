package com.moviebooking.movieticketbookingsystem.utils;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class FileUtils {
    private static final String DATA_DIR = "src/main/resources/data/";

    // Create the data directory if it doesn't exist
    static {
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
        } catch (IOException e) {
            System.err.println("Failed to create data directory: " + e.getMessage());
        }
    }

    /**
     * Reads all lines from a text file.
     *
     * @param filename Name of the file (e.g., "movies.txt")
     * @return List of lines from the file, or empty list if file doesn't exist
     */
    public static List<String> readFromFile(String filename) {
        Path filePath = Paths.get(DATA_DIR + filename);

        try {
            if (Files.exists(filePath)) {
                return Files.readAllLines(filePath);
            }
        } catch (IOException e) {
            System.err.println("Error reading file " + filename + ": " + e.getMessage());
        }

        return new ArrayList<>();
    }

    /**
     * Writes data to a text file (overwrites existing content).
     *
     * @param filename Name of the file (e.g., "users.txt")
     * @param data List of strings to write to the file
     * @return true if successful, false otherwise
     */
    public static boolean writeToFile(String filename, List<String> data) {
        Path filePath = Paths.get(DATA_DIR + filename);

        try {
            Files.write(filePath, data);
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file " + filename + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Appends a single line to a text file.
     *
     * @param filename Name of the file (e.g., "bookings.txt")
     * @param line The line to append
     * @return true if successful, false otherwise
     */
    public static boolean appendToFile(String filename, String line) {
        Path filePath = Paths.get(DATA_DIR + filename);

        try {
            Files.write(filePath, (line + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            System.err.println("Error appending to file " + filename + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Checks if a file exists.
     *
     * @param filename Name of the file
     * @return true if file exists, false otherwise
     */
    public static boolean fileExists(String filename) {
        return Files.exists(Paths.get(DATA_DIR + filename));
    }

    /**
     * Deletes a file.
     *
     * @param filename Name of the file to delete
     * @return true if successful, false otherwise
     */
    public static boolean deleteFile(String filename) {
        try {
            return Files.deleteIfExists(Paths.get(DATA_DIR + filename));
        } catch (IOException e) {
            System.err.println("Error deleting file " + filename + ": " + e.getMessage());
            return false;
        }
    }
}