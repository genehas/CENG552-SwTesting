/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Driver;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author test
 */
public class Driver extends  ArrayList<String> {
    public static List<Entities.MAPEntities> MapList = new ArrayList<Entities.MAPEntities>();
     public static ArrayList<String> TraceLogs = new ArrayList<String>();
     public static ArrayList<String> MultiValueList = new ArrayList<>();
     public static ArrayList<String> MultiIDList = new ArrayList<>();
     public static int DebugLevel = 2;
     public static String ConfigFile = "";
     public static LogsDriver LogInstance = new LogsDriver();
     public static String TraceLogsName = "";
     public static String _UIMAP = "";
     public static String _DUTConf = "";
     public static String _UniqID = "";
     public static String _Function = "";
     public static String _FunctionalValue = "";
     public static String _FunctionalMultiValues = "";
}
