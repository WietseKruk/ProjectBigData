import java.util.regex.*;

public class App {
    public static void main(String[] args) throws Exception {

        ReadFile readFile = new ReadFile();

        readFile.FileReader("src/testMovies.list");


        

    //     //wietse
    //     final String expression = "(^.*)\\((\\d{4}|\\?{4})(/I|II|III)?\\)([ ]?)()(\\(V\\)|\\(TV\\))?\\t*(\\d{4})$";

    //     String[] lines = {
    //     "Lord of Asses 4: Poopchute Palace (2000) (V)		2000",
    //     "Lord of Asses 3: (1999) (V)		2000",
    //     "Lord of Asses 2: (1998) (V)		2000"
    //     };
       
    //     Pattern pattern = Pattern.compile(expression);

    //     for (String line : lines) {
            
    //         Matcher matcher = pattern.matcher(line);

    //         while(matcher.find()){
    //             System.out.println(matcher.start());
    //             System.out.println(matcher.end());
    //             System.out.println(matcher.group());
        
    //             for(int i = 0; i < matcher.groupCount(); i++){
    //                 System.out.println(matcher.group(i));
    //             }
    //         }
    //     }
    // }
    }

    


    
    
    



}
