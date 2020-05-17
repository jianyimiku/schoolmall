package com.cslg.exceptions;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-17 11:04
 **/
public class ProductOperationException extends RuntimeException {

    private static final long serialVersionUID = 5076172298827469013L;

    public ProductOperationException(String msg) {
        super(msg);
    }
}

