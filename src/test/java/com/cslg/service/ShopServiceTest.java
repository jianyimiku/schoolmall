package com.cslg.service;

import com.cslg.BaseTest;
import com.cslg.dto.ShopExecution;
import com.cslg.entity.Area;
import com.cslg.entity.PersonInfo;
import com.cslg.entity.Shop;
import com.cslg.entity.ShopCategory;
import com.cslg.exceptions.ShopOperationException;
import com.cslg.global.enums.ShopStateEnum;
import com.cslg.service.impl.ShopServiceImpl;
import com.cslg.util.ImageUtil;
import com.cslg.util.PathUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-11 13:38
 **/
public class ShopServiceTest extends BaseTest {
    @Autowired
    private IShopService shopService;

    @Test
    public void testModifyShop(){
        Shop shop = new Shop();
        shop.setShopId(37L);
        shop.setShopName("修改后的店铺名称");
        File file = new File(PathUtil.getImagePath()+"miku2.jpg");
        InputStream inputStream = null;
        try {
             inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ShopExecution shopExecution = shopService.modifyShop(shop, inputStream, "miku2.jpg");
        System.out.println(shopExecution.getShop().getShopImg());
    }


    @Test
    @Ignore
    public void testAddShop(){
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
        shop.setShopName("测试店铺1");
        shop.setShopDesc("test1");
        shop.setShopAddr("test1");
        shop.setPhone("test1");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");

        File file = new File(PathUtil.getImagePath()+"miku.jpg");
//        ShopExecution shopExecution = shopService.addShop(shop, file);
//        System.out.println(ShopStateEnum.getByState(shopExecution.getState()).getStateInfo());
    }

}
