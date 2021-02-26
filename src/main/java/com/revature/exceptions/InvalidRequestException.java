package com.revature.exceptions;

/**
 * a custom exception for when the request is invalid, can either have a custom message
 * or a default message that just says invalid request made
 */
public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException() {
        super("Invalid request made!");
    }

    public InvalidRequestException(String message) {
        super(message);
    }

}
