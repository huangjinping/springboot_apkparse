package com.example.demo.utils;

import com.example.demo.bean.Jentity;

import java.util.Comparator;

public class AppComparatorStats implements Comparator<Jentity> {
    @Override
    public int compare(Jentity o1, Jentity o2) {
        int c = 0;
        int c1 = 0;
        try {
            c = o1.getState();
            c1 = o2.getState();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c - c1;
    }
}
