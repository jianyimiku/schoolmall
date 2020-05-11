package com.cslg.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-03 10:45
 **/
public class ImageUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat simple = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();
    public static String generateThumbnail(CommonsMultipartFile thumbnail, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File file = new File(PathUtil.getImagePath()+relativeAddr);
        try {
            Thumbnails.of(thumbnail.getInputStream()).size(200,200)
                    .watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath+"/1.jpg")),0.25f)
                    .outputQuality(0.8f)
                    .toFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void makeDirPath(String targetAddr) {
    }

    private static String getFileExtension(CommonsMultipartFile thumbnail) {
       String name = thumbnail.getOriginalFilename();
       String extension = name.substring(name.lastIndexOf("."));
       return extension;
    }

    private static String getRandomFileName() {
        //获取随机五位数 [10000,99999)
        int rannum = r.nextInt(89999)+10000;
        String nowTimeStr = simple.format(new Date());
        return nowTimeStr+rannum;
    }


    public static void main(String[] args) throws IOException {
        String baseBath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        //将图片输入 输出大小 添加水印(水印位置，水印图片的地址，透明度)  压缩比 生成文件的地址
        Thumbnails.of(new File(PathUtil.getImagePath() + "miku.jpg")).size(1920, 1080)
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(baseBath + "/1.jpg")), 0.25f).outputQuality(0.8f)
                .toFile("D:\\schoolmall\\src\\main\\webapp\\resources\\images\\mikunew.jpg");
    }
}
