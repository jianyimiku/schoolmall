package com.cslg.dao;

import com.cslg.entity.Shop;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-02 11:05
 **/
public interface ShopDao {
    /**
     * 新增店铺
     * @param shop
     * @return
     */
    int insrtShop(Shop shop);

    /**
     * 更新店铺信息
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
