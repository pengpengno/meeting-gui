package com.github.meeting.gui.util;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *图像资源处理工具类
 */
@Slf4j
public class ImageUtil {

    /***
     * 图片圆形裁剪
     * @param srcFilePath 源图片文件路径
     * @param circularImgSavePath 新生成的图片的保存路径，需要带有保存的文件名和后缀
     * @param targetSize 文件的边长，单位：像素，最终得到的是一张正方形的图，所以要求targetSize<=源文件的最小边长
     * @param cornerRadius 圆角半径，单位：像素。如果=targetSize那么得到的是圆形图
     * @return  文件的保存路径
     * @throws IOException
     */
    public static String makeCircularImg(String srcFilePath, String circularImgSavePath,int targetSize, int cornerRadius) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(srcFilePath));
        BufferedImage circularBufferImage = roundImage(bufferedImage,targetSize,cornerRadius);
        ImageIO.write(circularBufferImage, "png", new File(circularImgSavePath));
        return circularImgSavePath;
    }

    /**
     *
     * @param image
     * @param targetSize
     * @param cornerRadius
     * @return
     */
    private static BufferedImage roundImage(BufferedImage image, int targetSize, int cornerRadius) {
        log.info("正在处理图片");
        BufferedImage outputImage = new BufferedImage(targetSize, targetSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = outputImage.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, targetSize, targetSize, cornerRadius, cornerRadius));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return outputImage;
    }
    /**
     * 生成圆角图标
     * @param image
     * @param cornerRadius 圆角半径
     * @return
     */
    public static BufferedImage makeRoundedCorner1(BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return output;
    }
    /**图片圆角处理，背景透明化
     * @param srcImageFile 原图片
     * @param result  处理后图片
     * @param type   图片格式
     * @param cornerRadius  720为圆角
     */
    public static  void makeRoundedCorner(File srcImageFile, File result, String type, int cornerRadius) {
        try {
            BufferedImage bi1 = ImageIO.read(srcImageFile);

            // 根据需要是否使用 BufferedImage.TYPE_INT_ARGB
            BufferedImage image = new BufferedImage(bi1.getWidth(), bi1.getHeight(),
                    BufferedImage.TYPE_INT_ARGB);

            Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, bi1.getWidth(), bi1
                    .getHeight());

            Graphics2D g2 = image.createGraphics();
            image = g2.getDeviceConfiguration().createCompatibleImage(bi1.getWidth(), bi1.getHeight(), Transparency.TRANSLUCENT);
            g2 = image.createGraphics();
            g2.setComposite(AlphaComposite.Clear);
            g2.fill(new Rectangle(image.getWidth(), image.getHeight()));
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 1.0f));
            g2.setClip(shape);
            // 使用 setRenderingHint 设置抗锯齿
            g2 = image.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fillRoundRect(0, 0,bi1.getWidth(), bi1.getHeight(), cornerRadius, cornerRadius);
            g2.setComposite(AlphaComposite.SrcIn);
            g2.drawImage(bi1, 0, 0, bi1.getWidth(), bi1.getHeight(), null);
            g2.dispose();
            ImageIO.write(image, type, result);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }



    public static void main(String[] args) throws IOException {
        String url="E:/eclipse/eclipse-jee-oxygen-R-win32-x86_64/eclipse/chat/src/gril.png";
        String url1="E:\\eclipse\\eclipse-jee-oxygen-R-win32-x86_64\\eclipse\\chat\\src";
        String url3="E:\\eclipse\\eclipse-jee-oxygen-R-win32-x86_64\\eclipse\\chat\\src\\temp.png";
        //url = URLDecoder.decode(url,"utf-8");
        int width=120;
        try {
            BufferedImage image = ImageIO.read(new File(url));
            BufferedImage rounded = ImageUtil.roundImage(image, 1000, 1000);
            //image_paint.makeRoundedCorner(new File(url),new File(url3),"png",720);
            ImageIO.write(rounded, "png", new File(url3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
