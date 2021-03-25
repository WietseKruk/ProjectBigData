import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;


public class ReadFile {
    private Parser parser = new Parser();
    public SoundtrackParser soundtrackParser = new SoundtrackParser();
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

            if(filename.contains("soundtracks")){
                ArrayList<String> soundLinesMovies = new ArrayList<String>();
                ArrayList<String> soundLinesSeries = new ArrayList<String>();

                String listType = "series";

                while(line != null){
                    type = parser.getParseType(line, filename);
                    matcher = parser.getMatcherSoundtrack(line, type);

                    if(type == "soundtrackmovie"){
                        listType = "movies"; 
                        soundtrackParser.newSet(matcher, type);
                    }else if(type == "soundtrackseries"){
                        listType = "series"; 
                        soundtrackParser.newSet(matcher, type);
                    }else if(type == "soundtracksong"){
                        soundtrackParser.addSongToSet(line);
                    }else if(type == "songinfo"){
                        //soundtrackParser.addInfoToSet(line);
                        line = reader.readLine();
                        continue;
                    }
                    if(type != "soundtrackmovie" && type != "soundtrackseries"){
                        if(listType == "movies"){
                            soundLinesMovies.add(soundtrackParser.getSongLine());
                        }else if (listType == "series"){
                            soundLinesSeries.add(soundtrackParser.getSongLine());
                        }
                    }
                    line = reader.readLine();
                }

                csvparser.setLines(soundLinesMovies);
                csvparser.createCSV("soundtrackmovies");

                csvparser.setLines(soundLinesSeries);
                csvparser.createCSV("soundtrackseries");





                //System.out.println("Type: " + type);



            }else{
                while(line != null){
                    type = parser.getParseType(line, filename);
                    matcher = parser.getMatcher(line, type);

                    parser.parse(matcher, csvp, filename);

                    //System.out.println("Type: " + type);

                    line = reader.readLine();
                }
            }
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    } 
}
