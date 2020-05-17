package com.cslg.dao;

import com.cslg.BaseTest;
import com.cslg.entity.ProductImg;
import com.google.common.collect.Lists;
import org.junit.FixMethodOrder;
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
 * @date : 2020-05-17 10:46
 **/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgDaoTest extends BaseTest {
    @Autowired
    private ProductImgDao productImgDao;

    @Test
    public void testABatchInsertProductImg(){
        ProductImg productImg = new ProductImg();

        productImg.setImgAddr("1");
        productImg.setImgDesc("测试1");
        productImg.setPriority(1);
        productImg.setCreateTime(new Date());
        productImg.setProductId(1L);


        ProductImg productImg1 = new ProductImg();

        productImg1.setImgAddr("2");
        productImg1.setImgDesc("测试2");
        productImg1.setPriority(2);
        productImg1.setCreateTime(new Date());
        productImg1.setProductId(1L);

        List<ProductImg> list = Lists.newArrayList(productImg,productImg1);

        int i = productImgDao.batchInsertProductImg(list);
        System.out.println(i);
    }


    @Test
    public void testCDeleteProductImgByProductId(){
        long productId = 1;
        System.out.println(productImgDao.deleteProductImgByProductId(1));
    }
}
