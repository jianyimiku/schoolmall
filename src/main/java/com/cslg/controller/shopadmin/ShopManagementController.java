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
import java.util.*;

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


    @GetMapping("getshopmanagementinfo")
    @ResponseBody
    private Map<String, Object> getShopManagementInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if (shopId <= 0) {
            Object currentShop = request.getSession().getAttribute("currentShop");
            if (currentShop == null) {
                modelMap.put("redirect", true);
                modelMap.put("url", "/shopadmin/shoplist");
            } else {
                Shop current = (Shop) currentShop;
                modelMap.put("redirect", false);
                modelMap.put("shopId", ((Shop) currentShop).getShopId());
            }
        } else {
            Shop currentShop = new Shop();
            currentShop.setShopId(shopId);
            request.getSession().setAttribute("currentShop", currentShop);
            modelMap.put("redirect", false);
        }
        return modelMap;
    }


    @GetMapping("getshoplist")
    @ResponseBody
    private Map<String, Object> getShopList(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        PersonInfo user = new PersonInfo();
        user.setUserId(1L);
        user.setName("test");
        request.getSession().setAttribute("user", user);
        user = (PersonInfo) request.getSession().getAttribute("user");
        long employeeId = user.getUserId();
        try {
            Shop shopCondititon = new Shop();
            shopCondititon.setOwner(user);
            ShopExecution se = iShopService.getShopList(shopCondititon, 0, 100);
            modelMap.put("shopList", se.getShopList());
            modelMap.put("user", user);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }


    @GetMapping(value = "getshopbyid")
    @ResponseBody
    private Map<String, Object> getShopById(HttpServletRequest request) {
        Map<String, Object> modelMap = Maps.newHashMap();
        Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if (shopId > -1) {
            try {
                Shop shop = iShopService.getByShopId(shopId);
                List<Area> list = areaService.getAreaList();
                modelMap.put("shop", shop);
                modelMap.put("areaList", list);
                modelMap.put("success", true);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty shopId");
        }
        return modelMap;
    }

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
            PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
            shop.setOwner(owner);
            ShopExecution se = null;
            try {
                se = iShopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
                if (se.getState().equals(ShopStateEnum.CHECK.getState())) {
                    modelMap.put("success", true);
                    //该用户可以操作的用户列表
                    List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
                    if (shopList == null || shopList.size() == 0) {
                        shopList = Lists.newArrayList();
                        shopList.add(se.getShop());
                    } else {
                        shopList.add(se.getShop());
                    }
                    request.getSession().setAttribute("shopList", shopList);
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


    @PostMapping("modifyshop")
    @ResponseBody
    private Map<String, Object> modifyShop(HttpServletRequest request) {

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
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getServletContext());

        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        }
        //修改店铺信息
        if (shop != null && shop.getShopId() != null) {
            ShopExecution se = null;
            try {
                if (shopImg == null) {
                    se = iShopService.modifyShop(shop, null, null);
                } else {
                    se = iShopService.modifyShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
                }
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
            modelMap.put("errMsg", "请输入店铺Id");
            return modelMap;
        }
    }
}
