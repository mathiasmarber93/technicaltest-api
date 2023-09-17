package api.countrylayer.com.utils;

import static api.countrylayer.com.utils.ApiUtils.responseCode;

public class RetryTime {

    public static void retryUntilTimeout(Runnable action) {
        long startTimeMillis = System.currentTimeMillis();
        long maxRetryDurationMillis = 60000;
        long endTimeMillis = startTimeMillis + maxRetryDurationMillis;

        while (System.currentTimeMillis() < endTimeMillis) {
            action.run();
            if (responseCode == 200 || responseCode == 404) {
                break;
            }
        }
    }

}
