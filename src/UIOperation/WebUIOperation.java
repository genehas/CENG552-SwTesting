/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UIOperation;

import Entities.MAPEntities;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class WebUIOperation extends GenericUIOperation{
    private static WebElement _UIElements;
    private static String _Value;
    private static String _returnValue;
    
    public boolean getAndVerifyMultiObject ( MAPEntities Object, String ExpectedValue,int ObjectCount) {
        if(Object != null && !ExpectedValue.equals("")){
            try {
                Driver.Driver.LogInstance.add("INFO : doing multi object get and verify operation. Expected value is : "
                        + ExpectedValue + " Using object ID is :" 
                        + Object.ObjectID);
                if(ObjectCount == 0){
                    _UIElements =  goToObject(Object);
                }else{
                    Driver.Driver.LogInstance.add("INFO : No need to goto url function because already been right page.");
                    _UIElements = getCurrentObject(Object);
                }
                if (_UIElements != null){
                    _Value = getValue(Object);
                    if (!_Value.equals("")){
                        Driver.Driver.LogInstance.add("INFO : Current value is : " + _Value + " Expected value is : " + ExpectedValue);
                        if (_Value.toUpperCase().equals(ExpectedValue.toUpperCase())){
                            Driver.Driver.LogInstance.add("INFO : Expected value has been found.");
                            return true;
                        }else {
                            Driver.Driver.LogInstance.add("WRONG : Expected value not found. Current value is : " + _Value);
                        }
                    }
                }
            } catch (Exception e) {
                Driver.Driver.LogInstance.add("ERROR : Get and Verify operation has been failed. Error code is : " + e.getMessage());
            }
        }
        return false;
    }
    
    public boolean getAndVerifyObjectValue (MAPEntities Object, String ExpectedValue){
        if(Object != null && !ExpectedValue.equals("")){
            try {
                Driver.Driver.LogInstance.add("INFO : doing get and verify operation. Expected value is : " 
                        + ExpectedValue + " Using object ID is :" 
                        + Object.ObjectID);
                _UIElements = goToObject(Object);
                Driver.Driver.LogInstance.add("INFO : Element Value :" +_UIElements.getAttribute("value"));
                if (_UIElements != null){
                     _Value = getValue(Object);
                    if (!_Value.equals("")){  
                       Driver.Driver.LogInstance.add("INFO : Current value is : " + _Value + " Expected value is : " + ExpectedValue);
                        if (_Value.toUpperCase().equals(ExpectedValue.toUpperCase())){
                            Driver.Driver.LogInstance.add("INFO : Expected value has been found.");
                            return true;
                        }else {
                            Driver.Driver.LogInstance.add("WRONG : Expected value not found. Current value is : " + _Value);
                        }
                    }
                }
            } catch (Exception e) {
                Driver.Driver.LogInstance.add("ERROR : Get and Verify operation has been failed. Error code is : " + e.getMessage());
            }
        }
        return false;
    }
    
    public boolean getObjectValue (MAPEntities Object){
        if (Object != null){
            try {
                  Driver.Driver.LogInstance.add("INFO : doing get operation. Using object ID is :" 
                          + Object.ObjectID);
                  _UIElements = goToObject(Object);
                  if(_UIElements != null ){
                      Driver.Driver.LogInstance.add("INFO : Will be get Object Value.");
                      _Value = getValue(Object);
                      if (!_Value.equals("")){
                          Driver.Driver.LogInstance.add("INFO : Object value is : " + _Value);
                          return true;
                      }
                  }
            } catch (Exception e) {
                Driver.Driver.LogInstance.add("ERROR : Detected same error.While doing value parsing operation. Error code is : " 
                        + e.getMessage());
                return false;
            }
        }
        Driver.Driver.LogInstance.add("WRONG : Object value is empty. Return null value");
        return false;
    }
            
    public boolean setObject(MAPEntities Object, String Value) {
        try {
            if(Object != null && !Value.equals("")){
                Driver.Driver.LogInstance.add("INFO : Will be do set operation on WEB UI. ");
                _UIElements =  goToObject(Object);
               if (_UIElements != null){
                   Driver.Driver.LogInstance.add("INFO : will be clear current value on WEB UI");
                   if(setObjectValue(Object, Value)){
                       Driver.Driver.LogInstance.add("INFO : Value was seted will be click save button.");
                       UIInstance.findElement(By.id(Object.SaveButtonID)).click();
                       Driver.Driver.LogInstance.add("INFO : Clicked to save button.");
                       Thread.sleep(1000);
                       try {
                           UIInstance.switchTo().alert().accept();
                       } catch (Exception e) {
                           Driver.Driver.LogInstance.add("WRONG : Doesnt seems any alert message on the WEB UI.");
                       }                       
                       Thread.sleep(5000);
                   }
               }else {
                   Driver.Driver.LogInstance.add("ERROR : UIElement object is null so returning false.");
                   System.err.println("FAIL : Test Result is fail.");
                   return false;
               }
            }else { 
                Driver.Driver.LogInstance.add("WRONG : Object / Set value is empty.");
                return false;
            }
        } catch (Exception e) {
            Driver.Driver.LogInstance.add("ERROR : set value operation has been failed. Error code is : " + e.getMessage());
            return false;
        }
        return true;
    }   
    
    public boolean SaveObject(MAPEntities Object) throws InterruptedException{
        UIInstance.findElement(By.id(Object.SaveButtonID)).click();
        Driver.Driver.LogInstance.add("INFO : Clicked to save button.");
        Thread.sleep(1000);
        try {
            UIInstance.switchTo().alert().accept();
        } catch (Exception e) {
            Driver.Driver.LogInstance.add("WRONG : Doenst seems any alert message on the WEB UI.");
        }
        Thread.sleep(12000);
        return false;
    }
   
    public boolean setAndVerifyObject (MAPEntities Object, String Value, String ExpectedValue){
        try {
            if(Object != null && !Value.equals("")){
                Driver.Driver.LogInstance.add("INFO : Will be do set operation on web uı. ");
                _UIElements =  goToObject(Object);
                if (_UIElements != null){
                    if(setObjectValue(Object, Value)){
                        Driver.Driver.LogInstance.add("INFO : Value was seted will be click save button.");
                        UIInstance.findElement(By.id(Object.SaveButtonID)).click();
                        Driver.Driver.LogInstance.add("INFO : Clicked to save button.");
                        Thread.sleep(5000);
                        if (getAndVerifyObjectValue(Object,ExpectedValue)){
                            return true;
                        }else{
                            return false;
                        }
                    }else {
                        Driver.Driver.LogInstance.add("ERROR : UIElement object is null so returning false.");
                        System.err.println("FAIL : Test Result is fail.");
                        return false;
                     }
                }
            }else {
                Driver.Driver.LogInstance.add("WRONG : Object / Set value is empty.");
            }
        } catch (Exception e) {
            Driver.Driver.LogInstance.add("ERROR : set value operation has been failed. Error code is : " + e.getMessage());
        }
        return false;
    }
    
    public boolean setMultiObject ( MAPEntities Object, String Value,int ObjectCount) {
        try {
            if(Object != null && !Value.equals("")){
                Driver.Driver.LogInstance.add("INFO : UI Will be do multiSet operation on web uı. Object Type :" + Object.ObjectType + " Object ID : " + Object.ObjectID +""
                        + "Object Value :" + Object.UniqID + " Object Page Link :" + Object.PageLink);                
                if(ObjectCount == 0){
                    _UIElements =  goToObject(Object);
                }else{
                    Driver.Driver.LogInstance.add("INFO : No need to goto url function because already been right page.");
                    _UIElements = getCurrentObject(Object);
                }
                if (_UIElements != null){
                   Driver.Driver.LogInstance.add("INFO : will be clear current value on WEB UI");
                   if(setObjectValue(Object, Value)){
                       Driver.Driver.LogInstance.add("INFO : Value has been seted");
                   }else{
                       return false;
                   }
               }else {
                   Driver.Driver.LogInstance.add("ERROR : UIElement object is null so returning false.");
                   System.err.println("FAIL : Test Result is fail.");
                   return false;
               }
            }else {
                Driver.Driver.LogInstance.add("WRONG : Object / Set value is empty.");
                return false;
            }
        } catch (Exception e) {
            Driver.Driver.LogInstance.add("ERROR : set value operation has been failed. Error code is : " + e.getMessage());
            return false;
        }
        return true;
    }
    
    public String getValue (MAPEntities Object){
        try {
            Thread.sleep(1000);
            switch (Object.ObjectType){
                case "txtbox":
                     Driver.LogsDriver.LogInstance.add("INFO : returning value is: " +_UIElements.getAttribute("value"));
                    return _UIElements.getAttribute("value").toString();
                case "combobox":
                    Driver.LogsDriver.LogInstance.add("INFO : returning value is: " +_UIElements.getText());
                    Driver.LogsDriver.LogInstance.add("INFO : returning value is: " +_UIElements.getAttribute("value"));
                    switch (_UIElements.getAttribute("value")){
                        case "wpa_both":
                            _returnValue = "WPA2";
                            return _returnValue;
                        case "off":
                            _returnValue = "No Encryption";
                        default : 
                            return _UIElements.getAttribute("value").toString();
                    }                    
                case "checkbox":
                    if(_UIElements.isSelected()){
                        Driver.Driver.LogInstance.add("INFO : Returning value is : ENABLE");
                        return "ENABLE";
                    }else {
                        Driver.Driver.LogInstance.add("INFO : Returning value is : DISABLE");
                        return "DISABLE";
                    }
                case "label":
                    Driver.LogsDriver.LogInstance.add("INFO : Will be read labels. Returning value is : " + _UIElements.getText());
                    return _UIElements.getText();
            }
        } catch (Exception e) {
            Driver.Driver.LogInstance.add("ERROR : set object error. Error code is : " + e.getMessage());
        }
        return "";
    }
    
    private boolean setObjectValue (MAPEntities Object,String Value){
        try {
            switch (Object.ObjectType){
                case "txtbox":
                    Thread.sleep(100);
                    _UIElements.clear();
                    Thread.sleep(500);
                    Driver.Driver.LogInstance.add("INFO : Will be set value. Using object id is : " + Object.ObjectID + " Value is : " + Value);
                    _UIElements.sendKeys(Value);
                    Thread.sleep(500);
                    break;
                case "combobox":
                    Driver.LogsDriver.LogInstance.add("INFO : returning value is: " +_UIElements.getText());
                     Select dropDown = new Select(_UIElements); 
                     if(Value.equals("No")){
                         Value="No Encryption";
                     }
                            String selected = dropDown.getFirstSelectedOption().getText();
                            if(selected.equals(Value)){
                                //already selected; 
                                //do stuff
                            }
                            else
                            {
                            List<WebElement> Options = dropDown.getOptions();
                            for(WebElement option:Options){
                                Thread.sleep(100);
                                
                                if(option.getText().equals(Value)) {
                                  option.click(); //select option here;  
                                  Thread.sleep(500);
                                }               
                            }
                            }
                    
                    Thread.sleep(100);
                    break;
                case "checkbox":
                    System.out.println(_UIElements.getAttribute("value").toString());
                    if(Value.toUpperCase().equals("ENABLE")){
                        Thread.sleep(500);
                        if(_UIElements.isSelected()){
                            Driver.Driver.LogInstance.add("INFO : Interface already enable so do not need anything");
                        }else {
                            Driver.Driver.LogInstance.add("INFO : Interface is disable, set to enable.");
                            _UIElements.click();
                        }
                    }else if (Value.toUpperCase().equals("DISABLE")){
                        Thread.sleep(600);
                        if(_UIElements.isSelected()){
                            Driver.Driver.LogInstance.add("INFO : Interface is enable so will be set disable.");
                            _UIElements.click();
                        }else {
                            Driver.Driver.LogInstance.add("INFO : Interface already disable.");
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            Driver.Driver.LogInstance.add("ERROR : set object error. Error code is : " + e.getMessage());
            return false;
        }
        return true;
    }
               
    public MAPEntities getObjectInstance (String UniqID){
        try {
            Driver.Driver.LogInstance.add("INFO : Will be get web uı object. Using UniqID is : " + UniqID);
            if (!UniqID.equals("")){
                Driver.Driver.LogInstance.add("INFO : WEBUI Object List size is : " + Driver.Driver.MapList.size());
                for (int i = 0; i < Driver.Driver.MapList.size(); i++) {
                    if(Driver.Driver.MapList.get(i).UniqID.equals(UniqID)){
                        Driver.Driver.LogInstance.add("INFO : Found expected object item.");
                        return Driver.Driver.MapList.get(i);
                    }
                }
            }
        } catch (Exception e) {
            Driver.Driver.LogInstance.add("ERROR : Detected same error while getting web uı object instance. Error code is : "+ e.getMessage());
        }
        Driver.Driver.LogInstance.add("WRONG : Expected web uı object not found so returning null. <Using Object id is : "+ UniqID +">");
        return null;
    }
    
    /*public boolean BrowserOperation(MAPEntities Object, String Filename) {
    	 try {
    		 System.out.println("File name is: "+Filename); 
             if(Object != null){
                 Driver.Driver.LogInstance.add("INFO : Will be do verify item operation on WEB UI. ");
                 _UIElements =  goToObject(Object);
                if (_UIElements != null){
                    Driver.Driver.LogInstance.add("INFO : object founded test result is pass");
                   
                }else {
                    Driver.Driver.LogInstance.add("ERROR : UIElement object is null so returning false.");
                    System.err.println("FAIL : Test Result is fail.");
                    return false;
                }
             }else { 
                 Driver.Driver.LogInstance.add("WRONG : Object value is empty.");
                 return false;
             }
         } catch (Exception e) {
             Driver.Driver.LogInstance.add("ERROR : set value operation has been failed. Error code is : " + e.getMessage());
             return false;
         }
         return true;
    }*/
}
