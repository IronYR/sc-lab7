package com.example.src;

import java.io.File;

public class FileSearcher {

    // Function to recursively search for the file in the directory and subdirectories
	public static boolean searchFile(File directory, String fileName, boolean caseSensitive) {
        // Check if the directory exists and is a directory
        if (directory == null || !directory.exists() || !directory.isDirectory()) {
            return false;
        }

        // Get all files and subdirectories in the directory
        File[] files = directory.listFiles();
        if (files == null) {
            return false;
        }

        // Iterate through the files/subdirectories
        for (File file : files) {
            // Case-sensitive or case-insensitive comparison
            if (caseSensitive) {
                if (file.isFile() && file.getName().equals(fileName)) {
                    System.out.println("File found: " + file.getAbsolutePath());
                    return true;
                }
            } else {
                if (file.isFile() && file.getName().equalsIgnoreCase(fileName)) {
                    System.out.println("File found: " + file.getAbsolutePath());
                    return true;
                }
            }

            // If the current file is a directory, recursively search it
            if (file.isDirectory()) {
                boolean foundInSubDir = searchFile(file, fileName, caseSensitive);
                if (foundInSubDir) {
                    return true;
                }
            }
        }

        // Return false if file not found
        return false;
    }

}
