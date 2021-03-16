import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ReadFile {
    public void FileReader(){
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader("E:/Docs/School/P3/PBigData/ProjectBigData/RegExParser/src/movies.list"));
            String line = reader.readLine();
            while(line != null){
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    } 
}
