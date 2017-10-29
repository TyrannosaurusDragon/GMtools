package com.zach.gmtools;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

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
			Set<Map.Entry<String,Object>> tempSet = getList().get(i).getValues().entrySet();
			for (Map.Entry<String, Object> tempObject : tempSet) {
				Object value = tempObject.getValue();
				if (!(value instanceof String)) continue;
				if (((String) value).toLowerCase().contains(bit.toLowerCase())) {
					toReturn.add(tempType);
					break;
				}
			}
		}
		return toReturn;
	}

	default Type getByID(int id){
		for(int i=0;i<getList().size();i++){
			Type tempType = getList().get(i);
			if(tempType.getID()==id){
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
			getList().add(obj);
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

	default boolean listContains(Type obj){
		return getList().contains(obj);
	}

	default int getNextID(){
		int idCount = getIDCount();
		idCount++;
		setIDCount(idCount);
		saveNextID();
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
