import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;


//Max Prakken
public class csvParser {
    ArrayList<String> lines = new ArrayList<String>();

    public void convertToCSVLine(ArrayList<String> data){
        String s = new String();
        for(int i = 0; i < data.size(); i++){
            s+=data.get(i);
            if(i != data.size() - 1) 
                s += ", ";
        }

        if(s != "")
	        lines.add(s);
        else
            System.out.println("data was not parsed successfully");
    }

    public void createCSV(String name) throws IOException {
        if(lines.size() > 0) {
            String nameR = name + ".csv";
            try {
                File myfile = new File(nameR);
                if(myfile.createNewFile()) {
                    System.out.println("File created: " + myfile.getName());

                    //start writing in file here
                    FileWriter writer = new FileWriter(nameR);
                    for(int i = 0; i < lines.size(); i++) {
                        String newLine = lines.get(i) + "\n";

                        writer.write(newLine);
                        System.out.println(lines.get(i));
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