/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BillionGraves;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author DartRA
 */
public class BillionGravesController implements Initializable {

    private final FileChooser myFileChooser = new FileChooser();
    private TextFileReader reader;
    private TextFileReader dataReader;
    private TextFileReader ingestReader;
    private File selectFilePath;
    private List<File> multiFilePath;
    private Map<String, File> namePath;
    private String strFilePath;
    private String strFileName;
    private String currentLine;
    private static String timeStamp;
    private RecordDataFields dataReaderSimpleString;
    private int rowCount;

    @FXML
    private Button btnFileChooser;

    @FXML
    private ListView lstViewFiles;

    @FXML
    private Button btnRemove1;

    @FXML
    private Button btnRemove2;

    @FXML
    private Button btnRemove3;

    @FXML
    private Button btnRemove4;

    @FXML
    private Button btnRemove5;

    @FXML
    private ComboBox cbxRowsToPreview;

    @FXML
    private Button btnPreviewData;

    @FXML
    private CheckBox chkBoxTruncate;

    @FXML
    private CheckBox chkBoxUI;

    @FXML
    private Button btnFileIngest;

    @FXML
    private TableView tvDataPreview;
    private ObservableList<String> cellData;

    @FXML
    public void btnFileChooser(ActionEvent event) {
        namePath = new HashMap<>();
        multiFilePath = new MiscUtilities().getFilePath();

        if (multiFilePath != null) {
            for (File b : multiFilePath) {
                strFileName = b.toString();

                if (lstViewFiles.getItems().contains(b.getName())) {
                    break;
                } else {
                    lstViewFiles.getItems().add(b.getName());
                    namePath.put(b.getName(), b);//creates a hash map with filename key and filepath value
                }
            }
        }
        int selectedFileCount = lstViewFiles.getItems().size();
        Map buttonState = MiscUtilities.getButtonState(selectedFileCount);

        btnRemove1.setVisible((boolean) buttonState.get("Button1"));
        btnRemove2.setVisible((boolean) buttonState.get("Button2"));
        btnRemove3.setVisible((boolean) buttonState.get("Button3"));
        btnRemove4.setVisible((boolean) buttonState.get("Button4"));
        btnRemove5.setVisible((boolean) buttonState.get("Button5"));
    }

    public void btnRemove1(ActionEvent event) {
        //need to remove clicked entry from file/filepath hashmap
        namePath.remove(lstViewFiles.getItems().get(0).toString());

        Object remove = lstViewFiles.getItems().remove(0);

        int selectedFileCount = lstViewFiles.getItems().size();
        Map buttonState = MiscUtilities.getButtonState(selectedFileCount);

        btnRemove1.setVisible((boolean) buttonState.get("Button1"));
        btnRemove2.setVisible((boolean) buttonState.get("Button2"));
        btnRemove3.setVisible((boolean) buttonState.get("Button3"));
        btnRemove4.setVisible((boolean) buttonState.get("Button4"));
        btnRemove5.setVisible((boolean) buttonState.get("Button5"));
    }

    public void btnRemove2(ActionEvent event) {
        //need to remove clicked entry from file/filepath hashmap
        namePath.remove(lstViewFiles.getItems().get(1).toString());
        Object remove = lstViewFiles.getItems().remove(1);

        int selectedFileCount = lstViewFiles.getItems().size();
        Map buttonState = MiscUtilities.getButtonState(selectedFileCount);

        btnRemove1.setVisible((boolean) buttonState.get("Button1"));
        btnRemove2.setVisible((boolean) buttonState.get("Button2"));
        btnRemove3.setVisible((boolean) buttonState.get("Button3"));
        btnRemove4.setVisible((boolean) buttonState.get("Button4"));
        btnRemove5.setVisible((boolean) buttonState.get("Button5"));

    }

    public void btnRemove3(ActionEvent event) {
        //need to remove clicked entry from file/filepath hashmap
        namePath.remove(lstViewFiles.getItems().get(2).toString());
        Object remove = lstViewFiles.getItems().remove(2);

        int selectedFileCount = lstViewFiles.getItems().size();
        Map buttonState = MiscUtilities.getButtonState(selectedFileCount);

        btnRemove1.setVisible((boolean) buttonState.get("Button1"));
        btnRemove2.setVisible((boolean) buttonState.get("Button2"));
        btnRemove3.setVisible((boolean) buttonState.get("Button3"));
        btnRemove4.setVisible((boolean) buttonState.get("Button4"));
        btnRemove5.setVisible((boolean) buttonState.get("Button5"));

    }

