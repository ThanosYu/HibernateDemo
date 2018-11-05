package com.thanos;

import com.thanos.model.O2OBanner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Thanos Yu
 * @date 10/25/2018 2:31 PM
 */
public class TestMap {

    public static void main(String[] args) {
        Map<String,O2OBanner> map = new HashMap<>();
        O2OBanner banner = new O2OBanner();
        map.put("key",banner);
        System.out.println("------------------key: "+map.get("key").getName());
        banner.setName("kaka");
        System.out.println("------------------key: "+map.get("key").getName());
    }
}