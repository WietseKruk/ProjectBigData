import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Parser {
    
    //Wietse
    final String movieExpression = "^(.*)\\((\\d{4}|\\?{4})(\\/I[A-Z]*|\\/V[A-Z]*|\\/X[A-Z]*)?\\)\\s*(\\(V\\)|\\(TV\\)|\\(VG\\))?\\s*(\\{.*\\})?\\s*(\\d{4}|\\?{4})";
    final String episodeExpression =  "^\\\"(.*)\\\"\\s*\\((\\d{4}|\\?{4})(\\/I[A-Z]*|\\/V[A-Z]*|\\/X[A-Z]*)?\\)\\s*(\\{[^\\{].*?\\})?\\s(\\{.*\\})?\\s*(\\d{4}|\\?{4})";
    final String seriesExpression =  "^\\\"(.*)\\\" \\(\\d{4}(\\/I[A-Z]*|\\/V[A-Z]*|\\/X[A-Z]*)?\\)\\s*(\\{.*\\})?\\s*(\\d{4})-(\\d{4}|\\?{4})";
    final String actorMovieExpression = "[\\t]{3}(.*)\\((\\d{4}|\\?{4})(\\/I[A-Z]*|\\/V[A-Z]*|\\/X[A-Z]*)?\\)\\s*(\\(V\\)|\\(TV\\)|\\(VG\\))?(\\{\\{.*\\}\\})?\\s*(\\(voice\\))?\\s*(\\(.*\\))?(\\[.*\\])?\\s*(\\<.*\\>)?$";
    final String actorEpisodeExpression = "[\\t]{3}\\\"(.*)\\\"\\s*\\((\\d{4}|\\?{4})(\\/I[A-Z]*|\\/V[A-Z]*|\\/X[A-Z]*)?\\)\\s*(\\{[^\\{].*\\})?\\s*(\\{.*\\})?\\s*(\\(voice\\))?\\s*(\\(.*\\))?\\s*(\\[.*\\])?\\s*(\\<\\d*\\>)?$";
    
    //Patrick
    //final String locationExpression = "^(.*)\\((\\d{4}|\\?{4})(\\/I[A-Z]*|\\/V[A-Z]*|\\/X[A-Z]*)?\\)\\s*(\\(V\\)|\\(TV\\))?\\s*(\\{[^\\{].*?\\})?\\s*(\\{.*\\})?\\s*(.*)";
    final String locationMovieExpression = "^([^\\\"].*[^\\\"])\\((\\d{4}|\\?{4})(\\/I[A-Z]*|\\/V[A-Z]*|\\/X[A-Z]*)?\\)\\s*(\\(V\\)|\\(TV\\))?\\s*(\\{[^\\{].*?\\})?\\s*(\\{.*\\})?\\s*(.*)";
    final String locationEpisodeExpression = "^\\\"(.*)\\\"\\s*\\((\\d{4}|\\?{4})(\\/I[A-Z]*|\\/V[A-Z]*|\\/X[A-Z]*)?\\)\\s*(\\(V\\)|\\(TV\\))?\\s*(\\{[^\\{].*?\\})\\s*(\\{.*\\})?\\s*(.*)";
    final String locationSeriesExpression = "^\\\"(.*)\\\"\\s*\\((\\d{4}|\\?{4})(\\/I[A-Z]*|\\/V[A-Z]*|\\/X[A-Z]*)?\\)\\s*(\\(V\\)|\\(TV\\))?\\s*(\\{.*\\})?\\s*(.*)";
    //Miel - genre
    final String genreExpression =  "^(.*)\\s\\((\\d{4}|\\?{4})(\\/I[A-Z]*|\\/V[A-Z]*|\\/X[A-Z]*)?\\)\\s(\\(V\\)|\\(TV\\)|\\(VG\\))?\\s*(\\{[^\\{].*?\\})?\\s*(\\{.*\\})?\\s*(.*)";
    //Miel - runtime
    final String movieRunTimesExpression =  "^([^\\\"].*[^\\\"])\\s*\\((\\d{4}|\\?{4})(\\/I[A-Z]*|\\/V[A-Z]*|\\/X[A-Z]*)?\\)\\s(\\(V\\)|\\(TV\\)|\\(VG\\))?\\s*(\\{.*\\})?\\s*(.*\\:)?(.*)";
    final String episodesRunTimesExpression = "^\\\"(.*)\\\"\\s\\((\\d{4}|\\?{4})(\\/I[A-Z]*|\\/V[A-Z]*|\\/X[A-Z]*)?\\)\\s*(\\{[^\\{].*?\\})\\s(\\{.*\\})?\\s*(.*\\:)?(\\d*.?\\d*)\\s*(\\(.*\\))?";
    final String seriesRunTimesExpression = "^\\\"(.*)\\\"\\s\\((\\d{4}|\\?{4})(\\/I[A-Z]*|\\/V[A-Z]*|\\/X[A-Z]*)?\\)\\s*(\\{.*\\})?\\s*(.*\\:)?(.*)\\s*";
    //Miel - soundstracks
    final String soundTrackMovieExpression = "^\\#\\s([^\\\"].*)\\((\\d{4}|\\?{4})(\\/I[A-Z]*|\\/V[A-Z]*|\\/X[A-Z]*)?\\)\\s*(\\(V\\)|\\(TV\\)|\\(VG\\))?\\s*(\\{\\{.*\\}\\})?";
    final String soundTrackSeriesExpression = "^\\#\\s*\\\"(.*)\\\"\\s*\\((\\d{4}|\\?{4})(\\/I[A-Z]*|\\/V[A-Z]*|\\/X[A-Z]*)?\\)\\s*(\\{[^\\{].*?\\})?\\s*(\\{.*\\})?";
    final String soundTrackSongExpression = "^\\-\\s\\\"(.*)\\\"\\\\?\\s*?(\\(.*\\))?";
    final String soundTrackInfoExpression = "^[^#^-]\\s(.*)";
    //Miel - actors/actresses + directors
    final String withTitleExpression = "^([^\\s].*)\\t(.*)\\((\\d{4}|\\?{4})(\\/I[A-Z]*|\\/V[A-Z]*|\\/X[A-Z]*)?\\)\\s*(\\(V\\)|\\(TV\\)|\\(VG\\))?\\s*(\\(voice\\))?\\s*(\\{[^\\{].*?\\})?\\s*(\\{.*\\})?(\\(.*\\))?\\s*(\\[.*\\])?\\s*(\\<\\d*\\>)?$";
    

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
            if(line.matches(locationMovieExpression))
                return "movielocation";
                else if(line.matches(locationEpisodeExpression))
                return "episodelocation";
                else if(line.matches(locationSeriesExpression))
                return "serieslocation";

        }else if(filename.contains("running")){
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
            else
                return "blank";
        }else if(filename.contains("actors") || filename.contains("actresses") || filename.contains("directors")){
            if(line.matches(withTitleExpression))
                return "withtitle";
            else if(line.matches(actorEpisodeExpression))
                return "actorepisode";
            else if(line.matches(actorMovieExpression))
                return "actormovie";
            else
                return "blank";
        }

        //Voor soundtracks: Maak een functie die de titel, jaartal (en episode) opslaat en die telkens voor de song zet. Als er een nieuwe song komt weer nieuwe info opslaan.
        // Uiteindelijk wil ik een lijst voor de songs en niet de episodes er los in.

        return "blank"; //Default als niets goed is
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
            case "movielocation":
                pattern = Pattern.compile(locationMovieExpression);
                break;
            case "episodelocation":
                pattern = Pattern.compile(locationEpisodeExpression);
                break;
            case "serieslocation":
                pattern = Pattern.compile(locationSeriesExpression);
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
            default:
                pattern = Pattern.compile(movieExpression);
                System.out.println("Couldn't match expression, defaulting to movie");
                System.out.println(line);
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
                System.out.println("Line: " + line);
        }
        Matcher matcher = pattern.matcher(line);
        return matcher;
    }

    public Matcher getMatcherActor(String line, String type){
        Pattern pattern;
        switch(type){
            case "withtitle":
                pattern = Pattern.compile(withTitleExpression);
                break;
            case "actormovie":
                pattern = Pattern.compile(actorMovieExpression);
                break;
            case "actorepisode":
                pattern = Pattern.compile(actorEpisodeExpression);
                break;
            default:
                pattern = Pattern.compile(movieExpression);
                System.out.println("Couldn't find expression, defaulting to movie");
                System.out.println("Line: " + line);
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