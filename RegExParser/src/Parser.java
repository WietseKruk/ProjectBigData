import java.util.ArrayList;
import java.util.regex.*;

public class Parser {
    
    //Wietse
    public String getParseType(String line){

        final String getTypeExpression = "^.*(\\d{4}-\\d{4}|\\d{4}-\\?{4})";

        Pattern pattern = Pattern.compile(getTypeExpression);
        Matcher matcher = pattern.matcher(line);

        if(line.contains("{") && line.contains("\"")){
            return "episode";
        }

        while(matcher.find()){   
            if(matcher.group(matcher.groupCount()) != null){
                return "series";
            }
        }

        return "movie";
    }


    //Wietse
    public Matcher getMatcher(String line, String type){
        Pattern pattern;
        
        //Wietse
        final String movieExpression = "(^.*)\\((\\d{4}|\\?{4})(/I|/II|/III)?\\)([ ]?)()(\\(V\\)|\\(TV\\)|\\(VG\\))?\\t*(\\d{4})$";
        final String episodeExpression = "(^\\\".*\\\") (\\(\\d{4}\\)) (\\{.*\\})\\t* *(\\d{4})";
        final String seriesExpression = "^\\\"(.*)\\\" \\((\\d{4})\\)\\s*(\\d{4})-(\\d{4}|\\?{4})";
        final String actorMovieExpression = "[\\t]{3}([\\w\\s!?\\-:&@#]*)\\(?(\\d{4}|\\?{4})/?(II)?\\)?\\s*(\\(V\\)|\\(TV\\)|\\(VG\\))?(\\(\\w*\\))?\\s*(\\[[\\w\\s]*\\])?";
        
        final String locationExpression = "^(.*)\\((\\d{4}|\\?{4}|\\d{4}/I{1,3}|d{4}/IV|d{4}/V|\\?{4}/I{1,3}|\\?{4}/IV|\\?{4}/V|\\?{4}/VI{1,3}|\\?{4}/IX|\\?{4}/X)\\)\\s*(\\(V\\)|\\(TV\\))?\\s*(.*)"; //Patrick

        //Miel - genre
        final String genreExpression = "^(.*)\\s\\((\\d{4}|\\?{4})(/I|/II|/III)?\\)\\s(\\(V\\)|\\(TV\\)|\\(VG\\))?\\s*(\\{.*\\})?\\s*(.*)";
        //Miel - runtime
        final String movieRunningTimesExpression = "^(.*)\\s\\((\\d{4}|\\?{4})(/I|/II|/III)?\\)\\s(\\(V\\)|\\(TV\\)|\\(VG\\))?(\\{.*\\})?\\s*(.*\\:)?(\\d*)\\s*(\\((.*)\\))?";
        final String seriesRunningTimesExpression = "^\\\"(.*)\\\"\\s\\((\\d{4}|\\?{4})(/I|/II|/III)?\\)\\s(\\(V\\)|\\(TV\\)|\\(VG\\))?(\\{.*\\})?\\s*(.*\\:)?(\\d*)\\s*(\\((.*)\\))?";
        //Miel - soundstracks
        final String soundTrackTimesExpression = "^((\\#.*)\\((\\d{4}|\\?{4})(/I|/II|/III)?\\))?(\\-.*)?"; //(Misschien nog niet helemaal af)
        //Miel - actors/actresses + directors
        final String actorWithMovieTitleExpression = "^(.*)(\\t)(.*)\\s\\((\\d{4}|\\?{4})(/I|/II|/III|/IV)?\\)\\s*(\\(V\\)|\\(TV\\)|\\(VG\\))?\\s*(\\(voice\\))?\\s*(\\(.*\\))?\\s*(\\[.*\\])?\\s*(\\<\\d*\\>)?";
        final String actorWithEpisodeTitleExpression = "^(.*)(\\t)(.*)\\s\\((\\d{4}|\\?{4})(/I|/II|/III|/IV)?\\)\\s* (\\{.*\\})\\s*(\\[.*\\])?\\s*(\\<\\d*\\>)?";
        

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
    public void parse(Matcher matcher, csvParser csvp){
        ArrayList<String> dataArray = new ArrayList<String>();

        while(matcher.find()){
                for(int i = 1; i < matcher.groupCount(); i++){
                    //System.out.println("Group " + i + ": " + matcher.group(i));
                    String matchplustype = matcher.group(i);
                    dataArray.add(matcher.group(i));
                }
                dataArray.add(getParseType(matcher.group(0)));
                csvp.convertToCSVLine(dataArray);
            }
    }

}