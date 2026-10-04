import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MemoryCrashTest {

    private static final Logger log = Logger.getLogger(MemoryCrashTest.class.getName());
    private static final int ONE_MEGABYTE = 1024 * 1024;
    public static void main(String[] args ){
        log.info("Starting Enterprise Memory Leak Simulator ....");
        List<byte[]>memoryLeakList = new ArrayList<>();
        int loopCount = 0;
        try {
            while (true){
                memoryLeakList.add(new byte[ONE_MEGABYTE]);
                loopCount++;
                if (loopCount % 10 == 0){
                    log.info("Allocated:" + loopCount + "MB so far....");
                }
                Thread.sleep(100);
            }
        } catch (OutOfMemoryError e) {
            log.log(Level.SEVERE, "CRITICAL ERROR: Server Low Memory (JVM OutOfMemoryError", e);
            log.severe("Total MB allocated before crash:" + loopCount + "MB");
        } catch (InterruptedException e){
            log.log(Level.WARNING, "Process was interrupted", e);
            Thread.currentThread().interrupt();
        }
    }


}
