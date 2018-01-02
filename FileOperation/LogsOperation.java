/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FileOperation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.regex.Pattern;
import FileOperation.LogsOperation;

/**
 *
 * @author Ferhat-PC
 */
public class LogsOperation extends Driver.LogsDriver {
    
    protected static File TraceFileInstance;
    protected static String FilePath = "";
    protected static String TextFile = "";
    private static BufferedWriter bw;
    private static FileWriter fw;
    private static String LogsString = "";
    private static File FileOperation;
    private static String ArgumentString = "";
    
    public static boolean CreateFile(String FolderName, String FileName,List<String> LogsList){
        try {
             if(FolderName != "" || FileName != ""){
                 FilePath = FolderName;
                 TextFile = FileName;
                 
                 if(LogsString != ""){ 
                     LogsString = ""; 
                 }
                 LogInstance.add(FileName);
                 LogInstance.add("INFO : Will be check file is exist.");
                 if(CheckFolder(FilePath)){
                     LogInstance.add("INFO : Check file operation complated by succsessfuly",2);
                     System.out.println("INFO : Check file operation complated by succsessfuly");
                     LogInstance.add("INFO : File operation instance will be create for create logs file.",1);
                     TraceFileInstance = new File(FilePath +"/"+ TextFile);
                     fw = new FileWriter(TraceFileInstance.getAbsoluteFile());
                     bw = new BufferedWriter(fw);
                     LogInstance.add("INFO : File operation instance has been created. Will be read outputlist.",2);
                     
                     if(LogsList != null){
                         for (int i = 0; i < LogsList.size(); i++) {
                             LogsString += LogsString.charAt(i);
                         }
                         
                         LogInstance.add("INFO : Read operation has been complate. Will be write to file.",1);
                         System.out.println("INFO : Read operation has been complate. Will be write to file.");
                         bw.write(LogsString);
                         bw.flush();
                         LogInstance.add("INFO : Write operation complate. Closing file instance. ",2);
                         System.out.println("INFO : Write operation complate. Closing file instance.");
                         bw.close();
                         bw = null;
                     }else { LogInstance.add("ERROR : Log list is empty returning false.",2);  
                        return false;
                     }
                 }
             }else{
                 LogInstance.add("ERROR : Required arguments is empty so returning false. Function Name is : CreateFile. ",2);
                 System.out.println("ERROR : Required arguments is empty.");
             }
        } catch (Exception e) {
            LogInstance.add("ERROR : Detected error while creating file. Error code is: " + e.getMessage(),1);
            System.out.println("ERROR : Detected error while creating file.");
        }
        return true;
    }
    
    public static boolean CheckFolder(String FileName){
        try {
                if(FileName == null || FileName == ""){
                    Driver.Driver.TraceLogs.add("ERROR : Filename arguments is empty so returning false.function name is : CheckFolder");
                    return false;
                }                
                Driver.Driver.TraceLogs.add("INFO : Will be check log files.");
                System.out.println("INFO : Will be check log files.");
               if(TraceFileInstance == null){
                    Driver.Driver.TraceLogs.add("INFO : File instance will be create.");
                    System.out.println("INFO : File instance will be create");
                    TraceFileInstance = new File(FileName);
                }
                Driver.Driver.TraceLogs.add("INFO : Will be check file is exist");
                System.out.println("INFO : Will be check file is exist");
                if(!TraceFileInstance.exists()){
                    Driver.Driver.TraceLogs.add("INFO : Trace log not found will be create Trace logs file.");
                    TraceFileInstance.mkdirs();
                    Driver.Driver.TraceLogs.add("INFO  : TraceLogs file was created.");
                    System.out.println("INFO  : TraceLogs file was created.");
                }
                
        } catch (Exception e) {
            Driver.Driver.TraceLogs.add("ERROR : Check file exist error. Error code is :" + e.getMessage());
            System.out.println("ERROR : Check file exist error.");
            TraceFileInstance = null;
            return false;
        }
        TraceFileInstance = null;
        return true;
    }
    
    public static boolean checkFile(String FileName){
        try {
            if(!FileName.equals("")){
                if(FileName.equals("") || FileName == null){
                    return false;
                }
                System.out.println("INFO : Will be check log files.");
                TraceFileInstance = new File(FileName);
                if(!TraceFileInstance.exists()){
                    return false;
                }else{
                    return true;
                }
            }
        } catch (Exception e) {
        }
        TraceFileInstance = null;
        return true;
       
    }
    
   public static boolean CreateIperfLogs(List<String> IperfLogs, String CaseCount){
        if(IperfLogs != null){
            try {
                if(!CheckFolder("Troughput")){
                   return false; 
                }
                LogInstance.add("INFO : Iperf test log will be create on log file.",1);
                System.out.println("INFO : Iperf test log will be create on log file.");
                FileOperation = new File("Troughput/"+CaseCount+".txt");
                
                if(FileOperation.exists()){
                    LogInstance.add("INFO : Will be delete old troughput file.",1);
                    System.out.println("INFO : Will be delete old troughput file.");
                    FileOperation.delete();
                }
                FileWriter gw = new FileWriter(FileOperation.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(gw);
		for (int i = 0; i < IperfLogs.size(); i++) {
                   if(IperfLogs.get(i).toString() != ""){
                           bw.write(IperfLogs.get(i).toString() +" \n");
                   }
               }
               
               bw.close();
               System.out.println("INFO : Troughput test log has been writed to file. you find iperf logs under <Troughput> folders.Log name is :" + CaseCount +".txt");
               LogInstance.add("INFO : Troughput test log has been writed to file. you find iperf logs under <Troughput> folders.Log name is :" + CaseCount +".txt");
               return true;
               
            } catch (Exception e) {
                LogInstance.add("ERROR : Dont write troughput logs. Error code is : " + e.getMessage());
                System.out.println("ERROR : Dont write troughput logs. Error code is : " + e.getMessage());
                return false;
            }
        }        
        LogInstance.add("INFO : IperfLogs list is empty because so can't create troughput log files.");
        System.out.println("INFO : IperfLogs list is empty because so can't create troughput log files.");
        return false;
    }
}
