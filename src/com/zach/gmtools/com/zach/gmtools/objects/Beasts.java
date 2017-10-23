package com.zach.gmtools.com.zach.gmtools.objects;

import com.zach.gmtools.FileProcessor;
import com.zach.gmtools.Holder;
import java.util.ArrayList;

public class Beasts implements Holder {

    private int IDCount;
    private ArrayList<Object> beasts = new ArrayList<>();

    public Beasts(){
        loadIDCount();
        loadAll();
    }

    @Override
    public ArrayList<Object> getList() {
        return beasts;
    }

    @Override
    public int getIDCount() {
        return IDCount;
    }

    @Override
    public void setIDCount(int id) {
        this.IDCount = id;
    }

    @Override
    public Object getByID(int id) {
        for(int i=0;i<beasts.size();i++){
            Beast tempBeast = (Beast)beasts.get(i);
            if(tempBeast.getValue("ID").equals(id)){
                return tempBeast;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Object> getBySearch(String bit) {
        ArrayList<Object> toReturn = new ArrayList<>();
        for(int i=0;i<beasts.size();i++){
            Beast tempBeast = (Beast)beasts.get(i);
            int valuesSize = tempBeast.getValues().length;
            for(int j=0;j<valuesSize;j++){
                if(tempBeast.getValues()[j][1].toString().toLowerCase()
                        .contains(bit.toLowerCase())){
                    toReturn.add(tempBeast);
                    break;
                }
            }
        }
        return toReturn;
    }

    @Override
    public void add(Object obj) {
        if(!beasts.contains(obj) && obj instanceof Beast){
            beasts.add(obj);
        }
    }

    @Override
    public void remove(Object obj) {
        if(beasts.contains(obj)){
            beasts.remove(obj);
        }
    }

    @Override
    public void loadAll() {
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
            e.printStackTrace();//TODO
        }
    }

    @Override
    public void saveAll() {
        for(int i=0;i<beasts.size();i++){
            ((Beast)beasts.get(i)).writeToFile();
        }
    }

    @Override
    public String getHolderString() {
        return "Beasts";
    }
}
