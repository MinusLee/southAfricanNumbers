import sanchecker.SouthAfricanNumber;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        SouthAfricanNumber southAfricanNumber = new SouthAfricanNumber(
                new File(new File("").getAbsolutePath() +
                        "/inputnumbers/South_African_Mobile_Numbers.csv"));

        southAfricanNumber.generateFiles();

    }

}
