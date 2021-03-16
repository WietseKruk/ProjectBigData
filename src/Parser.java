import java.util.regex.*;

public class Parser {
      //wietse

public void parse(String[] lines, String type){

    Pattern pattern;
    type.toLowerCase();

    final String movieExpression = "(^.*)\\((\\d{4}|\\?{4})(/I|II|III)?\\)([ ]?)()(\\(V\\)|\\(TV\\))?\\t*(\\d{4})$";
    final String episodeExpression = "(^\\\".*\\\") (\\(\\d{4}\\)) (\\{.*\\})\\t*| *(\\d{4})";
    final String seriesExpression = "(^\\\"\\\".\\\"\\\") ((\\d{4}|?{4})(/I|II|III)?) {(.?)(#(\\d).(\\d))}\\t*(\\d{4}|?{4})$";

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
    
    for (String line : lines) {

        Matcher matcher = pattern.matcher(line);

        while(matcher.find()){
        System.out.println(matcher.group());
            for(int i = 1; i < matcher.groupCount(); i++){
                System.out.println(matcher.group(i));
            }
        }
    }
}}

