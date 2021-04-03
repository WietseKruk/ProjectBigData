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
                //"NAME|MOVIE_OF_SERIES|TITEL|JAAR|DEEL|PLATFORM|EPISODE|SUSPENDED|VOICE|CREDIT|PLAYED"
                //1TITEL|2JAAR|3DEEL|4PLATFORM|  |5SUSPENDED|6VOICE|7CREDIT|8PLAYED"
                if(personType != "director")
                    parsedLine = personName + "|" + type + "|"+ line.group(1) + "|" + line.group(2) + "|" + line.group(3) + "|" + line.group(4) + "|" + null + "|" + line.group(5) + "|" + line.group(6) + "|" + line.group(7) + "|" +line.group(8);
                else
                    parsedLine = personName + "|" + type + "|"+ line.group(1) + "|" + line.group(2) + "|" + line.group(3) + "|" + line.group(4) + "|" + null + "|" + line.group(5) + "|" + line.group(7);
            
            }       
        }else if(type == "series"){
            if(line.matches()){
                                
                                //"1TITEL|2JAAR|3DEEL|  |4EPISODE|5SUSPENDED|6VOICE|7CREDIT|8PLAYED"
                if(personType != "director")
                    parsedLine = personName + "|" + type + "|"+ line.group(1) + "|" + line.group(2) + "|" + line.group(3) + "|" + null + "|" + line.group(4) + "|" + line.group(5) + "|" + line.group(6) + "|" + line.group(7) + "|" + line.group(8);
                else
                    parsedLine = personName + "|" + type + "|"+ line.group(1) + "|" + line.group(2) + "|" + line.group(3) + "|" + null + "|" + line.group(4) + "|" + line.group(5) + "|" + line.group(7) + "|" + line.group(8);
            
            }
        }
        if(personType == "actor"){
            parsedLine = parsedLine + "¦actors";
        }else if(personType == "actress")
            parsedLine = parsedLine + "¦actresses";
        else if(personType == "director")
            parsedLine = parsedLine + "¦directors";
            personLine = parsedLine;
    }

    public String getPersonLine(){
        return personLine;
    }
}
