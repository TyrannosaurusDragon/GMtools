package com.zach.gmtools.com.zach.gmtools.objects;

import com.zach.gmtools.FileProcessor;
import com.zach.gmtools.Holder;
import com.zach.gmtools.Type;

import java.util.ArrayList;

public class Items implements Holder {

    private ArrayList<Type> items = new ArrayList<>();
    private int IDCount;


    public Items(){
        loadIDCount();
        loadAll();
    }

    @Override
    public ArrayList<Type> getList() {
        return items;
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
    public void loadAll() {
        try {
            getList().clear();
            ArrayList<String> tempObj = FileProcessor.getFilesFromFolder(getHolderString());
            if(tempObj==null) return;
            tempObj.forEach(strng -> {
                Item tempItem = new Item(Integer.parseInt(strng));
                tempItem.readFromFile();
                getList().add(tempItem);
            });
        } catch (NumberFormatException e) {
            e.printStackTrace();//TODO
        }
    }

    @Override
    public String getHolderString() {
        return "Items";
    }
}
