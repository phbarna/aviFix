import java.io.*;
import  java.util.regex.*;

public class Convert {
   
    public void execute(String fullPath) throws IOException {
        //D:/media backup/series/Starsky & Hutch/Season 4/S04 E01 Discomania.avi
        int lastIndexOf = fullPath.lastIndexOf("/");
        
        String path = fullPath.substring(0, lastIndexOf+1);

        String mp4File = path+fullPath.substring(lastIndexOf+1, fullPath.length()-4)+".mp4";
        System.out.println(mp4File);
     
        ShellExec exec = new ShellExec(true, false);
        exec.execute("ffmpeg ", null, true,
           "-i", fullPath, "-c:v", "libx264", "-preset", "slow", "-crf", "20", "-c:a", "aac", "-b:a", "128k", mp4File
        );
    }

   
    public static void main(String[] args) throws IOException {

            Convert c = new Convert();
            c.execute("D:/media backup/series/Starsky & Hutch/Season 4/S04 E01 Discomania.avi");
      
   } 
}
