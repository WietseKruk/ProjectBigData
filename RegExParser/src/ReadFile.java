import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class ReadFile {
    private Parser parser = new Parser();

    public void FileReader(String filename){
        BufferedReader reader;
        try{
            File thisFile = new File(filename);

            reader = new BufferedReader(new FileReader(thisFile.getAbsolutePath()));
            
            String line = reader.readLine();
            while(line != null){
                //System.out.println(line);
                

                parser.parse(line, "movie");
                line = reader.readLine();
            }
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    } 
}
