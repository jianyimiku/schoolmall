package com.cslg.controller.shopadmin;

import com.cslg.dto.ShopExecution;
import com.cslg.entity.Area;
import com.cslg.entity.PersonInfo;
import com.cslg.entity.Shop;
import com.cslg.entity.ShopCategory;
import com.cslg.global.enums.ShopStateEnum;
import com.cslg.service.IAreaService;
import com.cslg.service.IShopCategoryService;
import com.cslg.service.IShopService;
import com.cslg.service.impl.ShopServiceImpl;
import com.cslg.util.CodeUtil;
import com.cslg.util.HttpServletRequestUtil;
import com.cslg.util.ImageUtil;
import com.cslg.util.PathUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-11 16:17
 **/
@Controller("shopManagerController")
@RequestMapping("/shopadmin/")
public class ShopManagementController {

    @Autowired
    private IShopService iShopService;

    @Autowired
    private IShopCategoryService shopCategoryService;

    @Autowired
    private IAreaService areaService;

    @GetMapping("getshopinitinfo")
    @ResponseBody
    private Map<String, Object> getshopinitinfo() {
        Map<String, Object> modelMap = Maps.newHashMap();

        List<ShopCategory> shopCategoryList = Lists.newArrayList();

        List<Area> areaList = Lists.newArrayList();
        try {
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("success", true);
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }


    @PostMapping("registershop")
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {

        Map<String, Object> modelMap = Maps.newHashMap();

        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "验证码错误");
            return modelMap;
        }

        String shopStr = HttpServletRequestUtil.getString(request, "shop");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            assert shopStr != null;
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (IOException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getServletContext());

        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "没有传入文件");
            return modelMap;
        }

        if (shop != null && shopImg != null) {
            PersonInfo owner = new PersonInfo();
            //Session todo
            owner.setUserId(1L);
            shop.setOwner(owner);
            ShopExecution se = null;
            try {
                se = iShopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
                if (se.getState().equals(ShopStateEnum.CHECK.getState())) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
                return modelMap;
            }
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "失败");
            return modelMap;
        }
    }
}
