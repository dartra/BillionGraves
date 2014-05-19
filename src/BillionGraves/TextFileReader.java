/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BillionGraves;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DartRA
 */
public class TextFileReader {

    private List<String> columnNamesList = null;
    private List<String> columnData = null;
    private List<String> recordData = null;
    private ObservableList<String> olData = FXCollections.observableArrayList();
    private List<ObservableList<String>> recordDataList = FXCollections.observableArrayList();
    private String currentLine = null;

    public TextFileReader(File filePath) throws IOException {//Get the column names

        BufferedReader br = null;
        Integer count = 0;
        String tabSplitBy = "\\t";
        String fieldNames[];
        String recordData[];
        String sCurrentLine;

        br = new BufferedReader(new FileReader(filePath.toString()));
        sCurrentLine = br.readLine();

        fieldNames = sCurrentLine.split(tabSplitBy);
        columnNamesList = Arrays.asList(fieldNames);

        //System.out.println(sCurrentLine + "\r\n");
        while ((sCurrentLine = br.readLine()) != null) {
            count = count + 1;
            if (count < 2) {
                recordData = sCurrentLine.split(tabSplitBy);

                olData = FXCollections.observableArrayList(Arrays.asList(recordData));

                for (int i = 0; i < recordData.length; i++) {
                    //System.out.println(recordData[i]);
                }
                //System.out.println("\r");
            }
            if (count > 1) {
                break;
            }
        }
        br.close();
    }

    public TextFileReader(File filePath, int rowCount) throws IOException {//get xx rows of data

        BufferedReader br = null;
        Integer count = 0;
        String tabSplitBy = "\\t";
        String recordData[];
        String sCurrentLine;

        br = new BufferedReader(new FileReader(filePath.toString()));
        sCurrentLine = br.readLine();//read first line and skip it
        sCurrentLine = br.readLine();

        while ((sCurrentLine = br.readLine()) != null) {
            count = count + 1;

            if (count < rowCount + 1) {
                recordData = sCurrentLine.split(tabSplitBy);
                System.out.println("Count = " + count + "  >>  " + sCurrentLine);
                olData = FXCollections.observableArrayList(Arrays.asList(recordData));
                recordDataList.add(olData);

                for (int i = 0; i < recordData.length; i++) {

                    //System.out.println(recordData[i]);
                }
                //System.out.println("\r");
            }

            if (count > rowCount) {
                break;
            }
        }
        br.close();
    }

    public TextFileReader(File filePath, String data1, String dateTime) throws IOException {//get all data
        BufferedReader br = null;
        String tabSplitBy = "\\t";
        String sCurrentLine;
        String recordData[];
        
        br = new BufferedReader(new FileReader(filePath.toString()));
        sCurrentLine = br.readLine();//read first line and skip it
        sCurrentLine = br.readLine();

        while ((sCurrentLine = br.readLine()) != null) {
            TreatBurialRecord doTreatments = new TreatBurialRecord(filePath, sCurrentLine, dateTime);

        }
        br.close();
    }

    List<String> getColumnNames() {
        return columnNamesList;
    }

    ObservableList<String> getData() {
        return olData;
    }

    String getCurrentLine() {
        return currentLine;
    }

    List<String> getRowData() {
        return recordData;
    }

    List<ObservableList<String>> getAllData() {
        return recordDataList;
    }

}
