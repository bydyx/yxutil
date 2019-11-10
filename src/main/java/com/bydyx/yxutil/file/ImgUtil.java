package com.bydyx.yxutil.file;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author bydyx
 * @date 2019/11/10 20:26
 */
public class ImgUtil {
    private final static int width = 200;
    private final static int height = 69;

    public static BufferedImage createBufferedImage(String randomText) {
        BufferedImage verifyImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //生成对应宽高的初始图片
        drawRandomText(verifyImg, randomText);
        return verifyImg;
    }

    /**
     * 随机取色
     */
    private static Color getRandomColor() {
        Random ran = new Random();
        Color color = new Color(ran.nextInt(256),
        ran.nextInt(256), ran.nextInt(256));
        return color;
    }

    private static void drawRandomText(BufferedImage verifyImg, String randomText) {
        Graphics2D graphics = (Graphics2D) verifyImg.getGraphics();
        //设置画笔颜色-验证码背景色
        graphics.setColor(Color.WHITE);
        //填充背景
        graphics.fillRect(0, 0, width, height);
        graphics.setFont(new Font("微软雅黑", Font.BOLD, 40));
        //旋转原点的 x 坐标
        int x = 10;
        Random random = new Random();
        for (final char ch : randomText.toCharArray()) {
            graphics.setColor(getRandomColor());
            //设置字体旋转角度,角度小于30度
            int degree = random.nextInt() % 30;
            //正向旋转
            graphics.rotate(degree * Math.PI / 180, x, 45);
            graphics.drawString(String.valueOf(ch), x, 45);
            //反向旋转
            graphics.rotate(-degree * Math.PI / 180, x, 45);
            x += 48;
        }
        //画干扰线
        for (int i = 0; i < 6; i++) {
            // 设置随机颜色
            graphics.setColor(getRandomColor());
            // 随机画线
            graphics.drawLine(random.nextInt(width), random.nextInt(height),
            random.nextInt(width), random.nextInt(height));
        }
        //添加噪点
        for (int i = 0; i < 30; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            graphics.setColor(getRandomColor());
            graphics.fillRect(x1, y1, 2, 2);
        }
        graphics.dispose();
    }
}