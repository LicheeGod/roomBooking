package com.demo.roombooking.common.util;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Data
public class Token {

    public static String getToken() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        Random random = new Random();

        long currentMills = System.currentTimeMillis();

        // 年份 + 13位时间戳 + 三位随机数
        return simpleDateFormat.format(new Date(currentMills)) + currentMills +random.nextInt(900);

    }
}
