import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Parser {
    
    //Wietse
    final String movieExpression = "(^.*)\\((\\d{4}|\\?{4})\\/?(I|II|III)?\\)?\\s*(\\{.*\\})?\\(?(V|TV|VG)?\\)?";
    final String episodeExpression = "(^\\\".*\\\") (\\(\\d{4}\\)) (\\{[^\\{].*?\\})?\\s(\\{.*\\})?\\s*(\\d{4}|\\?{4})";
    final String seriesExpression =  "^\\\"(.*)\\\" \\(\\d{4}\\)\\s*(\\{.*\\})?\\s*(\\d{4})-(\\d{4}|\\?{4})";
    final String actorMovieExpression = "[\\t]{3}([\\w\\s!?\\-:&@#]*)\\(?(\\d{4}|\\?{4})/?(II)?\\)?\\s*(\\(V\\)|\\(TV\\)|\\(VG\\))?(\\(\\w*\\))?\\s*(\\[[\\w\\s]*\\])?";
    final String actorSeriesExpression = "[\\t]{3}\\\"([\\w\\s!?\\-:&@#]*)\\\"\\s\\((\\d{4}|\\?{4})\\)\\s*\\{(.*)\\}\\s*(\\(.*\\))?\\s*\\[(\\w*)\\]";
    
    //Patrick
    final String locationExpression = "^(.*)\\((\\d{4}|\\?{4})(/I{1,3}|/IV|/V|/I{1,3}|/IV|/V|/VI{1,3}|/IX|/X)?\\)\\s*(\\(V\\)|\\(TV\\))?\\s*(.*)";

    //Miel - genre
    final String genreExpression =  "^(.*)\\s\\((\\d{4}|\\?{4})(\\/I|\\/II|\\/III)?\\)\\s(\\(V\\)|\\(TV\\)|\\(VG\\))?\\s*(\\{[^\\{].*?\\})?\\s*(\\{.*\\})?\\s*(.*)";
    //Miel - runtime
    final String movieRunTimesExpression =  "^([^\\\"].*[^\\\"])\\s\\((\\d{4}|\\?{4})(/I|/II|/III)?\\)\\s(\\(V\\)|\\(TV\\)|\\(VG\\))?(\\{.*\\})?\\s*(.*\\:)?(\\d*)\\s*(\\(.*\\))?";
    final String episodesRunTimesExpression = "^\\\"(.*)\\\"\\s\\((\\d{4}|\\?{4})(/I|/II|/III)?\\)\\s(\\(V\\)|\\(TV\\)|\\(VG\\))?(\\{.*\\})\\s*(.*\\:)?(\\d*)\\s*(\\(.*\\))?";
    final String seriesRunTimesExpression = "^\\\"(.*)\\\"\\s\\((\\d{4}|\\?{4})(/I|/II|/III)?\\)\\s(\\(V\\)|\\(TV\\)|\\(VG\\))?\\s*(.*\\:)?(\\d*)\\s*(\\(.*\\))?";
    //Miel - soundstracks
    final String soundTrackMovieExpression = "^\\#\\s([^\\\"].*)\\((\\d{4}|\\?{4})(\\/I|\\/II|\\/III)?\\)\\s*(\\(V\\)|\\(TV\\)|\\(VG\\))?\\s*(\\{\\{.*\\}\\})?";
    final String soundTrackSeriesExpression = "^\\#\\s*\\\"(.*)\\\" (\\(\\d{4}\\))\\s*(\\{[^\\{].*?\\})?\\s*(\\{.*\\})?";
    final String soundTrackSongExpression = "^\\-\\s(.*)\\s*?(\\(.*\\))?";
    final String soundTrackInfoExpression = "^[^#^-]\\s(.*)";
    //Miel - actors/actresses + directors
    final String withMovieTitleExpression = "^(.*)(\\t)(.*)\\s\\((\\d{4}|\\?{4})(/I|/II|/III|/IV)?\\)\\s*(\\(V\\)|\\(TV\\)|\\(VG\\))?\\s*(\\(voice\\))?\\s*(\\(.*\\))?\\s*(\\[.*\\])?\\s*(\\<\\d*\\>)?";
    final String withEpisodeTitleExpression = "^(.*)(\\t)(.*)\\s\\((\\d{4}|\\?{4})(/I|/II|/III|/IV)?\\)\\s* (\\{.*\\})\\s*(\\[.*\\])?\\s*(\\<\\d*\\>)?";
    
    private SoundtrackParser soundtrackParser = new SoundtrackParser();

    //Wietse / Miel aangevuld
    public String getParseType(String line, String filename){
        if (filename.contains("movies") || filename.contains("testMovies")){
            if(line.matches(episodeExpression))
                return "episode"; 
            else if(line.matches(seriesExpression))
                return "series"; 
            else if(line.matches(movieExpression))
                return "movie";

        }else if(filename.contains("genres")){
            return "genre";
        }else if(filename.contains("locations")){
            return "location";
        }else if(filename.contains("runtime")){
            if(line.matches(movieRunTimesExpression))
                return "movieruntime";
            else if(line.matches(episodesRunTimesExpression))
                return "episoderuntime";
            else if(line.matches(seriesRunTimesExpression))
                return "seriesruntime";
        }else if(filename.contains("soundtracks")){
            if(line.matches(soundTrackMovieExpression))
                return "soundtrackmovie";
            else if(line.matches(soundTrackSeriesExpression))
                return "soundtrackseries";
            else if(line.matches(soundTrackSongExpression))
                return "soundtracksong";
            else if(line.matches(soundTrackInfoExpression))
                return "songinfo";
        }

        //Voor soundtracks: Maak een functie die de titel, jaartal (en episode) opslaat en die telkens voor de song zet. Als er een nieuwe song komt weer nieuwe info opslaan.
        // Uiteindelijk wil ik een lijst voor de songs en niet de episodes er los in.

        return "movie"; //Default als niets goed is
    }

    public void soundtrackAssenbler(String[] lines){
        //if line starts met #
        //sla info op

        //if line start with - 
        // voeg opgeslagen info ervoor


    }


    //Wietse
    public Matcher getMatcher(String line, String type){
        Pattern pattern;
        
        switch(type){
            case "movie":
                pattern = Pattern.compile(movieExpression);
                break;
            case "episode":
                pattern = Pattern.compile(episodeExpression);
                break;
            case "series":
                pattern = Pattern.compile(seriesExpression);
                break;
            case "actormovie":
                pattern = Pattern.compile(actorMovieExpression);
                break;
            case "location":
                pattern = Pattern.compile(locationExpression);
                break;
            case "genre":
                pattern = Pattern.compile(genreExpression);
                break;
            case "movieruntime":
                pattern = Pattern.compile(movieRunTimesExpression);
                break;
            case "episoderuntime":
                pattern = Pattern.compile(episodesRunTimesExpression);
                break;
            case "seriesruntime":
                pattern = Pattern.compile(seriesRunTimesExpression);
                break;
            case "actorwithmovietitle":
                pattern = Pattern.compile(withMovieTitleExpression);
                break;
            case "actorwitseriestitle":
                pattern = Pattern.compile(withEpisodeTitleExpression);
                break;
            default:
                pattern = Pattern.compile(movieExpression);
                System.out.println("Couldn't find expression, defaulting to movie");
        }

        Matcher matcher = pattern.matcher(line);
        
        return matcher;
    }

    public Matcher getMatcherSoundtrack(String line, String type){
        Pattern pattern;
        switch(type){
            case "soundtrackmovie":
                pattern = Pattern.compile(soundTrackMovieExpression);
                break;
            case "soundtrackseries":
                pattern = Pattern.compile(soundTrackSeriesExpression);
                break;
            case "soundtracksong":
                pattern = Pattern.compile(soundTrackSongExpression);
                break;
            case "songinfo":
                pattern = Pattern.compile(soundTrackInfoExpression);
                break;
            default:
                pattern = Pattern.compile(movieExpression);
                System.out.println("Couldn't find expression, defaulting to movie");
        }
        Matcher matcher = pattern.matcher(line);
        return matcher;
    }

    //Wietse
    public void parse(Matcher matcher){

        while(matcher.find()){
                for(int i = 0; i < matcher.groupCount(); i++){
                    if(matcher.group(i) == null){
                        String temp = matcher.group(i);
                        //temp = "_";
                        //System.out.println("Group " + i + ": " + temp);
  
                    }else{
                        //System.out.println("Group " + i + ": " + matcher.group(i));
                    }
                    

                }
            }
    }

    //Wietse / Max overload
    public void parse(Matcher matcher, csvParser csvp, String filename){
        ArrayList<String> dataArray = new ArrayList<String>();

        while(matcher.find()){
                for(int i = 1; i <= matcher.groupCount(); i++){
                    //System.out.println("Group " + i + ": " + matcher.group(i));
                    String matchplustype = matcher.group(i);
                    dataArray.add(matchplustype);
                }
                dataArray.add(getParseType(matcher.group(0), filename));
                csvp.convertToCSVLine(dataArray);
            }
    }

}