package utils;

import java.time.Instant;
import java.util.Date;
import java.util.regex.Pattern;

public class Signature {

    private final String signature;

    private String hourMinuteSecond;

    private String year;
    private String month;
    private String day;

    public Signature(String signature) {
        this.signature = signature;
        setSignatureRecords();
    }

    @Override
    public String toString() {
        return year + "::" + month + "::" + day + "::" + hourMinuteSecond + "::" + signature;
    }

    private void setSignatureRecords() {
        String dateStamp = Date.from(Instant.now()).toString();
        setYear(dateStamp);
        setMonth(dateStamp);
        setDay(dateStamp);
        setHourMinuteSecond(dateStamp);
    }

    private void setHourMinuteSecond(String dateStamp) {
        hourMinuteSecond = dateStamp.split(Pattern.quote(" "))[3];
    }

    private void setDay(String dateStamp) {
        day = dateStamp.split(Pattern.quote(" "))[2];
    }

    private void setMonth(String dateStamp) {
        month = dateStamp.split(Pattern.quote(" "))[1];
    }

    private void setYear(String dateStamp) {
        year = dateStamp.split(Pattern.quote(" "))[5];
    }

}

