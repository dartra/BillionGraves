/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BillionGraves;

import java.io.File;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DartRA
 */
public class TreatBurialRecord {

    private static final String TAB_DELIMITER = "\\t";

    private final String famNames;
    private final String gnNames;
    private final String birYear;
    private final String birDay;
    private final String birMonth;
    private final String deaYear;
    private final String deaDay;
    private final String deaMonth;
    private final String marMonth;
    private final String marYear;
    private final String marDay;
    private final String cemCity;
    private final String cemState;
    private final String cemCounty;
    private final String cemCountry;
    private final String systemTimeStamp;

    String monthAlpha;
    String dayPadded;
    String yearVerified;
    int counter;

    /**
     *
     * @param currentLine the text line to be treated
     * @throws IOException
     */
    public TreatBurialRecord(File filePath, String currentLine, String timeStamp) throws IOException {

        List<String> treatedData = new ArrayList<String>();//initialize array container to hold treated fields
        for (int i = 0; i < 44; i++) {
            treatedData.add(null);
        }

        String[] recordArray = currentLine.split(TAB_DELIMITER);

        // Clean out the nulls before setting properties
        for (int i = 0; i < recordArray.length; i++) {
            String token = recordArray[i];
            if (token != null && token.trim().equals("NULL")) {
                recordArray[i] = null;
            }
        }

        treatedData.set(14, "Burial");//event_type
        treatedData.set(16, "2026973");//fs_collection_id
        treatedData.set(23, "12-0496");//ppq_id
        treatedData.set(17, recordArray[0]);//recId -->image_id
        treatedData.set(1, recordArray[1]);//url -->bg_url
        this.famNames = recordArray[2];
        this.gnNames = recordArray[3];
        treatedData.set(35, recordArray[4]);//mdnNames --> pr_name_maiden
        treatedData.set(36, recordArray[5]);//pre --> pr_name_prefix
        treatedData.set(37, recordArray[6]);//suf --> pr_name_suffix
        this.birYear = recordArray[7];
        this.birDay = recordArray[8];
        this.birMonth = recordArray[9];
        this.deaYear = recordArray[10];
        this.deaDay = recordArray[11];
        this.deaMonth = recordArray[12];
        this.marMonth = recordArray[13];
        this.marYear = recordArray[14];
        this.marDay = recordArray[15];
        treatedData.set(9, recordArray[16]);//created_timestamp
        treatedData.set(43, recordArray[17]);//updated_timestamp
        treatedData.set(7, recordArray[18]);//cemName --> cemetery_name
        this.cemCity = recordArray[19];
        this.cemState = recordArray[20];
        this.cemCounty = recordArray[21];
        this.cemCountry = recordArray[22];
        treatedData.set(5, recordArray[23]);//cemLatitude -->cemetery_latitude
        treatedData.set(12, recordArray[23]);//cemLatitude -->event_place_latitude
        treatedData.set(6, recordArray[24]);//cemLongitude -->cemetery_longitude        
        treatedData.set(13, recordArray[24]);//cemLongitude -->event_place_longitude 
        treatedData.set(0, recordArray[25]);//thumb --> bg_thumbnail_url

        this.systemTimeStamp = timeStamp;

        String prName = this.treatNames(famNames, gnNames);
        treatedData.set(33, prName);
        treatedData.set(34, gnNames);
        treatedData.set(38, famNames);

        String eventPlace = this.treatEventPlace(cemCity, cemCounty, cemState, cemCountry);
        treatedData.set(11, eventPlace);
        treatedData.set(2, cemCity);
        treatedData.set(4, cemCounty);
        treatedData.set(8, cemState);
        treatedData.set(3, cemCountry);

        String prDeathDate = this.treatDeathDate(deaDay, deaMonth, deaYear);
        if (prDeathDate == null) {
            treatedData.set(15, "No Event Date");//Exclude_From_Export
        }
        if (prDeathDate != null) {
            treatedData.set(10, prDeathDate);//event_date
            treatedData.set(28, prDeathDate);
            treatedData.set(29, dayToDayPadded(deaDay));
            treatedData.set(30, monthToAlpha(deaMonth));
            treatedData.set(31, yearVerify(deaYear));
            treatedData.set(32, recordArray[10]);//pr_death_year_orig
        }
        String prBirthDate = this.treatBirthDate(birDay, birMonth, birYear);
        if (prBirthDate != null) {
            treatedData.set(24, prBirthDate);
            treatedData.set(25, dayToDayPadded(birDay));
            treatedData.set(26, monthToAlpha(birMonth));
            treatedData.set(27, yearVerify(birYear));
        }
        String prMarriageDate = this.treatMarriageDate(marDay, marMonth, marYear);
        if (prMarriageDate != null) {
            treatedData.set(19, prMarriageDate);
            treatedData.set(20, dayToDayPadded(marDay));
            treatedData.set(21, monthToAlpha(marMonth));
            treatedData.set(22, yearVerify(marYear));
        }
        String sourceFile = filePath.getName();
        String recordGroup = this.createRecordGroup(sourceFile);
        treatedData.set(39, recordGroup);
        PPOFNLDataWriter myQuery = new PPOFNLDataWriter(treatedData);
    }

