package com.fastcampus.toyproject.common.util;

import org.junit.jupiter.api.Test;

class LocationUtilTest {

    @Test
    void 위치_변환() {
        String location1 = "서울특별시 종로구 청와대로 청와대";
        String location2 = "미국 펜실베니아 백악관";


        String[] result1 = LocationUtil.findLocation(location1);
        String[] result2 = LocationUtil.findLocation(location2);

        System.out.println("청와대: " + LocationUtil.findLocation(location1)[0] + ":"+LocationUtil.findLocation(location1)[1]);
        System.out.println("백악관: " + LocationUtil.findLocation(location2)[0] + ":"+LocationUtil.findLocation(location2)[1]);

    }

}