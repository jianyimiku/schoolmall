package com.cslg.service;

import com.cslg.entity.ShopCategory;

import java.util.List;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-13 11:58
 **/
public interface IShopCategoryService {
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondititon);
}
