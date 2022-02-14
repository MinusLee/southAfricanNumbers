import sanchecker.SouthAfricanNumbersFileParser;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        SouthAfricanNumbersFileParser southAfricanNumber = new SouthAfricanNumbersFileParser(
                new File(new File("").getAbsolutePath() +
                        "/inputnumbers/South_African_Mobile_Numbers.csv"));

        southAfricanNumber.generateFiles();

    }

}
