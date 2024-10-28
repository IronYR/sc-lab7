package com.example.test;

import org.junit.jupiter.api.Test;
import com.example.src.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FileSearcherTest {

    @Test
    public void testFileFoundInRootDirectory() throws IOException {
        // Setup
        File testDirectory = new File("testDir");
        testDirectory.mkdir();
        File existingFile = new File(testDirectory, "existingFile.txt");
        existingFile.createNewFile();

        // Test
        boolean result = FileSearcher.searchFile(testDirectory, "existingFile.txt", true);
        assertTrue(result, "File should be found in the root directory");

        // Cleanup
        existingFile.delete();
        testDirectory.delete();
    }

    @Test
    public void testFileNotFound() throws IOException {
        // Setup
        File testDirectory = new File("testDir");
        testDirectory.mkdir();
        File nonExistingFile = new File(testDirectory, "nonExistingFile.txt");

        // Test
        boolean result = FileSearcher.searchFile(testDirectory, "nonExistingFile.txt", true);
        assertFalse(result, "Non-existing file should not be found");

        // Cleanup
        testDirectory.delete();
    }

    @Test
    public void testSearchInSubDirectory() throws IOException {
        // Setup
        File testDirectory = new File("testDir");
        testDirectory.mkdir();
        File subDirectory = new File(testDirectory, "subDir");
        subDirectory.mkdir();
        File caseInsensitiveFile = new File(subDirectory, "CaseInsensitiveFile.TXT");
        caseInsensitiveFile.createNewFile();

        // Test
        boolean result = FileSearcher.searchFile(testDirectory, "CaseInsensitiveFile.TXT", true);
        assertTrue(result, "File should be found in the subdirectory");

        // Cleanup
        caseInsensitiveFile.delete();
        subDirectory.delete();
        testDirectory.delete();
    }

    @Test
    public void testSearchWithCaseInsensitiveFalse() throws IOException {
        // Setup
        File testDirectory = new File("testDir");
        testDirectory.mkdir();
        File subDirectory = new File(testDirectory, "subDir");
        subDirectory.mkdir();
        File caseInsensitiveFile = new File(subDirectory, "CaseInsensitiveFile.TXT");
        caseInsensitiveFile.createNewFile();

        // Test
        boolean result = FileSearcher.searchFile(testDirectory, "caseinsensitivefile.txt", false);
        assertTrue(result, "Case-insensitive search should find the file even with different case");

        // Cleanup
        caseInsensitiveFile.delete();
        subDirectory.delete();
        testDirectory.delete();
    }

    @Test
    public void testSearchInNonExistentDirectory() {
        // Setup
        File nonExistentDir = new File("nonExistentDir");

        // Test
        boolean result = FileSearcher.searchFile(nonExistentDir, "someFile.txt", true);
        assertFalse(result, "Search in a non-existent directory should return false");
    }

    @Test
    public void testSearchInFileInsteadOfDirectory() throws IOException {
        // Setup
        File regularFile = new File("regularFile.txt");
        regularFile.createNewFile();

        // Test
        boolean result = FileSearcher.searchFile(regularFile, "someFile.txt", true);
        assertFalse(result, "Search in a file (instead of a directory) should return false");

        // Cleanup
        regularFile.delete();
    }

    @Test
    public void testFileNotFoundInSubDirectory() throws IOException {
        // Setup
        File testDirectory = new File("testDir");
        testDirectory.mkdir();
        File subDirectory = new File(testDirectory, "subDir");
        subDirectory.mkdir();

        // Test
        boolean result = FileSearcher.searchFile(testDirectory, "nonExistentFile.txt", true);
        assertFalse(result, "Non-existing file should not be found in subdirectory");

        // Cleanup
        subDirectory.delete();
        testDirectory.delete();
    }

    @Test
    public void testMultipleFilesSearch() throws IOException {
        // Setup
        File testDirectory = new File("testDir");
        testDirectory.mkdir();
        File existingFile = new File(testDirectory, "existingFile.txt");
        existingFile.createNewFile();
        File subDirectory = new File(testDirectory, "subDir");
        subDirectory.mkdir();
        File caseInsensitiveFile = new File(subDirectory, "CaseInsensitiveFile.TXT");
        caseInsensitiveFile.createNewFile();

        // Test
        boolean result1 = FileSearcher.searchFile(testDirectory, "existingFile.txt", true);
        boolean result2 = FileSearcher.searchFile(testDirectory, "CaseInsensitiveFile.TXT", true);
        assertTrue(result1 && result2, "Both files should be found in the directory and subdirectory");

        // Cleanup
        existingFile.delete();
        caseInsensitiveFile.delete();
        subDirectory.delete();
        testDirectory.delete();
    }

    @Test
    public void testEmptyDirectory() throws IOException {
        // Setup
        File testDirectory = new File("testDir");
        testDirectory.mkdir();
        File emptyDir = new File(testDirectory, "emptyDir");
        emptyDir.mkdir();

        // Test
        boolean result = FileSearcher.searchFile(emptyDir, "anyFile.txt", true);
        assertFalse(result, "Search in an empty directory should return false");

        // Cleanup
        emptyDir.delete();
        testDirectory.delete();
    }

    @Test
    public void testDirectoryWithNoPermission() throws IOException {
        // Setup
        File testDirectory = new File("testDir");
        testDirectory.mkdir();
        File protectedDir = new File(testDirectory, "protectedDir");
        protectedDir.mkdir();
        protectedDir.setReadable(false); // Make directory unreadable

        // Test
        boolean result = FileSearcher.searchFile(protectedDir, "someFile.txt", true);
        assertFalse(result, "Search in a directory without read permissions should return false");

        // Cleanup
        protectedDir.setReadable(true); // Restore permission to allow deletion
        protectedDir.delete();
        testDirectory.delete();
    }
}
