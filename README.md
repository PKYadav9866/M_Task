# MuleSoftTask
JDBC Driver Used In this Assignment is 
sqlite-jdbc-3.37.2.jar
Important Note :--In this Assgnment I am Used Replict Website For Completing This Task
entire Task is completed By Using Three java File They Are Namely 1 Main.java ,2 Movie.java, 3 SqliteHandler.java
            
            
            
            
            Main.java file 
            
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




            Movie.java file
   
            public class Movie {
	private String Name;
	private String Actor;
	private String Director;
	private int Year;

	Movie() {

	}

	public Movie(String name, String actor, String director, int year) {
		Name = name;
		Actor = actor;
		Director = director;
		Year = year;
	}

	public String getName(){
		return Name;
	}
	public String getActor(){
		return Actor;
	}
	public String getDirector(){
		return Director;
	}
	public int getYear(){
		return Year;
	}

	public void display() {
		System.out.print(Name + "\t\t");
		System.out.print(Actor + "\t\t");
		System.out.print(Director + "\t\t");
		System.out.println(Year + "\t\t");
	}

}



                SqliteHandler.java file
                
               import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteHandler{
	public static void insertMovie(Statement statement,Movie movie){
		String insert = "INSERT INTO Movies" + " values ('" + movie.getName() + "','" + movie.getActor()
				+ "','" + movie.getDirector() + "'," + movie.getYear() + ")";
		try{
			statement.executeUpdate(insert);
		}catch (SQLException e) {
			System.err.println("error : " + e.getMessage());
		}
	}

	public static ResultSet getMovies(Statement statement){
		try{
			return statement.executeQuery("Select * from Movies");
		}catch (SQLException e) {
			System.err.println("error : " + e.getMessage());
		}
		return null;
	}

	public static Movie fatchMovie(ResultSet resultset){
		Movie movie;
		try{
			String name = resultset.getString("name");
			String actor = resultset.getString("actor");
			String director = resultset.getString("director");
			int year = resultset.getInt("year");
			movie = new Movie(name,actor,director,year);
			return movie;
		}catch (SQLException e) {
			System.err.println("error : " + e.getMessage());
		}
		return null;
	}
	
}
