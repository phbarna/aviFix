import java.io.File;
import java.nio.file.*;
import java.text.ParseException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.*;

public class AviFixer {
    private static HashSet<String> hs = new HashSet<>();
    public static void main(String... args) {
        File[] files = new File("D:/media backup").listFiles();
        if (files != null)
            try {
                getFiles(files);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(hs.size());
            for (String s : hs) {
                System.out.println(s);
            }
    }

    public static boolean checkForDivX(File file) throws IOException {

        // getBytes from anyWhere
        // I'm getting byte array from File
        FileInputStream fileStream = null;
        try {
            fileStream = new FileInputStream(file);

            // Instantiate array
            byte[] arr = new byte[(int) 120];

            // read All bytes of File stream
            fileStream.read(arr, 0, arr.length);

            String format = new String(arr).substring(112, 116);
        
            if (format.equalsIgnoreCase("divx") || format.equalsIgnoreCase("xvid")) {
                return true;
            }
            fileStream.close();
            return false;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            fileStream.close();
        }
        fileStream.close();
        return false;
    }

    public static void getFiles(File[] files) {
    try {
        if (files == null) {
            return;
        }
        for (File file : files) {

            if (file.isDirectory()) {
                getFiles(file.listFiles());
            } else {
                if (file.getName().endsWith("avi")) {
                    if (checkForDivX(file)) {

                        String s = file.getAbsolutePath();
                        int index = s.lastIndexOf("\\");
                        String folder = s.substring(0, index);
                        hs.add(folder);

                    }
                }
            }
        }

    } catch (Exception ex) {
        ex.printStackTrace();
    }


    }
}