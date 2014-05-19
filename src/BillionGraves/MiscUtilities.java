/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BillionGraves;

import java.io.File;
import javafx.stage.FileChooser;

/**
 *
 * @author DartRA
 */
public class MiscUtilities {
    
    public File getFilePath() {

        File defaultPath = new File("R:/FinalAssembly/PROJECTS/BillionGraves/SourceData");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(defaultPath);

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt"),
                new FileChooser.ExtensionFilter("All files (*.*)", "*.*"));
        File filePath = fileChooser.showOpenDialog(null);
        return filePath;
    }
}
