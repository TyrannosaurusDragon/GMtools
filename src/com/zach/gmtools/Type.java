package com.zach.gmtools;

import java.util.ArrayList;

public interface Type {

    Object[][] getValues();
    String getTypeString();
    String getHolderString();

    default void readFromFile(){
        try {
            ArrayList<Object[]> backTalk = FileProcessor.loadSingle(getTypeString(), getHolderString(),Integer.parseInt(getValues()[0][1].toString()));
            if(backTalk == null) return;
            for(int i=0;i<getValues().length;i++){
                for(int j=0;j<backTalk.size();j++){
                    if(getValues()[i][0] == backTalk.get(j)[0]){
                        getValues()[i][1] = backTalk.get(j)[1];
                    }
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    default void saveValue(String type, Object value){
        try {
            for(int i=0;i<getValues().length;i++){
                if(getValues()[i][0].equals(type)){
                    getValues()[i][1] = value;
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        writeToFile();
    }

    default void writeToFile(){
        try {
            ArrayList<Object[]> toReturn = new ArrayList<>();
            for(int i=0;i<getValues().length;i++){
                Object[] tempObj = {getValues()[i][0],getValues()[i][1]};
                toReturn.add(tempObj);
            }
            FileProcessor.saveSingle(getTypeString(), getHolderString(), toReturn);
        } catch (Exception e){
            e.printStackTrace();

        }
    }

    default Object getValue(String type){
        for(int i=0;i<getValues().length;i++){
            if(getValues()[i][0].equals(type)){
                return getValues()[i][1];
            }
        }
        return null;
    }

}
