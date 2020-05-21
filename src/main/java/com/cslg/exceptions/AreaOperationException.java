package com.cslg.exceptions;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-21 09:24
 **/
public class AreaOperationException extends RuntimeException {
    private static final long serialVersionUID = -4084424453333340753L;

    public AreaOperationException(String message){
        super(message);
    }
}
