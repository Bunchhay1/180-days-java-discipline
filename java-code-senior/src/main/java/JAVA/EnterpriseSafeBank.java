package JAVA;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class EnterpriseSafeBank {

    private static final Logger log = Logger.getLogger(EnterpriseSafeBank.class.getName());

    // use AtomicInteger replace int simple
    private static final AtomicInteger safeAccountBalance = new AtomicInteger(0);

    public static void main(String[] args ) {
        log.info("Starting Enterprise Thread-Safe Banking Simulator ...");
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 10000; i++){
            threadPool.submit(() -> {
                // safe selection
                // use method incrementAndGet() replace + simple addition (+1)
                // code this lock automation
                // Guaranteed that 100 thead waiting row to execute
                safeAccountBalance.incrementAndGet();
                    });
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Expected $10000
        log.info("Expected Balance: $10000");
        log.info("Actual Final Balance: $ " + safeAccountBalance.get());
    }

}
