package com.cslg.dao;

import com.cslg.BaseTest;
import com.cslg.entity.Area;
import com.cslg.entity.PersonInfo;
import com.cslg.entity.Shop;
import com.cslg.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-02 11:38
 **/
public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;

    @Test
    public void testShopDao(){
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(1);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int i = shopDao.insrtShop(shop);
        System.out.println(i);
    }


    @Test
    public void testUpdateShop(){
        Shop shop = new Shop();
        shop.setShopId(2L);
        shop.setShopDesc("测试描述");
        shop.setShopAddr("测试地址");
        shop.setLastEditTime(new Date());
        int i = shopDao.updateShop(shop);
        System.out.println(i);
    }
}
