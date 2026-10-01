package com.titancore.database;

public class PostgresConnection implements AutoCloseable {


    private final String connectionId;

    public PostgresConnection(String connectionId){
        this.connectionId = connectionId ;
        System.out.println("[DB LAYER} Opening physical  TCP connection for " + this.connectionId);
    }
    public void executeQuery(String sql){
        if (sql.contains("DROP")){
            throw new RuntimeException("CRITICAL: Unauthorized destructive query detected!");
        }
    }
    @Override
    public void close(){
        System.out.println("[DB LAYER] Connection " + this.connectionId + "successful CLOSED. Releasing memory/ports.");
    }
}
