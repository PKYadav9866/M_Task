// importing required packages 
import java.sql.Connection;// this package helped me to connect the database
import java.sql.DriverManager;// this package provides basic managing set of jdbc drivers
import java.sql.ResultSet;// this package helps in managing Query things 
import java.sql.SQLException;// this package helps exception handiling things
import java.sql.Statement;// this package helep me to execute some sql statements

class Main{
  //storing database path in databasepath variable to make thing clear
	private static String databasePath = "jdbc:sqlite:MoviesDatabase.db";

	public static void main(String[] args){
		Connection connect = null;
    // this is the input to the table that we are going to use
		Movie DJ = new Movie("Duvvada Jagannadham","Allu Arjun","Harish Shankar",2017);
    Movie Pushpa = new Movie("Pushpa","Allu Arjun","Sukumar",2021);
    Movie Sarrainodu = new Movie("Sarrainodu","Allu Arjun","Boyapati Srenu",2016);
    Movie RaceGurram = new Movie("Race Gurram","Allu Arjun","Surender",2014);
		// this is the exception handiling things 
		try { // basically we do all our required things in try if we get any error our system won't crash 
      // if we fail in doing things in this block, system simply executes the catch blocl 
			connect = DriverManager.getConnection(databasePath);// connecting to the existing databse 
			Statement statement = connect.createStatement();
			statement.setQueryTimeout(30);

			statement.executeUpdate("drop table if exists Movies");// dropd a table if its exists
			statement.executeUpdate("create table Movies (name string, actor string, director string,year integer)");
			// inserting data into table
			SqliteHandler.insertMovie(statement,DJ);
			SqliteHandler.insertMovie(statement,Pushpa);
			SqliteHandler.insertMovie(statement,Sarrainodu);
			SqliteHandler.insertMovie(statement,RaceGurram);

			ResultSet resultset = SqliteHandler.getMovies(statement);// getting movie details from table in database
      // printing movie details.
      System.out.println("");
			System.out.println("Name	|	Actor	|	Director	|	Year	");
			System.out.println("-------------------------------------------");
			if(resultset != null){// condition for cheking whether the movies table is empty or what
				while(resultset.next()){
					Movie movie = SqliteHandler.fatchMovie(resultset);
					movie.display();// funtion for displying movies 
				}
			}
			
		} catch (SQLException e) {
      // error handiling block
			System.err.println("error : " + e.getMessage());
		}
	}

	
}