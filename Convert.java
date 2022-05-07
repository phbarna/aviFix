import java.io.*;

public class Convert {
   

   
    public static void main(String[] args) throws IOException {

        //ffmpeg -i "%%a" -c:v libx264 -preset slow -crf 20 -c:a aac -b:a 128k "newfiles\%%~na.mp4
        ShellExec exec = new ShellExec(true, false);
        exec.execute("ffmpeg", null, true,
           "-i", "D:/media backup/series/Starsky & Hutch/Season 4/S04 E01 Discomania.avi", "-c:v", "libx264", "-preset", "slow", "-crf", "20", "-c:a", "aac", "-b:a", "128k", "newfiles/out.mp4"
        );
        System.out.println(exec.getOutput());
      
   } 
}
