import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Job
 * 
 * @author cyril
 * @version 12/5/2018
 */
public class Job
{
    private String jobName;
    private String description;
    private String company;
    private String location;
    private int salary;
    private int compensationLevel;
    private boolean isAdvertised;
    private Date expiryDate;
    private String category;
    private ArrayList<String> skillset;
    private String createdBy; // recruiterUserName
    
    /**
     * Initial constructor for job
     */
    public Job()
    {
        jobName="";
        description="";
        company="";
        location="";
        salary=0;
        compensationLevel=0;
        isAdvertised=false;
        expiryDate=new Date();
        category="";
        skillset = new ArrayList<String>();
        createdBy="";
    }
    
    /**
     * job
     * 
     */
    public Job(String jobName,String description,String company,String location,int salary,int compensationLevel,
    boolean isAdvertised,Date expiryDate,String category,ArrayList<String> skillSet,String createdBy)
    {
        this.jobName=jobName;
        this.description=description;
        this.company=company;
        this.location=location;
        this.salary=0;
        this.compensationLevel=0;
        this.isAdvertised=false;
        this.expiryDate=new Date();
        this.category=category;
        this.skillset = skillSet;
        this.createdBy=createdBy;
    }

    /**
     * set job name
     * 
     */
    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }
    
    /**
     * return job name
     * 
     */
    public String getJobName()
    {
        return jobName;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setCompany(String company)
    {
        this.company = company;
    }
    
    public String getCompany()
    {
        return company;
    }
    
    public void setLocation(String location)
    {
        this.location = location;
    }
    
    public String getLocation()
    {
        return location;
    }
    
    public void setSalary(int salary)
    {
        this.salary = salary;
    }
    
    public int getSalary()
    {
        return salary;
    }
    
    public void setCompensationLevel(int compensationLevel)
    {
        this.compensationLevel = compensationLevel;
    }
    
    public int getCompensationLevel()
    {
        return compensationLevel;
    }
    
    public void setIsAdvertised(boolean isAdvertised)
    {
        this.isAdvertised = isAdvertised;
    }
    
    public boolean getIsAdvertised()
    {
        return isAdvertised;
    }
    
    public void setExpiryDate(Date expiryDate)
    {
        this.expiryDate = expiryDate;
    }
    
    public Date getExpiryDate()
    {
        return expiryDate;
    }
    
    public void setCategory(String category)
    {
        this.category = category;
    }
    
    public String getCategory()
    {
        return category;
    }
    
    public void addSkill(String skill)
    {
        this.skillset.add(skill);
    }
    
    public void deleteSkill(String skill)
    {
        this.skillset.remove(skill);
    }
    
    public void setSkillset(ArrayList<String> skillset)
    {
        this.skillset = skillset;
    }
    
    public ArrayList<String> getSkillset()
    {
        return skillset;
    }
    
    public void setCreatedBy(String createdBy)
    {
       this.createdBy = createdBy;
    }
    
    public String getCreatedBy()
    {
        return createdBy;
    }
    
    public void displayDetails()
    {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("job name: "+jobName);
        System.out.println("description: "+description);
        System.out.println("company: "+company);
        System.out.println("location: "+location);
        System.out.println("salary: "+salary);
        System.out.println("compensation level: "+compensationLevel);
        System.out.println("is advertised: "+isAdvertised);
        System.out.println("expiry date: "+dateFormat.format(expiryDate));
        System.out.println("category: "+category);
        System.out.print("skill: ");
        for (String skill:skillset)
            System.out.print(skill+";");
        System.out.println();
        System.out.println("created by: "+createdBy);
    }
}