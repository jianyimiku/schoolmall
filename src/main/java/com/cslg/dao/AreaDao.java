package com.cslg.dao;

import com.cslg.entity.Area;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-04-30 10:54
 **/
public interface AreaDao {
    /**
     * 列出区域列表
     * @return
     */
    List<Area> getArea();
}
