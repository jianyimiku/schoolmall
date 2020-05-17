package com.cslg.dao;

import com.cslg.entity.Product;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-17 10:23
 **/
public interface ProductDao {

    Integer insertProduct(Product product);

    Product queryProductById(long productId);

    int updateProduct(Product product);
}
