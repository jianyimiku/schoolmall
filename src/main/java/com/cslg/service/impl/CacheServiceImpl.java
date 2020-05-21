package com.cslg.service.impl;

import com.cslg.cache.JedisUtil;
import com.cslg.service.ICacheService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-21 10:56
 **/
public class CacheServiceImpl implements ICacheService {
    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Override
    public void removeFromCache(String keyPrefix) {
        Set<String> keySet = jedisKeys.keys(keyPrefix + "*");
        for (String key : keySet) {
            jedisKeys.del(key);
        }
    }
}
