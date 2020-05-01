package com.cslg.service;

import com.cslg.BaseTest;
import com.cslg.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-04-30 17:15
 **/
public class AreaServiceTest extends BaseTest {

    @Autowired
    private IAreaService iAreaService;
    @Test
    public void areaServiceTest(){
        List<Area> areaList = iAreaService.getAreaList();
        System.out.println(areaList.size());
    }
}
