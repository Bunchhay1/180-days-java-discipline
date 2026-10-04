import java.util.logging.Logger;

public class MemorySafeEnterpriseProcess {

    // replace system.out.println to log.info
    private static final Logger log = Logger.getLogger(MemorySafeEnterpriseProcess.class.getName());

    // change thread true false
    private static volatile boolean isRunning = true;

    public static void main(String[] args){
        log.info("Starting safe background process..");

        // when press Control + C and process not shutdown when execute on this code
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Received shutdown signal from OS. Stopping safely..");
            isRunning = false;
        }));
        int transactionCount = 0 ;

        // use isRunning replace while(true)
        while(isRunning){

            // create Object in local scope
            byte[] temporaryData = new byte[1024 * 1024];

            transactionCount++;

            // on this GC clean memory
            if (transactionCount % 50 == 0){
                log.info("Process" + transactionCount + "transaction successfully.");
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                log.warning("Thread interrupted.");
                Thread.currentThread().interrupt();
            }
        }
        log.info("Application shut down cleanly. No data lost. ");
    }




}
