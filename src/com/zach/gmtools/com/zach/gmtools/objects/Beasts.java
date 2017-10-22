package com.zach.gmtools.com.zach.gmtools.objects;

import com.zach.gmtools.FileProcessor
import com.zach.gmtools.Holder

import java.util.ArrayList;

public class Beasts extends Holder {

	@Override
    private static ArrayList<> holder = new ArrayList<Beast>();
	

    public Beasts(){
        IDCount=FileProcessor.loadIDList("Beasts");
        loadAllBeasts();
    }

    public void setHolderID(int holderID) {
        this.holderID = holderID;
    }

    public int getHolderID(){
        return this.holderID;
    }

    public static int listSize(){
        return beasts.size();
    }

    public static boolean listContains(Beast b){
        return beasts.contains(b);
    }

    public static int getNextID(){
        IDCount++;
        saveNextID();
        return IDCount;
    }

    public static void saveNextID(){
        FileProcessor.saveIDList("Beasts", IDCount);
    }

    public static Beast getBeast(Beast b){
        if(beasts.contains(b)){
            return beasts.get(beasts.indexOf(b));
        } else {
            return null;
        }
    }

    public static Beast getBeastByIndex(int id){
        return beasts.get(id);
    }

    public static Beast getBeastByID(int id){
        for(int i=0;i<beasts.size();i++){
            if(beasts.get(i).getValue("ID").equals(id)){
                return beasts.get(i);
            }
        }
        return null;
    }

    public static void addBeast(Beast b){
        if(!listContains(b)) {
            beasts.add(b);
            saveAllBeasts();
        }
    }

    public static void removeBeast(Beast b){
        if(listContains(b)) {
            beasts.remove(b);
            saveAllBeasts();
        }
    }

    public static void loadAllBeasts(){
        try {
            beasts.clear();
            ArrayList<Object> tempObj = FileProcessor.getFilesFromFolder(Beast.Holder);
            if(tempObj==null) return;
            for(int i=0;i<tempObj.size();i++){
                Beast tempBeast = new Beast(Integer.parseInt(tempObj.get(i).toString()));
                tempBeast.readFromFile();
                beasts.add(tempBeast);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static void saveAllBeasts(){
        for(int i=0;i<beasts.size();i++){
            beasts.get(i).writeToFile();
        }
    }

}
