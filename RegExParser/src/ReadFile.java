import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ReadFile {
    private Parser parser = new Parser();

    public void FileReader(String filename){
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader("src/" + filename));
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
