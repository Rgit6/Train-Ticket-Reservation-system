package Book;

public class Passenger {
    public static int id = 1;
    public String name;
    public int age;
    public String berthpreference;
    public int passengerId;
    public String alloted;
    public int number;
    public  Passenger(String name, int age, String berthpreference)
    {
     this.name = name;
     this.age = age;
     this.berthpreference = berthpreference;
     this.passengerId = id++;
     alloted = "L";
     number = -1;
    }
}
