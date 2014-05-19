/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BillionGraves;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author DartRA
 */
public class TreatBurialRecord {

    private static final String TAB_DELIMITER = "\\t";

    private final String recId;
    private final String url;
    private final String famNames;
    private final String gnNames;
    private final String mdnNames;
    private final String pre;
    private final String suf;
    private final String birYear;
    private final String birDay;
    private final String birMonth;
    private final String deaYear;
    private final String deaDay;
    private final String deaMonth;
    private final String marMonth;
    private final String marYear;
    private final String marDay;
    private final String createTimestamp;
    private final String updateTimestamp;
    private final String cemName;
    private final String cemCity;
    private final String cemState;
    private final String cemCounty;
    private final String cemCountry;
    private final String cemLatitude;
    private final String cemLongitude;
    private final String thumb;
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

        String[] recordArray = currentLine.split(TAB_DELIMITER);

        // Clean out the nulls before setting properties
        for (int i = 0; i < recordArray.length; i++) {
            String token = recordArray[i];
            if (token != null && token.trim().equals("NULL")) {
                recordArray[i] = null;
            }
        }
        // Set null date values to "0" to avoid null pointer exceptions
        for (int i = 7; i < 16; i++) {
            if (recordArray[i] == null) {
                recordArray[i] = "0";
            }
        }
        this.recId = recordArray[0];
        this.url = recordArray[1];
        this.famNames = recordArray[2];
        this.gnNames = recordArray[3];
        this.mdnNames = recordArray[4];
        this.pre = recordArray[5];
        this.suf = recordArray[6];
        this.birYear = recordArray[7];
        this.birDay = recordArray[8];
        this.birMonth = recordArray[9];
        this.deaYear = recordArray[10];
        this.deaDay = recordArray[11];
        this.deaMonth = recordArray[12];
        this.marMonth = recordArray[13];
        this.marYear = recordArray[14];
        this.marDay = recordArray[15];
        this.createTimestamp = recordArray[16];
        this.updateTimestamp = recordArray[17];
        this.cemName = recordArray[18];
        this.cemCity = recordArray[19];
        this.cemState = recordArray[20];
        this.cemCounty = recordArray[21];
        this.cemCountry = recordArray[22];
        this.cemLatitude = recordArray[23];
        this.cemLongitude = recordArray[24];
        this.thumb = recordArray[25];

        this.systemTimeStamp = timeStamp;

        String prName = this.treatNames(famNames, gnNames);
        System.out.println("\r");
        System.out.println("This is the PR_NAME " + prName);

        String eventPlace = this.treatEventPlace(cemCity, cemCounty, cemState, cemCountry);
        //System.out.println("This is the EVENT_PLACE " + eventPlace);

        String prDeathDate = this.treatDeathDate(deaDay, deaMonth, deaYear);
        if (prDeathDate != null) {
            System.out.println("This is the PR_DEATH_DATE " + prDeathDate);
        }
        String prBirthDate = this.treatBirthDate(birDay, birMonth, birYear);
        if (prBirthDate != null) {
            System.out.println("This is the PR_BIRTH_DATE " + prBirthDate);
        }
        String prMarriageDate = this.treatMarriageDate(marDay, marMonth, marYear);
        if (prMarriageDate != null) {
            System.out.println("This is the PR_BIRTH_DATE " + prMarriageDate);
        }

        String sourceFile = filePath.getName();
        String recordGroup = this.createRecordGroup(sourceFile);
        //System.out.println("This is the record group " + recordGroup);
    }

    private String treatNames(String surname, String givenName) {

        String prName = null;
        if (gnNames != null || famNames != null) {

            String prNameA = gnNames + " " + famNames;
            prName = prNameA.trim();
        }
        return prName;

    }

    private String treatEventPlace(String cemeteryCity, String cemeteryCounty, String cemeteryState, String cemeteryCountry) {

        String eventPlaceB = null;
        String eventPlace = null;

        String eventPlaceA = cemCity + ", " + cemCounty + ", " + cemState + ", " + cemCountry;

        if (eventPlaceA.length() >= 4 && eventPlaceA.contains(", , ")) {
            eventPlaceB = eventPlaceA.replace(", , ", ", ");
        } else {
            eventPlaceB = eventPlaceA;
        }
        if (eventPlaceB.length() >= 2 && eventPlaceB.substring(0, 1) == ", ") {
            eventPlace = eventPlaceB.substring(2);
        } else {
            eventPlace = eventPlaceB;
        }
        return eventPlace;
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

    public String concatenateDate(String day, String month, String year) {
        String dateConcatenated;

        if (day != "0" && month != "0" && year != "0") {
            dateConcatenated = day + " " + month + " " + year;
        } else if (month == "0" && year != "0") {
            dateConcatenated = year;
        } else if (day == "0" && month != "0" && year != "0") {
            dateConcatenated = month + " " + year;
        } else {
            dateConcatenated = null;
        }
        return dateConcatenated;
    }

    public String dayToDayPadded(String rawDay) {
        if(rawDay == null) {
            return null;
        }
        String paddedDay = null;
//verify day is within the range of 1 to 31 and pad the value to length of 2
        if (rawDay != "0") {
            int dayInt = Integer.parseInt(rawDay);

            if (dayInt > 1 && dayInt < 32) {
                String strDay = Integer.toString(dayInt);
                if (strDay.length() == 1) {
                    paddedDay = "0" + strDay;
                }
            } else {
                paddedDay = "0";
            }
        }
        return paddedDay;
    }

    private String yearVerify(String rawYear) {
        String year = null;        
            if (rawYear.length() == 4) {
                year = rawYear;
            } else {
                year = "0";
            }        
        return year;
    }

    private String monthToAlpha(String numericMonth) {
        String month;
        switch (numericMonth) {
            case "1":
            case "01":
                month = "Jan";
                break;
            case "2":
            case "02":
                month = "Feb";
                break;
            case "3":
            case "03":
                month = "Mar";
                break;
            case "4":
            case "04":
                month = "Apr";
                break;
            case "5":
            case "05":
                month = "May";
                break;
            case "6":
            case "06":
                month = "Jun";
                break;
            case "7":
            case "07":
                month = "Jul";
                break;
            case "8":
            case "08":
                month = "Aug";
                break;
            case "9":
            case "09":
                month = "Sep";
                break;
            case "10":
                month = "Oct";
                break;
            case "11":
                month = "Nov";
                break;
            case "12":
                month = "Dec";
                break;
            default:
                month = "0";
                break;
        }
        return month;
    }

}
