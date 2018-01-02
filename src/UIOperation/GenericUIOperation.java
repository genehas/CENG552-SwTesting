/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UIOperation;

import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GenericUIOperation extends UIDriver{
    private String[] PartialList;
    
    protected WebElement getCurrentObject (Entities.MAPEntities object){
        try {
            if(UIInstance.findElement(By.id(object.ObjectID)).isDisplayed()){
                return UIInstance.findElement(By.id(object.ObjectID));
            }
        } catch (Exception e) {
            Driver.Driver.LogInstance.add("ERROR : Detected Same error. While going to target object. Error code is : " + e.getMessage());
        }
        Driver.Driver.LogInstance.add("WRONG : Doesnt go to target object so returning null object");
        return null;
    }
    
    protected WebElement goToObject (Entities.MAPEntities object){
        try {
            Driver.Driver.LogInstance.add("INFO : Will be find object." + object.PageLink);
            if (gotoURL(object.PageLink)) {
                Driver.Driver.LogInstance.add("INFO : Object ID has been found." + object.ObjectID);
                if (getPartialList(object.PartialObject) != null){
                    for (int i = 0; i < PartialList.length; i++) {
                        Driver.Driver.LogInstance.add("INFO : Click object id : " + PartialList[i].toString());
                        UIInstance.findElement(By.id(PartialList[i].toString())).click();
                        Thread.sleep(6000);
                    }
                    if(UIInstance.findElement(By.id(object.ObjectID)).isDisplayed()){
                        Driver.Driver.LogInstance.add("INFO : Returning object id");
                        return UIInstance.findElement(By.id(object.ObjectID));
                        
                    }
                }
            }
        } catch (Exception e) {
            Driver.Driver.LogInstance.add("ERROR : Detected Same error. While going to target object. Error code is : " + e.getMessage());
        }
        Driver.Driver.LogInstance.add("WRONG : Doesnt go to target object so returning null object");
        return null;
    }
    
    protected boolean gotoURL (String URL){
         try {
             Thread.sleep(7000);
             UIInstance.navigate().to("http://" + Entities.DUTEntities._DUTIP + "/" + URL);
             Driver.Driver.LogInstance.add("INFO : Redirect to page URL is  http://" + Entities.DUTEntities._DUTIP + "/\""+ URL);
             Thread.sleep(15000);
             if (Pattern.compile(URL).matcher(UIInstance.getCurrentUrl()).find()){
                 Driver.Driver.LogInstance.add("INFO : Page redirect operation has been success");
                 return true;
             }
         } catch (Exception e) {
             Driver.Driver.LogInstance.add("ERROR : Redirect to page error. Error code is : "+ e.getMessage());
         }
        return false;
    }
    
    private String[] getPartialList (String PartialPath){
         try {
             PartialList =  PartialPath.split(";");
         } catch (Exception e) {
             Driver.Driver.LogInstance.add("ERROR : getPartial object list error. Error code is : " + e.getMessage());
         }
        return PartialList;
    }
}
