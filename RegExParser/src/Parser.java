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
    public void parse(String line, String type){

        Pattern pattern;
        type.toLowerCase();

        final String movieExpression = "^(.*)\\((.*)\\)\\s*(\\{.*\\})?\\s*(\\d{4}|\\?{4})";
        final String episodeExpression = "(^\\\".*\\\") (\\(\\d{4}\\)) (\\{.*\\})\\t* *(\\d{4})";
        final String seriesExpression = "^\\\"(.*)\\\" \\((\\d{4})\\)\\s*(\\d{4})-(\\d{4}|\\?{4})";
        final String actorMovieExpression = "[\\t]{3}([\\w\\s!?\\-:&@#]*)\\(?(\\d{4}|\\?{4})/?(II)?\\)?\\s*(\\(V\\)|\\(TV\\)|\\(VG\\))?(\\(\\w*\\))?\\s*(\\[[\\w\\s]*\\])?";

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
        
        printMatch(matcher);
        
    }

    //Wietse
    public void printMatch(Matcher matcher){

        while(matcher.find()){
                for(int i = 0; i < matcher.groupCount(); i++){
                    if(matcher.group(i) == null){
                        String temp = matcher.group(i);
                        temp = "_";
                        System.out.println("Group " + i + ": " + temp);
  
                    }else{
                        System.out.println("Group " + i + ": " + matcher.group(i));
                    }
                    
                }
            }
    }

}