package com.cslg.service;

import com.cslg.dto.ShopExecution;
import com.cslg.entity.Shop;
import com.cslg.exceptions.ShopOperationException;

import java.io.File;
import java.io.InputStream;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-11 12:32
 **/
public interface IShopService {
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName);

    Shop getByShopId(Long shopId);

    ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream,String fileName);
}
