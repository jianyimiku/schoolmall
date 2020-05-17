package com.cslg.dao;

import com.cslg.BaseTest;
import com.cslg.entity.Product;
import com.cslg.entity.ProductCategory;
import com.cslg.entity.Shop;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-17 10:51
 **/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductImgDao productImgDao;

    @Test
    public void testAInsertProduct(){
        Shop shop = new Shop();
        shop.setShopId(2L);

        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(1L);

        Product product = new Product();
        product.setProductName("1");
        product.setProductDesc("测试");
        product.setImgAddr("test1");
        product.setPriority(1);
        product.setEnableStatus(1);
        product.setCreateTime(new Date());
        product.setLastEditTime(new Date());
        product.setShop(shop);
        product.setProductCategory(productCategory);

        Integer integer = productDao.insertProduct(product);

        System.out.println(integer);
    }

}
