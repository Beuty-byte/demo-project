package com.example.demoproject.exception;

public class WrongDataForSearchException extends RuntimeException{
    public WrongDataForSearchException(String message) {
        super(message);
    }
}
