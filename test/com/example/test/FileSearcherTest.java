package com.example.test;

import org.junit.jupiter.api.Test;

import com.example.src.FileSearcher;

import java.io.File;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

public class FileSearcherTest {

    @Test
    public void testFileExists() {
        File rootDir = new File("testDir");
        rootDir.mkdir();
        File testFile = new File(rootDir, "testFile.txt");
        try {
            testFile.createNewFile();
            assertTrue(FileSearcher.searchFile(rootDir, "testFile.txt"));
        } catch (Exception e) {
        } finally {
            testFile.delete();
            rootDir.delete();
        }
    }
    @Test
    public void testFileExistsInNestedDir() {
        File rootDir = new File("testDir");
        File nestedDir1 = new File(rootDir, "nestedDir1");
        File nestedDir2 = new File(rootDir, "nestedDir2");
        
        // Create directories for testing
        rootDir.mkdir();
        nestedDir1.mkdir();
        nestedDir2.mkdir();
        
        // Create the test file in nestedDir1
        File testFile = new File(nestedDir1, "testFile.txt");
        
        try {
            testFile.createNewFile();  // Create the file inside nestedDir1
            
            // Assert that searchFile finds "testFile.txt" within nestedDir1 in rootDir
            assertTrue(FileSearcher.searchFile(rootDir, "testFile.txt"));
            
        } catch (Exception e) {
        } finally {
            // Clean up: delete the file and directories
            testFile.delete();
            nestedDir1.delete();
            nestedDir2.delete();
            rootDir.delete();
        }
    }

    @Test
    public void testFileDoesNotExist() {
        File rootDir = new File("testDir");
        rootDir.mkdir();
        assertFalse(FileSearcher.searchFile(rootDir, "nonExistentFile.txt"));
        rootDir.delete();
    }

    @Test
    public void testInvalidDirectory() {
        File invalidDir = new File("invalidDir");
        assertFalse(invalidDir.exists());
        assertFalse(FileSearcher.searchFile(invalidDir, "testFile.txt"));
    }
}
