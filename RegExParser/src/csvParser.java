import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;


//Max Prakken
public class csvParser {

    ArrayList<String> lines = new ArrayList<String>();
    String type = "";

    public void convertToCSVLine(ArrayList<String> data){
        String s = new String();
        for(int i = 0; i < data.size(); i++){
            
            if(i < data.size() - 2) {
                s+=data.get(i) + ",";
            }else if (i < data.size() - 1){
                s+=data.get(i);
            }
            else if(i < data.size() - 1) { 
                s+=data.get(i);
            }else {
                s+= ";" + data.get(i);
            }
        }

        if(s != "")
	        lines.add(s);
        else
            System.out.println("data was not parsed successfully");
    }

    public void createCSV(String type) throws IOException {
        if(lines.size() > 0) {
            writeToFile();
        }else {
            System.out.println("no lines are set up for parsing. Call convertToCVSLine(String s) first");
        }
    }

    // Max Prakken
    private void writeToFile() throws IOException{

        String nameFile = "";
        FileWriter writer = null;
        File myfile = null;

        for(int i = 0; i < lines.size(); i++) {
            String type = lines.get(i).substring(lines.get(i).lastIndexOf(";") + 1);

            if(!type.equals(this.type)) {
                if(writer != null)
                    writer.close();

                nameFile = type + ".csv";
                myfile = new File(nameFile);
                writer = new FileWriter(myfile);

                if(myfile.createNewFile()) {
                    System.out.println("File created: " + myfile.getName());
                }else {
                    System.out.println("File " + nameFile + " does already exist");
                }

                this.type = type; // set type

                String headerline = "";
                switch(this.type) {
                    case "movie" :
                        headerline = "TITEL, JAAR, DEEL, SUSPENDED, PLATFORM";
                    break;
                    case "episode" :
                        headerline = "SERIE, JAAR, EPISODE";
                    break;
                    case "series" : 
                        headerline = "SERIE, JAAR_START, JAAR_START, JAAR_EINDIG";
                    break;
                }
                writer.write(headerline + "\n");
                System.out.println("HEADER PRINTED SUCCESSFULLY");
            }

            if(type.length() > 0 && writer != null) {
                try {
                    //start writing in file here
                    String newLine = lines.get(i).substring(0, lines.get(i).indexOf(";")) + "\n";
                    writer.write(newLine);
                    
                    System.out.println("Wrote to file successfully");

                }catch(IOException e) {
                    System.out.println("error took place: ");
                    e.printStackTrace();
                }
            }
            
            System.out.println(lines.get(i));
        }
        if(writer != null)
        writer.close();
    }
}