package com.zach.gmtools.com.zach.gmtools.objects;

import com.zach.gmtools.FileProcessor;
import com.zach.gmtools.Holder;
import com.zach.gmtools.Type;

import java.util.ArrayList;

public class Beasts implements Holder {

    private int IDCount;
    private ArrayList<Type> beasts = new ArrayList<>();

    public Beasts(){
        loadIDCount();
        loadAll();
    }

    @Override
    public ArrayList<Type> getList() {
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
    public String getHolderString() {
        return "Beasts";
    }

    @Override
    public void loadAll() {
        try {
            getList().clear();
            ArrayList<String> tempObj = FileProcessor.getFilesFromFolder(getHolderString());
            if(tempObj==null) return;
            tempObj.forEach(i->{
                Beast tempBeast = new Beast(Integer.parseInt(i));
                tempBeast.readFromFile();
                getList().add(tempBeast);
            });
        } catch (NumberFormatException e) {
            e.printStackTrace();//TODO
        }
    }
}
