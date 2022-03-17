// this program is writed for handle sql related things and implemented some functions here
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteHandler{
  // inserting movie into movies table 
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
  // fetch movie function implemented here that we are used to fatch movie in main .java
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