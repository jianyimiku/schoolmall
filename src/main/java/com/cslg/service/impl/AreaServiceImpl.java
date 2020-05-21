package com.cslg.service.impl;

import com.cslg.cache.JedisUtil;
import com.cslg.dao.AreaDao;
import com.cslg.entity.Area;
import com.cslg.exceptions.AreaOperationException;
import com.cslg.service.IAreaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;

    private static String AREALISTKEY = "arealist";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Area> getAreaList() {
        String key = AREALISTKEY;
        List<Area> areaList = null;
        ObjectMapper objectMapper = new ObjectMapper();
        if (!jedisKeys.exists(key)) {
            areaList = areaDao.getArea();
            String jsonString = null;
            try {
                jsonString = objectMapper.writeValueAsString(areaList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new AreaOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        } else {
            String jsonString = jedisStrings.get(key);
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
            try {
                areaList = objectMapper.readValue(jsonString, javaType);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return areaList;
    }


}
