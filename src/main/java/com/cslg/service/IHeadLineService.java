package com.cslg.service;

import com.cslg.entity.HeadLine;

import java.util.List;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-19 11:36
 **/
public interface IHeadLineService {
    List<HeadLine> getHeadLineList(HeadLine headLine);
}
