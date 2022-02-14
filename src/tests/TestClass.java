package tests;

import fileutils.FileHandler;
import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;
import sanchecker.SouthAfricanNumber;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class TestClass {

    private final FileHandler fileHandler = FileHandler
            .createFileHandler(new File(new File("").getAbsolutePath()
                    + "/inputnumbers/South_African_Mobile_Numbers.csv"));

    @Test
    public void correctReading() {
        assertEquals(1001, fileHandler.createStringListFromFile('\r').size());
    }

    @Test
    @RepeatedTest(10)
    public void isFastEnough() {
        assertAll(() -> assertTimeout(Duration.ofMillis(30), () -> fileHandler.createStringListFromFile('\r')),
                () -> assertTimeout(Duration.ofMillis(80),
                        () -> new SouthAfricanNumber(new File(new File("").getAbsolutePath() +
                                                "/inputnumbers/South_African_Mobile_Numbers.csv"))),
                () -> assertTimeout(Duration.ofMillis(150),
                        () -> new SouthAfricanNumber(new File(new File("").getAbsolutePath() +
                                "/inputnumbers/South_African_Mobile_Numbers.csv")).generateFiles()));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
