package UIOperation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Entities.BrowserEntities;
import Entities.UIOperationEntities;

import Entities.MAPEntities;

public class BrowserOperation {
	
	public static List<String> TestStepLine;
	public static List<UIOperationEntities> TestStepList;
	
	public static UIOperation.WebUIOperation UIObject;
    private static MAPEntities MapObject;
    private static int failcount=0;
	
    public static boolean BrowserOperations(String FileName)
    {
    	System.out.println("File name : " + FileName);
    	//tempFilename=FileName.split(":")[1].trim();
    	//System.out.println("File name : " + tempFilename);
    	
    	if(parseTestStep(FileName)!=null) {
    		 if(CreateOperationList(TestStepLine)){
             	Driver.Driver.LogInstance.add("INFO : Operation List prepared succesfully.");
             	if(DoBrowserOperations()==true) {
        			Driver.Driver.LogInstance.add("INFO : Browser Operations done successfully.");
            		return true;
        		}
        		else {
        			Driver.Driver.LogInstance.add("ERROR : Browser Operations has an error.");
            		return false;
        		}
             }else{
                 System.exit(-1);
                 Driver.Driver.LogInstance.add("ERROR : Operation list creation error.");
                 return false;
             }
    	}
    	
    	else {
    		
    		Driver.Driver.LogInstance.add("ERROR : Test step parse operation could not done.");
    		return false;
    	} 
    	
    }
	public static boolean DoBrowserOperations (){
        if(TestStepList != null){
            try {
            	System.out.println("Test Step arr : "+TestStepList);
            	System.out.println("Test Step arr size : "+TestStepList.size());
            	for (int i = 0; i < TestStepList.size(); i++) {
                	System.out.println("Test Step is : "+TestStepList.get(i).Operation);
                	System.out.println("Test Step is : "+TestStepList.get(i).ObjectID);
                	System.out.println("Test Step i= : "+i+" : "+TestStepList.get(i).ObjectID);
                	 if(TestStepList.get(i).Operation.contains(BrowserEntities._Click)){
                    	if(ClickObject(GetMapObject(TestStepList.get(i).ObjectID),UIDriver.UIInstance)==true){
                    		System.out.println("Object ID : "+TestStepList.get(i).ObjectID);
                            Driver.Driver.LogInstance.add("PASS : Test Step is success");
                             System.out.println("PASS : Test Step is success");
                    	}
                    	else failcount=failcount+1;
                        
                    }
                    if(TestStepList.get(i).Operation.contains(BrowserEntities._VerifyObject)){
                    	if(IsExistObject(GetMapObject(TestStepList.get(i).ObjectID),UIDriver.UIInstance)==true){
                    		System.out.println("Object ID : "+TestStepList.get(i).ObjectID);
                            Driver.Driver.LogInstance.add("PASS : Test Step is success");
                             System.out.println("PASS : Test Step is success");
                    	}
                    	else failcount=failcount+1;
                    }
                    if(TestStepList.get(i).Operation.contains(BrowserEntities._VerifyParam)){
                    	if(VerifyParam(GetMapObject(TestStepList.get(i).ObjectID), TestStepList.get(i).Parameter, UIDriver.UIInstance)==true){
                    		System.out.println("Object ID : "+TestStepList.get(i).ObjectID);
                            Driver.Driver.LogInstance.add("PASS : Test Step is success");
                             System.out.println("PASS : Test Step is success");
                    	}
                    	else failcount=failcount+1;
                        
                    }
                    if(TestStepList.get(i).Operation.contains(BrowserEntities._SetParam)){
                    	System.out.println("PArameter : "+TestStepList.get(i).Parameter);
                    	if(SetParam(GetMapObject(TestStepList.get(i).ObjectID), TestStepList.get(i).Parameter.toString(), UIDriver.UIInstance)==true){
                    		System.out.println("Object ID : "+TestStepList.get(i).ObjectID);
                            Driver.Driver.LogInstance.add("PASS : Test Step is success");
                             System.out.println("PASS : Test Step is success");
                    	}
                    	else failcount=failcount+1;
                    }
                }
            }catch (Exception e) {
                System.out.println("ERROR : operation list error. Error code is : " + e.getMessage());
            }
        }
        if(failcount==0) return true;
        else {
        	
        	System.out.println("fail count > 0");
        	return false;
        
        }
    }
    private static List<String> parseTestStep(String TestSteps){
        try {
        	
            Thread.sleep(1000);
            BufferedReader br = new BufferedReader(new FileReader(TestSteps));
            try {
            	if(TestStepLine != null){
            		TestStepLine.clear();
                }else{
                	TestStepLine = new ArrayList<String>();
                }
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                while (line != null) {
                    sb.append(line);
                    sb.append("\n");
                    TestStepLine.add(line);
                    line = br.readLine();
                }
            }
            catch(FileNotFoundException e){
            	Driver.Driver.LogInstance.add("ERROR : file error. Error code is : " + e.getMessage());
            }finally {
                br.close();
                System.out.println("Test Step Line arr : "+TestStepLine);
                Driver.Driver.LogInstance.add("INFO : Test step parse operation done successfully.");
        		System.out.println("INFO : Test step parse operation done successfully.");
            }
            return TestStepLine;
        } catch (Exception e) {
            Driver.Driver.LogInstance.add("ERROR : set object error. Error code is : " + e.getMessage());
            return null;
        }
        
    }
	private static boolean CreateOperationList (List<String> List){
		if(TestStepList != null){
			TestStepList.clear();
			 System.out.println("Test Step Clear");
        }else{
        	TestStepList = new ArrayList<UIOperationEntities>();
        	 System.out.println("new test step arr create");
        }
		System.out.println("Step list size : "+ List.size());
		System.out.println("Step list : "+ List);
        if(List.size()>0){
            try {
                for (int i = 0; i < List.size(); i++) {
                	UIOperationEntities tmpElement= new UIOperationEntities(List.get(i).split(":")[0].toString(), List.get(i).split(":")[1].toString(), List.get(i).split(":")[2].toString());
                	TestStepList.add(tmpElement);
                	System.out.println("Test Steps Elements");
                	System.out.println("Operation "+i+" : "+TestStepList.get(i).Operation);               
                    System.out.println("Object ID "+i+" : "+TestStepList.get(i).ObjectID);
                    System.out.println("Parameter "+i+" : "+TestStepList.get(i).Parameter);
                    Driver.Driver.LogInstance.add("INFO : "+"Operation "+i+" : "+TestStepList.get(i).Operation);   
                    Driver.Driver.LogInstance.add("INFO : "+"Object ID "+i+" : "+TestStepList.get(i).ObjectID);
                    Driver.Driver.LogInstance.add("INFO : "+"Parameter "+i+" : "+TestStepList.get(i).Parameter);
                }
                return true;
                   
            } catch (Exception e) {
                System.out.println("ERROR : creating test step list error. Error code is : " + e.getMessage());
                return false;
            }
        }
        else return false;
    }
	 public static MAPEntities GetMapObject(String ID)
	    {
	     
	    	if(checkObjectID(ID)){
	        	System.out.println("INFO : required object id is exist.");
	            Driver.Driver.LogInstance.add("INFO : required object id is exist.");
	            MapObject = getMapObject(ID);
	            if(MapObject != null){
	                Driver.Driver.LogInstance.add("INFO : Will be create UI Instance.");
	                System.out.println("INFO : Will be create UI Instance.");
		            
	                
	     
	            }else {
	                Driver.Driver.LogInstance.add("WRONG : Map Object is null.");
	                System.out.println("WRONG : Map Object is null.");
	                
	            }
	        }
	        System.out.println("Map object is : "+MapObject.ObjectID);
			return MapObject;
	        
	    }
	 public static MAPEntities getMapObject (String UniqID){
	    	System.out.println("getMapObject");
	        if(!UniqID.equals("")){
	            if(Driver.Driver.MapList.size() > 0 ){
	                for (int i = 0; i < Driver.Driver.MapList.size(); i++) {
	                    if(Driver.Driver.MapList.get(i).UniqID.equals(UniqID)){
	                        Driver.Driver.LogInstance.add("INFO : Uniq ID has been found return objectList.");
	                        System.out.println("INFO : Uniq ID has been found return objectList.");
	                        return Driver.Driver.MapList.get(i);
	                    }
	                }
	            }
	        }
	        Driver.Driver.LogInstance.add("WRONG : UniqID not found in objectList. Return null");
	        return null;
	    }    
	 public static boolean checkObjectID (String UniqID){
    	 System.out.println("checkObjectID : " + UniqID);
        if(!UniqID.equals("")){
            Driver.Driver.LogInstance.add("INFO : will be check ObjectID. Comming ID is : " + UniqID);
            if(Driver.Driver.MapList.size() > 0 ){
                Driver.Driver.LogInstance.add("INFO : will be check uniqID in mapList");
                System.out.println("INFO : will be check uniqID in mapList");
                for (int i = 0; i < Driver.Driver.MapList.size(); i++) {
                    if(Driver.Driver.MapList.get(i).UniqID.equals(UniqID)){
                        Driver.Driver.LogInstance.add("INFO : Uniq ID has been found in objectList.");
                        return true;
                    }
                }
            }
        }
        Driver.Driver.LogInstance.add("WRONG : UniqID not found in objectList.");
        return false;
    }
	 public static boolean ClickObject(MAPEntities Object, WebDriver driver) throws InterruptedException{
		 try {
			System.out.println("Click Object : "+Object.ObjectID);
	    	Driver.Driver.LogInstance.add("Click Object : "+Object.ObjectID);
	    	WebElement UIElement = getElementByID(Object.ObjectID,driver);
	    	if(UIElement==null){
                Driver.Driver.LogInstance.add("INFO : Trying to find partial element by Xpath instead");
                UIElement = getElementByXpath(Object.ObjectID,driver);
	        }
	        System.out.println("UIELement: "+UIElement);
	        UIElement.click();
	        Driver.Driver.LogInstance.add("INFO : Clicked to object.");
	        Thread.sleep(3000);
	       /* try {
	            driver.switchTo().alert().accept();
	        } catch (Exception e) {
	            Driver.Driver.LogInstance.add("WRONG : Doesnt seems any alert message on the WEB UI.");
	        }*/
	        return true;
	 }catch (Exception e)  {
	         Driver.Driver.LogInstance.add("ERROR : Could not clicked to object.");
		 return false;}
	    }
	 public static boolean IsExistObject(MAPEntities Object, WebDriver driver) throws InterruptedException{
		 try {
			 Driver.Driver.LogInstance.add("TEST STEP : Clicked Object: "+Object.ObjectID);
			 System.out.println("TEST STEP : Clicked Object: "+Object.ObjectID);
		    
	    	try {
		            driver.switchTo().alert().accept();
		    } catch (Exception e) {
		            Driver.Driver.LogInstance.add("WRONG : Doesnt seems any alert message on the WEB UI.");
		    }
		    WebElement UIElement = driver.findElement(By.id(Object.ObjectID.toString()));
		    if(UIElement==null){
                Driver.Driver.LogInstance.add("INFO : Trying to find partial element by Xpath instead");
                UIElement = getElementByXpath(Object.ObjectID,driver);
	        }
	        if(UIElement.isEnabled()) {
	        Driver.Driver.LogInstance.add("INFO : Object is exist");
	        Thread.sleep(3000);
	        return true;}
	        else {return false;}
		 }catch (Exception e)  {
			 Driver.Driver.LogInstance.add("ERROR : Parameter could not cilicked. Exception : "+e);
			 return false;}
	    }
	 public static boolean VerifyParam(MAPEntities Object, String Param, WebDriver driver) throws InterruptedException{
		 try {
			Driver.Driver.LogInstance.add("TEST STEP : Verified Object: "+Object.ObjectID);
		    Driver.Driver.LogInstance.add("TEST STEP : Verified Parameter: "+Param);
		    System.out.println("TEST STEP : Verified Object: "+Object.ObjectID);
	    	 try {
		            driver.switchTo().alert().accept();
		        } catch (Exception e) {
		            Driver.Driver.LogInstance.add("WRONG : Doesnt seems any alert message on the WEB UI.");
		        }
	        WebElement UIElement = driver.findElement(By.id(Object.ObjectID));
	        if(UIElement==null){
                Driver.Driver.LogInstance.add("INFO : Trying to find partial element by Xpath instead");
                UIElement = getElementByXpath(Object.ObjectID,driver);
	        }
	        if(UIElement.getText().equals(Param)) 
	        {
	        	Thread.sleep(3000);
	        Driver.Driver.LogInstance.add(UIElement.getText()+" Parameters are equal "+Param);
	        return true;}
	        else {
	        	Driver.Driver.LogInstance.add("ERROR : Parameter could not controlled.");
	        	return false;
	        
	        }
	        
	 }catch (Exception e) {
		 Driver.Driver.LogInstance.add("ERROR : Parameter could not controlled. Exception : "+e);
		 return false;}
	 }
	 public static boolean SetParam(MAPEntities Object, String Param, WebDriver driver) throws InterruptedException{
		 try {
			System.out.println("TEST STEP : Setted Object: "+Object.ObjectID);
	    	Driver.Driver.LogInstance.add("TEST STEP : Setted Object: "+Object.ObjectID);
	    	Driver.Driver.LogInstance.add("TEST STEP : Setted Parameter: "+Param);
	    	try {
		            driver.switchTo().alert().accept();
		    } catch (Exception e) {
		            Driver.Driver.LogInstance.add("WRONG : Doesnt seems any alert message on the WEB UI.");
		    }
		    WebElement UIElement = driver.findElement(By.id(Object.ObjectID.toString()));
		    if(UIElement==null){
                Driver.Driver.LogInstance.add("INFO : Trying to find partial element by Xpath instead");
                UIElement = getElementByXpath(Object.ObjectID,driver);
	        }
	        UIElement.sendKeys(Param);
	        Driver.Driver.LogInstance.add("TEST STEP : Parameter setted.");
	        Thread.sleep(3000);
	        return true;
	        
	 }catch (Exception e)  {
		 Driver.Driver.LogInstance.add("ERROR : Parameter could not setted. Exception : "+e);
		 return false;}
	    }
	  private static WebElement getElementByXpath(String xpath,WebDriver driver){
	        try{
	        	return driver.findElement(By.xpath(xpath));
	        	
	        }catch(Exception e){
	            return null;
	        }
	    }
	  private static WebElement getElementByID(String id,WebDriver driver){
		  try{
	        	return driver.findElement(By.id(id));
	        } catch(Exception e){
	            return null;
	        }
	    }
	    
}
