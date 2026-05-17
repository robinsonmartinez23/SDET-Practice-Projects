package com.sdet.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.log4j.Logger;

/**
 * Custom TestNG listener for logging test results
 */
public class TestListener implements ITestListener {
    protected static Logger logger = Logger.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("========== TEST START: " + result.getName() + " ==========");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("✓ TEST PASSED: " + result.getName());
        logger.info("Test Duration: " + (result.getEndMillis() - result.getStartMillis()) + " ms");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("✗ TEST FAILED: " + result.getName());
        logger.error("Failure Message: " + result.getThrowable().getMessage());
        logger.error("Test Duration: " + (result.getEndMillis() - result.getStartMillis()) + " ms");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("⊘ TEST SKIPPED: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.warn("TEST FAILED WITHIN SUCCESS %: " + result.getName());
    }
}
