package com.cslg.controller.superadmin;

import com.cslg.entity.Area;
import com.cslg.service.IAreaService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * @date : 2020-04-30 17:25
 **/
@Controller("areaController")
@RequestMapping("/superadmin/")
public class AreaController {
    private static final Logger log = LoggerFactory.getLogger(AreaController.class);
    @Autowired
    private IAreaService iAreaService;

    @RequestMapping("listarea.do")
    @ResponseBody
    public Map<String,Object> listArea(){
        HashMap<String, Object> modelMap = Maps.newHashMap();
        List<Area> list = Lists.newArrayList();
        try {
            list = iAreaService.getAreaList();
            modelMap.put("rows",list);
            modelMap.put("total",list.size());
        }catch (Exception e){
            log.error("错误",e);
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
        }
        return modelMap;
    }

}
