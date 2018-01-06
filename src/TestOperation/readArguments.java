/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TestOperation;

import Driver.Driver;
import java.util.regex.Pattern;

public class readArguments extends ParsingOperation.doParsingOperation{
    private int ArgumentsCount = 0;
    
    public boolean readUserArguments(String[] Arguments){
        try {
            if(Arguments.length == 0){
                System.out.println("ERROR : Argument list is missing ");
                this.PrintHelp();
                System.exit(-1);
            }
          for (int i = 0; i < Arguments.length; i++) {
              if(Pattern.compile("HELP").matcher(Arguments[i].toString().toUpperCase()).find()){
                  this.PrintHelp();
              }
              if (Pattern.compile("-DO").matcher(Arguments[i].toString()).find()){
                  Driver.LogInstance.add("INFO : -DO Paramaters argument is : " +Arguments[i].toString() );
                  detectFunction(Arguments[i].toString());
                  ArgumentsCount +=1;
              }
              if(Pattern.compile("-ID").matcher(Arguments[i].toString().toUpperCase()).find()){
                  Driver._UniqID = ParsingOperation.doParsingOperation.GetEntities(Arguments[i].toString());
                   Driver.LogInstance.add("INFO : -ID Paramaters argument is : " + Driver._UniqID);
                  ArgumentsCount +=1;
                  if(Driver._UniqID.equals("")){
                      System.out.println("ERROR : Uniq id is empty.");
                      System.exit(-1);
                  }
                  System.out.println("INFO : Uniq ID is : " + Driver._UniqID);
              }
              if (Pattern.compile("-MAP").matcher(Arguments[i].toString().toUpperCase()).find()){
                  Driver._UIMAP =  ParsingOperation.doParsingOperation.GetEntities(Arguments[i].toString());
                  Driver.LogInstance.add("INFO : -MAP Paramaters argument is : " + Driver._UIMAP);
                  ArgumentsCount +=1;
                  if (Driver._UIMAP.equals("")){
                      System.out.println("ERROR : UIMap is empty.");
                      System.exit(-1);
                  }else {
                      System.out.println("INFO : Will be read xml file.");
                      readXml(Driver._UIMAP);
                  }
              }
              if (Pattern.compile("-DUT").matcher(Arguments[i].toString().toUpperCase()).find()){
                    Driver._DUTConf = ParsingOperation.doParsingOperation.GetEntities(Arguments[i].toString());
                    Driver.LogInstance.add("INFO : -DUT Paramaters argument is : " + Driver._DUTConf);
                    ArgumentsCount +=1;
                    if(Driver._DUTConf.equals("")){
                       System.out.println("ERROR : DUT Conf is empty");
                        System.exit(-1);
                    }else {
                        System.out.println("INFO : Will be read dut config.");
                        System.out.println("readfile : "+readFile(Driver._DUTConf));
                        System.out.println("Argumetn Count : "+ArgumentsCount);
                       
                    }
                }
                if (Pattern.compile("-f").matcher(Arguments[i].toString()).find()){
                    Driver.TraceLogsName = ParsingOperation.doParsingOperation.GetEntities(Arguments[i].toString());
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR : read user arguments error. Error code is : " + e.getMessage());
            return false;
        }
        if(ArgumentsCount != 4){
            System.out.println(ArgumentsCount);
            System.out.println("Check configuration file.");
            PrintHelp();
        }
        return true;
    }
    
    private static void PrintHelp () {
        System.out.println("########################################################################");
        System.out.println("########################################################################");
        System.out.println("-DO=SET:SetValue || -DO=GET <SET,GET,SETANDVERIFY,GETANDVERIFY,MULTISET,MULTIGET,BROWSEROP>");
        System.out.println("-DUT= DUT Configuration file Path");
        System.out.println("-MAP= DUT xml file path.");
        System.out.println("-ID=1233  <ID is uniq id depended xml object>");
        System.out.println("Example Command as is below;");
        System.out.println("java -jar Apps -DO=SET:SetValue -ID=1234 -MAP=../map.xml -DUT=../dut.txt");
        System.out.println("java -jar Apps -DO=BROWSEROP:TestStep.txt -ID=null -MAP=../map.xml -DUT=../dut.txt");
        System.out.println("########################################################################");
        System.out.println("########################################################################");
        System.out.println("");
        System.exit(-1);
    }
    
    public void detectFunction (String Row){
        if (!Row.equals("")){
            try {
                if (Pattern.compile("GETANDVERIFY").matcher(Row).find()){
                    Driver._Function = "GETANDVERIFY";
                    System.out.println("INFO : Function is : Get and Verify");
                    if(!detectValue(Row)){
                        Driver.LogInstance.add("WRONG : Exit code, Row data is : " + Row);
                        System.exit(-1);
                    }
                }else if (Pattern.compile("SETANDVERIFY").matcher(Row).find()){
                    Driver._Function = "SETANDVERIFY";
                    System.out.println("INFO : Function is : Set and Verify");
                    if(!detectValue(Row)){
                        Driver.LogInstance.add("WRONG : Exit code, Row data is : " + Row);
                        System.exit(-1);
                    }
                }else if (Pattern.compile("MULTISET").matcher(Row).find()){
                    Driver._Function = "MULTISET";
                    Driver.LogInstance.add("INFO : Detect Function Will be do multiSet operation.");
                    if(!detectValue(Row)){
                        Driver.LogInstance.add("WRONG : Exit code, Row data is : " + Row);
                        System.exit(-1);
                    }
                }else if (Pattern.compile("MULTIGET").matcher(Row).find()){
                    Driver._Function = "MULTIGET";
                    Driver.LogInstance.add("INFO : Detect Function Will be do multiSet operation.");
                    if(!detectValue(Row)){
                        Driver.LogInstance.add("WRONG : Exit code, Row data is : " + Row);
                        System.exit(-1);
                    }
                }else if (Pattern.compile("GET").matcher(Row).find()){
                    Driver._Function = "GET";
                }else if (Pattern.compile("SET").matcher(Row).find()){
                    Driver._Function = "SET";
                    if(!detectValue(Row)){
                        Driver.LogInstance.add("WRONG : Exit code, Row data is : " + Row);
                        System.exit(-1);
                    }
                }else if (Pattern.compile("BROWSEROP").matcher(Row).find()){
                    Driver._Function = "BROWSEROP";
                    if(!detectValue(Row)){
                        Driver.LogInstance.add("WRONG : Exit code, Row data is : " + Row);
                        System.exit(-1);
                    }
                }else {
                    System.out.println("ERROR : Wrong function type. Commimg row is : " + Row); 
                    System.exit(-1);
                }
            } catch (Exception e) {
                System.out.println("ERROR : Detect some error. While functionality detect operation. Error code is : " + e.getMessage());
                System.exit(-1);
            }
        }
    }
    
   public boolean detectValue (String Row){
       if (!Row.equals("")){
           try {
               Driver._FunctionalValue = ParsingOperation.doParsingOperation.getValue(Row);
               if(!Driver._FunctionalValue.equals("")){
                   System.out.println("INFO : Using value is : " + Driver._FunctionalValue);
                   return true;
               }
           } catch (Exception e) {
               System.out.println("ERROR : detected some error. while parsing arguments value. Error code is : " 
                       + e.getMessage());
           }
       }
       System.out.println("WRONG : Value is empty so returning false.");
       return false;
   }
}
