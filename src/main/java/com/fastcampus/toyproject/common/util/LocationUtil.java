package com.fastcampus.toyproject.common.util;

import com.fastcampus.toyproject.common.exception.DefaultException;
import com.fastcampus.toyproject.common.exception.ExceptionCode;
import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
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

    private static String connectGoogleMap(String location) {
        try {
            return baseUrl + URLEncoder.encode(location, "UTF-8")
                + "&key=" + key;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String[] findLocation(String location) {
        connectGoogleMap(location);
        GeocoderRequest geocoderRequest = new GeocoderRequestBuilder()
            .setAddress(location)
            .setLanguage("ko")
            .getGeocoderRequest();
        try {
            Geocoder geocoder = new Geocoder();
            GeocodeResponse geocoderResponse = geocoder.request(new Gson(), connectGoogleMap(location));

            if (geocoderResponse.getStatus() == GeocoderStatus.OK
                & !geocoderResponse.getResults().isEmpty()) {
                GeocoderResult geocoderResult = geocoderResponse.getResults().iterator().next();
                LatLng latitudeLongitude = geocoderResult.getGeometry().getLocation();

                String[] coords = new String[2];
                coords[0] = latitudeLongitude.getLat().toString();
                coords[1] = latitudeLongitude.getLng().toString();
                return coords;
            }
        } catch (IOException e) {
            throw new DefaultException(ExceptionCode.NO_LOCATION);
        }
        return new String[]{location};
    }



}
