package com.pos.iduka.exception;

public class DuplicateEntryException extends  RuntimeException{

    private static final String ERROR_MESSAGE="Duplicate data. Data already exist";
    public DuplicateEntryException(String message){
        super(ERROR_MESSAGE);
    }
}
