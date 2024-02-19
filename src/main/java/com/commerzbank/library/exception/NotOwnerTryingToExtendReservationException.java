package com.commerzbank.library.exception;

public class NotOwnerTryingToExtendReservationException extends RuntimeException {

    public NotOwnerTryingToExtendReservationException(String message) {
        super(message);
    }
}
