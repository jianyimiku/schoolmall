package com.cslg.dao;

import com.cslg.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-18 16:51
 **/
public interface HeadLineDao {
    /**
     * 根据传入的查询条件
     * @param headLine
     * @return
     */
    List<HeadLine> queryHeadLine(HeadLine headLine);
}
