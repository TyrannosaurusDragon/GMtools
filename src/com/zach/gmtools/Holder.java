package com.zach.gmtools;

import com.zach.gmtools.com.zach.gmtools.objects.Beast;

import java.util.ArrayList;

public interface Holder {

	ArrayList<Type> getList();
	int getIDCount();
	void setIDCount(int id);
	String getHolderString();
	void loadAll();

	default ArrayList<Type> getBySearch(String bit){
		ArrayList<Type> toReturn = new ArrayList<>();
		for (int i = 0; i < getList().size(); i++) {
			Type tempType = getList().get(i);
			for (int j = 0; j < tempType.getValues().size(); j++) {
				if(tempType.getValues().get(j)[1].toString().toLowerCase().contains(bit.toLowerCase())){
					if(!toReturn.contains(tempType)){
						toReturn.add(tempType);
					}
				}
			}
		}
		return toReturn;
	}

	default Type getByID(int id){
		for(int i=0;i<getList().size();i++){
			Type tempType = getList().get(i);
			if(tempType.getValue("ID").equals(id)){
				return tempType;
			}
		}
		return null;
	}

	default void saveAll(){
		for(int i=0;i<getList().size();i++){
			(getList().get(i)).writeToFile();
		}
	}

	default void add(Type obj){
		if(!listContains(obj)){
			getList().add((Type)obj);
		}
	}

	default void remove(Type obj){
		if(listContains(obj)){
			getList().remove(obj);
			FileProcessor.deleteFile(getHolderString(), obj.getValue("ID").toString());
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

	default Type getByIndex(int index){
		return getList().get(index);
	}
}
