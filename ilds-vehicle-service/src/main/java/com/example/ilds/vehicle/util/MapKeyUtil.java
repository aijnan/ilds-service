package com.example.ilds.vehicle.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MapKeyUtil {
    public static String getMapMinOrMaxValueKey(Map<String, Double> map, String choose) {
        List<Map.Entry<String,Double>> list = new ArrayList(map.entrySet());
        Collections.sort(list, (o1, o2) -> (o1.getValue().intValue() - o2.getValue().intValue()));
        String key = "";
        if (choose.equals("min")) {
            key = list.get(0).getKey();
        } else if (choose.equals("max")) {
            key = list.get(list.size() - 1).getKey();
        }
        return key;
    }
}
