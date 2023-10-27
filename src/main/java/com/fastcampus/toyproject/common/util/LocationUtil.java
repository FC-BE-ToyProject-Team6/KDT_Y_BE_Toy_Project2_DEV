package com.fastcampus.toyproject.common.util;

import com.fastcampus.toyproject.common.util.api.GoogleApiProperties;
import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

public class LocationUtil {

    private static void connectGoogleMap(String location) {
        try {
            String surl = googleApiProperties.getBaseUrl()
                + URLEncoder.encode(location, "UTF-8")
                + "&key=" + googleApiProperties.getKey();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String[] findLocation(String location) {
        connectGoogleMap(location);

        GeocoderRequest geocoderRequest = new GeocoderRequestBuilder()
            .setAddress(location)
            .setLanguage("ko")
            .getGeocoderRequest();
        try {
            Geocoder geocoder = new Geocoder();
            GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);

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
            e.printStackTrace();//!!!default exception으로 바꾸기!!!!
        }
        return new String[]{location};
    }

}
