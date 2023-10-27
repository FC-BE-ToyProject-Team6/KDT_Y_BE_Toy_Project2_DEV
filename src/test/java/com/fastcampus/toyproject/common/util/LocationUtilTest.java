package com.fastcampus.toyproject.common.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LocationUtilTest {

    @Autowired
    LocationUtil locationUtil;
    @Test
    void 위치_변환() {

        String location1 = "청와대";
        String location2 = "백악관";

        String[] result1 = locationUtil.findLocation(location1);
        String[] result2 = locationUtil.findLocation(location2);

        System.out.println("청와대: " + result1[0] + ":"+result1[1]);
        System.out.println("백악관: " + result2[0] + ":"+result2[1]);

    }

}