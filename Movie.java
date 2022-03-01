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