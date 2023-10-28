package com.fastcampus.toyproject.common.util;

import com.fastcampus.toyproject.common.exception.DefaultException;
import com.fastcampus.toyproject.common.exception.ExceptionCode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
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
        this.baseUrl = baseUrl;
    }

    @Value("${spring.google-api.key}")
    private void setKey(String key) {
        this.key = key;
    }

    private URL createResultUrl(String location) {
        try {
            return new URL(baseUrl + URLEncoder.encode(location, "UTF-8") + "&key=" + key);
        } catch (IOException e) {
            throw new DefaultException(ExceptionCode.NO_LOCATION);
        }
    }

    public String findLocation(String location) {
        try {
            HttpURLConnection httpURLConnection =
                (HttpURLConnection) createResultUrl(location).openConnection();
            httpURLConnection.setRequestProperty("Content-type", "application/json");

            BufferedReader br =
                new BufferedReader(new InputStreamReader
                    (httpURLConnection.getInputStream(), "UTF-8")
                );
            StringBuilder response = new StringBuilder();

            while (br.ready()) {
                response.append(br.readLine());
            }
            br.close();
            httpURLConnection.disconnect();

            JSONObject jsonResponse = new JSONObject(response.toString())
                .getJSONArray("results").getJSONObject(0)
                .getJSONObject("geometry").getJSONObject("location");


            return "위도 "+String.valueOf(jsonResponse.getDouble("lat"))
                + "\n경도" + String.valueOf(jsonResponse.getDouble("lng"));

        } catch (IOException e) {
            log.error(ExceptionCode.NO_LOCATION.getMsg());
            return location;
        }
    }

}
