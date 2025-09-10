package com.beekeeperApp.exception;

/**
 * Exception thrown when trying to create a Hive
 * that already exists in the system.
 */
public class HiveAlreadyExistException extends RuntimeException {
    /**
     * Constructs a new HiveAlreadyExistException with the specified detail message.
     * @param message the detail message explaining why the exception occurred
     */
    public HiveAlreadyExistException(String message) {
        super(message);
    }
}
