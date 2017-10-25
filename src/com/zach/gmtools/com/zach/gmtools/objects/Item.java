package com.zach.gmtools.com.zach.gmtools.objects;

import com.zach.gmtools.Type;

import java.util.ArrayList;

public class Item implements Type {

    public Item(int... id){

    }

    private ArrayList<Object[]> Values = new ArrayList<>();

    @Override
    public ArrayList<Object[]> getValues() {
        return Values;
    }

    @Override
    public String getTypeString() {
        return "Item";
    }

    @Override
    public String getHolderString() {
        return "Items";
    }

    @Override
    public void setupValues() {

    }
}
