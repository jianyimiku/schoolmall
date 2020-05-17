package com.cslg.exceptions;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-11 12:57
 **/
public class ProductCategoryOperationException extends RuntimeException {

    private static final long serialVersionUID = -3694392458498738852L;

    public ProductCategoryOperationException(String msg){
        super(msg);
    }
}