    private String treatNames(String surname, String givenName) {

        String prName;

        if (surname != null) {
        } else {
            surname = "";
        }
        if (givenName != null) {
        } else {
            givenName = "";
        }
        String prNameA = givenName + " " + surname;
        prName = prNameA.trim();

        return prName;
    }

    private String treatEventPlace(String cemeteryCity, String cemeteryCounty, String cemeteryState, String cemeteryCountry) {

        if (cemeteryCity != null && cemeteryCity.length() > 0) {
            cemeteryCity = cemeteryCity + ", ";
        } else {
            cemeteryCity = "";
        }
        if (cemeteryCounty != null && cemeteryCounty.length() > 0) {
            cemeteryCounty = cemeteryCounty + ", ";
        } else {
            cemeteryCounty = "";
        }
        if (cemeteryState != null && cemeteryState.length() > 0) {
            cemeteryState = cemeteryState + ", ";
        } else {
            cemeteryState = "";
        }
        if (cemeteryCountry != null && cemeteryCountry.length() > 0) {
        } else {
            cemeteryCountry = "";
        }

        return cemeteryCity + cemeteryCounty + cemeteryState + cemeteryCountry;
    }

    private String createRecordGroup(String fileName) {
        String recordGroup = systemTimeStamp + ";" + fileName;
        return recordGroup;
    }

    private String treatDeathDate(String deathDay, String deathMonth, String deathYear) {

        dayPadded = dayToDayPadded(deathDay);
        monthAlpha = monthToAlpha(deathMonth);
        yearVerified = yearVerify(deathYear);

        return concatenateDate(dayPadded, monthAlpha, yearVerified);
    }

    private String treatBirthDate(String birthDay, String birthMonth, String birthYear) {

        dayPadded = dayToDayPadded(birthDay);
        monthAlpha = monthToAlpha(birthMonth);
        yearVerified = yearVerify(birthYear);

        return concatenateDate(dayPadded, monthAlpha, yearVerified);
    }

    private String treatMarriageDate(String marriageDay, String marriageMonth, String marriageYear) {

        dayPadded = dayToDayPadded(marriageDay);
        monthAlpha = monthToAlpha(marriageMonth);
        yearVerified = yearVerify(marriageYear);

        return concatenateDate(dayPadded, monthAlpha, yearVerified);
    }

    public String dayToDayPadded(String rawDay) {

        if (rawDay == null) {
            return null;
        }
//verify day is within the range of 1 to 31 and pad the value to length of 2
        if (Integer.parseInt(rawDay) > 0 && Integer.parseInt(rawDay) < 32 && rawDay.length() == 2) {
            return rawDay;
        }
        if (Integer.parseInt(rawDay) > 0 && Integer.parseInt(rawDay) < 32 && rawDay.length() == 1) {
            return "0" + rawDay;
        }
        return null;
    }

    private String yearVerify(String rawYear) {
        if (rawYear == null) {
            return null;
        }
        if (rawYear.length() == 4) {
            return rawYear;
        }
        return null;
    }

    private String monthToAlpha(String numericMonth) {
        if (numericMonth == null) {
            return null;
        }
        int monthNumber = Integer.parseInt(numericMonth);
        if (monthNumber < 13) {
            String month = new DateFormatSymbols().getMonths()[monthNumber - 1].substring(0, 3);
            return month;
        }
        return null;
    }

    public String concatenateDate(String day, String month, String year) {

        if (day == null && month == null && year == null) {
            return null;
        }
        if (day != null && month != null && year != null) {
            return day + " " + month + " " + year;
        }
        if (month == null && year != null) {
            return year;
        }
        if (day == null && month != null && year != null) {
            return month + " " + year;
        }
        return null;
    }
}
