/**
 * @author Simar Pal Kalsi
 * Live Long And Prosper -----> Simt'pal
 **/
public class Student {

    String firstName;
    String lastName;
    int age;

    public Student(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public void GetInfo(){
    System.out.println("This student's first name is " + firstName + " and their last name is " + lastName + " and their age is  " + age + ".'");
    }
}
