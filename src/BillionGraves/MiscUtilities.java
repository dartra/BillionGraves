/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BillionGraves;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.stage.FileChooser;

/**
 *
 * @author DartRA
 */
public class MiscUtilities {
    
                         
    public List<File> getFilePath() {
        
        File defaultPath = new File("R:/FinalAssembly/PROJECTS/BillionGraves/SourceData/");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(defaultPath);

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt"));
        List<File> filePath = fileChooser.showOpenMultipleDialog(null);

        return filePath;
    }
     /**
     *
     * @return
     */
    public static String getTimeStamp() {
            Date systemDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
            String timeStamp = sdf.format(systemDate);
        return timeStamp;
    }
    public static String getGroupTimeStamp() {
            Date systemDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_h-mm-ss a");
            String timeStamp = sdf.format(systemDate);
        return timeStamp;
    }    
    /**
     *
     * @param selectedFileCount
     * @return
     */
    public static Map getButtonState(int selectedFileCount) {
        Map mMap = new HashMap();
        
        switch (selectedFileCount) {
            case 1:
                mMap.clear();
                mMap.put("Button1", true);
                mMap.put("Button2", false);
                mMap.put("Button3", false);
                mMap.put("Button4", false);
                mMap.put("Button5", false);
                break;
            case 2:
                mMap.clear();
                mMap.put("Button1", true);
                mMap.put("Button2", true);
                mMap.put("Button3", false);
                mMap.put("Button4", false);
                mMap.put("Button5", false);
                break;
            case 3:
                mMap.clear();
                mMap.put("Button1", true);
                mMap.put("Button2", true);
                mMap.put("Button3", true);
                mMap.put("Button4", false);
                mMap.put("Button5", false);
                break;
            case 4:
                mMap.clear();
                mMap.put("Button1", true);
                mMap.put("Button2", true);
                mMap.put("Button3", true);
                mMap.put("Button4", true);
                mMap.put("Button5", false);
                break;
            case 5:
                mMap.clear();
                mMap.put("Button1", true);
                mMap.put("Button2", true);
                mMap.put("Button3", true);
                mMap.put("Button4", true);
                mMap.put("Button5", true);
                break;

            default:
                mMap.clear();
                mMap.put("Button1", false);
                mMap.put("Button2", false);
                mMap.put("Button3", false);
                mMap.put("Button4", false);
                mMap.put("Button5", false);
        }
        return mMap;

    }
}
