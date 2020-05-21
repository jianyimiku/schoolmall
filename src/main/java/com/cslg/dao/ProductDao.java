package com.cslg.dao;

import com.cslg.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    List<Product> queryProductList(@Param("productCondition") Product productCondition,
                                   @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    int queryProductCount(@Param("productCondition") Product productCondition);

    int updateProductCategoryToNull(long productCategoryId);
}
