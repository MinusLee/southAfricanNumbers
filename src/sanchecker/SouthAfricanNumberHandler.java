package sanchecker;

import java.util.regex.Pattern;

public class SouthAfricanNumberHandler {

    private static final String correctCountryCode = "27";
    private static final String correctLandlineAreaCode = "83";

    private final String id;

    private String countryCode = "";
    private String landlineAreaCode = "";
    private String landlineNumber = "";

    SouthAfricanNumberHandler(String number, String id) {
        this.id = id.split(Pattern.quote("\n"))[1];
        this.parseNumber(number);
    }

    boolean isWellFormed() {
        return this.countryCode.equals(SouthAfricanNumberHandler.correctCountryCode) &&
                this.landlineAreaCode.equals(SouthAfricanNumberHandler.correctLandlineAreaCode) &&
                Pattern.matches("\\d{7}", this.landlineNumber);
    }

    boolean correctable() {
        if (this.isWellFormed()) return false;
        return Pattern.matches("17|37|26|28|27", this.countryCode) &&
                Pattern.matches("73|93|82|84|83", this.landlineAreaCode) &&
                Pattern.matches("\\d{7}", this.landlineNumber);
    }

    String getNumber() {
        return this.countryCode + this.landlineAreaCode + this.landlineNumber;
    }

    String getId() {
        return id;
    }

    SouthAfricanNumberHandler correctNumber() {
        if (!correctable()) return this;
        modifyNumber();
        return this;
    }

    private void modifyNumber() {
        this.countryCode = SouthAfricanNumberHandler.correctCountryCode;
        this.landlineAreaCode = SouthAfricanNumberHandler.correctLandlineAreaCode;
    }

    private void parseNumber(String number) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < number.length(); i++) {
            stringBuilder.append(number.charAt(i));
            if (i == 1) {
                this.countryCode = stringBuilder.toString();
                stringBuilder.replace(0, stringBuilder.toString().length(), "");
            }
            else if (i == 3) {
                this.landlineAreaCode = stringBuilder.toString();
                stringBuilder.replace(0, stringBuilder.toString().length(), "");
            }
            else this.landlineNumber = stringBuilder.toString();
        }

    }

}
