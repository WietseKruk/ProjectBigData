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
                s+=data.get(i) + "|"; 
            }else if(i < data.size() - 1) { 
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

    public void setLines(ArrayList<String> data){
        lines = data;
    }

    // Max Prakken
    public void createCSV() throws IOException{

        if(lines.size() > 0) {
            String nameFile = "";
            FileWriter writer = null;
            File myfile = null;

            for(int i = 0; i < lines.size(); i++) {
                String type = lines.get(i).substring(lines.get(i).lastIndexOf(";") + 1);

                if(!type.equals(this.type)) {
                    if(writer != null)
                        writer.close();

                    String url = "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/";
                    nameFile = url + type + ".csv";
                    myfile = new File(nameFile);
                    writer = new FileWriter(myfile, true);

                    if(myfile.createNewFile()) {
                        System.out.println("File created: " + myfile.getName());
                    }else {
                        //System.out.println("File " + nameFile + " does already exist");
                    }

                    this.type = type; // set type

                    String headerline = "";
                    if(myfile.length() < 1){
                        selectHeader(this.type);
                    
                    writer.write(headerline + "\n");
                    }
                    //System.out.println("HEADER PRINTED SUCCESSFULLY");
                }

                //write to file
                if(type.length() > 0 && writer != null) {
                    try {
                        String newLine = "";

                        if(this.type.equals("series")) {
                            newLine = lines.get(i).substring(0, lines.get(i).indexOf(";")) + "| |0\n";
                        }
                        else {
                            newLine = lines.get(i).substring(0, lines.get(i).indexOf(";")) + "\n";
                        }

                        //start writing in file here
                        writer.write(newLine);
                        
                        //System.out.println("Wrote to file successfully");

                    }catch(IOException e) {
                        System.out.println("error took place: ");
                        e.printStackTrace();
                    }
                }
                
                //System.out.println(lines.get(i));
            }
            if(writer != null)
            writer.close();
        }else {
            System.out.println("no lines are set up for parsing. Call convertToCVSLine(String s) first");
        }
    }

    private String selectHeader(String type) {
        switch(this.type) {
            case "movie" :
                    return "TITEL|JAAR|DEEL|SUSPENDED|PLATFORM";
                
            case "episode" :
                    return "SERIE|JAAR_SERIE|EPISODE|JAAR_UITGEZONDEN";
                
            case "series" : 
                    return "SERIE|SUSPENDED|JAAR_START|JAAR_EIND";
                
            case "actormovie":
                    return "";
                
            case "location":
                    return "TITEL|JAAR|DEEL|PLATFORM|LOCATIE";
                
            case "genre":
                    return "TITEL|JAAR|DEEL|PLATFORM|AFLEVERING_TITEL|SUSPENDED|GENRE";
                
            case "movieruntime":
                    return "TITEL|JAAR|DEEL|PLATFORM|SUSPENDED|LOCATIE|TIJD|NOTITIES";
                
            case "episoderuntime":
                    return "TITEL|JAAR|DEEL|PLATFORM|AFLEVERING_TITEL|SUSPENDED|LOCATIE|TIJD|NOTITIES";
                
            case "seriesruntime":
                    return "TITEL|JAAR|DEEL|PLATFORM|SUSPENDED|LOCATIE|TIJD|NOTITIES";
                
            case "soundtrackmovies":
                    return "TITEL|JAAR|DEEL|PLATFORM|SUSPENDED|NUMMER_TITEL";
                
            case "soundtrackseries":
                    return "TITEL|JAAR|EPISODE|SUSPENDED|NUMMER_TITEL";
                
            case "actorwithmovietitle":
                    return "";
                
            case "actorwitseriestitle":
                    return "";
            
            default:
                    return "";
        }
    }
}