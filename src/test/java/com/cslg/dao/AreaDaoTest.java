package com.cslg.dao;

import com.cslg.BaseTest;
import com.cslg.entity.Area;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-04-30 11:04
 **/
public class AreaDaoTest extends BaseTest {
    @Autowired
    private AreaDao areaDao;

    @Test
    public void testGetArea(){
        List<Area> areaList = areaDao.getArea();
        Assert.assertEquals(2,areaList.size());
    }
}
