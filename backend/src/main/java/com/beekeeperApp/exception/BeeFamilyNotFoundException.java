package com.beekeeperApp.exception;

/**
 * Exception thrown when a BeeFamily not found.
 */
public class BeeFamilyNotFoundException extends RuntimeException {
    /**
     * Constructs a new BeeFamilyNotFoundException with the specified detail message.
     * @param message the detail message explaining why the exception occurred
     */
    public BeeFamilyNotFoundException(String message) {
        super(message);
    }

}
