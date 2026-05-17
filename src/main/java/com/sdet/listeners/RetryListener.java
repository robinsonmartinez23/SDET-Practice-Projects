package com.sdet.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.apache.log4j.Logger;

/**
 * Retry analyzer for handling flaky tests
 * Automatically retries failed tests up to specified count
 */
public class RetryListener implements IRetryAnalyzer {
    private static Logger logger = Logger.getLogger(RetryListener.class);

    // Approach 1: Fixed retry count
    private int retryCount = 0;
    private int maxRetryCount = 2;  // Change this to adjust retry attempts

    /**
     * Approach 2: Check if test should retry
     */
    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            logger.warn("RETRYING TEST: " + result.getName() + 
                       " (Attempt " + (retryCount + 1) + " of " + (maxRetryCount + 1) + ")");
            return true;  // Retry
        }
        return false;  // Don't retry
    }

    /**
     * Usage approaches:
     *
     * // Approach 1: Apply to single test
     * @Test(retryAnalyzer = RetryListener.class)
     * public void testFlakeyElement() {
     *     // This test will retry up to 2 times on failure
     * }
     *
     * // Approach 2: Apply to all tests in class
     * @Listeners(RetryListener.class)
     * public class MyTests {
     *     @Test
     *     public void test1() {
     *         // Will retry on failure
     *     }
     *
     *     @Test
     *     public void test2() {
     *         // Will retry on failure
     *     }
     * }
     *
     * // Approach 3: Configurable retry count
     * To change max retries:
     * 1. Modify maxRetryCount in this class
     * 2. Recompile and rebuild
     * 3. Tests will use new retry count
     *
     * // Approach 4: Conditional retry
     * You can customize retry logic:
     * if (result.getThrowable() instanceof TimeoutException) {
     *     // Only retry on timeout
     *     return retryCount < maxRetryCount;
     * }
     * return false;
     */

    /**
     * Alternative Approach 5: Retry with exponential backoff
     * Adds delay between retries
     */
    public static class RetryWithBackoff implements IRetryAnalyzer {
        private int retryCount = 0;
        private int maxRetryCount = 2;
        private static Logger logger = Logger.getLogger(RetryWithBackoff.class);

        @Override
        public boolean retry(ITestResult result) {
            if (retryCount < maxRetryCount) {
                retryCount++;
                long waitTime = (long) Math.pow(2, retryCount) * 1000;  // Exponential backoff

                logger.warn("RETRYING TEST: " + result.getName() +
                           " (Attempt " + (retryCount + 1) + " of " + (maxRetryCount + 1) + ")" +
                           " - Wait time: " + waitTime + "ms");

                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException e) {
                    logger.error("Interrupted during retry backoff: " + e.getMessage());
                }

                return true;
            }
            return false;
        }
    }

    /**
     * Usage example with backoff:
     *
     * @Test(retryAnalyzer = RetryListener.RetryWithBackoff.class)
     * public void testWithBackoff() {
     *     // Retries with increasing delays (2s, 4s)
     * }
     */

    /**
     * Alternative Approach 6: Conditional retry based on exception type
     */
    public static class ConditionalRetry implements IRetryAnalyzer {
        private int retryCount = 0;
        private int maxRetryCount = 2;
        private static Logger logger = Logger.getLogger(ConditionalRetry.class);

        @Override
        public boolean retry(ITestResult result) {
            // Only retry on specific exceptions
            Throwable throwable = result.getThrowable();

            if (throwable != null) {
                // Retry on timeout
                if (throwable.getMessage().contains("timeout") ||
                    throwable.getMessage().contains("NoSuchElementException")) {
                    if (retryCount < maxRetryCount) {
                        retryCount++;
                        logger.warn("CONDITIONAL RETRY: " + result.getName() +
                                   " (Exception: " + throwable.getClass().getSimpleName() + ")");
                        return true;
                    }
                }
            }

            return false;
        }
    }

    /**
     * Usage example with conditional retry:
     *
     * @Test(retryAnalyzer = RetryListener.ConditionalRetry.class)
     * public void testConditionalRetry() {
     *     // Only retries if test fails with timeout or NoSuchElementException
     * }
     */
}
