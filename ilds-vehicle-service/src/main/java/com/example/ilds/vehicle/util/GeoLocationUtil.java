package com.example.ilds.vehicle.util;

import com.alibaba.fastjson.JSON;
import com.example.ilds.vehicle.model.entity.Vehicle;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;


@Service
public class GeoLocationUtil {
    public static Vehicle getAddress(String address) {
        //获取地址的url
        String httpUrl = "http://api.map.baidu.com/geocoding/v3/?address=" + address + "&output=json&ak=DLWEtqgKlNDmzrDB2fsSMnWspZmVsaLA";

        StringBuilder json = new StringBuilder();
        try {
            URL url = new URL(httpUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        String data = json.toString();
        if (data != null && !"".equals(data)) {
            Map map = JSON.parseObject(data, Map.class);
            if ("0".equals(map.getOrDefault("status", "500").toString())) {
                Map childMap = (Map) map.get("result");
                Map posMap = (Map) childMap.get("location");
                // 经度
                double lng = Double.parseDouble(posMap.getOrDefault("lng", "0").toString());
                // 纬度
                double lat = Double.parseDouble(posMap.getOrDefault("lat", "0").toString());

                Vehicle vehicle = new Vehicle();
                vehicle.setLat(lat);
                vehicle.setLng(lng);
                Map<String, Double> addressMap = new HashMap<>();
                addressMap.put("lng", lng);
                addressMap.put("lat", lat);
                return vehicle;
            }
        }
        return null;
    }
    public static double getDistance(double longitudeFrom, double latitudeFrom, double longitudeTo, double latitudeTo) {
        GlobalCoordinates source = new GlobalCoordinates(latitudeFrom, longitudeFrom);
        GlobalCoordinates target = new GlobalCoordinates(latitudeTo, longitudeTo);

        return new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.Sphere, source, target).getEllipsoidalDistance();
    }
}
