import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

    private String firstName;
    private String lastName;
    private String password;
    private String email;

    public User(String firstName, String lastName, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public User(){}

    public User(String firstName, String lastName, String password, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }
    public String getFirstName(){return this.firstName;}
    public void setFirstName(String firstName){this.firstName = firstName;}

    public String getlastName(){return this.lastName;}
    public void setLastName(String lastName){this.lastName = lastName;}

    public String getPassword(){return this.password;}
    public void setPassword(String password){this.password = password;}

    public String getEmail(){return this.email;}
    public void setEmail(String email){this.email = email;}


    public boolean validateEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();


   }

}
