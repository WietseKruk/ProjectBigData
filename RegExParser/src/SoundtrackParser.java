import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

//Miel
public class SoundtrackParser {

    public ArrayList<String> set = new ArrayList<String>();
    public String songLine;
    public String movieName;
    public String seriesName;
    public String year;
    public String deel;
    public String episode;
    public String platform;
    public String suspended;
    public boolean setTypeMovie;

    public void newSet(Matcher starterLine, String type){
        while(starterLine.find()){
            if(type == "soundtrackmovie"){
                movieName = starterLine.group(1);
                year = starterLine.group(2);
                deel = starterLine.group(3);
                platform = starterLine.group(4);
                suspended = starterLine.group(5);
                setTypeMovie = true;
            }

            if(type == "soundtrackseries"){
                seriesName = starterLine.group(1);
                year = starterLine.group(2);
                episode = starterLine.group(3);
                suspended = starterLine.group(4);
                setTypeMovie = false;
            }
        }
    }

    public void addSongToSet(String line){
        if(setTypeMovie == true){
            line = movieName + "|" + year + "|" + deel + "|" + platform + "|" + suspended + "|" + line + ";soundtrackmovies";
        }else if(setTypeMovie == false){
            line = seriesName + "|" + year + "|" + episode + "|" + suspended + "|" + line + ";soundtrackseries";
        }
        songLine = line;
    }

    public void addInfoToSet(String line){
        if(setTypeMovie == true)
            songLine = line + ";soundtrackmovies";
        else
            songLine = line + ";soundtrackseries";
    }

    public String getSongLine(){
        return songLine;
    }
}
