/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webuiapplication;

import Driver.Driver;
import ParsingOperation.doParsingOperation;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

public class WEBUIApplication {

    private static File _file;
    private static TestOperation.doTestCase OperationInstance = new TestOperation.doTestCase();
    
    /**TestOperation.doTestCase Op
     * @param args the command line arguments
     * @throws javax.xml.stream.XMLStreamException
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
     */
    
    public static void main(String[] args) throws IOException{
        _file = new File(".");
        System.out.println(System.getProperty("os.name").toLowerCase());
        if(!System.getProperty("os.name").toLowerCase().startsWith("win")){
            System.out.println("OS is : Linux/Mac");
            System.out.println("INFO : File path is : " + _file.getCanonicalPath() + "//exec//geckodriver" );
            System.setProperty("webdriver.gecko.driver", _file.getCanonicalPath() + "//exec//geckodriver");
        }else {
            System.out.println("OS is : WIN");
            System.setProperty("webdriver.gecko.driver", _file.getCanonicalPath() + "//exec//geckodriver.exe");
            System.out.println("INFO : File path is : " +_file.getCanonicalPath() + "//exec//geckodriver.exe" );
        }
        try {
            if (OperationInstance.readUserArguments(args)){
                if(OperationInstance.doTestCase()){
                    Driver.LogInstance.add("INFO : Test is complated.");
                }
            }else {
                Driver.LogInstance.add("INFO : Arguments error. ");
            }
        } catch (Exception e) {
            Driver.LogInstance.add("ERROR : FATAL Error. Error code is : " + e.getMessage());
        }        
       //ParsingOperation.doParsingOperation test = new doParsingOperation();
       //test.readFile("G:\\Ferhat\\AutomationFramework\\development\\WEBUIApplication\\msf.txt");
       //test.readXml("G:\\Ferhat\\AutomationFramework\\development\\WEBUIApplication\\xml.xml");
       //UIOperation.WebUIOperation instance = new UIOperation.WebUIOperation();
       //Entities.MAPEntities dene = new Entities.MAPEntities("2012", "wireless/settings/settings_new.html","ui-id-2;ui-id-1;ui-id-2;" ,"txt_ssid_1_0" , "txt_box", "__ML_save");
       //instance.setObject(dene,"Ferhat_TEst_Deneme denedim.");
       //instance.getAndVerifyObjectValue(dene,"Ferhat_TEst_Deneme denedim.");
       //instance.closeWEBUI();
    }
    
}
