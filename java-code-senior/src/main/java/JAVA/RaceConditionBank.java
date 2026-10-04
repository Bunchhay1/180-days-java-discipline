package JAVA;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class RaceConditionBank {

    private static final Logger log = Logger.getLogger(RaceConditionBank.class.getName());

    // Shared resource data together and all thread seize
    private static int accountBalance = 0 ;
    public static void main(String[] args){
        log.info("Starting Core Banking Transaction Simulator...");

        // create thead 100 ( working together )
        ExecutorService threadPool = Executors.newFixedThreadPool(100);

        // execute add 1$ 10000 time
        for (int i = 0; i < 10000; i++){
            threadPool.submit(() -> {
                // high risk (Critical Selection)
                // 1 read 2 add 3 write
                accountBalance = accountBalance +1;
            });
        }
        // close process
        threadPool.shutdown();
        try {
            // waiting all thead 100 complete task
            threadPool.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        // about Thread safe Balance $10000 when running code
        log.info("Expected Balance: $10000");
        log.info("Actual Final Balance:" + accountBalance);
    }



}
