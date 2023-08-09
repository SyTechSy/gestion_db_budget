package com.groupe2_API.tp_gestion_budget.exception;

import javax.naming.NotContextException;

public class NoContentException extends RuntimeException{
    public NoContentException(String message) {
        super(message);
    }
}
