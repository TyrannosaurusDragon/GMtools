package com.zach.gmtools.com.zach.gmtools.objects;

import com.zach.gmtools.MainScreen;
import com.zach.gmtools.Type;
import java.util.HashMap;

public class Item implements Type {

    private HashMap<String, Object> Values;

    public Item(int... id){
        Values = new HashMap<>();
        if(id==null){
            new Item(MainScreen.items.getNextID());
        } else {
            Values.put("ID", id[0]);
        }
    }

    @Override
    public HashMap<String, Object> getValues() {
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
