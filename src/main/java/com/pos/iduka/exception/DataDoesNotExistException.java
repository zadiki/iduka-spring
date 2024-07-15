package com.pos.iduka.exception;

public class DataDoesNotExistException extends RuntimeException{

    public DataDoesNotExistException(String message){
        super(message);
    }
}
