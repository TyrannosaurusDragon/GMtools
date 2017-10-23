package com.zach.gmtools.com.zach.gmtools.objects;

import com.zach.gmtools.MainScreen;
import com.zach.gmtools.Type;

public class Item implements Type {

    public Item(int... id){

    }

    private Object[][] Values = {
            {}
    };

    @Override
    public Object[][] getValues() {
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
}
