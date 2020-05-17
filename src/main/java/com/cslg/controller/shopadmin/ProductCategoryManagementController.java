package com.cslg.controller.shopadmin;

import com.cslg.dto.ProductCategoryExecution;
import com.cslg.dto.Result;
import com.cslg.entity.ProductCategory;
import com.cslg.entity.Shop;
import com.cslg.global.enums.ProductCategoryStateEnum;
import com.cslg.service.IProductCategoryService;
import com.sun.glass.ui.Application;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-16 12:34
 **/
@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {
    @Autowired
    private IProductCategoryService productCategoryService;


    @GetMapping("/getproductcategorylist")
    @ResponseBody
    private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request) {
        Shop shop = new Shop();
        shop.setShopId(2L);
        request.getSession().setAttribute("currentShop", shop);

        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        List<ProductCategory> list = null;
        if (currentShop != null && currentShop.getShopId() > 0) {
            list = productCategoryService.getProductCategoryList(currentShop.getShopId());
            return new Result<List<ProductCategory>>(true, list);
        } else {
            ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
            return new Result<List<ProductCategory>>(false, ps.getState(), ps.getStateInfo());
        }
    }


    @PostMapping("/addproductcategory")
    @ResponseBody
    //因为前端没有写对应的名字 直接传到json字符串所以需要加RequestBody  RequestParam 一般处理表单请求
    public Map<String, Object> addproductcategory(@RequestBody List<ProductCategory> productCategoryList,
                                                  HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");

        for (ProductCategory productCategory : productCategoryList) {
            productCategory.setShopId(currentShop.getShopId());
        }

        if (CollectionUtils.isNotEmpty(productCategoryList)) {
            try {
                ProductCategoryExecution pe = productCategoryService.batchAddProduct(productCategoryList);
                if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStateInfo());
                }
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "列表为空");
        }
        return modelMap;
    }


    @PostMapping("/removeproductcategory")
    @ResponseBody
    //因为前端没有写对应的名字 直接传到json字符串所以需要加RequestBody  RequestParam 一般处理表单请求
    public Map<String, Object> removeProductCategory(Long productCategoryId,
                                                     HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        if (productCategoryId != null && productCategoryId > 0) {
            try {
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                ProductCategoryExecution pe = productCategoryService.deleteProductCategory(productCategoryId, currentShop.getShopId());
                if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStateInfo());
                }
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "至少选择一个商品类别");
        }

        return modelMap;
    }
}
