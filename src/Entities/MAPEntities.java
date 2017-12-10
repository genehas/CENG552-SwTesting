/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entities;

public class MAPEntities {
    public String UniqID;
    public String PageLink;
    public String PartialObject;
    public String ObjectID;
    public String ObjectType;
    public String SaveButtonID;
    
    
    public MAPEntities (String ID, String Link,String PartialPath,String ObjectID,String ObjectType, String SaveButtonID){
        super();
        this.UniqID = ID;
        this.PageLink = Link;
        this.PartialObject = PartialPath;
        this.ObjectID = ObjectID;
        this.ObjectType = ObjectType;
        this.SaveButtonID =SaveButtonID;
    }
}
