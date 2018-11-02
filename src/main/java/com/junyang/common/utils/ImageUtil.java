package com.junyang.common.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 类: ImageUtil <br>
 * 描述: 图片工具<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年08月28日 10:36
 */
public class ImageUtil {

    private static Random generator = new Random();

    private static char[] captchars = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'k', 'm',
            'n', 'p', 'q', 'r', 's', 't', 'w', 'x', 'y', 'z', '2', '3', '4', '5', '6' , '7','8' };

    /**
     * 产生随机字体
     *
     * @return
     */
    private static Font getFont() {
        int size = 55;
        Random random = new Random();
        Font font[] = new Font[5];
        font[0] = new Font("Ravie", Font.PLAIN, size);
        font[1] = new Font("Antique Olive Compact", Font.PLAIN, size);
        font[2] = new Font("Forte", Font.PLAIN, size);
        font[3] = new Font("Wide Latin", Font.PLAIN, size);
        font[4] = new Font("Gill Sans Ultra Bold", Font.PLAIN, size);
//        return font[random.nextInt(5)];
        return font[1];
    }


    /**
     * 随机产生定义的颜色
     *
     * @return
     */
    private Color getRandColor() {
        Random random = new Random();
        Color color[] = new Color[10];
        color[0] = new Color(32, 158, 25);
        color[1] = new Color(218, 42, 19);
        color[2] = new Color(31, 75, 208);
        return color[random.nextInt(3)];
    }

    public  static Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public static BufferedImage createValidCodeImage(String code){
        // 在内存中创建图象
        int width = 160, height = 80;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();

        // 生成随机类
        Random random = new Random();

        // 设定背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);

        // 设定字体
        g.setFont(getFont());

        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }


        for (int i = 0; i < code.length(); i++) {
            g.setColor(new Color(30 + random.nextInt(80), 30 + random.nextInt(80), 30 + random.nextInt(80)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(code.charAt(i)+"", 30 * i + 20, 58);
        }
        return image;
    }
}
