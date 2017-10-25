package com.zach.gmtools;

import java.util.ArrayList;

public interface Type {

    ArrayList<Object[]> getValues();
    String getTypeString();
    String getHolderString();

    default void readFromFile(){
        try {
            ArrayList<Object[]> backTalk = FileProcessor.loadSingle(getTypeString(), getHolderString(),Integer.parseInt(getValues().get(0)[1].toString()));
            if(backTalk == null) return;
            getValues().clear();
            getValues().addAll(backTalk);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    default void saveValue(String type, Object value){
        try {
            for(int i=0;i<getValues().size();i++){
                if(getValues().get(i)[0].equals(type)){
                    getValues().get(i)[1] = value;
                    return;
                }
            }
            Object[] tempIbj = {type, value};
            getValues().add(tempIbj);
        } catch(Exception e){
            e.printStackTrace();
        }
        writeToFile();
    }

    default void writeToFile(){
        try {
            FileProcessor.saveSingle(getTypeString(), getHolderString(), getValues());
        } catch (Exception e){
            e.printStackTrace();

        }
    }

    default Object getValue(String type){
        for(int i=0;i<getValues().size();i++){
            if(getValues().get(i)[0].equals(type)){
                return getValues().get(i)[1];
            }
        }
        return null;
    }

}
