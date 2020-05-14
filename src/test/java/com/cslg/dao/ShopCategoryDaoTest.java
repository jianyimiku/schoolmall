package com.cslg.dao;

import com.cslg.BaseTest;
import com.cslg.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-13 11:46
 **/
public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void testQueryShopCategory(){
        List<ShopCategory> shopCategories = shopCategoryDao.queryShopCategory(new ShopCategory());
        System.out.println(shopCategories);
    }
}
