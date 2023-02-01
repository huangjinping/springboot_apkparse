package com.example.demo.utils;

import com.example.demo.jsonBean.Jentity;

import java.util.Comparator;

public class AppComparator implements Comparator<Jentity> {
    @Override
    public int compare(Jentity o1, Jentity o2) {
        try {
            char c = o1.getName().toLowerCase().toCharArray()[0];
            char c1 = o2.getName().toLowerCase().toCharArray()[0];
            return c - c1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
