import java.io.File;
import java.nio.file.*;
import java.text.ParseException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class AviFixer {

    public static void main(String... args) {
        File[] files = new File("D:/").listFiles();
        if (files != null)
            getFiles(files);
    }

    public static boolean checkForDivX(File file) {

        // getBytes from anyWhere
        // I'm getting byte array from File
        try {
            FileInputStream fileStream = new FileInputStream(file);

            // Instantiate array
            byte[] arr = new byte[(int) 150];

            // read All bytes of File stream
            fileStream.read(arr, 0, arr.length);
            StringBuffer sb = new StringBuffer();
            int count = 0;
            for (int X : arr) {

                sb.append((char) X);
                if (count == 0 && ((char) X == 'X' || (char) X == 'x')) {
                    count++;
                } else if (count == 1 && ((char) X == 'V' || (char) X == 'v')) {
                    count++;
                } else if (count == 2 && ((char) X == 'I' || (char) X == 'i')) {
                    count++;
                } else if (count == 3 && ((char) X == 'D' || (char) X == 'd')) {
                    return true;
                } else {
                    count = 0;
                }
            }
            return false;
            // System.out.print((char) X);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            fileStream.close();
        }

        return false;
    }

    public static void getFiles(File[] files) {
        if (files == null) {
            return;
        }
        for (File file : files) {

            if (file.isDirectory()) {
                getFiles(file.listFiles());
            } else {
                if (file.getName().endsWith("avi")) {
                    if (checkForDivX(file)) {
                        System.out.println(file.getAbsolutePath());

                    }
                }
            }
        }

    }
}