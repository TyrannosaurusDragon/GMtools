package com.zach.gmtools;

import java.util.HashMap;

public interface Type {

    default HashMap<String, Object> getValues(){
        HashMap<String,Object> thing = new HashMap<>();
        thing.put("ID",3000);
        return thing;
    }
    String getTypeString();
    String getHolderString();

    default int getID(){
        return Integer.parseInt(getValues().get("ID").toString());
    }

    default void readFromFile(){
        try {
            HashMap<String, Object> backTalk = FileProcessor.loadSingle(getTypeString(), getHolderString(),Integer.parseInt(getValues().get("ID").toString()));
            if(backTalk == null) return;
            getValues().clear();
            getValues().putAll(backTalk);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    default void saveValue(String type, Object value){
        getValues().put(type, value);
    }

    default void writeToFile(){
        try {
            FileProcessor.saveSingle(getTypeString(), getHolderString(), getValues());
        } catch (Exception e){
            e.printStackTrace();

        }
    }

    default Object getValue(String type){
        return getValues().get(type);
    }

}
