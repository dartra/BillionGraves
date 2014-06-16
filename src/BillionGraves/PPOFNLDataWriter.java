/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BillionGraves;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dartra
 */
public class PPOFNLDataWriter {

    static String insertQuery;

    public PPOFNLDataWriter(List<String> lineData) {
        List<String> myLineData = new ArrayList<>(lineData);

        StringBuilder query = new StringBuilder(10000);

        query.append("INSERT INTO WRK_BILLION_GRAVES_LOAD (");
        query.append("BG_THUMBNAIL_URL,");//0
        query.append(" BG_URL,");//1
        query.append(" CEMETERY_CITY,");//2
        query.append(" CEMETERY_COUNTRY,");//3
        query.append(" CEMETERY_COUNTY,");//4
        query.append(" CEMETERY_LATITUDE,");//5
        query.append(" CEMETERY_LONGITUDE,");//6
        query.append(" CEMETERY_NAME,");//7
        query.append(" CEMETERY_STATE,");//8
        query.append(" CREATED_TIMESTAMP,");//9
        query.append(" EVENT_DATE,");//10
        query.append(" EVENT_PLACE,");//11
        query.append(" EVENT_PLACE_LATITUDE,");//12
        query.append(" EVENT_PLACE_LONGITUDE,");//13
        query.append(" EVENT_TYPE,");//14
        query.append(" EXCLUDE_FROM_EXPORT,");//15
        query.append(" FS_COLLECTION_ID,");//16
        query.append(" IMAGE_ID,");//17
        query.append(" MAP_LOOKUP_URL,");//18
        query.append(" MARRIAGE_DATE,");//19
        query.append(" MARRIAGE_DAY,");//20
        query.append(" MARRIAGE_MONTH,");//21
        query.append(" MARRIAGE_YEAR,");//22
        query.append(" PPQ_ID,");//23
        query.append(" PR_BIRTH_DATE,");//24
        query.append(" PR_BIRTH_DAY,");//25
        query.append(" PR_BIRTH_MONTH,");//26
        query.append(" PR_BIRTH_YEAR,");//27
        query.append(" PR_DEATH_DATE,");//28
        query.append(" PR_DEATH_DAY,");//29
        query.append(" PR_DEATH_MONTH,");//30
        query.append(" PR_DEATH_YEAR,");//31
        query.append(" PR_DEATH_YEAR_ORIG,");//32
        query.append(" PR_NAME,");//33
        query.append(" PR_NAME_GN,");//34
        query.append(" PR_NAME_MAIDEN,");//35
        query.append(" PR_NAME_PREFIX,");//36
        query.append(" PR_NAME_SUFFIX,");//37
        query.append(" PR_NAME_SURN,");//38
        query.append(" RECORD_GROUP,");//39
        query.append(" SORT_KEY,");//40
        query.append(" SPOUSE_NAME_GN,");//41
        query.append(" UNIQUE_IDENTIFIER,");//42
        query.append(" UPDATED_TIMESTAMP");//43
        query.append(") values (");
        for (int n = 0; n < 43; n++) {
            if (myLineData.get(n) != null) {
                String fieldString = myLineData.get(n).replace("'", "''");
                query.append("'");
                query.append(fieldString);
                query.append("', ");

            } else {
                query.append("null, ");
            }
        }
        if (myLineData.get(43) != null) {
            String fieldString = myLineData.get(43).replace("'", "''");
            query.append("'");
            query.append(fieldString);
            query.append("')");
        } else {
            query.append("null)");
        }

        insertQuery = query.toString();
        try {
            saveToDatabase(insertQuery);
        } catch (SQLException ex) {
            Logger.getLogger(PPOFNLDataWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static final Connection dbConnection = getConnection();

    private static void saveToDatabase(String query) throws SQLException {

        try (Statement statement = dbConnection.createStatement()) {
            statement.execute(insertQuery);
        }
    }

    private static void truncateTable(String query) throws SQLException {

        try (Statement statement = dbConnection.createStatement()) {
            statement.execute(query);
        }
    }

    private void assignUI(String UIQuery) throws SQLException {

        try (Statement statement = dbConnection.createStatement()) {
            statement.execute(UIQuery);
        }
    }

    private void removeDupes(String orderedQuery) throws SQLException {
        List<String> dupeList = new ArrayList<>();
        String idHold = null;
        String imageId;
        String rowId;
        
        try (Statement statement = dbConnection.createStatement()) {
            ResultSet rs = statement.executeQuery(orderedQuery);//read each row and analyze for dupe and assign rowid's of dupes to list

            while (rs.next()) {
                imageId = rs.getString("IMAGE_ID");
                rowId = rs.getString("ROWID");

                if (imageId.equals(idHold)) {
                    dupeList.add(rowId);
                }
                idHold = imageId;
            }
            statement.close();
            if (!dupeList.isEmpty()) {
                for (String id : dupeList) {
                    String dedupeSQL = "DELETE FROM WRK_BILLION_GRAVES_LOAD WHERE ROWID = '" + id + "'";

                    try (Statement deleteStatement = dbConnection.createStatement()) {
                        deleteStatement.execute(dedupeSQL);
                        deleteStatement.close();
                    }
                }
            }
        }
    }

    private static Connection getConnection() {
        try {
            try {
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(PPOFNLDataWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PPOFNLDataWriter.class.getName()).log(Level.SEVERE, null, ex);
            }

            Properties connectionProperties = new Properties();
            connectionProperties.put("user", "ppofnl");
            connectionProperties.put("password", "junesnow");
            DriverManager.setLoginTimeout(30);
            return DriverManager.getConnection("jdbc:oracle:thin:@10.34.84.227:1521:F410", connectionProperties);

        } catch (SQLException ex) {
            Logger.getLogger(PPOFNLDataWriter.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(PPOFNLDataWriter.class.getName()).log(Level.SEVERE, null, ex.getErrorCode());
            return null;
        }
    }

    public PPOFNLDataWriter(boolean truncate) {
        if (truncate == true) {
            String truncateQuery = "TRUNCATE TABLE WRK_BILLION_GRAVES_LOAD";
            try {
                truncateTable(truncateQuery);
            } catch (SQLException ex) {
                Logger.getLogger(PPOFNLDataWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public PPOFNLDataWriter() {//This constructor is used to identify dupe records
        String orderedQuery = "SELECT ROWID, IMAGE_ID FROM WRK_BILLION_GRAVES_LOAD ORDER BY IMAGE_ID";
        try {
            removeDupes(orderedQuery);
        } catch (SQLException ex) {
            Logger.getLogger(PPOFNLDataWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PPOFNLDataWriter(boolean assignUI, String emptyString) {
        if (assignUI == true) {
            String UIQuery = "";//this needs to be changed to UI assignment
            try {
                assignUI(UIQuery);
            } catch (SQLException ex) {
                Logger.getLogger(PPOFNLDataWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
