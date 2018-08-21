import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.io.*;

/**
 * Write a description of class JobController here.
 * 
 * @author Robin 
 * @version 23/05/2018
 */
public class JobController
{
    private ArrayList<Job> jobList;

    /**
     * Default constructor for objects of class JobController
     */
    public JobController()
    {
        // initialise instance variables
        jobList = new ArrayList<Job>();
    }

    /**
     * Other constructor for objects of class JobController
     */
    public JobController(ArrayList<Job> jobList)
    {
        // initialise instance variables
        this.jobList = jobList;
    }
    
    /**
     * Create a job for the recruiter
     * 
     * @param recruiterUserName recruiter user name
     */
    public void createJob(String recruiterUserName)
    {
        Scanner console = new Scanner(System.in);
        Job job = new Job();

        //Enter job name
        String jobName = "";
        System.out.print("Please enter a job name: ");
        jobName = console.nextLine().trim();
        // check if the job already existed
        boolean found = false;
        boolean finishSearch = false;

        while (jobName.equals("") || jobName.contains(";") || finishSearch == false)
        {
            // check if the job already existed
            if (jobList.size() == 0)
                finishSearch = true;
            else
            {
                int index = 0;
                while (index < jobList.size())
                {
                    Job jobInList = jobList.get(index);
                    // job is found in the jobList
                    if (jobInList.getJobName().equalsIgnoreCase(jobName))
                    {
                        found = true;
                        System.out.println("The following job has been found.");
                        jobInList.displayDetails();
                        System.out.println("");
                    }
                    index++;
                }
                finishSearch = true;
            }
            
            // if jobName contains "" or ";" or is found in the jobList
            if  (jobName.equals("") || jobName.contains(";") || found == true )
            {
                if (found == true)
                    System.out.println("A same job already existed!!");
                enterAgain("a job name");
                jobName = console.nextLine().trim();
                found = false;
                finishSearch = false;
            }
        }
        job.setJobName(jobName);
        
        //Enter description
        String description = "";
        System.out.print("Please enter a description: ");
        description = console.nextLine().trim();
        while (description.equals("") || description.contains(";"))
        {
            enterAgain("a description");
            description = console.nextLine().trim();
        }
        job.setDescription(description);
        
        //Enter company
        String company = "";
        System.out.print("Please enter a company: ");
        company = console.nextLine().trim();
        while (company.equals("") || company.contains(";"))
        {
            enterAgain("a company");
            company = console.nextLine().trim();
        }
        job.setCompany(company);
        
        //Enter location
        String location = "";
        System.out.print("Please enter a location: ");
        location = console.nextLine().trim();
        while (location.equals("") || location.contains(";"))
        {
            enterAgain("a location");
            location = console.nextLine().trim();
        }
        job.setLocation(location);
        
        //Enter salary
        String salaryString = "";
        int salary = 0;
        while (salary == 0) {
            System.out.print("Please enter a salary: ");
            salaryString = console.nextLine().trim();
            while (salaryString.length() == 0)
            {
                enterAgain("a salary");
                salaryString = console.nextLine().trim();
            }
            try {
                salary = Integer.parseInt(salaryString);
            } catch (NumberFormatException e) {
                System.out.println("You should enter an integer.");
            }
        }
        job.setSalary(salary);
        
        //Enter compensation level
        String compensationLevelString = "";
        int compensationLevel = 0;
        while (compensationLevel == 0) {
            System.out.print("Please enter a compensation level: ");
            compensationLevelString = console.nextLine().trim();
            while (compensationLevelString.length() == 0)
            {
                enterAgain("a compensation level");
                compensationLevelString = console.nextLine().trim();
            }
            try {
                compensationLevel = Integer.parseInt(compensationLevelString);
            } catch (NumberFormatException e) {
                System.out.println("You should enter an integer.");
            }
        }
        job.setCompensationLevel(compensationLevel);
        
        //Enter expiryDate
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("Enter date and time in the format dd-MM-yyyy");
        System.out.println("For example, it is now " + format.format(new Date()));
        Date date = null;
        while (date == null) {
            String line = console.nextLine().trim();
            try {
                date = format.parse(line);
            } catch (ParseException e) {
                System.out.println("Sorry, that's not valid. Please try again.");
            }
        }
        job.setExpiryDate(date);
        
        //Enter category
        String category = "";
        System.out.print("Please enter a category: ");
        category = console.nextLine().trim();
        while (category.equals("") || category.contains(";"))
        {
            enterAgain("a category");
            category = console.nextLine().trim();
        }
        job.setCategory(category);
        
        //Enter skillset
        String skillName = "";

        System.out.println("Skill set : ");
        System.out.println("You need to enter at least 1 and up to 3 skill names.");
        System.out.println("Blank string in skill 2 or 3 means that there is no skill after that field.");
        for (int index = 1; index <= 3; index++)
        {
            System.out.print("Please enter skill " + index + " : ");
            skillName = console.nextLine().trim();
            if (index == 1)
            {
                while (skillName.equals("") || skillName.contains(";"))
                {
                    enterAgain("a skill name");
                    skillName = console.nextLine().trim();
                }
            }
            else
            {
                while (skillName.contains(","))
                {
                    System.out.println("");
                    System.out.println("A skill name must not contain \";\" !!");
                    System.out.print("Please enter a skill name again: ");
                    skillName = console.nextLine().trim();
                }
                if (skillName.equals(""))
                    while (index < 3)
                    {
                        job.addSkill(skillName);
                        index++;
                    }
            }
            job.addSkill(skillName);
        }
        
        //set createdBy
        job.setCreatedBy(recruiterUserName);
        
        //display all
        //job.displayDetails();
        
        jobList.add(job);
        
        System.out.println(job.getJobName() + " has been created.");
    }

