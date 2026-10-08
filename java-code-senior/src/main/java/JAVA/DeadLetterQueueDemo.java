package JAVA;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;



public class DeadLetterQueueDemo {

    private static final Logger log = Logger.getLogger(DeadLetterQueueDemo.class.getName());

    public static void main(String[] args){
        log.info("starting Enterprise KAFKA DLQ simulator ");

        // 1 row box message (main Queue)
        BlockingQueue<String> mainQueue = new ArrayBlockingQueue<>(10);

        // 2 row box message fail ( Dead Letter Queue - DLQ )
        BlockingQueue<String> deadLetterQueue = new ArrayBlockingQueue<>(10);

        // Production create transfer money 5 time
        Thread producer = new Thread(() -> {
                try {
            for (int i = 1; i <= 5; i++) {
                mainQueue.put("Transaction_Receipt_#" + i);
            }
            mainQueue.put("EOF"); // stop
        } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
        });

        // CONSUMER: custom sent message
        Thread consumer = new Thread(() -> {
            Random randomFailure =  new Random();

            try {
                while (true){
                    String task = mainQueue.take();
                    if (task.equals("EOF")) break;

                    log.info("[CONSUMER] Received task: " + task);

                    boolean success = false;
                    int maxRetries = 3 ;
                    int attempt = 1 ;

                    // system retry for success or fail with max 3 time ( attempt maxRetries )
                    while (attempt <= maxRetries && !success) {
                        try{
                            log.info( "     -> Attempt " + "for" + task + "......");

                            // try for crash system 70% for test retry
                            if (randomFailure.nextInt(100) < 70){
                                throw new RuntimeException("Telecom API is DOWN!");
                            }
                            // for code run on this success
                            log.info(" [SUCCESS]" + task + "Processed successfully");
                            success = true;
                        } catch (Exception e) {
                            log.warning("[FAILURE] Attempt "  + attempt + " failed: " + e.getMessage());
                            attempt++;
                            Thread.sleep(500);
                        }
                    }

                    // then try 3 time and false ( success == false )
                    // develop sent to DLQ
                    if (!success) {
                        log.severe("   [DLQ ROUTING] Max retries reached. Moving " + task +" to Dead Letter Queue.");
                        deadLetterQueue.put(task);
                    }
                }
                // when consumer complete wh check DLQ for box message how many lost
                log.info("===================================================");
                log.info("SYSTEM SHUTDOWN REPORT");
                log.info("Message stuck in DLQ waiting for DevOps fix:" + deadLetterQueue.size());
                for (String deadMessage : deadLetterQueue) {
                    log.info(" - DLQ ITEM: " + deadMessage);
                }

            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        });
        producer.start();
        consumer.start();
    }
}
//តុលា 08, 2026 8:28:12 PM JAVA.DeadLetterQueueDemo main
//INFO: starting Enterprise KAFKA DLQ simulator
//តុលា 08, 2026 8:28:12 PM JAVA.DeadLetterQueueDemo lambda$main$1
//INFO: [CONSUMER] Received task: Transaction_Receipt_#1
//តុលា 08, 2026 8:28:12 PM JAVA.DeadLetterQueueDemo lambda$main$1
//INFO:      -> Attempt forTransaction_Receipt_#1......
//តុលា 08, 2026 8:28:12 PM JAVA.DeadLetterQueueDemo lambda$main$1
//INFO:  [SUCCESS]Transaction_Receipt_#1Processed successfully
//តុលា 08, 2026 8:28:12 PM JAVA.DeadLetterQueueDemo lambda$main$1
//INFO: [CONSUMER] Received task: Transaction_Receipt_#2
//តុលា 08, 2026 8:28:12 PM JAVA.DeadLetterQueueDemo lambda$main$1
//INFO:      -> Attempt forTransaction_Receipt_#2......
//តុលា 08, 2026 8:28:12 PM JAVA.DeadLetterQueueDemo lambda$main$1
//INFO:  [SUCCESS]Transaction_Receipt_#2Processed successfully
//តុលា 08, 2026 8:28:12 PM JAVA.DeadLetterQueueDemo lambda$main$1
//INFO: [CONSUMER] Received task: Transaction_Receipt_#3
//តុលា 08, 2026 8:28:12 PM JAVA.DeadLetterQueueDemo lambda$main$1
//INFO:      -> Attempt forTransaction_Receipt_#3......
//តុលា 08, 2026 8:28:12 PM JAVA.DeadLetterQueueDemo lambda$main$1
//WARNING: [FAILURE] Attempt 1 failed: Telecom API is DOWN!
//តុលា 08, 2026 8:28:13 PM JAVA.DeadLetterQueueDemo lambda$main$1
//INFO:      -> Attempt forTransaction_Receipt_#3......
//តុលា 08, 2026 8:28:13 PM JAVA.DeadLetterQueueDemo lambda$main$1
//WARNING: [FAILURE] Attempt 2 failed: Telecom API is DOWN!
//តុលា 08, 2026 8:28:13 PM JAVA.DeadLetterQueueDemo lambda$main$1
//INFO:      -> Attempt forTransaction_Receipt_#3......
//តុលា 08, 2026 8:28:13 PM JAVA.DeadLetterQueueDemo lambda$main$1
//INFO:  [SUCCESS]Transaction_Receipt_#3Processed successfully
//តុលា 08, 2026 8:28:13 PM JAVA.DeadLetterQueueDemo lambda$main$1
//INFO: [CONSUMER] Received task: Transaction_Receipt_#4
//តុលា 08, 2026 8:28:13 PM JAVA.DeadLetterQueueDemo lambda$main$1
//INFO:      -> Attempt forTransaction_Receipt_#4......
//តុលា 08, 2026 8:28:13 PM JAVA.DeadLetterQueueDemo lambda$main$1
//WARNING: [FAILURE] Attempt 1 failed: Telecom API is DOWN!
//តុលា 08, 2026 8:28:14 PM JAVA.DeadLetterQueueDemo lambda$main$1
//INFO:      -> Attempt forTransaction_Receipt_#4......
//តុលា 08, 2026 8:28:14 PM JAVA.DeadLetterQueueDemo lambda$main$1
//WARNING: [FAILURE] Attempt 2 failed: Telecom API is DOWN!
//តុលា 08, 2026 8:28:15 PM JAVA.DeadLetterQueueDemo lambda$main$1
//INFO:      -> Attempt forTransaction_Receipt_#4......
//តុលា 08, 2026 8:28:15 PM JAVA.DeadLetterQueueDemo lambda$main$1
//WARNING: [FAILURE] Attempt 3 failed: Telecom API is DOWN!
//តុលា 08, 2026 8:28:15 PM JAVA.DeadLetterQueueDemo lambda$main$1
//SEVERE:    [DLQ ROUTING] Max retries reached. Moving Transaction_Receipt_#4 to Dead Letter Queue.
//តុលា 08, 2026 8:28:15 PM JAVA.DeadLetterQueueDemo lambda$main$1
//INFO: [CONSUMER] Received task: Transaction_Receipt_#5
//តុលា 08, 2026 8:28:15 PM JAVA.DeadLetterQueueDemo lambda$main$1
//INFO:      -> Attempt forTransaction_Receipt_#5......
//តុលា 08, 2026 8:28:15 PM JAVA.DeadLetterQueueDemo lambda$main$1
//INFO:  [SUCCESS]Transaction_Receipt_#5Processed successfully
//តុលា 08, 2026 8:28:15 PM JAVA.DeadLetterQueueDemo lambda$main$1
//INFO: ===================================================
//តុលា 08, 2026 8:28:15 PM JAVA.DeadLetterQueueDemo lambda$main$1
//INFO: SYSTEM SHUTDOWN REPORT
//តុលា 08, 2026 8:28:15 PM JAVA.DeadLetterQueueDemo lambda$main$1
//INFO: Message stuck in DLQ waiting for DevOps fix:1
//តុលា 08, 2026 8:28:15 PM JAVA.DeadLetterQueueDemo lambda$main$1
//INFO:  - DLQ ITEM: Transaction_Receipt_#4