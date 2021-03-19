import java.util.regex.*;

public class Parser {
//wietse

    public String getParseType(String line){

        final String getTypeExpression = "^.*(\\d{4}-\\d{4}|\\d{4}-\\?{4})";

        Pattern pattern = Pattern.compile(getTypeExpression);
        Matcher matcher = pattern.matcher(line);
        
        System.out.println(line);

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


    public void parse(String line, String type){

        Pattern pattern;
        type.toLowerCase();

        

        final String movieExpression = "(^.*)\\((\\d{4}|\\?{4})(/I|II|III)?\\)([ ]?)()(\\(V\\)|\\(TV\\))?\\t*(\\d{4})$";
        final String episodeExpression = "(^\\\".*\\\") (\\(\\d{4}\\)) (\\{.*\\})\\t*| *(\\d{4})";
        final String seriesExpression = "^\\\"(.*)\\\" \\((\\d{4})\\)\\s*(\\d{4})-(\\d{4}|\\?{4})";
        final String locationExpression = "^(.*)\\((\\d{4}|\\?{4}|\\d{4}/I{1,3}|d{4}/IV|d{4}/V|\\?{4}/I{1,3}|\\?{4}/IV|\\?{4}/V|\\?{4}/VI{1,3}|\\?{4}/IX|\\?{4}/X)\\)\\s*(\\(V\\)|\\(TV\\))?\\s*(.*)"; //Patrick

        switch(type){
            case "movie":
                pattern = Pattern.compile(movieExpression);
                break;
            case "episode":
                pattern = Pattern.compile(episodeExpression);
                break;
            case "series":
                pattern = Pattern.compile(seriesExpression);
            default:
                pattern = Pattern.compile(movieExpression);
        }

        Matcher matcher = pattern.matcher(line);
        System.out.println();
        printMatch(matcher);
        
    }

    public void printMatch(Matcher matcher){

        while(matcher.find()){
            System.out.println(matcher.group());
                for(int i = 1; i < matcher.groupCount(); i++){
                    if(matcher.group(i) != null)
                    System.out.println(matcher.group(i));
                }
            }
    }

}