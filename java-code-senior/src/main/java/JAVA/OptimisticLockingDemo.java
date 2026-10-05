package JAVA;

import java.util.logging.Logger;

public class OptimisticLockingDemo {

    private static final Logger log = Logger.getLogger(OptimisticLockingDemo.class.getName());


    public static class DatabaseRecord {
        int accountId;
        int balance;
        int version;

        public DatabaseRecord(int accountId, int balance, int version){
            this.accountId = accountId;
            this.balance = balance;
            this.version = version;
        }
    }

    public static void main(String[] args) throws InterruptedException{
        log.info("Starting Optimistic Locking Simulator...");

        // set database 100$ in version 1
        DatabaseRecord dbRow = new DatabaseRecord(777, 100, 1);
        log.info("DB Initial State -> Balance: $" + dbRow.balance + " | version: " + dbRow.version);

        // Thread 1 chhay transfer in database
        int chhayReadBalance = dbRow.balance;
        int chhayReadVersion = dbRow.version;
        // ជួសជុលការ Print ឲ្យស្អាតមើលយល់
        log.info("Chhay read Balance: $" + chhayReadBalance + " | version: " + chhayReadVersion);

        // Thread 2 chhie transfer in database
        int chhieReadBalance = dbRow.balance;
        int chhieReadVersion = dbRow.version;
        log.info("Chhie reads Balance: $" + chhieReadBalance + " | version: " + chhieReadVersion);

        // executing update before withdraw 100$
        log.info("Chhay tries to withdraw $100....");

        // update in SQL
        if (dbRow.version == chhayReadVersion){
            dbRow.balance = chhayReadBalance - 100;
            dbRow.version = dbRow.version + 1;
            log.info("Chhay SUCCESS! New Balance: $" + dbRow.balance + " | New Version: " + dbRow.version);
        } else {
            log.warning("Chhay FAILED! Data was modified by someone else.");
        }

        // Update late time
        log.info("Chhie tries to withdraw $50...");

        //
        if(dbRow.version == chhieReadVersion){
            dbRow.balance = chhieReadBalance - 50;
            dbRow.version = dbRow.version + 1;
            log.info("Chhie SUCCESS! New DB balance: $" + dbRow.balance);
        } else {
            log.severe("Chhie FAILED! Transaction Rejected (Optimistic Lock Exception). Version mismatch!");
            log.severe("Chhie expected Version: " + chhieReadVersion + ", but DB is at Version: " + dbRow.version);
        }
    }
}