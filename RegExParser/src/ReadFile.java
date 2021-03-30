import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;


public class ReadFile {
    private Parser parser = new Parser();
    public SoundtrackAssembler soundtrackAssembler = new SoundtrackAssembler();
    public PersonAssembler personAssembler = new PersonAssembler();
    public csvParser csvparser = new csvParser();
    private Matcher matcher;
    
    public String type;


    //Miel
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

            //Voor de soundtracks
            //Miel
            if(filename.contains("soundtracks")){ //De info wordt nog niet toegevoegd
                ArrayList<String> soundLinesMovies = new ArrayList<String>();
                ArrayList<String> soundLinesSeries = new ArrayList<String>();

                String listType = "series";

                while(line != null){
                    type = parser.getParseType(line, filename);
                    if(type == "blank"){
                        line = reader.readLine();
                        continue;
                    }

                    matcher = parser.getMatcherSoundtrack(line, type);

                    if(type == "soundtrackmovie"){
                        listType = "movies"; 
                        soundtrackAssembler.newSet(matcher, type);
                    }else if(type == "soundtrackseries"){
                        listType = "series"; 
                        soundtrackAssembler.newSet(matcher, type);
                    }else if(type == "soundtracksong"){
                        soundtrackAssembler.addSongToSet(line);
                    }else if(type == "songinfo"){
                        //soundtrackParser.addInfoToSet(line);
                        line = reader.readLine();
                        continue;
                    }
                    if(type != "soundtrackmovie" && type != "soundtrackseries"){
                        if(listType == "movies"){
                            soundLinesMovies.add(soundtrackAssembler.getSongLine());
                        }else if (listType == "series"){
                            soundLinesSeries.add(soundtrackAssembler.getSongLine());
                        }
                    }
                    line = reader.readLine();
                }

                csvparser.setLines(soundLinesMovies);
                csvparser.setLines(soundLinesSeries);
                
            
            //Miel
            }else if(filename.contains("actors") || filename.contains("actresses") || filename.contains("directors")){
                ArrayList<String> personLines = new ArrayList<String>();
                String personType = "actor";

                if(filename.contains("actors")){
                    personType = "actor";
                }else if(filename.contains("actresses")){
                    personType = "actress";
                }else if(filename.contains("directors")){
                    personType = "director";
                }

                while(line != null){
                    type = parser.getParseType(line, filename);
                    if(type == "blank"){
                        line = reader.readLine();
                        continue;
                    }

                    matcher = parser.getMatcherActor(line, type);

                    if(type == "withtitle"){ // naam + movie
                        personAssembler.newSet(matcher, type, personType);
                    }else if(type == "actormovie"){ // alleen movie
                        personAssembler.addToSet(matcher, "movie", personType);
                    }else if(type == "actorepisode"){ // alleen episide
                        personAssembler.addToSet(matcher, "series", personType);
                    }
                    
                    personLines.add(personAssembler.getPersonLine());
                    
                    line = reader.readLine();
                }

                if(filename.contains("actors")){
                    csvparser.setLines(personLines);

                }else if (filename.contains("actresses")){
                    csvparser.setLines(personLines);

                }else if (filename.contains("directors")){
                    csvparser.setLines(personLines);
                }

            }else{
                while(line != null){
                    type = parser.getParseType(line, filename);
                    matcher = parser.getMatcher(line, type);

                    parser.parse(matcher, csvp, filename);

                    line = reader.readLine();
                }
            }
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    } 
}
