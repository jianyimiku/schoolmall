package com.cslg.dao;

import com.cslg.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-16 10:49
 **/
public interface ProductCategoryDao {
    /**
     * 获取商品类别
     * @param shopId
     * @return
     */
    List<ProductCategory> queryProductCategoryList(long shopId);

    /**
     * 批量插入
     * @param productCategoryList
     * @return
     */
    Integer batchInsertProductCategory(@Param("productCategoryList") List<ProductCategory> productCategoryList);

    /**
     * 删除类别
     * @param productCategoryId
     * @param shopId
     * @return
     */
    Integer deleteProductCategory(@Param("productCategoryId")long productCategoryId,@Param("shopId")long shopId);
}
