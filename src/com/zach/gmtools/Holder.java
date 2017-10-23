package com.zach.gmtools;

import java.util.ArrayList;

public interface Holder {

	ArrayList<Object> getList();
	int getIDCount();
	void setIDCount(int id);
	void loadAll();
	void saveAll();
	String getHolderString();
	Object getByID(int id);
	ArrayList<Object> getBySearch(String bit);

	default void add(Object obj){
		if(!listContains(obj)){
			getList().add(obj);
		}
	}

	default void remove(Object obj){
		if(listContains(obj)){
			getList().remove(obj);
		}
	}

	default void refreshList(){
		loadIDCount();
		loadAll();
	}

	default int listSize(){
		return getList().size();
	}

	default boolean listContains(Object obj){
		return getList().contains(obj);
	}

	default int getNextID(){
		int idCount = getIDCount();
		idCount++;
		saveNextID();
		setIDCount(idCount);
		return idCount;
	}

	default void loadIDCount(){
		int idcount = FileProcessor.loadIDList(getHolderString());
		setIDCount(idcount);
	}

	default void saveNextID(){
		FileProcessor.saveIDList(getHolderString(), getIDCount());
	}

	default Object getByIndex(int index){
		return getList().get(index);
	}
}
