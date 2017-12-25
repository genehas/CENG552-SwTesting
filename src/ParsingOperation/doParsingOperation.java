/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ParsingOperation;

import Entities.MAPEntities;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import Driver.Driver;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class doParsingOperation{ 

    private static String _ID = "";
    private static String _PageLink ="";
    private static String _PartialObject ="";
    private static String _ObjectID="";
    private static String _ObjectType="";
    private static String _SaveButton="";
    private static File fXmlFile;
    private static DocumentBuilder dBuilder;
    private static Document doc;
    private static List<String> TestEntities;
    private static String _entities ="";    
    private static Integer EntitiesFlag = 0;
    
    public boolean readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            if(TestEntities != null){
                TestEntities.clear();
            }else{
                TestEntities = new ArrayList<String>();
            }
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                TestEntities.add(line);
                line = br.readLine();
            }
        }
        catch(FileNotFoundException e){
            return false;
        }finally {
            br.close();
        }
        if(CheckRequiredEntities(TestEntities)){
            return true;
        }else{
            System.exit(-1);
            return false;
        }
    }
    
    public boolean CheckRequiredEntities (List<String> List){
        if(List != null){
            try {
                for (int i = 0; i < List.size(); i++) {
                    if(Pattern.compile("WEBLINK").matcher(List.get(i).toString()).find()){
                        Entities.DUTEntities._WebLink = GetEntities(List.get(i).toString());
                    }
                    if(Pattern.compile("DUT:Name").matcher(List.get(i).toString()).find()){
                        Entities.DUTEntities._DUTName = GetEntities(List.get(i).toString());
                    }
                    if(Pattern.compile("DUT:IP").matcher(List.get(i).toString()).find()){
                        Entities.DUTEntities._DUTIP = GetEntities(List.get(i).toString());
                    }
                    if(Pattern.compile("DUT:UserName").matcher(List.get(i).toString()).find()){
                        Entities.DUTEntities._UIUsername = GetEntities(List.get(i).toString());
                    }
                    if (Pattern.compile("DUT:Password").matcher(List.get(i).toString()).find()){
                        Entities.DUTEntities._UIPassword = GetEntities(List.get(i).toString());
                    }
                }
            } catch (Exception e) {
                System.out.println("ERROR : check arguemnts error. Error code is : " + e.getMessage());
            }
            if (!Entities.DUTEntities._DUTIP.equals("")||!Entities.DUTEntities._WebLink.equals("")){
                return true;
            }
        }
        return false;
    }
    
    public static String getValue(String Row){
        try {
            _entities ="";
            EntitiesFlag = 0;
               if(!Row.equals("null")  || !Pattern.compile("#").matcher(Row).find()){
                 for (int j = 0; j < Row.length(); j++) {
                   if(EntitiesFlag == 1){
                      _entities  += String.valueOf(Row.charAt(j));
                   }
                   if(String.valueOf(Row.charAt(j)).equals(":")){
                      EntitiesFlag =1;
                   }
                 }
                if(!_entities.equals(""))
                {
                    return _entities;
                }               
              }
        } catch (Exception e) {
        }
        return "";
    }
    
    public static void GetMultibleValue(String Row){
            try {
            _entities ="";
            EntitiesFlag = 1;
            Driver.MultiValueList.clear();
            System.out.println("INFO : MultiValue" + Row);
            if(!Row.equals("null")  || !Pattern.compile("#").matcher(Row).find()){
                for (int j = 0; j < Row.length(); j++) {
                    EntitiesFlag = 1;
                    if(String.valueOf(Row.charAt(j)).equals(",")){
                        EntitiesFlag = 0;
                        System.out.print("INFO : Adding new ID");
                        System.out.println(_entities);
                        Driver.MultiValueList.add(_entities);
                        System.out.println("INFO : Value Size is : " +Driver.MultiValueList.size());
                        _entities="";
                    }
                    if(EntitiesFlag == 1){
                        _entities  += String.valueOf(Row.charAt(j));
                    }
                }
                Driver.MultiValueList.add(_entities);
            }
        } catch (Exception e) {
                System.out.println("ERROR : Get Multible ID Error. Error code is : " + e.getMessage());
        }
    }
    
    public static void GetMultibleID(String Row){
            try {
            _entities ="";
            EntitiesFlag = 0;
            Driver.MultiIDList.clear();
            System.out.println("INFO : MultiID" + Row);
            if(!Row.equals("null")  || !Pattern.compile("#").matcher(Row).find()){
                for (int j = 0; j < Row.length(); j++) {
                    EntitiesFlag = 1;
                    if(String.valueOf(Row.charAt(j)).equals(",")){
                        EntitiesFlag = 0;
                        System.out.print("INFO : Adding new ID");
                        System.out.println(_entities);
                        Driver.MultiIDList.add(_entities);
                        System.out.println("INFO : ID Size is : " +Driver.MultiIDList.size());
                        _entities="";
                    }
                    if(EntitiesFlag == 1){
                        _entities  += String.valueOf(Row.charAt(j));
                    }
                }
            }
        } catch (Exception e) {
                System.out.println("ERROR : Get Multible ID Error. Error code is : " + e.getMessage());
        }
            Driver.MultiIDList.add(_entities);
    }
    
    public static String GetEntities(String Row){
        try {
            _entities ="";
            EntitiesFlag = 0;
               if(!Row.equals("null")  || !Pattern.compile("#").matcher(Row).find()){
                 for (int j = 0; j < Row.length(); j++) {
                   if(EntitiesFlag == 1){
                      _entities  += String.valueOf(Row.charAt(j));
                   }
                   if(String.valueOf(Row.charAt(j)).equals("=")){
                      EntitiesFlag =1;
                   }
                 }
                if(!_entities.equals("")){
                    return _entities;
                }               
              }
        } catch (Exception e) {
        }
        return "";
    }
    
    public void readXml (String FilePath) throws InterruptedException {
        if(!FilePath.equals("")){
            try {
                fXmlFile = new File(FilePath);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                dBuilder = dbFactory.newDocumentBuilder();
                doc = dBuilder.parse(fXmlFile);
                doc.getDocumentElement().normalize();
                System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
                NodeList nList = doc.getElementsByTagName("Uniq");
                System.out.println("----------------------------");
                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    System.out.println("\nCurrent Element :" + nNode.getNodeName());
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        _ID = eElement.getAttribute("id");
                        _PageLink = eElement.getElementsByTagName("PageLink").item(0).getTextContent();
                        _PartialObject = eElement.getElementsByTagName("PartialObject").item(0).getTextContent();
                        _ObjectID = eElement.getElementsByTagName("PageObject").item(0).getTextContent();
                        _ObjectType = eElement.getElementsByTagName("ObjectType").item(0).getTextContent();
                        _SaveButton = eElement.getElementsByTagName("SaveButton").item(0).getTextContent();
                    }
                    Driver.MapList.add(new MAPEntities(_ID,_PageLink,_PartialObject,_ObjectID, _ObjectType,_SaveButton));
                }
            } catch (Exception e) {
                Driver.LogInstance.add("ERROR : XML Parser error. Error code is : " + e.getMessage());
            }
        }else {
             Driver.LogInstance.add("ERROR : MAP Path is empty.");
             System.exit(-1);
        }
}
    
    public void PrintAllMapNode () throws InterruptedException{
        for (int i = 0; i < Driver.MapList.size(); i++) {
            Thread.sleep(10);
            System.err.println("####################################################");
            Thread.sleep(10);
            System.out.println("ID : " + Driver.MapList.get(i).UniqID);
            Thread.sleep(10);
            System.out.println("URL : " + Driver.MapList.get(i).PageLink);
            Thread.sleep(10);
            System.out.println("Partial Path : " + Driver.MapList.get(i).PartialObject);
            Thread.sleep(10);
            System.out.println("Page Object : " + Driver.MapList.get(i).ObjectID);
            Thread.sleep(10);
            System.out.println("Object Type : " + Driver.MapList.get(i).ObjectType);
            Thread.sleep(10);
            System.err.println("####################################################");
        }
    }
}

