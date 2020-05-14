package com.cslg.controller.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-13 09:00
 **/
@Controller
@RequestMapping("/shopadmin/")
public class ShopAdminController {

    @GetMapping("shopoperate")
    public String shopOperation(){
        return "shop/shopoperation";
    }
    
}
