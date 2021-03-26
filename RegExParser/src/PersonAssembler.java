import java.util.regex.*;

//Miel
public class PersonAssembler {
    public String personLine;
    public String personName;

    public String movieName;
    public String episodeName;
    public String year;
    public String deel;
    public String platform;
    public String suspended;
    public boolean setTypeMovie;

    public void newSet(Matcher starterLine, String type, String personType){
        while(starterLine.find()){
            personName = starterLine.group(1);
        }
        addToSet(starterLine, type, personType);
    }

    // "NAME|MOVIE_OF_SERIE|TITEL|JAAR|DEEL|PLATFORM|EPISODE|SUSPENDED|VOICE|CREDIT|PLAYED"
    /* actor + movie/series
    1 Naam
    + series of movie
    2 titel
    3 jaar
    4 deel
    5 platform
    6 episode
    7 suspended
    8 voice (ja/nee)
    9 credit
    10 Played as
    11 cijfetje
    */
        /* series
    + Naam
    + series of movie
    1 titel
    2 jaar
    3 deel
    4 episode
    5 suspended
    6 voice (ja/nee) $
    7 credit
    8 Played as $
    9 cijfetje $
    */
        /* movie
    + Naam
    + series of movie
    1 titel
    2 jaar
    3 deel
    4 platform
    5 suspended
    6 voice (ja/nee) $
    7 credit
    8 Played as $
    9 cijfetje $
    */
        /* director
    1 Naam
    + series of movie
    2 titel
    3 jaar
    4 deel
    5 platform
    6 episode
    7 suspended
    8 -
    9 credit
    10 -
    11 -
    */
    public void addToSet(Matcher line, String type, String personType){
        String parsedLine = "";
        if (type == "withtitle"){
            if(line.matches()){

                if(line.group(7) != null)
                    type = "series";
                else
                    type = "movie";
            }

            if(personType != "director")
                parsedLine = personName + "|" + type + "|"+ line.group(2) + "|" + line.group(3) + "|" + line.group(4)+ "|" + line.group(5) + "|" + line.group(6) + "|" + line.group(7) + "|" + line.group(8) + "|" + line.group(9) + "|" + line.group(10);
            else
                parsedLine = personName + "|" + type + "|"+ line.group(2) + "|" + line.group(3) + "|" + line.group(4)+ "|" + line.group(5) + "|" + line.group(6) + "|" + line.group(7) + "|" + line.group(9);
            
            }else if(type == "movie"){
            
            if(line.matches()){
                if(personType != "director")
                    parsedLine = personName + "|" + type + "|"+ line.group(1) + "|" + line.group(2) + "|" + line.group(3) + "|" + line.group(4) + "|" + null + "|" + line.group(5) + "|" + line.group(6) + "|" + line.group(7) + "|" +line.group(8);
                else
                    parsedLine = personName + "|" + type + "|"+ line.group(1) + "|" + line.group(2) + "|" + line.group(3) + "|" + line.group(4) + "|" + null + "|" + line.group(5) + "|" + line.group(7);
            
            }        /* series
            + Naam
            + series of movie
            1 titel
            2 jaar
            3 deel
            4 episode
            5 suspended
            6 voice (ja/nee) $
            7 credit
            8 Played as $
            9 cijfetje $
            */
    // "NAME|MOVIE_OF_SERIE|TITEL|JAAR|DEEL|PLATFORM|EPISODE|SUSPENDED|VOICE|CREDIT|PLAYED"
        }else if(type == "series"){
            if(line.matches()){
                if(personType != "director")
                    parsedLine = personName + "|" + type + "|"+ line.group(1) + "|" + line.group(2) + "|" + line.group(3) + "|" + null + "|" + null + "|" + line.group(4) + "|" + null + line.group(5) + "|" + line.group(6) + "|" + line.group(7);
                else
                    parsedLine = personName + "|" + type + "|"+ line.group(1) + "|" + line.group(2) + "|" + line.group(3) + "|" + null + "|" + null + "|" + line.group(4) + "|" + line.group(6);
            
            }
        }
        if(personType == "actor"){
            parsedLine = parsedLine + ";actors";
        }else if(personType == "actress")
            parsedLine = parsedLine + ";actresses";
        else if(personType == "director")
            parsedLine = parsedLine + ";directors";
            personLine = parsedLine;
    }

    public String getPersonLine(){
        return personLine;
    }
}
