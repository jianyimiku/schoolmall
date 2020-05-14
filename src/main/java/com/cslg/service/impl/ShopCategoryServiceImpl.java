package com.cslg.service.impl;

import com.cslg.dao.ShopCategoryDao;
import com.cslg.entity.ShopCategory;
import com.cslg.service.IShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-13 11:59
 **/
@Service
public class ShopCategoryServiceImpl implements IShopCategoryService {
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondititon) {
        return shopCategoryDao.queryShopCategory(shopCategoryCondititon);
    }
}
