package com.zach.gmtools.com.zach.gmtools.objects;

import java.util.ArrayList;
import java.util.HashMap;

public class Beast implements com.zach.gmtools.Type {
    private ArrayList<Item> items;
    private ArrayList<Skill> skills;
    private HashMap<String, Object> Values;

    public Beast(int id){
        items = new ArrayList<>();
        skills = new ArrayList<>();
        Values = new HashMap<>();
        Values.put("ID",id);
    }

    @Override
    public HashMap<String, Object> getValues(){
        return Values;
    }

    @Override
    public String getTypeString() {
        return "Beast";
    }

    @Override
    public String getHolderString() {
        return "Beasts";
    }

}
