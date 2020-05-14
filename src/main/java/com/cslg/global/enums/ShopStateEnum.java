package com.cslg.global.enums;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-11 11:23
 **/
public enum ShopStateEnum {
    CHECK(0,"审核中"),
    OFFINE(-1,"非法店铺"),
    SUCCESS(1,"操作成功"),
    PASS(2,"通过认证"),
    INNER_ERROR(-1001,"内部系统错误"),
    NULL_SHOPID(-1002,"ShopId为空"),
    NULL_SHOP(-1003,"Shop信息为空");

    private Integer state;
    private String stateInfo;

    ShopStateEnum(Integer state,String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public Integer getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }


    public static ShopStateEnum getByState(Integer state){
        for (ShopStateEnum shopStateEnum:ShopStateEnum.values()){
            if (shopStateEnum.state.equals(state)){
                return shopStateEnum;
            }
        }
        return null;
    }
}
