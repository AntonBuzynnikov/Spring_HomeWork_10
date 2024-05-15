package ru.buzynnikov.bidservice.exceptions;

public class BidNotFoundException extends RuntimeException{
    public BidNotFoundException(String message) {
        super(message);
    }
}
