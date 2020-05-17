package com.cslg.service.impl;

import com.cslg.dao.ShopDao;
import com.cslg.dto.ShopExecution;
import com.cslg.entity.Shop;
import com.cslg.exceptions.ShopOperationException;
import com.cslg.global.enums.ShopStateEnum;
import com.cslg.service.IShopService;
import com.cslg.util.ImageUtil;
import com.cslg.util.PageCalculator;
import com.cslg.util.PathUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-11 12:32
 **/
@Service("shopServiceImpl")
public class ShopServiceImpl implements IShopService {

    @Autowired
    private ShopDao shopDao;

    @Override
    //自动处理事务ShopOperationException
    @Transactional(rollbackFor = {Exception.class})
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) {
        //空值判断
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
//            给店铺信息赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            Integer effectedNum = shopDao.insrtShop(shop);
            if (effectedNum <= 0) {
                throw new ShopOperationException("店铺创建失败");
            } else {
                if (shopImgInputStream != null) {
                    try {
                        addShopImg(shop, shopImgInputStream, fileName);
                    } catch (Exception e) {
                        throw new ShopOperationException("addShopImg失败" + e.getMessage());
                    }
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new ShopOperationException("更新图片失败");
                    }
                }
            }
        } catch (Exception e) {
            throw new ShopOperationException("addShop error" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    @Override
    public Shop getByShopId(Long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) {
        //判断是否需要处理图片
        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        } else {
            try {
                if (shopImgInputStream != null && StringUtils.isNotBlank(fileName)) {
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopImg() != null) {
                        ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                    }
                    addShopImg(shop, shopImgInputStream, fileName);
                    //2 更新店铺信息
                    shop.setLastEditTime(new Date());
                    int effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        return new ShopExecution(ShopStateEnum.INNER_ERROR);
                    } else {
                        shop = shopDao.queryByShopId(shop.getShopId());
                    }
                } else {
                    shop.setLastEditTime(new Date());
                    int effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        return new ShopExecution(ShopStateEnum.INNER_ERROR);
                    } else {
                        shop = shopDao.queryByShopId(shop.getShopId());
                    }
                }
            } catch (Exception e) {
                throw new ShopOperationException("modifyShop" + e.getMessage());
            }
            return new ShopExecution(ShopStateEnum.SUCCESS, shop);
        }
    }

    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution se = new ShopExecution();
        if (shopList!=null){
            se.setShopList(shopList);
            se.setCount(count);
        }else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }

    private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, fileName, dest);
        shop.setShopImg(shopImgAddr);
    }
}
