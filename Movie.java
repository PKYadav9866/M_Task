// program for getting and arranging movie details in sequence
public class Movie {
  // variable of movie table
	private String Name;
	private String Actor;
	private String Director;
	private int Year;

	Movie() {
// this is the constructer this helps to initialize objects
	}

	public Movie(String name, String actor, String director, int year) {
    // this is the copy consructer used for assining actual arguments with formal arguments
		Name = name;
		Actor = actor;
		Director = director;
		Year = year;
	}
  // functions for return thiere name when it called
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
  // this is the displing function that we are used in main.java to display the movie
  // here the display function implemented
	public void display() {
		System.out.print(Name + "\t\t");
		System.out.print(Actor + "\t\t");
		System.out.print(Director + "\t\t");
		System.out.println(Year + "\t\t");
	}

}