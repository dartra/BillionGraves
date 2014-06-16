/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BillionGraves;

import java.io.File;
import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author DartRA
 */
public class RecordDataFields extends TextFileReader {

    private final SimpleStringProperty recordId = new SimpleStringProperty();
    private final SimpleStringProperty url = new SimpleStringProperty();
    private final SimpleStringProperty familyNames = new SimpleStringProperty();
    private final SimpleStringProperty givenNames = new SimpleStringProperty();
    private final SimpleStringProperty maidenNames = new SimpleStringProperty();
    private final SimpleStringProperty prefix = new SimpleStringProperty();
    private final SimpleStringProperty suffix = new SimpleStringProperty();
    private final SimpleStringProperty birthYear = new SimpleStringProperty();
    private final SimpleStringProperty birthDay = new SimpleStringProperty();
    private final SimpleStringProperty birthMonth = new SimpleStringProperty();
    private final SimpleStringProperty deathYear = new SimpleStringProperty();
    private final SimpleStringProperty deathDay = new SimpleStringProperty();
    private final SimpleStringProperty deathMonth = new SimpleStringProperty();
    private final SimpleStringProperty marriageMonth = new SimpleStringProperty();
    private final SimpleStringProperty marriageYear = new SimpleStringProperty();
    private final SimpleStringProperty marriageDay = new SimpleStringProperty();
    private final SimpleStringProperty createdTimestamp = new SimpleStringProperty();
    private final SimpleStringProperty updatedTimestamp = new SimpleStringProperty();
    private final SimpleStringProperty cemeteryName = new SimpleStringProperty();
    private final SimpleStringProperty cemeteryCity = new SimpleStringProperty();
    private final SimpleStringProperty cemeteryState = new SimpleStringProperty();
    private final SimpleStringProperty cemeteryCounty = new SimpleStringProperty();
    private final SimpleStringProperty cemeteryCountry = new SimpleStringProperty();
    private final SimpleStringProperty cemeteryLatitude = new SimpleStringProperty();
    private final SimpleStringProperty cemeteryLongitude = new SimpleStringProperty();
    private final SimpleStringProperty thumbnail = new SimpleStringProperty();

    public RecordDataFields(File filePath, int rowCount) throws IOException {
        super(filePath, rowCount);

        Object[] rowData = super.getData().toArray();

        this.recordId.setValue(rowData[0].toString());
        this.url.setValue(rowData[1].toString());
        this.familyNames.setValue(rowData[2].toString());
        this.givenNames.setValue(rowData[3].toString());
        this.maidenNames.setValue(rowData[4].toString());
        this.prefix.setValue(rowData[5].toString());
        this.suffix.setValue(rowData[6].toString());
        this.birthYear.setValue(rowData[7].toString());
        this.birthDay.setValue(rowData[8].toString());
        this.birthMonth.setValue(rowData[9].toString());
        this.deathYear.setValue(rowData[10].toString());
        this.deathDay.setValue(rowData[11].toString());
        this.deathMonth.setValue(rowData[12].toString());
        this.marriageMonth.setValue(rowData[13].toString());
        this.marriageYear.setValue(rowData[14].toString());
        this.marriageDay.setValue(rowData[15].toString());
        this.createdTimestamp.setValue(rowData[16].toString());
        this.updatedTimestamp.setValue(rowData[17].toString());
        this.cemeteryName.setValue(rowData[18].toString());
        this.cemeteryCity.setValue(rowData[19].toString());
        this.cemeteryState.setValue(rowData[20].toString());
        this.cemeteryCounty.setValue(rowData[21].toString());
        this.cemeteryCountry.setValue(rowData[22].toString());
        this.cemeteryLatitude.setValue(rowData[23].toString());
        this.cemeteryLongitude.setValue(rowData[24].toString());
        this.thumbnail.setValue(rowData[25].toString());
    }

    public String getRecordId() {
        return this.recordId.get();
    }

    public final void setRecordId(String recordId) {
        this.recordId.set(recordId);
    }

    public StringProperty recordIdProperty() {
        return recordId;
    }

    public String geturl() {
        return this.url.get();
    }

    public final void seturl(String url) {
        this.recordId.set(url);
    }

    public StringProperty urlProperty() {
        return url;
    }

    public String getfamilyNames() {
        return this.familyNames.get();
    }

    public final void setfamilyNames(String familyNames) {
        this.familyNames.set(familyNames);
    }

    public StringProperty familyNamesProperty() {
        return familyNames;
    }

    public String getgivenNames() {
        return this.givenNames.get();
    }

    public final void setgivenNames(String givenNames) {
        this.givenNames.set(givenNames);
    }

    public StringProperty givenNamesProperty() {
        return givenNames;
    }

    public String getmaidenNames() {
        return this.maidenNames.get();
    }

    public final void setmaidenNames(String maidenNames) {
        this.maidenNames.set(maidenNames);
    }

    public StringProperty maidenNamesProperty() {
        return maidenNames;
    }

    public String getprefix() {
        return this.prefix.get();
    }

    public final void setprefix(String prefix) {
        this.prefix.set(prefix);
    }

    public StringProperty prefixProperty() {
        return prefix;
    }

    public String getsuffix() {
        return this.suffix.get();
    }

    public final void setsuffix(String suffix) {
        this.suffix.set(suffix);
    }

