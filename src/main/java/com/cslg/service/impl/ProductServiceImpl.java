package com.cslg.service.impl;

import com.cslg.dao.ProductDao;
import com.cslg.dao.ProductImgDao;
import com.cslg.dto.ImageHolder;
import com.cslg.dto.ProductExecution;
import com.cslg.entity.Product;
import com.cslg.entity.ProductImg;
import com.cslg.exceptions.ProductCategoryOperationException;
import com.cslg.exceptions.ProductOperationException;
import com.cslg.global.enums.ProductStateEnum;
import com.cslg.service.IProductService;
import com.cslg.util.ImageUtil;
import com.cslg.util.PageCalculator;
import com.cslg.util.PathUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-17 11:05
 **/
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductImgDao productImgDao;


    @Override
    @Transactional(rollbackFor = Exception.class)
    //处理缩略图 获得缩略图相对路径并赋值给Product

    public ProductExecution addProduct(Product product, ImageHolder imageHolder, List<ImageHolder> imageHolderList) {
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            product.setEnableStatus(1);
            if (imageHolder != null) {
                addThumbnail(product, imageHolder);
            }
            try {
                int effectedNum = productDao.insertProduct(product);
                if (effectedNum < 0) {
                    throw new ProductCategoryOperationException("创建商品失败");
                }
            } catch (Exception e) {
                throw new ProductCategoryOperationException("创建商品失败:" + e.getMessage());
            }
            //若商品详情图不为空则添加
            if (CollectionUtils.isNotEmpty(imageHolderList)) {
                addProductImgList(product, imageHolderList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS, product);
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    @Override
    public Product getProductById(long productId) {
        return productDao.queryProductById(productId);
    }

    @Override
    public ProductExecution modifyProduct(Product product, ImageHolder imageHolder, List<ImageHolder> imageHolderList) {
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            product.setLastEditTime(new Date());
            if (imageHolder != null) {
                Product tempProduct = productDao.queryProductById(product.getProductId());
                if (tempProduct.getImgAddr() != null) {
                    ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
                }
                addThumbnail(product, imageHolder);
            }
            if (CollectionUtils.isNotEmpty(imageHolderList)) {
                deleteProductImgList(product.getProductId());
                addProductImgList(product, imageHolderList);
            }
            try {
                int effectedNum = productDao.updateProduct(product);
                if (effectedNum < 0){
                    throw new ProductOperationException("更新商品失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS,product);
            }catch (Exception e){
                throw new ProductOperationException("更新商品信息失败:"+e.getMessage());
            }
        }else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
        // 基于同样的查询条件返回该查询条件下的商品总数
        int count = productDao.queryProductCount(productCondition);
        ProductExecution pe = new ProductExecution();
        pe.setProductList(productList);
        pe.setCount(count);
        return pe;
    }

    private void addProductImgList(Product product, List<ImageHolder> imageHolderList) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<>();
        for (ImageHolder imageHolder : imageHolderList) {
            String thunbnail = ImageUtil.generateNormal(imageHolder.getImage(), imageHolder.getImageName(), dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(thunbnail);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImgList.add(productImg);
        }
        if (CollectionUtils.isNotEmpty(productImgList)) {
            try {
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if (effectedNum < 0) {
                    throw new ProductOperationException("创建商品详情页图片失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品详情页图片失败:" + e.toString());
            }
        }
    }

    private void addThumbnail(Product product, ImageHolder imageHolder) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(imageHolder.getImage(), imageHolder.getImageName(), dest);
        product.setImgAddr(thumbnailAddr);
    }

    private void deleteProductImgList(long productId) {
        List<ProductImg> productImgs = productImgDao.queryProductImgList(productId);
        if (CollectionUtils.isNotEmpty(productImgs)) {
            for (ProductImg productImg : productImgs) {
                ImageUtil.deleteFileOrPath(productImg.getImgAddr());
            }
            productImgDao.deleteProductImgByProductId(productId);
        }
    }


}
