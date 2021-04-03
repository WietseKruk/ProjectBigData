import java.util.ArrayList;
import java.util.regex.*;

//Miel
public class SoundtrackAssembler {

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

    public void addSongToSet(Matcher line){
        String parsedline = "";
        if(setTypeMovie == true){
            while(line.find()){
                parsedline = movieName + "|" + year + "|" + deel + "|" + platform + "|" + suspended + "|" + line.group(1) + "¦soundtrackmovies";
            }
        }else if(setTypeMovie == false){
            while(line.find()){
                parsedline = seriesName + "|" + year + "|" + episode + "|" + suspended + "|" + line.group(1) + "¦soundtrackseries";
            }
        }
        songLine = parsedline;
    }

    public void addInfoToSet(String line){
        if(setTypeMovie == true)
            songLine = line + "¦soundtrackmovies";
        else
            songLine = line + "¦soundtrackseries";
    }

    public String getSongLine(){
        return songLine;
    }
}
