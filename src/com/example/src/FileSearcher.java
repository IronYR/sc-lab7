package com.example.src;

import java.io.File;

public class FileSearcher {
    
   

    /**
     * Recursively searches for the specified file in the given directory and subdirectories.
     *
     * @param directory - the root directory where the search starts.
     * @param fileName - the name of the file to search for.
     * @return true if the file is found, false otherwise.
     */
    public static boolean searchFile(File directory, String fileName) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    if (searchFile(file, fileName)) {
                        return true;
                    }
                } else if (file.getName().equals(fileName)) {
                    System.out.println("File found: " + file.getAbsolutePath());
                    return true;
                }
            }
        }
        return false;
    }
}
