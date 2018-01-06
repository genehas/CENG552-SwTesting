/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Driver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;

public class LogsDriver extends  Driver{
   
    private static File _file;
    private static String _directory = "WEBUI_Trace";
    private static BufferedWriter bw;
    private static FileWriter fw;
    private static Date DateTimeInstance;
    private static int FileInstance = 0;
    
    @Override
    public boolean add(String e) {
        Driver.TraceLogs.add(e + "Time is : " + DateObject());
        if(FlushLogs(e)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean closeAllSeason(){
        try {
            bw.close();
            bw = null;
            fw.close();
            fw = null;
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public boolean add(String e,int LogSeverity){
        if(Driver.DebugLevel == 2){
            this.add(e);
        }else if(LogSeverity == 1 && Driver.DebugLevel == 1){
           this.add(e);
        }
        return true;
    }
    
    public static String DateObject(){
        if (DateTimeInstance != null){
            DateTimeInstance = null;
        }
        DateTimeInstance = new Date();
        return String.format(DateTimeInstance.toString());
    }
  
    private boolean FlushLogs(String logs){
        try {
            if(bw == null && fw == null){
                if(CreateInstanceForLogs()){
                    System.out.println("INFO : Creating log file for traceLogs.");
                }
            }
            bw.write(logs);
            bw.flush();
            bw.newLine();
        } catch (Exception e) {
            Driver.TraceLogs.add("ERROR : TraceLogs ERROR.");
            bw = null;
            fw= null;
            return false;
        }
        return true;
    }
    
    private boolean CreateInstanceForLogs(){
        if(FileOperation.LogsOperation.CheckFolder("Logs")){
            try {
                if(Driver.TraceLogsName.equals("")){
                    while(true){
                        if(!FileOperation.LogsOperation.checkFile("Logs/"+_directory +"_" + FileInstance +".txt")){
                            break;
                        }else{
                            FileInstance +=1;
                        }
                    }
                    _file = new File("Logs/"+_directory +"_" + FileInstance +".txt");
                    fw = new FileWriter(_file.getAbsoluteFile());
                    bw = new BufferedWriter(fw);
                }else {
                    _file = new File("Logs/"+Driver.TraceLogsName +".txt");
                    fw = new FileWriter(_file.getAbsoluteFile());
                    bw = new BufferedWriter(fw);
                }
           } catch (Exception e) {
               
               return false;
           }
        }else{
            return false;
        }
        return true;
    }
}
