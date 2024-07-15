package com.pos.iduka.exception;

public class DuplicateEntryException extends  RuntimeException{

    public DuplicateEntryException(String message){
        super(message);
    }
}
