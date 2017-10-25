package com.zach.gmtools;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;

public class FileProcessor {

    private static final String home = System.getProperty("user.home")+"/.gmtools/";

    public static ArrayList<String> getFilesFromFolder(String holder){
        try {
            ArrayList<String> toReturn = new ArrayList<>();
            File folder = new File(home+holder+"/");
            if(!folder.isDirectory()) return null;
            File[] fileList = folder.listFiles();
            for(int i=0;i<fileList.length;i++){
                String fileName = fileList[i].getName();
                if(fileName.contains(".")){
                    int locationofDot = fileName.indexOf(".");
                    fileName = fileName.substring(0,locationofDot);
                }
                toReturn.add(fileName);
            }
            return toReturn;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static int loadIDList(String type){
        try {
            File idFile = new File(home + "IDS.xml");
            if (!idFile.exists()) {
                return 0;
            }
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(idFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName(type);
            for(int i=0;i<nList.getLength();i++){
                Node nd = nList.item(i);
                if(nd.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) nd;
                    return Integer.parseInt(element.getAttribute("ID"));
                }
            }
            return 0;
        } catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public static void saveIDList(String type, int id){
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element rootElement = doc.createElement("IDS");
            doc.appendChild(rootElement);

            Element idElement = doc.createElement(type);
            rootElement.appendChild(idElement);
            idElement.setAttribute("ID", id+"");

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(home + "IDS.xml");

            trans.transform(source,result);

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Object[][]> loadFile(String type, String holder) {
        try {
            ArrayList<Object[][]> objects = new ArrayList<>();
            File loadFile = new File(home+holder+".xml");
            if(!loadFile.exists()){
                return null;
            }
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(loadFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName(type);
            for(int i=0;i<nodeList.getLength();i++){
                Object[][] singleType;
                Node tempNode = nodeList.item(i);
                if(tempNode.getNodeType()!=Node.ELEMENT_NODE){
                    continue;
                }
                NamedNodeMap namedNodeMap = tempNode.getAttributes();
                Object[] idObject = {namedNodeMap.item(0).getNodeName(),namedNodeMap.item(0).getTextContent()};

                if(!tempNode.hasChildNodes()){
                    continue;
                }

                NodeList objectData = tempNode.getChildNodes();
                singleType = new Object[objectData.getLength()+1][2];
                singleType[0][0] = idObject[0];
                singleType[0][1] = idObject[1];

                for(int j=0;j<objectData.getLength();j++){
                    if(objectData.item(j).getNodeType()!=Node.ELEMENT_NODE){
                        continue;
                    }
                    Element tempElement = (Element)objectData.item(j);
                    singleType[j+1][0] = tempElement.getTagName();
                    singleType[j+1][1] = tempElement.getTextContent();
                }
                objects.add(singleType);
            }
            return objects;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void saveFile(String type, String holder, ArrayList<Object[][]> data){
        try{
            File savefile = new File(home+holder+".xml");
            if(!savefile.exists()){
                savefile.getParentFile().mkdirs();
            }
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element rootElement = doc.createElement(holder);

            for(int j=0;j<data.size();j++) {
                Object[][] container = data.get(j);
                Element object = doc.createElement(type);
                object.setAttribute(container[0][0].toString(), container[0][1].toString());
                rootElement.appendChild(object);

                for (int i = 1; i < container.length; i++) {
                    Element ele = doc.createElement(container[i][0].toString());
                    ele.appendChild(doc.createTextNode(container[i][1].toString()));
                    object.appendChild(ele);
                }
            }

            doc.appendChild(rootElement);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult result = new StreamResult(savefile);

            trans.transform(domSource,result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void saveSingle(String type, String holder, ArrayList<Object[]> data){
        try{
            File saveFile = new File(home+holder+"/"+data.get(0)[1]+".xml");
            if(!saveFile.exists()){
                saveFile.getParentFile().mkdirs();
            }
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element rootElement = doc.createElement(holder);
            Element typeObject = doc.createElement(type);
            typeObject.setAttribute(data.get(0)[0].toString(),data.get(0)[1].toString());

            for(int i=1;i<data.size();i++){
                Object[] tempObj = data.get(i);
                Element name = doc.createElement(tempObj[0].toString());
                name.appendChild(doc.createTextNode(tempObj[1].toString()));
                typeObject.appendChild(name);
            }

            rootElement.appendChild(typeObject);
            doc.appendChild(rootElement);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult output = new StreamResult(saveFile);

            trans.transform(source,output);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public static ArrayList<Object[]> loadSingle(String type, String holder, int id){
        try{
            File loadFile = new File(home+holder+"/"+id+".xml");
            if(!loadFile.exists()){
                //TODO
                return null;
            }
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(loadFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName(type);

            ArrayList<Object[]> toReturn = new ArrayList<>();
            for(int i=0;i<nList.getLength();i++){
                Node tempNode = nList.item(i);
                if(tempNode.getNodeType()!=Node.ELEMENT_NODE){
                    continue;
                }
                NamedNodeMap namedNodeMap = tempNode.getAttributes();
                Object[] idObject = {namedNodeMap.item(0).getNodeName(),namedNodeMap.item(0).getTextContent()};
                toReturn.add(idObject);
                if(!tempNode.hasChildNodes()){
                    continue;
                }
                NodeList objectData = tempNode.getChildNodes();
                for(int j=0;j<objectData.getLength();j++){
                    if(objectData.item(j).getNodeType()!=Node.ELEMENT_NODE){
                        continue;
                    }
                    Element tempElement = (Element)objectData.item(j);
                    Object[] tempObj = {tempElement.getTagName(),tempElement.getTextContent()};
                    toReturn.add(tempObj);
                }
            }
            return toReturn;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
