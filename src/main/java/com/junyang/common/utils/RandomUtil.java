package com.junyang.common.utils;

import java.util.Random;

/**
 * 类: RandomUtil <br>
 * 描述: 随机码生成工具<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年08月21日 11:16
 */
public class RandomUtil {

    private static char[] captchars = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'k', 'm',
            'n', 'p', 'q', 'r', 's', 't', 'w', 'x', 'y', 'z', '2', '3', '4', '5', '6' , '7','8' };

    private static char[] numbers = new char[] { '0','1', '2', '3', '4', '5', '6' , '7','8','9' };
    private static char[] charAndNumber = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', 'A','B','C','D','E','F','G','H','I','J','K',
            'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y' ,'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'  };

    public static String randomCode(int length) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            Random r = new Random();
            int index = r.nextInt(captchars.length);
            sb.append(captchars[index]);
        }

        return sb.toString();
    }

    public static String randomSmsCode(int length) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            Random r = new Random();
            int index = r.nextInt(numbers.length);
            sb.append(numbers[index]);
        }
        return sb.toString();
    }
}
