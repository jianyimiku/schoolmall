package com.cslg.dao;

import com.cslg.entity.ProductImg;

import java.util.List;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-17 10:23
 **/
public interface ProductImgDao {
    /**
     * 批量添加商品详情图片
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    List<ProductImg> queryProductImgList(long productId);

    int deleteProductImgByProductId(long productId);
}