    public void btnRemove4(ActionEvent event) {
        //need to remove clicked entry from file/filepath hashmap
        namePath.remove(lstViewFiles.getItems().get(3).toString());
        Object remove = lstViewFiles.getItems().remove(3);

        int selectedFileCount = lstViewFiles.getItems().size();
        Map buttonState = MiscUtilities.getButtonState(selectedFileCount);

        btnRemove1.setVisible((boolean) buttonState.get("Button1"));
        btnRemove2.setVisible((boolean) buttonState.get("Button2"));
        btnRemove3.setVisible((boolean) buttonState.get("Button3"));
        btnRemove4.setVisible((boolean) buttonState.get("Button4"));
        btnRemove5.setVisible((boolean) buttonState.get("Button5"));

    }

    public void btnRemove5(ActionEvent event) {
        //need to remove clicked entry from file/filepath hashmap
        namePath.remove(lstViewFiles.getItems().get(4).toString());
        Object remove = lstViewFiles.getItems().remove(4);

        int selectedFileCount = lstViewFiles.getItems().size();
        Map buttonState = MiscUtilities.getButtonState(selectedFileCount);

        btnRemove1.setVisible((boolean) buttonState.get("Button1"));
        btnRemove2.setVisible((boolean) buttonState.get("Button2"));
        btnRemove3.setVisible((boolean) buttonState.get("Button3"));
        btnRemove4.setVisible((boolean) buttonState.get("Button4"));
        btnRemove5.setVisible((boolean) buttonState.get("Button5"));

    }

    @FXML  //get the column names
    public void btnPreviewData(ActionEvent event) {
        String hashKey = lstViewFiles.getItems().get(0).toString();//grabs the first file item in the list
        try {
            reader = new TextFileReader(namePath.get(hashKey));

            List<String> columnNames = reader.getColumnNames();

            for (String a : columnNames) {
                TableColumn newColumn = new TableColumn(a);
                tvDataPreview.getColumns().add(newColumn);
            }
        } catch (IOException ex) {
            Logger.getLogger(BillionGravesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tvDataPreview.setEditable(true);
        tvDataPreview.setItems(reader.getData());

//get the fielded record data for table display        
        try {
            dataReader = new TextFileReader(namePath.get(hashKey), rowCount);
            //dataReaderSimpleString = new RecordDataFields(selectFilePath, rowCount);
        } catch (IOException ex) {
            Logger.getLogger(BillionGravesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tvDataPreview.setEditable(true);

        Object myRowData[] = dataReader.getAllData().toArray();

        for (int i = 0; i < (dataReader.getAllData().size()); i++) {

        }
        tvDataPreview.setItems(dataReader.getData());
    }

    @FXML
    public void btnFileIngest(ActionEvent event) {

        //Truncate table is checkbox is selected    
        PPOFNLDataWriter truncateTable = new PPOFNLDataWriter(chkBoxTruncate.isSelected());

        //Start the data ingest
        for (int i = 0; i < (lstViewFiles.getItems().size()); i++) {  //loop to process each selected file*********
            String hashKey = lstViewFiles.getItems().get(i).toString();
            selectFilePath = namePath.get(hashKey);

            //set timestamp for record group field
            Date systemDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
            String ingestTimeStamp = sdf.format(systemDate);

            //get all the data one row at a time and treat        
            try {
                dataReader = new TextFileReader(selectFilePath, currentLine, ingestTimeStamp);

            } catch (IOException ex) {
                Logger.getLogger(BillionGravesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        PPOFNLDataWriter removeDupes = new PPOFNLDataWriter();//removes dupes from load table.
        //PPOFNLDataWriter assignUI = new PPOFNLDataWriter(chkBoxUI.isSelected(), "");Not ready to use.  Need to remove dupe rows first.
    }

    @FXML
    public void cbxRowsToPreview(ActionEvent event) {
        //System.out.println(cbxRowsToPreview.getSelectionModel().getSelectedItem());
        rowCount = Integer.valueOf(cbxRowsToPreview.getSelectionModel().getSelectedItem().toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tvDataPreview.getColumns().clear();
        cbxRowsToPreview.getSelectionModel().select(0);

        int selectedFileCount = lstViewFiles.getItems().size();
        Map buttonState = MiscUtilities.getButtonState(selectedFileCount);

        btnRemove1.setVisible((boolean) buttonState.get("Button1"));
        btnRemove2.setVisible((boolean) buttonState.get("Button2"));
        btnRemove3.setVisible((boolean) buttonState.get("Button3"));
        btnRemove4.setVisible((boolean) buttonState.get("Button4"));
        btnRemove5.setVisible((boolean) buttonState.get("Button5"));

    }
}
