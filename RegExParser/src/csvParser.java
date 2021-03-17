import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;

public class csvParser {
    ArrayList<String> lines = new ArrayList<String>();

    public void convertToCVSLine(String[] data){
        String s = new String();
        for(int i = 0; i < data.length; i++){
            s+=data[i];
            if(i == data.length - 1) 
                s+=", ";
        }

        if(s != "")
	        lines.add(s);
        else
            System.out.println("data was not parsed successfully");
    }

    public void createCVS(String name) throws IOException {
        if(lines.size() > 0) {
            String nameR = name + ".csv";
            try {
                File myfile = new File(nameR);
                if(myfile.createNewFile()) {
                    System.out.println("File created: " + myfile.getName());

                    //start writing in file here
                    FileWriter writer = new FileWriter(name);
                    for(int i = 0; i < lines.size(); i++) {
                        writer.write(lines.get(i) + "\n");
                    }
                    writer.close();
                    System.out.println("Wrote to file successfully");

                }
                else {
                    System.out.println("File already exist");
                }

            }catch(IOException e) {
                System.out.println("a error took place");
                e.printStackTrace();
            }
        }else {
            System.out.println("no lines are set up for parsing. Call convertToCVSLine(String s) first");
        }
    }
}