import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.DriverManager;
import java.util.Properties;
import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {

        ReadFile readFile = new ReadFile();
        csvParser csvparser = new csvParser();
        DBmanager dbm = new DBmanager();

        readFile.FileReaderParseCSV("RegExParser/src/testMovies.list", csvparser);
        csvparser.createCSV();

        dbm.executeScript("RegExParser/src/sql_scripts/createDB.sql"); //Patrick
        dbm.executeScript("RegExParser/src/sql_scripts/loadCSV.sql");

      
    }

        // Connection conn = DriverManager.getConnection("jdbc:mysql//localhost:3306/mysql", "root", "password");
        // conn.prepareStatement("LOAD DATA INFILE'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/series.csv' INTO TABLE series FIELDS TERMINATED BY '|' LINES TERMINATED BY '\n' IGNORE 1 ROWS;").executeQuery();

}

