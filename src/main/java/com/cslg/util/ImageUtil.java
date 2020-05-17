package com.cslg.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    private static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);


    /**
     * 将CommonsMultipartFile 转换成为File
     *
     * @param cFile
     * @return
     */
    public static File transferCommonsMultipartFiletoFile(CommonsMultipartFile cFile) {
        File file = new File(cFile.getOriginalFilename());

        try {
            cFile.transferTo(file);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return file;
    }

    public static String generateThumbnail(InputStream inputStream, String fileName, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(fileName);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug(String.format("currrent relativeAddr is %s", relativeAddr));
        File file = new File(PathUtil.getImagePath() + relativeAddr);
        logger.debug(String.format("currrent addr is %s", PathUtil.getImagePath() + relativeAddr));
        try {
            Thumbnails.of(inputStream).size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/1.jpg")), 0.25f)
                    .outputQuality(0.8f)
                    .toFile(file);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;
    }

    public static String generateNormal(InputStream inputStream, String fileName, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(fileName);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug(String.format("currrent relativeAddr is %s", relativeAddr));
        File file = new File(PathUtil.getImagePath() + relativeAddr);
        logger.debug(String.format("currrent addr is %s", PathUtil.getImagePath() + relativeAddr));
        try {
            Thumbnails.of(inputStream).size(337, 640)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/1.jpg")), 0.25f)
                    .outputQuality(0.9f)
                    .toFile(file);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;
    }

    private static void makeDirPath(String targetAddr) {
        String realFileParternPath = PathUtil.getImagePath() + targetAddr;
        File file = new File(realFileParternPath);
        if (!file.exists()) {
            file.setWritable(true);
            file.mkdirs();
        }
    }

    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }


    public static String getRandomFileName() {
        //获取随机五位数 [10000,99999)
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = simple.format(new Date());
        return nowTimeStr + rannum;
    }

    /**
     * storePath 是文件路径还是目录
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath){
        File fileOrPath = new File(PathUtil.getImagePath()+storePath);
        if (fileOrPath.exists()){
            if (fileOrPath.isDirectory()){
                File[] files = fileOrPath.listFiles();
                for (File file:files){
                    file.delete();
                }
            }
            fileOrPath.delete();
        }
    }

}
