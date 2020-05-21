package com.cslg.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-19 13:22
 **/
@Controller
@RequestMapping("/frontend")
public class FrontendController {
    @RequestMapping("/index")
    private String index(){
        return "frontend/index";
    }

    @RequestMapping("/shoplist")
    private String shoplist(){
        return "frontend/shoplist";
    }

    @RequestMapping("/productdetail")
    private String showProductDetail(){
        return "frontend/productdetail";
    }

    @RequestMapping("/shopdetail")
    private String shopdetail(){
        return "frontend/shopdetail";
    }
}
