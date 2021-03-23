import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;

//Miel
public class ReadFile {
    private Parser parser = new Parser();
    private Matcher matcher;
    
    public String type;

    public void FileReader(String filename){
        BufferedReader reader;
        try{
            File thisFile = new File(filename);

            reader = new BufferedReader(new FileReader(thisFile.getAbsolutePath()));
            
            String line = reader.readLine();
            while(line != null){
                type = parser.getParseType(line, filename);
                matcher = parser.getMatcher(line, type);
                parser.parse(matcher);
               // System.out.println("Type: " + type + "\n");

                line = reader.readLine();
            }
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    } 

    //Max Prakken
    public void FileReaderParseCSV(String filename, csvParser csvp){
        BufferedReader reader;
        try{
            File thisFile = new File(filename);

            reader = new BufferedReader(new FileReader(thisFile.getAbsolutePath()));
            
            String line = reader.readLine();
            while(line != null){
                type = parser.getParseType(line, filename);
                matcher = parser.getMatcher(line, type);
                parser.parse(matcher, csvp, filename);

                //System.out.println("Type: " + type);

                line = reader.readLine();
            }
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    } 
}
