public class App {

    
    public static void main(String[] args) throws Exception {
    
    Parser parser = new Parser();

    String[] movieLines = {
        "Lord of Asses 4: Poopchute Palace (2000) (V)		2000",
        "Lord of Asses 3: (1999) (V)		2000",
        "Lord of Asses 2: (1998) (V)		2000"
        };
    
    String[] episodeLines ={
        "\"Üheotsapilet\" (2015) {Britta - 11 045. päev (#1.12)}	2016",
        "\"Üheotsapilet\" (2015) {Guido - 22 123. päev (#1.4)}	2015",
        "\"Üheotsapilet\" (2015) {Hans - 26 735. päev (#1.9)}	2016",
        "\"Üheotsapilet\" (2015) {Ireeni - 13 915. päev (#1.6)}	2015",
        "\"Üheotsapilet\" (2015) {Janek - 14658. päev (2015) (#1.2)}	2015"
    };

    parser.parse(movieLines, "movie");
    parser.parse(episodeLines, "episode");
    
    }

    


    
    
    



}
