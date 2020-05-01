package com.cslg.service.impl;

import com.cslg.dao.AreaDao;
import com.cslg.entity.Area;
import com.cslg.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-04-30 17:13
 **/
@Service("areaServiceImpl")
public class AreaServiceImpl implements IAreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.getArea();
    }
}