    /**
     * View job details for the recruiter
     * 
     * @param recruiterUserName recruiter user name
     */
    public void viewJobDetails(String recruiterUserName)
    {
        seeExistingJobList(recruiterUserName);
        System.out.println("Please enter the job name which you want to view: ");
        
        Scanner console = new Scanner(System.in);
        String searchString = "";
        
        searchString = console.nextLine().trim();
        int index = 0;
        boolean found = false;
        
        while (index < jobList.size())
        {
            Job job = new Job();
            job = jobList.get(index);
            if (job.getCreatedBy().equalsIgnoreCase(recruiterUserName))
            {
                while (searchString.equals(""))
                {
                    System.out.println("Please enter the job name which you want to view: ");
                    searchString = console.nextLine().trim();
                }

                if (job.getJobName().equalsIgnoreCase(searchString))
                {
                    found = true;
                    System.out.println("The following job has been found.");
                    job.displayDetails();
                    System.out.println("");
                }
            }
            index++;
        }
        
        if (found == false)
            System.out.println("No job has been found.");
    }
    
    /**
     * Modify a job for the recruiter
     * 
     * @param recruiterUserName recruiter user name
     */
    public void modifyJob(String recruiterUserName)
    {
        seeExistingJobList(recruiterUserName);
        
        System.out.println("Please enter the job name which you want to modify: ");
        
        Scanner console = new Scanner(System.in);
        String searchString = "";
        
        searchString = console.nextLine().trim();
        int index = 0;
        boolean edited = false;
        
        while (index < jobList.size())
        {
            Job job = new Job();
            job = jobList.get(index);
            if (job.getCreatedBy().equalsIgnoreCase(recruiterUserName))
            {
                while (searchString.equals(""))
                {
                    System.out.println("Please enter the job name which you want to modify: ");
                    searchString = console.nextLine().trim();
                }
    
                if (job.getJobName().equalsIgnoreCase(searchString))
                {
                    String choice = "";
                    
                    System.out.println("The following job has been found.");
                    job.displayDetails();
                    System.out.println("");
                    System.out.println("Would you like to edit it? (Enter y to edit it) ");
                    choice = console.nextLine().trim();
                    if (choice.equals("y"))
                    {
                        //Edit description
                        String description = "";
                        System.out.print("Please enter a description: ");
                        description = console.nextLine().trim();
                        while (description.equals("") || description.contains(";"))
                        {
                            enterAgain("a description");
                            description = console.nextLine().trim();
                        }
                        job.setDescription(description);
                        
                        //Edit company
                        String company = "";
                        System.out.print("Please enter a company: ");
                        company = console.nextLine().trim();
                        while (company.equals("") || company.contains(";"))
                        {
                            enterAgain("a company");
                            company = console.nextLine().trim();
                        }
                        job.setCompany(company);
                        
                        //Edit location
                        String location = "";
                        System.out.print("Please enter a location: ");
                        location = console.nextLine().trim();
                        while (location.equals("") || location.contains(";"))
                        {
                            enterAgain("a location");
                            location = console.nextLine().trim();
                        }
                        job.setLocation(location);
                        
                        //Edit salary
                        String salaryString = "";
                        int salary = 0;
                        while (salary == 0) {
                            System.out.print("Please enter a salary: ");
                            salaryString = console.nextLine().trim();
                            while (salaryString.length() == 0)
                            {
                                enterAgain("a salary");
                                salaryString = console.nextLine().trim();
                            }
                            try {
                                salary = Integer.parseInt(salaryString);
                            } catch (NumberFormatException e) {
                                System.out.println("You should enter an integer.");
                            }
                        }
                        job.setSalary(salary);
                        
                        //Edit compensation level
                        String compensationLevelString = "";
                        int compensationLevel = 0;
                        while (compensationLevel == 0) {
                            System.out.print("Please enter a compensation level: ");
                            compensationLevelString = console.nextLine().trim();
                            while (compensationLevelString.length() == 0)
                            {
                                enterAgain("a compensation level");
                                compensationLevelString = console.nextLine().trim();
                            }
                            try {
                                compensationLevel = Integer.parseInt(compensationLevelString);
                            } catch (NumberFormatException e) {
                                System.out.println("You should enter an integer.");
                            }
                        }
                        job.setCompensationLevel(compensationLevel);
                        
                        //Edit expiryDate
                        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                        System.out.println("Enter date and time in the format dd-MM-yyyy");
                        System.out.println("For example, it is now " + format.format(new Date()));
                        Date date = null;
                        while (date == null) {
                            String line = console.nextLine().trim();
                            try {
                                date = format.parse(line);
                            } catch (ParseException e) {
                                System.out.println("Sorry, that's not valid. Please try again.");
                            }
                        }
                        job.setExpiryDate(date);
                        
                        //Edit category
                        String category = "";
                        System.out.print("Please enter a category: ");
                        category = console.nextLine().trim();
                        while (category.equals("") || category.contains(","))
                        {
                            enterAgain("a category");
                            category = console.nextLine().trim();
                        }
                        job.setCategory(category);
                        
                        //Edit skillset
                        String skillName = "";
                        ArrayList<String> skillSet = new ArrayList<String>();
                
                        System.out.println("Skill set : ");
                        System.out.println("You need to enter at least 1 and up to 3 skill names.");
                        System.out.println("Blank string in skill 2 or 3 means that there is no skill after that field.");
                        for (int indexSkill = 1; indexSkill <= 3; indexSkill++)
                        {
                            System.out.print("Please enter skill " + indexSkill + " : ");
                            skillName = console.nextLine().trim();
                            if (indexSkill == 1)
                            {
                                while (skillName.equals("") || skillName.contains(";"))
                                {
                                    enterAgain("a skill name");
                                    skillName = console.nextLine().trim();
                                }
                            }
                            else
                            {
                                while (skillName.contains(";"))
                                {
                                    System.out.println("");
                                    System.out.println("A skill name must not contain \";\" !!");
                                    System.out.print("Please enter a skill name again: ");
                                    skillName = console.nextLine().trim();
                                }
                                if (skillName.equals(""))
                                    while (indexSkill < 3)
                                    {
                                        skillSet.add(skillName);
                                        indexSkill++;
                                    }
                            }
                            skillSet.add(skillName);
                        }
                        job.setSkillset(skillSet);
                        
                        System.out.println("");
                        System.out.println(job.getJobName() + " has been edited.");
                        edited = true;
                    }
                }
            }
            index++;
        } 

        if (!edited)
            System.out.println("No job has been found or edited.");
        System.out.println("");
    }
    
