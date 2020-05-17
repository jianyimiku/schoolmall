package com.cslg.dao;

import com.cslg.dto.ShopExecution;
import com.cslg.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.io.InputStream;
import java.util.List;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-02 11:05
 **/
public interface ShopDao {

    /**
     *
     * @param shopCondititon
     * @param rowIndex 从第几行开始取
     * @param pageSize 返回的条数
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition")Shop shopCondititon,@Param("rowIndex")Integer rowIndex,
                             @Param("pageSize")Integer pageSize);


    /**
     * 返回queryShopList总数
     * @param shopCondititon
     * @return
     */
    int queryShopCount(@Param("shopCondition")Shop shopCondititon);
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


    /**
     * 通过shopId查询店铺
     * @param shopId
     * @return
     */
    Shop queryByShopId(Long shopId);
}
