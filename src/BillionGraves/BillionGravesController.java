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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author DartRA
 */
public class BillionGravesController implements Initializable {

    private TextFileReader reader;
    private TextFileReader dataReader;
    private TextFileReader ingestReader;
    private File selectFilePath;
    private String strFilePath;
    private String strFileName;
    private String currentLine;
    private static String timeStamp;
    private RecordDataFields dataReaderSimpleString;
    private int rowCount;
    
    @FXML
    private ComboBox cbxRowsToPreview;

    @FXML
    private Button btnFileChooser;

    @FXML
    private Button btnFileIngest;

    @FXML
    private TableView tvDataPreview;
    private ObservableList<String> cellData;

    @FXML
    public void btnFileChooser(ActionEvent event) {
        selectFilePath = new MiscUtilities().getFilePath();

        if (selectFilePath != null) {
            strFileName = selectFilePath.getName();

//get the column names
            try {
                reader = new TextFileReader(selectFilePath);

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

        }

//get the fielded record data for table display        
        try {
            dataReader = new TextFileReader(selectFilePath, rowCount);
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
        
        //set timestamp for record group field

        Date systemDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        String timeStamp = sdf.format(systemDate);
        
//get all the data one row at a time for treatment        
        try {
            dataReader = new TextFileReader(selectFilePath, currentLine, timeStamp);

        } catch (IOException ex) {
            Logger.getLogger(BillionGravesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void cbxRowsToPreview(ActionEvent event) {
        System.out.println(cbxRowsToPreview.getSelectionModel().getSelectedItem());
        rowCount = Integer.valueOf(cbxRowsToPreview.getSelectionModel().getSelectedItem().toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tvDataPreview.getColumns().clear();
        cbxRowsToPreview.getSelectionModel().select(0);

    }
}
