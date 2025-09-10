package com.beekeeperApp.exception;

/**
 * Exception thrown when a Hive not found.
 */
public class HiveNotFoundException extends RuntimeException {
    /**
     * Constructs a new HiveNotFoundException with the specified detail message.
     * @param message the detail message explaining why the exception occurred
     */
    public HiveNotFoundException(String message) {
        super(message);
    }
}
