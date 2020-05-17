package com.cslg.service;

import com.cslg.dto.ProductCategoryExecution;
import com.cslg.entity.ProductCategory;

import java.util.List;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-16 12:20
 **/
public interface IProductCategoryService {

    List<ProductCategory> getProductCategoryList(long shopId);


    ProductCategoryExecution batchAddProduct(List<ProductCategory> productCategoryList);


    ProductCategoryExecution deleteProductCategory(long productCategoryId,long shopId);


}
