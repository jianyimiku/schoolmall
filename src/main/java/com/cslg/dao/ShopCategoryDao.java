package com.cslg.dao;

import com.cslg.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-13 11:04
 **/
public interface ShopCategoryDao {
    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
}
