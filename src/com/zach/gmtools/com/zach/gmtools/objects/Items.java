package com.zach.gmtools.com.zach.gmtools.objects;

import com.zach.gmtools.FileProcessor;
import com.zach.gmtools.Holder;
import java.util.ArrayList;

public class Items implements Holder {

    private ArrayList<Object> items = new ArrayList<>();
    private int IDCount;


    public Items(){
        loadIDCount();
        loadAll();
    }

    @Override
    public ArrayList<Object> getList() {
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
    public Object getByID(int id) {
        return null;//TODO
    }

    @Override
    public ArrayList<Object> getBySearch(String bit) {
        return null;//TODO
    }

    @Override
    public void loadAll() {
        try {
            items.clear();
            ArrayList<Object> tempObj = FileProcessor.getFilesFromFolder(/*Item.Holder*/"");
            if(tempObj==null) return;
            for(int i=0;i<tempObj.size();i++){
                Item tempItem = new Item(/*Integer.parseInt(tempObj.get(i).toString())*/);/*TODO*/
                //TODO tempItem.readFromFile();
                items.add(tempItem);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();//TODO
        }
    }

    @Override
    public void saveAll() {
        for(int i=0;i<items.size();i++){
            //TODO items.get(i).writeToFile();
        }
    }

    @Override
    public String getHolderString() {
        return "Items";
    }
}
