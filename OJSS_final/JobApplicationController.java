import java.util.*;
import java.io.*;
/**
 * Write a description of class JobApplicationController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class JobApplicationController
{
    // in the main controller class create a joblist which reads the file and create a list of jobs
    Scanner scan;
    ArrayList<String> keywordList;
    ArrayList<Job> jobSearchList;
    FileReader readingFile;
    FileWriter writingFile;
    String path;
    BufferedReader readingBuffer;
    BufferedWriter writingBuffer;
    
    /**
     * Constructor for objects of class JobApplicationController
     */
    public JobApplicationController()
    {
        // initialise instance variables
        keywordList = new ArrayList<String>();
        jobSearchList = new ArrayList<Job>();
        scan = new Scanner(System.in);
        path = System.getProperty("user.dir") + "\\applicationDetails.txt";
    }

    private void inputKeyword()
    {
        System.out.println("Enter keywords to be searched seperated by commas in a single line");
        String inputString = scan.nextLine();
        Collections.addAll(keywordList, inputString.split("\\s*,\\s*"));
    }
    
    //returning list of jobs which helps to access the job details easily
    //can add searching skillset list later
    public ArrayList<Job> searchJobs(ArrayList<Job> jobList)
    {
        //get keywords
        inputKeyword();
        for( Job j : jobList)
        {
            if (j.getIsAdvertised() == true)
            {
                for(String keyword: keywordList)
                {
                   String keywordLowerCase = keyword.toLowerCase();
                   if (j.getJobName().toLowerCase().contains(keywordLowerCase) 
                   || j.getDescription().toLowerCase().contains(keywordLowerCase)
                   || j.getCompany().toLowerCase().contains(keywordLowerCase)
                   || j.getLocation().toLowerCase().contains(keywordLowerCase)
                   || j.getCategory().toLowerCase().contains(keywordLowerCase))
                   {
                       jobSearchList.add(j);
                   }
                }
            }
        }
        keywordList.clear();
        return jobSearchList;
    }
    
   public Boolean applyJob(String jobName, String userName)
   {
       //check if the job seeker already applied for the job else apply.  
       //applicationId jobName, recruiterId, seekerId, invitationId, seekerEmailAddress
        Boolean isApplied = false;
        try
        {
            readingFile = new FileReader(path);
            readingBuffer = new BufferedReader(readingFile);
            String readLine = "";
            while ((readLine = readingBuffer.readLine()) != null) 
            {
                String[] applicationLine = readLine.split(";");
                if (applicationLine[0].equals(jobName) && applicationLine[1].equals(userName))
                {
                    System.out.println("Already applied for this job");
                    isApplied = true;
                }
            }
            try
            {
                readingBuffer.close();
                readingFile.close();
            }
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        if (isApplied != true)
        {
            try
            {
                writingFile = new FileWriter(path);
                writingBuffer = new BufferedWriter(writingFile);
                writingBuffer.write(jobName + ";" + userName);
                writingBuffer.newLine();
                try
                {
                    writingBuffer.close();
                    writingFile.close();
                }
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
            catch (IOException e) 
            {
                 e.printStackTrace();
            }
        }
        return isApplied;
    }
    
    public ArrayList<String> viewApplicants(String jobId)
    {
        ArrayList<String> applicantList = new ArrayList<>();
        try
        {
            readingFile = new FileReader(path);
            readingBuffer = new BufferedReader(readingFile);
            String readLine = "";
            while ((readLine = readingBuffer.readLine()) != null) 
            {
                String[] applicationLine = readLine.split(";");
                if (applicationLine[0].equals(jobId))
                {
                    applicantList.add(applicationLine[1]);
                }
            }
            try
            {
                readingBuffer.close();
                readingFile.close();
            }
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return applicantList;
    }
    
    public void sendInvitation(String userName)
    {
        //go through the applicant file and get the email address.
        String emailAddress;
        ArrayList<String> inviteList = new ArrayList<>();
        System.out.println("Enter the jobName");
        String jobName = scan.next();
        System.out.println("Enter the user names seperated by , to send invitations");
        String inputString = scan.nextLine();
        Collections.addAll(inviteList, inputString.split("\\s*,\\s*"));
        //sending invite
        Invitation invitation = new Invitation();
        for(String seeker: inviteList)
        {
            invitation.writeInvite(userName, seeker, jobName);
            System.out.println("Invitation has been sent successfully to " + seeker + " for the job " + jobName);
        }
    }
    
    public void viewInvitation(String userName)
    {
        Invitation invitation = new Invitation();
        invitation.readInvite(userName);
    }
    
    public void displayAppliedJobs(String userName)
    {
        try
        {
            readingFile = new FileReader(path);
            readingBuffer = new BufferedReader(readingFile);
            String readLine = "";
            while ((readLine = readingBuffer.readLine()) != null) 
            {
                    String[] appliedJobs = readLine.split(";");
                    if(appliedJobs[1].toLowerCase().equals(userName.toLowerCase()))
                    {
                        System.out.println("You have applied for the job: " + appliedJobs[0]);
                    }
            }
            try
                {
                    readingBuffer.close();
                    readingFile.close();
                }
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
        }
        catch (IOException e) 
        {
                e.printStackTrace();
        }
    }
}
