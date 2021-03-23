import java.sql.*;

public class Database {
    // JBDC driver name and DB url
    static final String JDBC_DRIVER = "com.mysql.jbdc.Driver";
    static final String DB_URL = ""; // db url

    // DB credentials
    static final String DB_USER = "root";
    static final String DB_PASS = "root";
    static final String DB_NAME = "IMDBDB";

    static ResultSet rs = null;

    String table_movies = "CREATE TABLE MOVIE" +
                            " (movie_title VARCHAR(255) not NULL," +
                            " director VARCHAR(255) not NULL," +
                            " location VARCHAR(255)," +
                            " running_time DOUBLE," + 
                            " genre VARCHAR(255)," +
                            " PRIMARY KEY (movie_title))";

    String table_series = "CREATE TABLE SERIES" +
                            " (title VARCHAR(255) not NULL," +
                             "year_start INTEGER(4) not NULL," +
                             "year_end VARCHAR(4) not NULL," +
                             "director VARCHAR(255) not NULL," +
                             "PRIMARY KEY (title, year_start))";
                             
    String table_actors = "CREATE TABLE ACTOR" +
                            " (name VARCHAR(255) not NULL," +
                            " PRIMARY KEY(name))";

    String table_actress = "CREATE TABLE ACTRESS" +
                            " (name VARCHAR(255) not NULL," +
                            " PRIMARY KEY(name))";
                            
    String table_cast = "CREATE TABLE CAST" +
                            " (movie_title VARCHAR(255) not NULL," +
                            " castmember VARCHAR(255) not NULL," +
                            " PRIMARY KEY (movie_title, castmember))";  

    String table_episodes = "CREATE TABLE EPISODE" +
                                " (title VARCHAR(255) not NULL," +
                                " running_time VARCHAR(255)" +
                                " location VARCHAR(255)" +
                                " cast VARCHAR(255)" +
                                " series_name VARCHAR(255) not NULL" +
                                " PRIMARY KEY(TITLE)" +
                                " FOREIGN KEY(CAST)" + 
                                " FOREIGN KEY(SERIES_NAME))";

    public static void main(String[] args){
        Connection conn = null;
        Statement stmt = null;

        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // open connection
            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=root");
            if(conn != null) {
                System.out.println("Check if database exists...");

                rs = conn.getMetaData().getCatalogs();
                while(rs.next()) {
                    String catalogs = rs.getString(1);

                    if(DB_NAME.equals(catalogs)) {
                       System.out.println("The database "+DB_NAME+" exists"); 
                    }
                }                
            }
            else {
                System.out.println("Creating database...");

                

                var s = conn.createStatement();
                //create db on url
                int result = s.executeUpdate("CREATE DATABASE " + DB_NAME);
            }            
        }
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			if( rs != null){
				try{
				    rs.close();
				}
				catch(SQLException ex){
					ex.printStackTrace();
				}
			}
			if(conn != null){
				try{
				    conn.close();
				}
				catch(SQLException ex){
					ex.printStackTrace();
				}
			}
		}
    }
}