    /**
     * Delete a job for the recruiter
     * 
     * @param recruiterUserName recruiter user name
     */
    public void deleteJob(String recruiterUserName)
    {
        Scanner console = new Scanner(System.in);
        String searchString = "";
        boolean deleted = false;
        
        seeExistingJobList(recruiterUserName);
        System.out.println("Please enter the job name which you want to delete: ");
        searchString = console.nextLine().trim();
        int index = 0;

        while (index < jobList.size())
        {
            Job job = new Job();
            job = jobList.get(index);
            if (job.getCreatedBy().equalsIgnoreCase(recruiterUserName))
            {
                while (searchString.equals(""))
                {
                    System.out.println("Please enter the job name which you want to delete: ");
                    searchString = console.nextLine().trim();
                }
                if (job.getJobName().equalsIgnoreCase(searchString))
                {
                    String choice = "";
                    
                    System.out.println("The following job has been found.");
                    job.displayDetails();
                    System.out.println("");
                    System.out.println("Would you like to delete it? (Enter y to delete it) ");
                    choice = console.nextLine().trim();
                    if (choice.equals("y"))
                    {
                        System.out.println(jobList.get(index).getJobName() + " has been deleted.");
                        jobList.remove(job);
                        deleted = true;
                    }
                }
            }
            index++;
        }
        
        if (!deleted)
            System.out.println("No job has been found or deleted.");
            
        System.out.println("");
    }
    
