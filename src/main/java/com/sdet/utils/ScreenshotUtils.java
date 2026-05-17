package com.sdet.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Screenshot capture utility
 * Multiple approaches for capturing screenshots at different points
 */
public class ScreenshotUtils {
    private static Logger logger = Logger.getLogger(ScreenshotUtils.class);
    private static String screenshotDir = "target/screenshots/";

    static {
        // Create screenshots directory if not exists
        new File(screenshotDir).mkdirs();
    }

    /**
     * Approach 1: Capture screenshot with timestamp
     * Basic screenshot capture with automatic timestamp
     */
    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String fileName = testName + "_" + timestamp + ".png";
            String filePath = screenshotDir + fileName;

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(filePath));

            logger.info("Screenshot captured: " + filePath);
            return filePath;
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Approach 2: Capture with custom path
     * Useful for organizing screenshots by test class
     */
    public static String captureScreenshot(WebDriver driver, String folderPath, String testName) {
        try {
            String fullPath = screenshotDir + folderPath + "/";
            new File(fullPath).mkdirs();

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String fileName = testName + "_" + timestamp + ".png";
            String filePath = fullPath + fileName;

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(filePath));

            logger.info("Screenshot captured: " + filePath);
            return filePath;
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Approach 3: Capture and return base64 string
     * Useful for embedding in HTML reports without file I/O
     */
    public static String getScreenshotAsBase64(WebDriver driver) {
        try {
            String base64Screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BASE64);
            logger.info("Screenshot captured as base64");
            return base64Screenshot;
        } catch (Exception e) {
            logger.error("Failed to capture base64 screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Approach 4: Capture as File object
     * Useful when you need to manipulate file after capture
     */
    public static File getScreenshotAsFile(WebDriver driver) {
        try {
            File screenshotFile = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            logger.info("Screenshot captured as file");
            return screenshotFile;
        } catch (Exception e) {
            logger.error("Failed to capture screenshot as file: " + e.getMessage());
            return null;
        }
    }

    /**
     * Approach 5: Clear old screenshots before test run
     * Useful in setup method to clean previous runs
     */
    public static void clearScreenshots() {
        try {
            File dir = new File(screenshotDir);
            if (dir.exists()) {
                FileUtils.cleanDirectory(dir);
                logger.info("Old screenshots cleared");
            }
        } catch (Exception e) {
            logger.error("Failed to clear screenshots: " + e.getMessage());
        }
    }

    /**
     * Usage examples:
     *
     * // Approach 1: Simple capture with timestamp
     * ScreenshotUtils.captureScreenshot(driver, "testLogin");
     * // Result: target/screenshots/testLogin_2026-05-17_04-35-22.png
     *
     * // Approach 2: Capture with folder organization
     * ScreenshotUtils.captureScreenshot(driver, "LoginTests", "testValidLogin");
     * // Result: target/screenshots/LoginTests/testValidLogin_2026-05-17_04-35-22.png
     *
     * // Approach 3: Get base64 for report embedding
     * String base64 = ScreenshotUtils.getScreenshotAsBase64(driver);
     * // Use in HTML report: <img src="data:image/png;base64,{base64}"/>
     *
     * // Approach 4: Get file for further processing
     * File screenshot = ScreenshotUtils.getScreenshotAsFile(driver);
     * // Send via email, upload to S3, etc.
     *
     * // Approach 5: Clear old screenshots
     * ScreenshotUtils.clearScreenshots();  // In setup method
     */
}
