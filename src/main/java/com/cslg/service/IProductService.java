package com.cslg.service;

import com.cslg.dto.ImageHolder;
import com.cslg.dto.ProductExecution;
import com.cslg.entity.Product;

import java.io.InputStream;
import java.util.List;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-17 10:59
 **/
public interface IProductService {
    ProductExecution addProduct(Product product, ImageHolder imageHolder,
                                List<ImageHolder> imageHolderList);
}
