package com.car.exceptions;

public class CustomExceptions {
//    resource not found exception
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

//    validation response
    public static class ValidationException extends RuntimeException {
        public ValidationException(String message) {
            super(message);
        }
    }

//    Database Exception
    public static class DatabaseException extends RuntimeException {
        public DatabaseException(String message) {
            super(message);
        }
    }

//    Invalid Input Exception
    public static class InvalidInputException extends RuntimeException {
        public InvalidInputException(String message) {
            super(message);
        }
    }

//    Bad Request Exception
    public static class BadRequestException extends RuntimeException {
        public BadRequestException(String message) {
            super(message);
        }
    }

//    Invalid Parameter Exception
    public static class InvalidParameterException extends RuntimeException {
        public InvalidParameterException(String message) {
            super(message);
        }
    }

//    Unsupported format exception
    public static class UnsupportedFormatException extends RuntimeException {
        public UnsupportedFormatException(String message) {
            super(message);
        }
    }

//    Service unavailable exception
    public static class ServiceUnavailableException extends RuntimeException {
        public ServiceUnavailableException(String message) {
            super(message);
        }
    }

}
