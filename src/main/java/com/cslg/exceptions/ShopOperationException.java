package com.cslg.exceptions;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-11 12:57
 **/
public class ShopOperationException extends RuntimeException {

    private static final long serialVersionUID = -458909342933571939L;

    public ShopOperationException(String msg){
        super(msg);
    }
}
