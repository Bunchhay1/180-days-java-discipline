package JAVA;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ConnectionPoolDemo {

    private static final Logger log = Logger.getLogger(ConnectionPoolDemo.class.getName());

    // ArrayBlockingQueu it is for main connection with pool
    // Thread safe for Thread conquer each other
    private static BlockingQueue<String> connectionPool = new ArrayBlockingQueue<>(3);

    public static void main(String[] args){
        log.info("Initializing Enterprise Connection Pool... ");

        // when start server we create thread 3 stored connection  ( Max = 3 )
        connectionPool.offer("DB-Connection-1");
        connectionPool.offer("DB-Connection-2");
        connectionPool.offer("DB-Connection-3");

        log.info("Pool initialized with 3 active connections.");

        // customer 10 requesting on 1 time
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 1; i <= 10; i++){
            final int requestId= i;
            threadPool.submit(() -> {
                try {
                    log.info("Request #" + requestId + " is waiting for a database connection...");

                    //take() use connection pool
                    // for don't have pool executing stop thread or block for have pool
                    String connection = connectionPool.take();
                    log.info(">>> Request # " + requestId + " ACQUIRED" + connection);

                    // pretend to pull data from database for 2s
                    Thread.sleep(2000);

                    // put() use connection to give requestion for request new use
                    connectionPool.put(connection);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                    });
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        log.info("all 10 requests completed successfully using only 3 connections! .");

    }
}

// តុលា 07, 2026 7:39:28 PM JAVA.ConnectionPoolDemo main
//INFO: Initializing Enterprise Connection Pool...
//តុលា 07, 2026 7:39:28 PM JAVA.ConnectionPoolDemo main
//INFO: Pool initialized with 3 active connections.
//តុលា 07, 2026 7:39:28 PM JAVA.ConnectionPoolDemo lambda$main$0
//INFO: Request #7 is waiting for a database connection...
//តុលា 07, 2026 7:39:28 PM JAVA.ConnectionPoolDemo lambda$main$0
//INFO: Request #2 is waiting for a database connection...
//តុលា 07, 2026 7:39:28 PM JAVA.ConnectionPoolDemo lambda$main$0
//INFO: Request #10 is waiting for a database connection...
//តុលា 07, 2026 7:39:28 PM JAVA.ConnectionPoolDemo lambda$main$0
//INFO: Request #4 is waiting for a database connection...
//តុលា 07, 2026 7:39:28 PM JAVA.ConnectionPoolDemo lambda$main$0
//INFO: Request #9 is waiting for a database connection...
//តុលា 07, 2026 7:39:28 PM JAVA.ConnectionPoolDemo lambda$main$0
//INFO: Request #3 is waiting for a database connection...
//តុលា 07, 2026 7:39:28 PM JAVA.ConnectionPoolDemo lambda$main$0
//INFO: Request #1 is waiting for a database connection...
//តុលា 07, 2026 7:39:28 PM JAVA.ConnectionPoolDemo lambda$main$0
//INFO: Request #6 is waiting for a database connection...
//តុលា 07, 2026 7:39:28 PM JAVA.ConnectionPoolDemo lambda$main$0
//INFO: Request #8 is waiting for a database connection...
//តុលា 07, 2026 7:39:28 PM JAVA.ConnectionPoolDemo lambda$main$0
//INFO: Request #5 is waiting for a database connection...
//តុលា 07, 2026 7:39:28 PM JAVA.ConnectionPoolDemo lambda$main$0
//INFO: >>> Request # 2 ACQUIREDDB-Connection-2
//តុលា 07, 2026 7:39:28 PM JAVA.ConnectionPoolDemo lambda$main$0
//INFO: >>> Request # 7 ACQUIREDDB-Connection-1
//តុលា 07, 2026 7:39:28 PM JAVA.ConnectionPoolDemo lambda$main$0
//INFO: >>> Request # 10 ACQUIREDDB-Connection-3
//តុលា 07, 2026 7:39:29 PM JAVA.ConnectionPoolDemo main
//INFO: all 10 requests completed successfully using only 3 connections! .
//តុលា 07, 2026 7:39:30 PM JAVA.ConnectionPoolDemo lambda$main$0
//INFO: >>> Request # 4 ACQUIREDDB-Connection-2
//តុលា 07, 2026 7:39:30 PM JAVA.ConnectionPoolDemo lambda$main$0
//INFO: >>> Request # 9 ACQUIREDDB-Connection-1
//តុលា 07, 2026 7:39:30 PM JAVA.ConnectionPoolDemo lambda$main$0
//INFO: >>> Request # 3 ACQUIREDDB-Connection-3
//តុលា 07, 2026 7:39:32 PM JAVA.ConnectionPoolDemo lambda$main$0
//INFO: >>> Request # 1 ACQUIREDDB-Connection-2
//តុលា 07, 2026 7:39:32 PM JAVA.ConnectionPoolDemo lambda$main$0
//INFO: >>> Request # 6 ACQUIREDDB-Connection-1
//តុលា 07, 2026 7:39:32 PM JAVA.ConnectionPoolDemo lambda$main$0
//INFO: >>> Request # 8 ACQUIREDDB-Connection-3
//តុលា 07, 2026 7:39:34 PM JAVA.ConnectionPoolDemo lambda$main$0
//INFO: >>> Request # 5 ACQUIREDDB-Connection-2