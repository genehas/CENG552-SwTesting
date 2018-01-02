/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TestOperation;

import Driver.Driver;
import Entities.MAPEntities;
import UIOperation.BrowserOperation;
import UIOperation.WebUIOperation;

public class doTestCase extends readArguments{
    public static UIOperation.WebUIOperation UIObject;
    private static MAPEntities MapObject;
    private static int PassCount = 0;
    private static int FailCount = 0;
    
    public boolean doTestCase (){
        try {
            Driver.LogInstance.add(Driver._Function);
            if(Driver._Function.equals("MULTISET")){
                GetMultibleValue(Driver._FunctionalValue);
                GetMultibleID(Driver._UniqID);
                return doMultibleTest();
            }else if(Driver._Function.equals("MULTIGET")){
                GetMultibleValue(Driver._FunctionalValue);
                GetMultibleID(Driver._UniqID);
                return doMultibleGetTest();
            }
            UIObject = new WebUIOperation();
            if(Driver._Function.equals("BROWSEROP")) {
            	 Driver.LogInstance.add("INFO : required object id is exist.");
	                    Driver.LogInstance.add("INFO : Will be create UI Instance.");
	                    if(doTestCase(Driver._Function,0)){
	                        Driver.LogInstance.add("PASS : Test Result is success");
	                        System.out.println("PASS : Test Result is success");
	                        UIObject.closeWEBUI();
	                        return true;
	                    }else {
	                        Driver.LogInstance.add("FAIL : Test Result is failure.");
	                        System.out.println("FAIL : Test Result is failure.");
	                        UIObject.closeWEBUI();
	                    }
	             
            }
            else {
		            if(UIObject.checkObjectID(Driver._UniqID)){
		                Driver.LogInstance.add("INFO : required object id is exist.");
		                MapObject = UIObject.getMapObject(Driver._UniqID);
		                if(MapObject != null){
		                    Driver.LogInstance.add("INFO : Will be create UI Instance.");
		                    if(doTestCase(Driver._Function,0)){
		                        Driver.LogInstance.add("PASS : Test Result is success");
		                        System.out.println("PASS : Test Result is success");
		                        UIObject.closeWEBUI();
		                        return true;
		                    }else {
		                        Driver.LogInstance.add("FAIL : Test Result is failure.");
		                        System.out.println("FAIL : Test Result is failure.");
		                        UIObject.closeWEBUI();
		                    }
		                }else {
		                    Driver.LogInstance.add("WRONG : Map Object is null.");
		                    UIObject.closeWEBUI();
		                }
		            }
            }
        }
         catch (Exception e) {
            Driver.LogInstance.add("ERROR : do Test case error. Error code is : " + e.getMessage());
            UIObject.closeWEBUI();
            return false;
        }
        UIObject.closeWEBUI();
        return false;
    }
    
    private boolean doMultibleGetTest(){
        
        UIObject = new WebUIOperation();
        Driver.LogInstance.add("INFO : Come to multiset function");
        Driver.LogInstance.add("INFO : ID List Count is : " + Driver.MultiIDList.size());
        Driver.LogInstance.add("INFO : Value List Count is : " + Driver.MultiValueList.size());
        
        if(Driver.MultiIDList.size() == Driver.MultiValueList.size()){
            for (int i = 0; i < Driver.MultiIDList.size(); i++) {
                if(UIObject.checkObjectID(Driver.MultiIDList.get(i))){
                    Driver.LogInstance.add("INFO : required object id is exist.");
                    MapObject = UIObject.getMapObject(Driver.MultiIDList.get(i));
                    if(MapObject != null){
                        Driver._FunctionalValue = Driver.MultiValueList.get(i);
                        if(doTestCase("MULTIGET",i)){
                            PassCount +=1;
                        }else {
                            FailCount+=1;            
                        }
                    }else {
                        Driver.LogInstance.add("WRONG : Map Object is null.");
                        UIObject.closeWEBUI();
                        return false;
                    }
                }
            }
            if(PassCount == Driver.MultiIDList.size()){
                Driver.LogInstance.add("INFO : PassCount : " + PassCount);
                Driver.LogInstance.add("PASS : Test Result is success");
                System.out.println("PASS : Test Result is success");
            }else{
                Driver.LogInstance.add("INFO : FailCount : " + FailCount);
                Driver.LogInstance.add("FAIL : Test Result is failure.");
                System.out.println("FAIL : Test Result is failure.");
            }
        }else{
            Driver.LogInstance.add("WRONG : Multi ID and Multi Value count is not equals.Check your arguments.");
            return false;
        }
        UIObject.closeWEBUI();
        return true;
    }
    
    
    private boolean doMultibleTest(){
        UIObject = new WebUIOperation();
        Driver.LogInstance.add("INFO : Come to multiset function");
        Driver.LogInstance.add("INFO : ID List Count is : " + Driver.MultiIDList.size());
        Driver.LogInstance.add("INFO : Value List Count is : " + Driver.MultiValueList.size());
        if(Driver.MultiIDList.size() == Driver.MultiValueList.size()){
            for (int i = 0; i < Driver.MultiIDList.size(); i++) {
                if(UIObject.checkObjectID(Driver.MultiIDList.get(i))){
                    Driver.LogInstance.add("INFO : required object id is exist.");
                    MapObject = UIObject.getMapObject(Driver.MultiIDList.get(i));
                    if(MapObject != null){
                        Driver._FunctionalValue = Driver.MultiValueList.get(i);
                        if(doTestCase("MULTISET",i)){
                            PassCount +=1;
                        }else {
                            FailCount+=1;            
                        }
                    }else {
                        Driver.LogInstance.add("WRONG : Map Object is null.");
                        UIObject.closeWEBUI();
                        return false;
                    }
                }
            }
            if(PassCount == Driver.MultiIDList.size()){
                Driver.LogInstance.add("INFO : PassCount : " + PassCount);
                Driver.LogInstance.add("PASS : Test Result is success");
                System.out.println("PASS : Test Result is success");
            }else{
                Driver.LogInstance.add("INFO : FailCount : " + FailCount);
                Driver.LogInstance.add("FAIL : Test Result is failure.");
                System.out.println("FAIL : Test Result is failure.");
            }
        }else{
            Driver.LogInstance.add("WRONG : Multi ID and Multi Value count is not equals.Check your arguments.");
            return false;
        }
        MapObject = UIObject.getMapObject(Driver.MultiIDList.get(0));
        doTestCase("SAVE",1);
        UIObject.closeWEBUI();
        return true;
    }
    
