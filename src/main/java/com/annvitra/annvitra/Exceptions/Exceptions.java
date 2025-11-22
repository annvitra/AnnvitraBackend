package com.annvitra.annvitra.Exceptions;

public class Exceptions {

    public static class UserNotFoundException extends RuntimeException {


        public UserNotFoundException() {
            super("User not found");
        }

        public UserNotFoundException(String message) {
            super(message);
        }
    }

    public static class InvalidCredentialsException extends RuntimeException {
        public InvalidCredentialsException(String message) {
            super(message);
        }
    }
    public static class MobileAlreadyExistsException extends RuntimeException {
        public MobileAlreadyExistsException(String message) {
            super(message);
        }
    }
    public static class OTPMismatchException extends RuntimeException {
        public OTPMismatchException(String message) {
            super(message);
        }
    }
    public static class RoleNotProvidedException extends RuntimeException {
        public RoleNotProvidedException(String message) {
            super(message);
        }
    }
    public static class InvalidOTPException extends RuntimeException {
        public InvalidOTPException() {
            super("Invalid OTP");
        }

        public InvalidOTPException(String message) {
            super(message);
        }
    }

    public static class NGOAlreadyExistsException extends RuntimeException {
        public NGOAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class FarmerAlreadyExistsException extends RuntimeException {
        public FarmerAlreadyExistsException(String message) {
            super(message);
        }
    }
    public static class DeliveryPartnerAlreadyExistsException extends RuntimeException {
        public DeliveryPartnerAlreadyExistsException(String message) {
            super(message);
        }
    }
    public static class RestaurantAlreadyExistsException extends RuntimeException {
        public RestaurantAlreadyExistsException(String message) {
            super(message);
        }
    }
    


}
