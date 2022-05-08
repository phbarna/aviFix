import java.io.File;
import java.nio.file.*;
import java.text.ParseException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.*;

public class AviFixer {

    public static void main(String... args) {
        if (args.length == 1) {
            String arg = args[0].toLowerCase();
            if (arg.contains("h") || args.equals("info")) {
                System.out.println("This program will convert all your old DIVX or XVID to mp4 format.");
                System.out.println("It can take a while to run (depending on how many files need processing).");
                System.out.println(".... because some newer tvs won't play video encoded with divx/xvid codec.");
                System.exit(0);
            }
        }
        Scanner sc = new Scanner(System.in); // System.in is a standard input stream.
        System.out.print("Enter the full path for the root directory: ");

        String rootPath = sc.nextLine(); 
        System.out.println(rootPath);
        sc.close();

        File rootPathFile = new File(rootPath);
        if (!rootPathFile.exists()) {
            System.out.println("The path " + rootPath + " does not exist.");
            System.out.println("Example of the correct format is: D:/media backup");
            System.exit(0);
        }

        System.out.println("Processing from " + rootPath + " This could take a while.");
        System.out.println("");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        File[] files = new File(rootPath).listFiles();
        if (files != null)
            try {
                processFiles(files);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    /**
     * Checks to see if the file contains xvid or divx 
     * @param file the file to check
     * @return true if it is an xvid or divx
     * @throws IOException
     */
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
        return false;
    }

    /**
     * Processes files from the current directory - method can be recursively called as it drills down through directory structure
     * @param files - list of files and directories (called from current directory)
     */
    public static void processFiles(File[] files) {
        try {
            if (files == null) {
                return;
            }
            for (File file : files) {

                if (file.isDirectory()) {
                    processFiles(file.listFiles());
                } else {
                    if (file.getName().endsWith("avi")) {
                        if (checkForDivX(file)) {

                            String s = file.getAbsolutePath();

                            Convert c = new Convert();
                            c.execute(s);

                            File fileToDelete = new File(s);
                            if (!fileToDelete.delete()) {
                                System.err.println("Cannot delete " + s);
                            }

                        }
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}