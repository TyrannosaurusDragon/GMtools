package com.zach.gmtools.com.zach.gmtools.objects;

import com.zach.gmtools.FileProcessor;
import com.zach.gmtools.MainScreen;

import java.util.ArrayList;

public class Beast implements Type {
    public static final String Type = "Beast";
    public static final String Holder = "Beasts";
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Skill> skills = new ArrayList<>();
    private final Object[][] Values = {
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

    public Beast(int... id){
        if(id==null){
            new Beast(MainScreen.beasts.getNextID());
            return;
        } else {
            Values[0][1] = id[0];
        }
    }

    public Object[][] getValues(){
        return Values;
    }

    public Object getValue(String type){
        if (type.equals("Items") || type.equals("Skills")) {
            return null;//TODO
        }
        for(int i=0;i<Values.length;i++){
            if(Values[i][0].equals(type)){
                return Values[i][1];
            }
        }
        return null;
    }

    public void saveValue(String type, Object value){
        try {
            if(type.equals("Items") || type.equals("Skills")){
                return;//TODO
            }
            for(int i=0;i<Values.length;i++){
                if(Values[i][0].equals(type)){
                    Values[i][1] = value;
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        writeToFile();
    }

    public void writeToFile(){
        try {
            ArrayList<Object[]> toReturn = new ArrayList<>();
            for(int i=0;i<Values.length;i++){
                if (Values[i][0].equals("Items") || Values[i][0].equals("Skills")) {
                    continue;//TODO
                }
                Object[] tempObj = {Values[i][0],Values[i][1]};
                toReturn.add(tempObj);
            }
            FileProcessor.saveSingle(Type, Holder, toReturn);
        } catch (Exception e){
            e.printStackTrace();

        }
    }

    public void readFromFile(){
        try {
            ArrayList<Object[]> backTalk = FileProcessor.loadSingle(Type, Holder,Integer.parseInt(Values[0][1].toString()));
            if(backTalk == null) return;
            for(int i=0;i<Values.length;i++){
                for(int j=0;j<backTalk.size();j++){
                    if (Values[i][0].equals("Items") || Values[i][0].equals("Skills")) {
                        continue;//TODO
                    }
                    if(Values[i][0] == backTalk.get(j)[0]){
                        Values[i][1] = backTalk.get(j)[1];
                    }
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }
}
