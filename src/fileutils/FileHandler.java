package fileutils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FileHandler {

    private final File f;
    private final String path;

    public static FileHandler createFileHandler(File f) {
        return new FileHandler(f);
    }

    public static FileHandler createFileHandlerForNewFile(String path) {
        return new FileHandler(path);
    }

    @Override
    public String toString() {
        return "FileHandler{" +
                "path='" + path + '\'' +
                '}';
    }

    public void rewriteFile(char separator, String... strings) {
        try {
            FileWriter fw = new FileWriter(f);

            for (String s: strings)
                fw.write(s + separator);

            fw.close();

        } catch (IOException e) {
            throw new IllegalArgumentException("File can not be written");
        }
    }

    public List<String> createStringListFromFile(char separator) {
        Collection<Character> characterCollection = new ArrayList<>();

        try {
            FileReader fr = new FileReader(f);
            boolean stop = false;

            do {
                int intRead = fr.read();
                char charRead = (char) intRead;

                if (intRead != -1)
                    characterCollection.add(charRead);
                else
                    stop = true;

            } while (!stop);

            fr.close();

        } catch (IOException e) {
            throw new IllegalArgumentException("File can not be read");
        }

        return generateStringList(separator, characterCollection);
    }

    private FileHandler(File f) {
        if (f == null || !f.exists())
            throw new IllegalArgumentException("Invalid file");

        this.f = f;
        path = f.getAbsolutePath();
    }

    private FileHandler(String path) {
        this.path = path;
        f = create();
    }

    private List<String> generateStringList(char separator, Collection<Character> characterCollection) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> stringList = new ArrayList<>();

        for (char c: characterCollection) {
            if (c != separator)
                stringBuilder.append(c);
            else {
                stringList.add(stringBuilder.toString());
                stringBuilder.delete(0, stringBuilder.toString().length());
            }
        }

        if (stringBuilder.length() != 0)
            stringList.add(stringBuilder.toString());

        return stringList;
    }

    private File create() {
        File f = new File(path);

        try {
            if (!f.createNewFile())
                throw new IllegalArgumentException("File already exists");
        } catch (IOException e) {
            throw new IllegalArgumentException("File can not be created");
        }

        return f;
    }

}