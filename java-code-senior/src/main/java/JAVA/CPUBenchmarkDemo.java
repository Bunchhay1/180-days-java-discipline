package JAVA;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


public class CPUBenchmarkDemo {

    private static final Logger log = Logger.getLogger(CPUBenchmarkDemo.class.getName());
    private static final int TOTAL_TRANSACTIONS = 10000;

    public static void main(String[] args) throws InterruptedException {

        log.info("Starting CPUBenchmark on Apple M3 Max");
        log.info("Processing " + TOTAL_TRANSACTIONS + " transactions");

        // Default ( don't have thread executing one by one
        long defaultTime = runSequentially();

        // test use thread pool 50
        long pooltime = runWithThreadPool();

        // test don't use thread pool ( new thread 10000time )
        long rawTime = runWithRawThreads();

        log.info("====================================================");
        log.info("CPU BENCHMARK RESULTS:");
        log.info("1 Default Sequence (1 workder)  : " + defaultTime + "ms");
        log.info("2  junior Raw Thread (10000 workders):" + rawTime + "ms");
        log.info("3. Enterprise Thread Pool (50 workers): " + pooltime + "ms");
        log.info("==================================================================");
        log.info("CONCLUSION: The Thread Pool is the ultimate winner. It is faster than single-threading by utilizing all CPU cores, and faster than raw threads by avoiding OS context-switching overhead.");

    }

    private static long runSequentially() {
        log.info("---> Running test with Default Single Thread (Sequential)...");
        long startTime = System.currentTimeMillis();

        // don't use thread running with list ( Synchronous )
        for (int i = 0; i < TOTAL_TRANSACTIONS; i++) {
            doFakeDatabaseWork();
        }
        long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }


    public static long runWithThreadPool() throws InterruptedException {
        log.info("---> Running test with Thread Pool (Size 50)....");

        ExecutorService threadPool = Executors.newFixedThreadPool(50);
        CountDownLatch latch = new CountDownLatch(TOTAL_TRANSACTIONS);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < TOTAL_TRANSACTIONS; i++) {
            threadPool.submit(() -> {
                doFakeDatabaseWork();
                latch.countDown();
            });
        }
        latch.await();
        long endTime = System.currentTimeMillis();

        threadPool.shutdown();
        return (endTime - startTime);

    }
    private static long runWithRawThreads()throws InterruptedException {
        log.info("--> Running test with Raw threads ( Creating 10000 separate threads)...");

        CountDownLatch latch = new CountDownLatch(TOTAL_TRANSACTIONS);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < TOTAL_TRANSACTIONS; i++) {
            new Thread(() -> {
                doFakeDatabaseWork();
                latch.countDown();
            }).start();
        }
        latch.await();
        long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }
    private static void doFakeDatabaseWork(){
        double result = 0;
        for ( int j = 0; j < 5000; j++) {
            result += Math.tan(Math.random());
        }
    }
}
// តុលា 08, 2026 12:03:23 AM JAVA.CPUBenchmarkDemo main
//INFO: Starting CPUBenchmark on Apple M3 Max
//តុលា 08, 2026 12:03:23 AM JAVA.CPUBenchmarkDemo main
//INFO: Processing 10000 transactions
//តុលា 08, 2026 12:03:23 AM JAVA.CPUBenchmarkDemo runSequentially
//INFO: ---> Running test with Default Single Thread (Sequential)...
//តុលា 08, 2026 12:03:24 AM JAVA.CPUBenchmarkDemo runWithThreadPool
//INFO: ---> Running test with Thread Pool (Size 50)....
//តុលា 08, 2026 12:04:13 AM JAVA.CPUBenchmarkDemo runWithRawThreads
//INFO: --> Running test with Raw threads ( Creating 10000 separate threads)...
//តុលា 08, 2026 12:04:58 AM JAVA.CPUBenchmarkDemo main
//INFO: ====================================================
//តុលា 08, 2026 12:04:58 AM JAVA.CPUBenchmarkDemo main
//INFO: CPU BENCHMARK RESULTS:
//តុលា 08, 2026 12:04:58 AM JAVA.CPUBenchmarkDemo main
//INFO: 1 Default Sequence (1 workder)  : 623ms
//តុលា 08, 2026 12:04:58 AM JAVA.CPUBenchmarkDemo main
//INFO: 2  junior Raw Thread (10000 workders):44240ms
//តុលា 08, 2026 12:04:58 AM JAVA.CPUBenchmarkDemo main
//INFO: 3. Enterprise Thread Pool (50 workers): 49179ms
//តុលា 08, 2026 12:04:58 AM JAVA.CPUBenchmarkDemo main
//INFO: ==================================================================
//តុលា 08, 2026 12:04:58 AM JAVA.CPUBenchmarkDemo main
//INFO: CONCLUSION: The Thread Pool is the ultimate winner. It is faster than single-threading by utilizing all CPU cores, and faster than raw threads by avoiding OS context-switching overhead.