    public StringProperty suffixProperty() {
        return suffix;
    }

    public String getbirthYear() {
        return this.birthYear.get();
    }

    public final void setbirthYear(String birthYear) {
        this.birthYear.set(birthYear);
    }

    public StringProperty birthYearProperty() {
        return birthYear;
    }

    public String getbirthDay() {
        return this.birthDay.get();
    }

    public final void setbirthDay(String birthDay) {
        this.birthDay.set(birthDay);
    }

    public StringProperty birthDayProperty() {
        return birthDay;
    }

    public String getbirthMonth() {
        return this.birthMonth.get();
    }

    public final void setbirthMonth(String birthMonth) {
        this.birthMonth.set(birthMonth);
    }

    public StringProperty birthMonthProperty() {
        return birthMonth;
    }

    public String getdeathYear() {
        return this.deathYear.get();
    }

    public final void setdeathYear(String deathYear) {
        this.deathYear.set(deathYear);
    }

    public StringProperty deathYearProperty() {
        return deathYear;
    }

    public String getdeathDay() {
        return this.deathDay.get();
    }

    public final void setdeathDay(String deathDay) {
        this.deathDay.set(deathDay);
    }

    public StringProperty deathDayProperty() {
        return deathDay;
    }

    public String getdeathMonth() {
        return this.deathMonth.get();
    }

    public final void setdeathMonth(String deathMonth) {
        this.deathMonth.set(deathMonth);
    }

    public StringProperty deathMonthProperty() {
        return deathMonth;
    }

    public String getmarriageMonth() {
        return this.marriageMonth.get();
    }

    public final void setmarriageMonth(String marriageMonth) {
        this.marriageMonth.set(marriageMonth);
    }

    public StringProperty marriageMonthProperty() {
        return marriageMonth;
    }

    public String getmarriageYear() {
        return this.marriageYear.get();
    }

    public final void setmarriageYear(String marriageYear) {
        this.marriageYear.set(marriageYear);
    }

    public StringProperty marriageYearProperty() {
        return marriageYear;
    }

    public String getmarriageDay() {
        return this.marriageDay.get();
    }

    public final void setmarriageDay(String marriageDay) {
        this.marriageDay.set(marriageDay);
    }

    public StringProperty marriageDayProperty() {
        return marriageDay;
    }

    public String getcreatedTimestamp() {
        return this.createdTimestamp.get();
    }

    public final void setcreatedTimestamp(String createdTimestamp) {
        this.createdTimestamp.set(createdTimestamp);
    }

    public StringProperty createdTimestampProperty() {
        return createdTimestamp;
    }

    public String getupdatedTimestamp() {
        return this.updatedTimestamp.get();
    }

    public final void setupdatedTimestamp(String updatedTimestamp) {
        this.updatedTimestamp.set(updatedTimestamp);
    }

    public StringProperty updatedTimestampProperty() {
        return updatedTimestamp;
    }

    public String getcemeteryName() {
        return this.cemeteryName.get();
    }

    public final void setcemeteryName(String cemeteryName) {
        this.cemeteryName.set(cemeteryName);
    }

    public StringProperty cemeteryNameProperty() {
        return cemeteryName;
    }

    public String getcemeteryCity() {
        return this.cemeteryCity.get();
    }

    public final void setcemeteryCity(String cemeteryCity) {
        this.cemeteryCity.set(cemeteryCity);
    }

    public StringProperty cemeteryCityProperty() {
        return cemeteryCity;
    }

    public String getcemeteryState() {
        return this.cemeteryState.get();
    }

    public final void setcemeteryState(String cemeteryState) {
        this.cemeteryState.set(cemeteryState);
    }

    public StringProperty cemeteryStateProperty() {
        return cemeteryState;
    }

    public String getcemeteryCounty() {
        return this.cemeteryCounty.get();
    }

    public final void setcemeteryCounty(String cemeteryCounty) {
        this.cemeteryCounty.set(cemeteryCounty);
    }

    public StringProperty cemeteryCountyProperty() {
        return cemeteryCounty;
    }

    public String getcemeteryCountry() {
        return this.cemeteryCountry.get();
    }

    public final void setcemeteryCountry(String cemeteryCountry) {
        this.cemeteryCountry.set(cemeteryCountry);
    }

    public StringProperty cemeteryCountryProperty() {
        return cemeteryCountry;
    }

    public String getcemeteryLatitude() {
        return this.cemeteryLatitude.get();
    }

    public final void setcemeteryLatitude(String cemeteryLatitude) {
        this.cemeteryLatitude.set(cemeteryLatitude);
    }

    public StringProperty cemeteryLatitudeProperty() {
        return cemeteryLatitude;
    }

    public String cemeteryLongitude() {
        return this.cemeteryLongitude.get();
    }

    public final void setcemeteryLongitude(String cemeteryLongitude) {
        this.cemeteryLongitude.set(cemeteryLongitude);
    }

    public StringProperty cemeteryLongitudeProperty() {
        return cemeteryLongitude;
    }

    public String getthumbnail() {
        return this.thumbnail.get();
    }

    public final void setthumbnail(String thumbnail) {
        this.thumbnail.set(thumbnail);
    }

    public StringProperty thumbnailProperty() {
        return thumbnail;
    }
}
