import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.FileWriter;


//Max Prakken
public class csvParser {

    ArrayList<String> lines = new ArrayList<String>();
    String type = "";

    public void convertToCSVLine(ArrayList<String> data){
        String s = new String();
        for(int i = 0; i < data.size(); i++){
            
            if(i < data.size() - 1) {
                s+=data.get(i) + ",";
            }
            else { 
                s+= ";" + data.get(i);
            }
        }

        if(s != "")
	        lines.add(s);
        else
            System.out.println("data was not parsed successfully");
    }

    public void createCSV(String name, String type) throws IOException {
        if(lines.size() > 0) {
            String nameR = name + ".csv";
            try {
                File myfile = new File(nameR);
                if(myfile.createNewFile()) {
                    System.out.println("File created: " + myfile.getName());

                    //start writing in file here
                    writeToFile(nameR);
                    System.out.println("Wrote to file successfully");

                }
                else { //write to existing file. clearing it first
                    System.out.println("Written to existing file");
                    PrintWriter pWriter = new PrintWriter(nameR);
                    pWriter.print("");
                    pWriter.close();

                    writeToFile(nameR);
                    System.out.println("Wrote to existing file successfully");

                }

            }catch(IOException e) {
                System.out.println("a error took place");
                e.printStackTrace();
            }
        }else {
            System.out.println("no lines are set up for parsing. Call convertToCVSLine(String s) first");
        }
    }

    private void writeToFile(String nameR) throws IOException{
        FileWriter writer = new FileWriter(nameR);
        for(int i = 0; i < lines.size(); i++) {
            String type = lines.get(i).substring(lines.get(i).lastIndexOf(";") + 1);
            if(!type.equals(this.type)) {
                
                writer.write("=========== NEW TYPE GIVE NEW TEMPLATE HERE =========== \n");
                this.type = type; 
            }
            
            String newLine = lines.get(i).substring(0, lines.get(i).indexOf(";")) + "\n";

            writer.write(newLine);
            System.out.println(lines.get(i));
        }
        writer.close();
    }
}