package com.cslg.util;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-03 11:11
 **/
public class PathUtil {
    private static String seperator = System.getProperty("file.separator");

    public static String getImagePath() {
        //System.getProperty 可以获取java的一些环境变量相关信息
        //os.name  可以获取当前系统的版本 user.dir 可以获取当前系统的路径
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "D:/schoolmall/src/main/webapp/resources/images/";
        } else {
            basePath = "/home/";
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }


    public static String getShopImagePath(long shopId) {
        String imagePath = "upload/item/shop/" + shopId + "/";
        return imagePath.replace("/",seperator);
    }
}