    /**
     * Advertise a job for the recruiter
     * 
     * @param recruiterUserName recruiter user name
     */
    public void advertiseJob(String recruiterUserName)
    {
        Scanner console = new Scanner(System.in);
        String searchString = "";
        boolean found = false;
        
        seeExistingJobList(recruiterUserName);
        System.out.println("Please enter the job name which you want to change the advertisement status: ");
        searchString = console.nextLine().trim();
        int index = 0;

        while (index < jobList.size())
        {
            Job job = new Job();
            job = jobList.get(index);
            if (job.getCreatedBy().equalsIgnoreCase(recruiterUserName))
            {
                while (searchString.equals(""))
                {
                    System.out.println("Please enter the job name which you want to change the advertisement status: ");
                    searchString = console.nextLine().trim();
                }
                if (job.getJobName().equalsIgnoreCase(searchString))
                {
                    String choice = "";
                    
                    System.out.println("The following job has been found.");
                    job.displayDetails();
                    System.out.println("");
                    System.out.println("What would you like to do for advertisement?");
                    System.out.println("Please press 1 to make it active, 2 to make it inactive or enter to exit:");
                    choice = console.nextLine().trim();
                    if (choice.equals("1"))
                    {
                        job.setIsAdvertised(true);
                        System.out.println(jobList.get(index).getJobName() + " advertisement has been made active.");
                    }
                    else if(choice.equals("2"))
                    {
                        job.setIsAdvertised(false);
                        System.out.println(jobList.get(index).getJobName() + " advertisement has been made inactive.");
                    }
                    found = true;
                }
            }
            index++;
        }
        
        if (!found)
            System.out.println("No job has been found.");
            
        System.out.println("");
    }

    /**
     * Get a job list for the recruiter
     * 
     * @param recruiterUserName recruiter user name
     * @return recruiter job list
     */
    public ArrayList<Job> getJobList(String recruiterUserName)
    {        
        ArrayList<Job> recruiterJobList = new ArrayList<Job>();
        for (Job job : jobList)
        {
            if (job.getCreatedBy().equalsIgnoreCase(recruiterUserName))
                recruiterJobList.add(job);
        }
        return recruiterJobList;
    }
    
    /**
     * Write the job file
     * 
     */
    public void readJobFile()
    {
        String filename = "Job.txt";
        try
        {
            //if file not found create a new file
            new FileOutputStream(filename, true).close(); //aaded by Sharat
            //
            FileReader inputFile = new FileReader(filename);
            Scanner parser = new Scanner(inputFile);
            while (parser.hasNextLine())
            {
                String[] stringArray = parser.nextLine().split(";");
                Job job = new Job();
                job.setJobName(stringArray[0]);
                job.setDescription(stringArray[1]);
                job.setCompany(stringArray[2]);
                job.setLocation(stringArray[3]);
                job.setSalary(Integer.parseInt(stringArray[4]));
                job.setCompensationLevel(Integer.parseInt(stringArray[5]));
                job.setIsAdvertised(Boolean.parseBoolean(stringArray[6]));
                // convert String to Date
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date = null;
                try {
                    date = dateFormat.parse(stringArray[7]);
                } catch (ParseException e) {
                    System.out.println("Sorry, that's not valid. Please try again.");
                }
                job.setExpiryDate(date);
                job.setCategory(stringArray[8]);
                for (int index = 1; index <= 3; index++)
                {
                    job.addSkill(stringArray[index+8]);
                }
                job.setCreatedBy(stringArray[12]);
                
                jobList.add(job);
            }
            inputFile.close();
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println(filename + " is not found");
        }
        catch(IOException ioe)
        {
            System.out.println("Unexpected I/O error occured");
        }
    }
    
    /**
     * Write the job file
     * 
     */
    public void writeJobFile()
    {
        String filename = "Job.txt";
        //if file not found create a new file
        try
        {
            PrintWriter outputFile = new PrintWriter(filename);
            for (Job job : jobList)
            {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                outputFile.print(job.getJobName() + ";" + 
                                 job.getDescription() + ";" +
                                 job.getCompany() + ";" +
                                 job.getLocation() + ";" +
                                 job.getSalary() + ";" +
                                 job.getCompensationLevel() + ";" +
                                 job.getIsAdvertised() + ";" +
                                 dateFormat.format(job.getExpiryDate()) + ";" +
                                 job.getCategory() + ";");
                for (int index = 0; index < 3; index++ )
                {
                    if (index < job.getSkillset().size())
                        outputFile.print(job.getSkillset().get(index));
                    outputFile.print(";");
                }
                outputFile.print(job.getCreatedBy());
                outputFile.println();
            }
            outputFile.close();
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println(filename + " is not found");
        }
    }
    
