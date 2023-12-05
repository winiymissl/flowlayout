package com.example.beautifulspinner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2023-12-05 14:14
 * @Version 1.0
 */
public class Character {
    static List list = new ArrayList();

    static int[] image = new int[]{R.drawable.ban, R.drawable.ban2, R.drawable.ban3, R.drawable.ban4,};
    static String[] desc = new String[]{"我是宇智波斑", "我是宇智波鼬", "我是宇智波佐助", "我是迪迦奥特曼",};
    int imageOne;
    String descOne;

    public int getImageOne() {
        return imageOne;
    }

    public void setImageOne(int imageOne) {
        this.imageOne = imageOne;
    }

    public String getDescOne() {
        return descOne;
    }

    public void setDescOne(String descOne) {
        this.descOne = descOne;
    }

    public Character(int imageOne, String descOne) {
        this.imageOne = imageOne;
        this.descOne = descOne;
    }

    public static List getInfo() {
        for (int i = 0; i < desc.length; i++) {
            list.add(new Character(image[i], desc[i]));
        }
        return list;
    }
}
