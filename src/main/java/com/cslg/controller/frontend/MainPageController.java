package com.cslg.controller.frontend;

import com.cslg.entity.HeadLine;
import com.cslg.entity.ShopCategory;
import com.cslg.service.IHeadLineService;
import com.cslg.service.IShopCategoryService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-19 11:39
 **/
@Controller
@RequestMapping("/frontend")
public class MainPageController {
    @Autowired
    private IShopCategoryService shopCategoryService;

    @Autowired
    private IHeadLineService headLineService;

    @GetMapping("/listmainpageinfo")
    @ResponseBody
    private Map<String,Object> listMainPageInfo(){
        Map<String,Object> modelMap = Maps.newHashMap();
        List<ShopCategory> shopCategoryList = Lists.newArrayList();
        try {
            //获取一级店铺的名称
            shopCategoryList = shopCategoryService.getShopCategoryList(null);
            modelMap.put("shopCategoryList",shopCategoryList);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        List<HeadLine> headLineList = Lists.newArrayList();
        try {
            HeadLine headLine = new HeadLine();
            headLine.setEnableStatus(1);
            headLineList = headLineService.getHeadLineList(headLine);
            modelMap.put("headLineList",headLineList);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        modelMap.put("success",true);
        return modelMap;
    }
}