    /**
     * Error message for unaccepted input
     * 
     * @param item item
     */
    public void enterAgain(String item)
    {
            System.out.println("");
            System.out.println(item + " must not contain blank or \";\" !!");
            System.out.print("Please enter " + item + " again: ");
    }
    
    /**
     * See the existing job list
     * 
     * @param recruiterUserName recruiter user name
     */
    public void seeExistingJobList(String recruiterUserName)
    {
        Scanner console = new Scanner(System.in);
        String choice = "";
        
        System.out.println("Do you want to see the existing job list? (Enter y to see it) ");
        choice = console.nextLine().trim();
        if (choice.equals("y"))
        {
            System.out.println("");
            System.out.println("The existing jobs are: ");
            for (Job job : jobList)
            {
                if (job.getCreatedBy().equalsIgnoreCase(recruiterUserName))
                    System.out.println(job.getJobName());
            }
            System.out.println("");
        }
    }
    
    /**
     * Set function for the attribute jobList
     * 
     * @param jobList job list
     */
    public void setAllJobList(ArrayList<Job> jobList)
    {
        this.jobList = jobList;
    }
    
    /**
     * Get function for the attribute jobList
     * 
     * @return jobList job list
     */
    public ArrayList<Job> getAllJobList()
    {
        return jobList;
    }
    
    /**
     * Get a job object based on job name
     * 
     * @param jobName job name
     * @return recruiter job list
     */
    public Job getJob(String jobName)
    {        
        Job jobReturn = new Job();
        for (Job job : jobList)
        {
            if (job.getJobName().equalsIgnoreCase(jobName))
                jobReturn = job;
        }
        return jobReturn;
    }
    
    /**
     * Modify job kills for the recruiter
     * 
     * @param recruiterUserName recruiter user name
     */
    public void modifyJobSkill(String recruiterUserName)
    {
        seeExistingJobList(recruiterUserName);
        
        System.out.println("Please enter the job name which you want to modify: ");
        
        Scanner console = new Scanner(System.in);
        String searchString = "";
        
        searchString = console.nextLine().trim();
        int index = 0;
        boolean edited = false;
        
        while (index < jobList.size())
        {
            Job job = new Job();
            job = jobList.get(index);
            if (job.getCreatedBy().equalsIgnoreCase(recruiterUserName))
            {
                while (searchString.equals(""))
                {
                    System.out.println("Please enter the job name which you want to modify: ");
                    searchString = console.nextLine().trim();
                }
    
                if (job.getJobName().equalsIgnoreCase(searchString))
                {
                    String choice = "";
                    
                    System.out.println("The following job has been found.");
                    job.displayDetails();
                    System.out.println("");
                    System.out.println("Would you like to edit skills for it? (Enter y to edit it) ");
                    choice = console.nextLine().trim();
                    if (choice.equals("y"))
                    {                       
                        //Edit skillset
                        String skillName = "";
                        ArrayList<String> skillSet = new ArrayList<String>();
                
                        System.out.println("Skill set : ");
                        System.out.println("You need to enter at least 1 and up to 3 skill names.");
                        System.out.println("Blank string in skill 2 or 3 means that there is no skill after that field.");
                        for (int indexSkill = 1; indexSkill <= 3; indexSkill++)
                        {
                            System.out.print("Please enter skill " + indexSkill + " : ");
                            skillName = console.nextLine().trim();
                            if (indexSkill == 1)
                            {
                                while (skillName.equals("") || skillName.contains(";"))
                                {
                                    enterAgain("a skill name");
                                    skillName = console.nextLine().trim();
                                }
                            }
                            else
                            {
                                while (skillName.contains(";"))
                                {
                                    System.out.println("");
                                    System.out.println("A skill name must not contain \";\" !!");
                                    System.out.print("Please enter a skill name again: ");
                                    skillName = console.nextLine().trim();
                                }
                                if (skillName.equals(""))
                                    while (indexSkill < 3)
                                    {
                                        skillSet.add(skillName);
                                        indexSkill++;
                                    }
                            }
                            skillSet.add(skillName);
                        }
                        job.setSkillset(skillSet);
                        
                        System.out.println("");
                        System.out.println(job.getJobName() + " skills have been edited.");
                        edited = true;
                    }
                }
            }
            index++;
        } 

        if (!edited)
            System.out.println("No job has been found or edited.");
        System.out.println("");
    }
}