package sanchecker;

import fileutils.FileHandler;
import utils.Pair;
import utils.Signature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SouthAfricanNumbersFileParser {

    private static List<SouthAfricanNumberHandler> generateCheckers(File numbersFile) {
        FileHandler fileHandler = FileHandler.createFileHandler(numbersFile);

        List<String> numbers = fileHandler.createStringListFromFile('\r');
        numbers.remove(0);

        return numbers.
                stream().
                map(s -> {
                    String[] idNumberPair = s.split(Pattern.quote(","));
                    return new SouthAfricanNumberHandler(idNumberPair[1], idNumberPair[0]);
                }).
                collect(Collectors.toList());
    }

    private static void createFile(String fileName,
                                   List<? extends Pair<?, ?>> pairIdNumber,
                                   Function<Pair<?, ?>, String> f) {

        FileHandler fileHandler = FileHandler.
                createFileHandlerForNewFile(
                        new File("").getAbsolutePath() + "/outputnumbers/" + fileName);

        fileHandler.rewriteFile('\n', pairIdNumber.stream().map(f).toArray(String[]::new));

    }

    private final  List<SouthAfricanNumberHandler> sanCheckers;

    public SouthAfricanNumbersFileParser(File numbersFile) {
        this.sanCheckers = SouthAfricanNumbersFileParser.generateCheckers(numbersFile);
    }

    public void generateFiles() {
        List<Pair<String, String>> correctNumbers = getCorrect();
        List<Pair<Pair<String, String>, String>> modifiedNumbers = getModified();
        List<Pair<String, String>> wrongNumbers = getWrong();

        try {
            if (!Files.exists(Path.of(new File("").getAbsolutePath() + "/outputnumbers")))
                Files.createDirectory(Path.of(new File("").getAbsolutePath() + "/outputnumbers"));
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new RuntimeException("Directory creation failed");
        }

        createFile(new Signature("correct.txt").toString(),
                correctNumbers, pair -> "numberId::" + pair.first + "::number::" + pair.second);

        createFile(new Signature("wrong.txt").toString(),
                wrongNumbers, pair -> "numberId::" + pair.first + "::wrongNumber::" + pair.second);

        createFile(new Signature("modified.txt").toString(), modifiedNumbers,
                pair -> "numberId&originalNumber::" + pair.first + "::modifiedNumber::" + pair.second);

    }

    private List<Pair<String, String>> getWrong() {
        return sanCheckers.
                stream().
                filter(numberHandler -> !numberHandler.isWellFormed() && !numberHandler.correctable()).
                map(numberHandler -> new Pair<>(numberHandler.getId(), numberHandler.getNumber())).
                collect(Collectors.toList());
    }

    private List<Pair<Pair<String, String>, String>> getModified() {
        return this.sanCheckers.
                stream().
                filter(SouthAfricanNumberHandler::correctable).
                map(numberHandler ->
                        new Pair<>(new Pair<>(numberHandler.getId(), numberHandler.getNumber()),
                                numberHandler.correctNumber().getNumber())).
                collect(Collectors.toList());
    }

    private List<Pair<String, String>> getCorrect() {
        return this.sanCheckers.
                stream().
                filter(SouthAfricanNumberHandler::isWellFormed).
                map(numberHandler -> new Pair<>(numberHandler.getId(), numberHandler.getNumber())).
                collect(Collectors.toList());
    }

}
