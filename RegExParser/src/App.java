import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.DriverManager;
import java.util.Properties;
import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {

        ReadFile readFile = new ReadFile();
        csvParser csvparser = new csvParser();

        readFile.FileReaderParseCSV("RegExParser/src/testMovies.list", csvparser);
        csvparser.createCSV();


        Connection dbConnection = null;

        try {
            String url = "jdbc:mysql://localhost:3306/imdbdb";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "password");
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(url, "root", "password");
      
            if (dbConnection != null) {
              System.out.println("Successfully connected to MySQL database imdbdb");
              

              String query = "SELECT * FROM series";
              Statement stmt = dbConnection.createStatement();
              ResultSet rs = stmt.executeQuery(query);
              
              while(rs.next()){
                  System.out.println(rs.getString(1)); 
              }
              dbConnection.close();
            }
      
          } catch (SQLException ex) {
            System.out.println("An error occurred while connecting MySQL databse");
            ex.printStackTrace();
          }
      
        }

        
      
      

        // Connection conn = DriverManager.getConnection("jdbc:mysql//localhost:3306/mysql", "root", "password");
        // conn.prepareStatement("LOAD DATA INFILE'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/series.csv' INTO TABLE series FIELDS TERMINATED BY '|' LINES TERMINATED BY '\n' IGNORE 1 ROWS;").executeQuery();

    }

