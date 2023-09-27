package com.example.demo.utils;

import com.example.demo.jsonBean.Jentity;

import java.util.Comparator;

public class AppComparator implements Comparator<Jentity> {
    @Override
    public int compare(Jentity o1, Jentity o2) {
        char c = 'a';
        char c1 = 'a';
        try {
            c = o1.getName().toLowerCase().toCharArray()[0];
            c1 = o2.getName().toLowerCase().toCharArray()[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c - c1;
    }
}
