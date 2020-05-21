package com.cslg.service.impl;

import com.cslg.cache.JedisUtil;
import com.cslg.dao.HeadLineDao;
import com.cslg.entity.HeadLine;
import com.cslg.service.IHeadLineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-19 11:37
 **/
@Service
public class HeadLineServiceImpl implements IHeadLineService {
    @Autowired
    private HeadLineDao headLineDao;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;

    private static String HLLISTKEY = "headlinelist";


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<HeadLine> getHeadLineList(HeadLine headLine) {
        String key = HLLISTKEY;
        List<HeadLine> headLineList = null;
        ObjectMapper objectMapper = new ObjectMapper();
        if (headLine != null && headLine.getEnableStatus() != null) {
            key = key + "_" +headLine.getEnableStatus();
        }
        if (!jedisKeys.exists(key)){
            headLineList = headLineDao.queryHeadLine(headLine);
            String jsonString;

            try {
                jsonString = objectMapper.writeValueAsString(headLineList);
            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
            jedisStrings.set(key,jsonString);
        }else {
            String jsonString = jedisStrings.get(key);
            try {
                headLineList = objectMapper.readValue(jsonString, new TypeReference<List<HeadLine>>() {});
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return headLineList;
    }
}
