import java.util.*;
import java.text.*;

public class User{
    private String userName;
    private String name;
    private String phoneNo;
    private String email;
    private Date dateOfBirth;
    private int type;

    public User(){
        userName = "";
        name = "";
        phoneNo = "";
        email = "";
        dateOfBirth = new Date();
        type = 0;
    }

    public User(String userName, String name, String phoneNo, String email, Date dateOfBirth){
        this.userName = userName;
        this.name = name;
        this.phoneNo = phoneNo;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPhoneNumber(String phoneNo){
        this.phoneNo = phoneNo;
    }

    public String getPhoneNumber(){
        return phoneNo;
    }

    public void setEmailAddress(String email){
        this.email = email;
    }

    public String getEmailAddress(){
        return email;
    }

    public void setDateOfBirth(String dob){
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            dateOfBirth = format.parse(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getDateOfBirth(){
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String dob = format.format(dateOfBirth);
        return dob;
    }

    public void setType(int type){
        this.type = type;
    }

    public int getType(){
        return type;
    }
}

