package com.zach.gmtools.com.zach.gmtools.objects;

import com.zach.gmtools.MainScreen;
import java.util.ArrayList;
import java.util.Arrays;

public class Beast implements com.zach.gmtools.Type {
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Skill> skills = new ArrayList<>();
    private Object[][] defines = {
            {"ID", 0},
            {"HP", 0},
            {"Name", ""},
            {"CR", 0},
            {"Init", 0},
            {"Speed", ""},
            {"XP", 0},
            {"AC", 0},
            {"Touch", 0},
            {"FF", 0},
            {"Fort", 0},
            {"Will", 0},
            {"Reflex", 0},
            {"Immunity", ""},
            {"DefAb", ""},
            {"SD", ""},
            {"DR", ""},
            {"Weak", ""},
            {"Offence", ""},
            {"STR", 0},
            {"DEX", 0},
            {"CON", 0},
            {"INT", 0},
            {"WIS", 0},
            {"CHA", 0},
            {"Skills", skills},
            {"Items", items},
            {"Special", ""}
    };

    private ArrayList<Object[]> Values = new ArrayList<>();

    public Beast(int... id){
        setupValues();
        if(id==null){
            new Beast(MainScreen.beasts.getNextID());
        } else {
            Values.get(0)[1] = id[0];
        }

    }

    public ArrayList<Object[]> getValues(){
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

    @Override
    public void setupValues() {
        Values.clear();
        Values.addAll(Arrays.asList(defines));
    }

}
