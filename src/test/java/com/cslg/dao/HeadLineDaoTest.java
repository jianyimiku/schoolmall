package com.cslg.dao;

import com.cslg.BaseTest;
import com.cslg.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-18 17:07
 **/
public class HeadLineDaoTest extends BaseTest {
    @Autowired
    private HeadLineDao headLineDao;

    @Test
    public void testQuerArea(){
        List<HeadLine> headLineList = headLineDao.queryHeadLine(new HeadLine());
        System.out.println(headLineList.size());
    }
}