    private boolean doTestCase(String Function,int EntryCount){
        try {
            if (!Function.equals("")){
                Driver.LogInstance.add("INFO : Come to doTestCase : |" + Function + "|");
                switch (Function){
                   case "GET":
                        Driver.LogInstance.add("INFO : Do get operation.");
                        if(UIObject.getObjectValue(MapObject)){
                            Driver.LogInstance.add("INFO : Test is complate");
                            return true;
                        }
                       break;
                   case "SET":
                       Driver.LogInstance.add("INFO : doing Set Operation.");
                       Driver.LogInstance.add("INFO : Functional Value is : " + Driver._FunctionalValue);
                       if(!Driver._FunctionalValue.equals("")){
                           if(UIObject.setObject(MapObject, Driver._FunctionalValue)){
                               Driver.LogInstance.add("INFO : Test is complate.");
                               return true;
                           }
                       }
                   break;
                   case "GETANDVERIFY":
                       Driver.LogInstance.add("INFO : will be do get and verify operation");
                       Driver.LogInstance.add("INFO : Functional Value is : " + Driver._FunctionalValue);
                       if(!Driver._FunctionalValue.equals("")){
                           if(UIObject.getAndVerifyObjectValue(MapObject, Driver._FunctionalValue)){
                               return true;
                           }
                       }
                   break;
                   case "SETANDVERIFY":
                       Driver.LogInstance.add("INFO : Will be do set and verify operation.");
                       Driver.LogInstance.add("INFO : Functional Value is : " + Driver._FunctionalValue);
                       if(!Driver._FunctionalValue.equals("")){
                           if(UIObject.setAndVerifyObject(MapObject, Driver._FunctionalValue, Driver._FunctionalValue)){
                               Driver.LogInstance.add("INFO : test is complate.");
                               return true;
                           }
                       }
                       break;
                   case "MULTISET":
                       Driver.LogInstance.add("INFO : Will be do multiSet operation.");
                       Driver.LogInstance.add("INFO : Functional Value is : " + Driver._FunctionalValue);
                       if(!Driver._FunctionalValue.equals("")){
                           if(UIObject.setMultiObject(MapObject, Driver._FunctionalValue,EntryCount)){
                               Driver.LogInstance.add("INFO : test is complate.");
                               return true;
                           }
                       }                       
                       break;
                   case "MULTIGET":
                       Driver.LogInstance.add("INFO : Will be do multiGet operation.");
                       Driver.LogInstance.add("INFO : Function Value is : " + Driver._FunctionalValue);
                       if(!Driver._FunctionalValue.equals("")){
                           if(UIObject.getAndVerifyObjectValue(MapObject, Driver._FunctionalValue)){
                               return true;
                           }
                       }
                       break;
                   case "SAVE":
                       Driver.LogInstance.add("INFO : Save operation.");
                       if(UIObject.SaveObject(MapObject)){
                           Driver.LogInstance.add("INFO : Save Operation");
                           return true;
                       }                       
                       break;
                   case "BROWSEROP":
                       Driver.LogInstance.add("INFO : doing Browser Operation.");
                       System.out.println("Driver : "+Driver._FunctionalValue);
                       
                       if(BrowserOperation.BrowserOperations(Driver._FunctionalValue)){
                             Driver.LogInstance.add("INFO : Test is complete.");
                             return true;
                           
                       }
                   break;
                   default : 
                       Driver.LogInstance.add("ERROR : Requested functionality is not define. Functionality Name is : " + Function);
                       System.out.println("ERROR : Requested functionality is not define. Functionality Name is : " + Function);
                       break;
                }
            }
        } catch (Exception e) {
            Driver.LogInstance.add("ERROR : Functional test case operation error. Error code is : " + e.getMessage());
        }
       return false;
    }
}
