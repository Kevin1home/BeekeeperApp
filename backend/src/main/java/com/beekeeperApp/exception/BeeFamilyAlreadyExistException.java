package com.beekeeperApp.exception;

/**
 * Exception thrown when trying to create a BeeFamily
 * that already exists in the system.
 */
public class BeeFamilyAlreadyExistException extends RuntimeException {
    /**
     * Constructs a new BeeFamilyAlreadyExistException with the specified detail message.
     * @param message the detail message explaining why the exception occurred
     */
    public BeeFamilyAlreadyExistException(String message) {
        super(message);
    }
}
