
public class App {
    public static void main(String[] args) throws Exception {

        ReadFile readFile = new ReadFile();
        csvParser csvparser = new csvParser();

        readFile.FileReaderParseCSV("RegExParser/src/testdirectors.list", csvparser);
    }
}
