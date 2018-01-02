/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UIOperation;
import Entities.MAPEntities;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class UIDriver {
    public static WebDriver UIInstance;
    public static WebElement Elemets;
    public static JavascriptExecutor Script;
    
    public UIDriver (){
        try {
           if(!Entities.DUTEntities._DUTIP.equals("")){
               System.out.println("INFO : Will be create firefox driver");
               this.UIInstance  = new FirefoxDriver();
                this.UIInstance.navigate().to("http://" + Entities.DUTEntities._DUTIP + "/loginmain.html");
                Thread.sleep(10000);
                if (!Entities.DUTEntities._UIUsername.equals("")){
                        this.UIInstance.findElement(By.id("uiPostGetPage")).clear();
                        Thread.sleep(100);
                        System.out.println("INFO : Will be set login username  : " + Entities.DUTEntities._UIPassword);
                        this.UIInstance.findElement(By.id("uiPostGetPage")).sendKeys(Entities.DUTEntities._UIUsername);
                        Thread.sleep(100);
                }
                if (!Entities.DUTEntities._UIPassword.equals("")){
                     System.out.println("INFO : Will be set login password  : " + Entities.DUTEntities._UIPassword);
                     this.UIInstance.findElement(By.id("uiPostPassword")).clear();
                     Thread.sleep(100);
                     this.UIInstance.findElement(By.id("uiPostPassword")).sendKeys(Entities.DUTEntities._UIPassword);
                     Thread.sleep(100);
                }               
                this.UIInstance.findElement(By.id("__ML_ok")).click();
                System.out.println("INFO : Will be click WEB UI login button.");
                Thread.sleep(8000);
        	   
            }
           else if(!Entities.DUTEntities._WebLink.equals("")) {
    		   System.out.println("INFO : Initilazation firefox driver 22");
               System.out.println("http://" + Entities.DUTEntities._WebLink);
               this.UIInstance  = new FirefoxDriver();
               this.UIInstance.navigate().to("http://" + Entities.DUTEntities._WebLink); 
    	   }
           else {
                this.closeWEBUI();
            }
        } catch (Exception e) {
            System.out.println("ERROR : Driver Instance Error. Error code is : " + e.getMessage());
        }
    }
    
    public boolean closeWEBUI (){
        try {
            Thread.sleep(5000);
            Driver.Driver.LogInstance.add("INFO : Will be close browser");
            UIInstance.close();
            UIInstance.quit();
            UIInstance = null;
            Elemets =null;
        } catch (Exception e) {
            Driver.Driver.LogInstance.add("ERROR : Detected same error. While close web UI instance. Error code is : " + e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean checkObjectID (String UniqID){
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
    
    public MAPEntities getMapObject (String UniqID){
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
}
