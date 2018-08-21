import java.util.Date;

/**
 * Invitation -- entity class
 *
 * @author cyril
 * @version 14/5/2018
 */
public class Invitation
{
    private String type;
    private String message;
    private Date date;

    /**
     * initial constructor for Invitation
     */
    public Invitation()
    {
        type="";
        message="";
        date=new Date();
    }
    /**
     * Constructor for objects of class Invitation
     */
    public Invitation(String type,String message,Date date)
    {
        this.type=type;
        this.message=message;
        this.date=date;
    }

    /**
     * return invitation type
     *
     * @return invitation type
     */
    public void setType(String type)
    {
        this.type=type;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setMessage(String message)
    {
        this.message=message;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    public void setDate(Date date)
    {
        this.date=date;
    }
    
    public Date getDate()
    {
        return date;
    }
    
    //Invite File format ---  recruiterName;seekerName;jobName
    //method to read invitation --------- public void readInvite(String seekerName)
    // read the file if seekerName == seekerName print seekerName and jobName
    public void readInvite(String seekerName)
    {
    }
    
    //method to write invitation  -------- 
    //write recruiterName;seekerName;JobName into a file. Don't forget to create a file inside the package
    public void writeInvite(String RecruiterName, String seekerName, String JobName)
    {
    }
    
}