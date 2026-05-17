package com.sdet.utils;

import org.apache.log4j.Logger;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * File utilities for handling Excel, CSV, JSON operations
 * Multiple approaches for reading different file types
 */
public class FileUtils {
    private static Logger logger = Logger.getLogger(FileUtils.class);

    /**
     * Approach 1: Read CSV file
     * Returns array of string arrays (each row)
     * Simple approach using buffered reader
     */
    public static String[][] readCSV(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            StringBuilder content = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();

            String[] lines = content.toString().split("\n");
            String[][] data = new String[lines.length][];

            for (int i = 0; i < lines.length; i++) {
                data[i] = lines[i].split(",");
            }

            logger.info("CSV file read successfully: " + filePath + " (" + lines.length + " rows)");
            return data;
        } catch (IOException e) {
            logger.error("Error reading CSV file: " + e.getMessage());
            return new String[0][0];
        }
    }

    /**
     * Approach 2: Read JSON file as string
     * Useful for loading JSON test data or configuration
     */
    public static String readJSON(String filePath) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(filePath));
            String jsonContent = new String(encoded);
            logger.info("JSON file read successfully: " + filePath);
            return jsonContent;
        } catch (IOException e) {
            logger.error("Error reading JSON file: " + e.getMessage());
            return null;
        }
    }

    /**
     * Approach 3: Write data to file
     * Useful for logging test results to file
     */
    public static void writeToFile(String filePath, String content) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(content);
            writer.close();
            logger.info("Data written to file: " + filePath);
        } catch (IOException e) {
            logger.error("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Approach 4: Append data to file
     * Useful for adding logs incrementally
     */
    public static void appendToFile(String filePath, String content) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(content + "\n");
            writer.close();
            logger.info("Data appended to file: " + filePath);
        } catch (IOException e) {
            logger.error("Error appending to file: " + e.getMessage());
        }
    }

    /**
     * Approach 5: Check if file exists
     * Simple validation before processing
     */
    public static boolean fileExists(String filePath) {
        boolean exists = new File(filePath).exists();
        if (exists) {
            logger.info("File exists: " + filePath);
        } else {
            logger.warn("File not found: " + filePath);
        }
        return exists;
    }

    /**
     * Approach 6: Delete file
     * Useful for cleanup after tests
     */
    public static void deleteFile(String filePath) {
        try {
            if (new File(filePath).delete()) {
                logger.info("File deleted: " + filePath);
            } else {
                logger.warn("Failed to delete file: " + filePath);
            }
        } catch (Exception e) {
            logger.error("Error deleting file: " + e.getMessage());
        }
    }

    /**
     * Approach 7: Get file size
     * Useful for validation
     */
    public static long getFileSize(String filePath) {
        try {
            long size = new File(filePath).length();
            logger.info("File size: " + size + " bytes");
            return size;
        } catch (Exception e) {
            logger.error("Error getting file size: " + e.getMessage());
            return 0;
        }
    }

    /**
     * Approach 8: Read Excel file
     * NOTE: Requires Apache POI library (poi and poi-ooxml)
     * This is a placeholder - actual implementation would use POI API
     */
    public static String[][] readExcel(String filePath, String sheetName) {
        // Placeholder - requires Apache POI dependency
        // Actual implementation would use:
        // FileInputStream fis = new FileInputStream(filePath);
        // Workbook wb = new XSSFWorkbook(fis);
        // Sheet sheet = wb.getSheet(sheetName);
        // ... iterate rows and cells

        logger.warn("Excel reading requires Apache POI library implementation");
        return new String[0][0];
    }

    /**
     * Usage examples:
     *
     * // Approach 1: Read CSV
     * String[][] csvData = FileUtils.readCSV("testdata/users.csv");
     * String email = csvData[0][0];
     *
     * // Approach 2: Read JSON
     * String jsonString = FileUtils.readJSON("testdata/config.json");
     *
     * // Approach 3: Write to file
     * FileUtils.writeToFile("results/test_results.txt", "Test passed");
     *
     * // Approach 4: Append to file
     * FileUtils.appendToFile("logs/test.log", "New log entry");
     *
     * // Approach 5: Check if file exists
     * if (FileUtils.fileExists("testdata/data.csv")) {
     *     // process file
     * }
     *
     * // Approach 6: Delete file
     * FileUtils.deleteFile("temp/temp_file.txt");
     *
     * // Approach 7: Get file size
     * long size = FileUtils.getFileSize("large_file.dat");
     *
     * // Approach 8: Read Excel
     * String[][] excelData = FileUtils.readExcel("testdata/users.xlsx", "Sheet1");
     */
}
