package com.globalcode.ges.model.exception;

/**
 * Custom exception for Globalcode GES application
 */
public class GlobalcodeException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    public GlobalcodeException() {
        super();
    }
    
    public GlobalcodeException(String message) {
        super(message);
    }
    
    public GlobalcodeException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public GlobalcodeException(Throwable cause) {
        super(cause);
    }
}
