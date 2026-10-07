package JAVA;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
public class ThreadPoollSizingDemo {

    private static final Logger log = Logger.getLogger(ThreadPoollSizingDemo.class.getName());
    private static final AtomicInteger senderAccount = new AtomicInteger(5000);
    private static final AtomicInteger receiverAccount = new AtomicInteger(5000);

    public static void main(String[] args) {
        log.info("starting enterprise thread pool sizing simulator ");

        // thread size 50
        // cpu server not high limit
        ExecutorService threadPool = Executors.newFixedThreadPool(50);
        Random randomCrashGenerator = new Random();

        // transaction 100 ( task 100 )
        for ( int i = 1; i <=100; i++){
            final int txId = i;
            threadPool.submit(() -> {
                boolean willCrash = randomCrashGenerator.nextBoolean();
                performTransfer(txId, 10 , willCrash);
            });
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        int totalMoneyInBank = senderAccount.get() + receiverAccount.get();
        log.info("=======================================================");
        log.info("Total money in bank (must be exactly $10000:" + totalMoneyInBank) ;

    }
    private static void performTransfer(int txId, int amount , boolean simulateNetworkCrash){
        String workerName = Thread.currentThread().getName();

        senderAccount.addAndGet(-amount);
        try{
            Thread.sleep(15);
            if (simulateNetworkCrash){
                throw new RuntimeException("Database Timeout!");
            }
            receiverAccount.addAndGet(amount);
            log.info("[" + workerName + "] TX-" + txId + "COMMITL: Success.");
        } catch (Exception e){
            log.warning("[" + workerName + "] TX-" + txId + " ROLLBACK: Refunding $" + amount);
        }
    }
}
// តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo main
//INFO: starting enterprise thread pool sizing simulator
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-22] TX-22COMMITL: Success.
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-2] TX-2COMMITL: Success.
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-38] TX-38 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-16] TX-16COMMITL: Success.
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-4] TX-4COMMITL: Success.
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-28] TX-28COMMITL: Success.
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-29] TX-29COMMITL: Success.
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-1] TX-1 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-35] TX-35 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-18] TX-18COMMITL: Success.
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-11] TX-11 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-47] TX-47 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-48] TX-48 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-24] TX-24 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-46] TX-46COMMITL: Success.
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-13] TX-13 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-12] TX-12 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-49] TX-49 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-6] TX-6COMMITL: Success.
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-15] TX-15COMMITL: Success.
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-14] TX-14 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-30] TX-30 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-17] TX-17COMMITL: Success.
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-19] TX-19 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-20] TX-20 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-25] TX-25COMMITL: Success.
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-23] TX-23 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-7] TX-7 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-9] TX-9 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-5] TX-5 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-21] TX-21COMMITL: Success.
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-26] TX-26COMMITL: Success.
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-32] TX-32 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-31] TX-31 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-34] TX-34 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-3] TX-3 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-10] TX-10 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-27] TX-27 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-37] TX-37 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-8] TX-8 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-36] TX-36 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-42] TX-42 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-43] TX-43COMMITL: Success.
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-39] TX-39 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-44] TX-44COMMITL: Success.
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-50] TX-50COMMITL: Success.
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-45] TX-45 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-40] TX-40 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-41] TX-41 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:38:59 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-33] TX-33 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-22] TX-51COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-29] TX-57 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-16] TX-54 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-2] TX-52 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-38] TX-53 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-4] TX-55COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-28] TX-56 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-18] TX-60COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-1] TX-58COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-15] TX-70 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-24] TX-64COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-13] TX-66 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-14] TX-71 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-19] TX-74 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-30] TX-72COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-20] TX-75COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-35] TX-59 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-7] TX-78 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-25] TX-76COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-11] TX-61COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-12] TX-67 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-48] TX-63 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-6] TX-69 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-46] TX-65COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-47] TX-62COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-17] TX-73 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-49] TX-68 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-23] TX-77COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-33] TX-100COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-8] TX-90 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-39] TX-94 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-36] TX-91COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-21] TX-81 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-9] TX-79 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-3] TX-86COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-32] TX-83 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-34] TX-85COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-27] TX-88 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-43] TX-93 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-5] TX-80COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-31] TX-84COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-50] TX-96COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-42] TX-92COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-10] TX-87COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-40] TX-98 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-26] TX-82COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-37] TX-89COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-44] TX-95COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//WARNING: [pool-1-thread-45] TX-97 ROLLBACK: Refunding $10
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo performTransfer
//INFO: [pool-1-thread-41] TX-99COMMITL: Success.
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo main
//INFO: =======================================================
//តុលា 07, 2026 10:39:00 PM JAVA.ThreadPoollSizingDemo main
//INFO: Total money in bank (must be exactly $10000:9420