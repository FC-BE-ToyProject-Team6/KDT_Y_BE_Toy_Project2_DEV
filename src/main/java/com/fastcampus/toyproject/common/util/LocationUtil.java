package com.fastcampus.toyproject.common.util;

import com.fastcampus.toyproject.common.exception.DefaultException;
import com.fastcampus.toyproject.common.exception.ExceptionCode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*공백 기준 첫 글자만 따서 검색 되므로 공백 없는 키워드만 넣어주세요*/
@Component
@Slf4j
public class LocationUtil {

    private static String baseUrl;
    private static String key;

    @Value("${spring.google-api.base-url}")
    private void setBaseUrl(String baseUrl) {
        LocationUtil.baseUrl = baseUrl;
    }

    @Value("${spring.google-api.key}")
    private void setKey(String key) {
        LocationUtil.key = key;
    }

    private URL createResultUrl(String location) {
        try {
            return new URL(baseUrl + URLEncoder.encode(location, StandardCharsets.UTF_8) + "&key=" + key);
        } catch (IOException e) {
            throw new DefaultException(ExceptionCode.BAD_REQUEST);
        }
    }

    public String findLocation(String location) {
        try {
            HttpURLConnection httpURLConnection =
                (HttpURLConnection) createResultUrl(location).openConnection();
            httpURLConnection.setRequestProperty("Content-type", "application/json");

            BufferedReader br =
                new BufferedReader(new InputStreamReader
                    (httpURLConnection.getInputStream(), StandardCharsets.UTF_8)
                );
            StringBuilder response = new StringBuilder();

            if (br.ready()) {
                String str = "";
                while ((str = br.readLine()) != null) {
                    response.append(str);
                }
            }

            br.close();
            httpURLConnection.disconnect();

            JSONObject jsonResponse = new JSONObject(response.toString());

            if ("OK".equals(jsonResponse.get("status"))) {
                JSONObject okResponse = new JSONObject(response.toString())
                    .getJSONArray("results").getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location");

                return "위도: " + okResponse.getDouble("lat")
                    + System.lineSeparator()
                    + "경도: " + okResponse.getDouble("lng");
            } else {
                return "좌표가 없습니다.";
            }


        } catch (IOException e) {
            log.error(ExceptionCode.BAD_REQUEST.getMsg());
            return location;
        }
    }

}
