package com.cslg.dao;

import com.cslg.BaseTest;
import com.cslg.entity.ProductCategory;
import com.google.common.collect.Lists;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-16 12:08
 **/
//@FixMethodOrder(MethodSorters.NAME_ASCENDING) 按照名字的定义顺序执行 因为junit执行的时候顺序不固定 这个注解的时候就要对方法名进行排序 testA B C
public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    @Ignore
    public void testQueryByShopId(){
        long shopId = 2;
        List<ProductCategory> productCategories = productCategoryDao.queryProductCategoryList(shopId);
        System.out.println(productCategories.size());
    }


    @Test
    public void testABatchInsertProductCategory(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCreateTime(new Date());
        productCategory.setPriority(10);
        productCategory.setProductCategoryName("店铺类别4");
        productCategory.setShopId(2L);

        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setCreateTime(new Date());
        productCategory1.setPriority(5);
        productCategory1.setProductCategoryName("店铺类别5");
        productCategory1.setShopId(2L);

        List<ProductCategory> list = Lists.newArrayList(productCategory,productCategory1);

        productCategoryDao.batchInsertProductCategory(list);
    }

}
