import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Main{
	private static String databasePath = "jdbc:sqlite:MoviesDatabase.db";

	public static void main(String[] args){
		Connection connect = null;

		Movie DJ = new Movie("Duvvada Jagannadham","Allu Arjun","Harish Shankar",2017);
    Movie Pushpa = new Movie("Pushpa","Allu Arjun","Sukumar",2021);
    Movie Sarrainodu = new Movie("Sarrainodu","Allu Arjun","Boyapati Srenu",2016);
    Movie RaceGurram = new Movie("Race Gurram","Allu Arjun","Surender",2014);
		
		try {
			connect = DriverManager.getConnection(databasePath);
			Statement statement = connect.createStatement();
			statement.setQueryTimeout(30);

			statement.executeUpdate("drop table if exists Movies");
			statement.executeUpdate("create table Movies (name string, actor string, director string,year integer)");
			
			SqliteHandler.insertMovie(statement,DJ);
			SqliteHandler.insertMovie(statement,Pushpa);
			SqliteHandler.insertMovie(statement,Sarrainodu);
			SqliteHandler.insertMovie(statement,RaceGurram);

			ResultSet resultset = SqliteHandler.getMovies(statement);
      System.out.println("");
			System.out.println("Name	|	Actor	|	Director	|	Year	");
			System.out.println("-------------------------------------------");
			if(resultset != null){
				while(resultset.next()){
					Movie movie = SqliteHandler.fatchMovie(resultset);
					movie.display();
				}
			}
			
		} catch (SQLException e) {
			System.err.println("error : " + e.getMessage());
		}
	}

	
}