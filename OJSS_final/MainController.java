import java.util.*;
import java.io.*;
/**
 * Write a description of class MainController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainController
{
    private UserInterface userInterface;
    private Scanner scan;
    private JobApplicationController jobApplicationController;
    private JobController jobController;
    private JobMatchingController jobMatchingController;
    private AccountControl accountController;
    private String seekerAccountCredentials;
    private String recruiterAccountCredentials;
    private BufferedReader readingBuffer;
    private FileReader readingFile;
    private ArrayList<Job> jobList;
    private FileWriter writingFile;
    private BufferedWriter writingBuffer;
    String[] userNameAndPassword = new String[2];
    String userName;
    int userType;

    /**
     * Constructor for objects of class MainController
     */
    public MainController()
    {
        userInterface = new UserInterface();
        scan = new Scanner(System.in);
        jobApplicationController = new JobApplicationController();
        jobController = new JobController();
        jobController.readJobFile();
        jobMatchingController = new JobMatchingController();
        accountController = new AccountControl();
        accountController.readRecruiter();
        accountController.readJobSeeker();
        seekerAccountCredentials = System.getProperty("user.dir") + "\\seekerAccountCredentials.txt";
        recruiterAccountCredentials = System.getProperty("user.dir") + "\\recruiterAccountCredentials.txt";
        jobList = jobController.getAllJobList();
        jobMatchingController = new JobMatchingController();
        userType = 0;
    }

    public void start()
    {
        Boolean exit = false;
        while(!exit)
        {
            //login
            int startMenuInput = userInterface.startMenu();
            switch(startMenuInput)
            {
                //JobSeeker
                case 1: userNameAndPassword = userInterface.loginMenu();
                //System.out.println(userNameAndPassword[0] + " " +  userNameAndPassword[1]);
                Boolean correctSeekerCredentials = areCredentialsCorrect(seekerAccountCredentials, userNameAndPassword[0], userNameAndPassword[1] );
                System.out.println("are credentials correct: " + correctSeekerCredentials); //############################to be removed
                if(correctSeekerCredentials == true)
                {
                    userName = userNameAndPassword[0];
                }
                while( !exit && correctSeekerCredentials)//if correct credentials
                {
                    int input = userInterface.jobSeekerMenu();
                    switch(input)
                    {
                        case 1: accountController.modifyJobSeeker(userName);//modify jobseeker
                        break;
                        case 2: //search job
                        ArrayList<Job> searchList = jobApplicationController.searchJobs(jobList);
                        if(searchList.size() == 0)
                        {
                            System.out.println("no results found");
                        }
                        else
                        {
                            System.out.println("--------------------Search Result----------------------------------");
                            for(Job job: searchList)
                            {
                                System.out.println(job.getJobName());
                                System.out.println();
                            }
                        }
                        System.out.println("Enter 1 to get job details");
                        System.out.println("Enter 2 to go back");
                        int details = 0;
                        while (details < 1 || details > 2)
                        {
                            try {
                                details = scan.nextInt();
                            } catch (InputMismatchException e){
                                System.out.println("You should enter an integer.");
                                scan.nextLine();
                            }
                            if (details < 1 || details > 2)
                                System.out.println("Invalid input. Please enter the number between 1 and 2:\n");
                        }
                        if(details == 1)
                        {
                            System.out.println("Enter the job name to get the job details");
                            String jobName = scan.next();
                            Boolean jobFound = false;
                            for(Job job: searchList)
                            {
                                if(job.getJobName().equals(jobName))
                                {
                                    System.out.println();
                                    job.displayDetails();
                                    jobFound = true;
                                    break;
                                }
                                if(jobFound == false)
                                {
                                    System.out.println("The job name that you have entered not found");
                                }    
                            }
                            System.out.println("Enter the jobName to apply for");
                            String applyJobName = scan.next();
                            jobApplicationController.applyJob(applyJobName, userName); 
                        }
                        break;
                        case 3: System.out.println("------------The list of applied jobs are-----------------");
                        jobApplicationController.displayAppliedJobs(userName);
                        break;
                        case 4: jobApplicationController.viewInvitation(userName);
                        break;
                        case 5: accountController.modifyJobSeekerSkill(userName);
                        break;
                        case 6: exit = true;
                        jobController.writeJobFile();
                        accountController.writeJobSeeker();
                        accountController.writeRecruiter();
                        break;
                    }
                }
                break;
                //recruiter
                case 2: userNameAndPassword = userInterface.loginMenu();
                Boolean correctRecruiterCredentials = areCredentialsCorrect(recruiterAccountCredentials, userNameAndPassword[0], userNameAndPassword[1] );
                if(correctRecruiterCredentials == true)
                {
                    userName = userNameAndPassword[0];
                }
                while( !exit && correctRecruiterCredentials)
                {
                    int input = userInterface.jobRecruiterMenu();
                    switch(input)
                    {
                        case 1: jobController.seeExistingJobList(userName);
                        System.out.println("Enter 1 to advertise job");
                        System.out.println("Enter 2 to go back");
                        int advertiseOption = 0;
                        while (advertiseOption < 1 || advertiseOption > 2)
                        {
                            try {
                                advertiseOption = scan.nextInt();
                            } catch (InputMismatchException e){
                                System.out.println("You should enter an integer.");
                                scan.nextLine();
                            }
                            if (advertiseOption < 1 || advertiseOption > 2)
                                System.out.println("Invalid input. Please enter the number between 1 and 2:\n");
                        }
                        if(advertiseOption == 1)
                        {
                            jobController.advertiseJob(userName);
                        }
                        break;
                        case 2: accountController.modifyRecruiter(userName);
                        break;
                        case 3: jobController.createJob(userName);
                        break;
                        case 4: matchJob();
                        break;
                        case 5: jobApplicationController.sendInvitation(userName);
                        break;
                        case 6: jobController.modifyJobSkill(userName);
                        break;
                        case 7: exit = true;
                        jobController.writeJobFile();
                        accountController.writeJobSeeker();
                        accountController.writeRecruiter();
                        break;
                    }
                }
                break;
                //register
                case 3: Boolean userNameExists = true;
                String user = "";
                //userNameAndPassword = userInterface.loginMenu();
                while (userNameExists)
                {
                    System.out.println("Enter the user name. User name should be unique");
                    user = scan.next();
                    //chek for the userName in recruiter and seeker credential if exists?
                    userNameExists = isUserNameExists(recruiterAccountCredentials, user) || isUserNameExists(seekerAccountCredentials, user);
                }    
                userName = user;
                System.out.println("Enter the password");
                String password = scan.next();
                String seekerInput;
                String recruiterInput;
                int registrationType = 0;
//                 while( registrationType < 1 || registrationType > 2)
//                 {
                System.out.println("1. Register as Job Seeker");
                System.out.println("2. Register as Job Recruiter");
                
                while (registrationType < 1 || registrationType > 2)
                {
                    try {
                        registrationType = scan.nextInt();
                    } catch (InputMismatchException e){
                        System.out.println("You should enter an integer.");
                        scan.nextLine();
                    }
                    if (registrationType < 1 || registrationType > 2)
                        System.out.println("Invalid input. Please enter the number between 1 and 2:\n");
                }
//                     registrationType = scan.nextInt();
//                     if (registrationType < 1 || registrationType > 2 )
//                     {
//                         System.out.println("Invalid input");
//                     }
//                 }
                if (registrationType == 1)
                {
                    //update the credentials file
                    addCredentialsToFile(userName, password, seekerAccountCredentials);
                    accountController.createJobSeeker(userName);//with the help of accountCOntroller create a job seeker with the given input
                    userType = 1;//might not be needed
                    //after creating the job seeker go to login 
                }
                else if(registrationType == 2)
                {
                    //update the credentials file
                    addCredentialsToFile(userName, password, recruiterAccountCredentials);
                    accountController.createRecruiter(userName);//with the help of accountCOntroller create a job recruiter with the given input
                    userType = 2;//might not be needed
                    //after creating the job recruiter go to login
                }
                break;
                //logout
                case 4: exit = true;
                jobController.writeJobFile();
                accountController.writeJobSeeker();
                accountController.writeRecruiter();
                break;
                default:    System.out.println("Invalid Input");//can be used later if we want to add any functionality                            
            }   
        }
    }

    public void matchJob(){ 
        Scanner sc = new Scanner(System.in); 
        System.out.println("Please enter the job title:"); 
        String jobname = sc.nextLine();
        Job job = jobController.getJob(jobname); 
        System.out.println("Please enter the job seeker user name: "); 
        String jsname = sc.nextLine();
        JobSeeker jobSeeker = accountController.searchJobSeeker(jsname);
        int rank = jobMatchingController.matchJobSeeker(job, jobSeeker); 
        System.out.println("The compare result is: " + rank); 
    } 

    /**
     * Check for unique user name
     */
    private Boolean isUserNameExists(String filePath, String user)
    {
        try
        {
            readingFile = new FileReader(filePath);
            readingBuffer = new BufferedReader(readingFile);
            String readLine = "";
            while ((readLine = readingBuffer.readLine()) != null) 
            {
                String[] credentials = readLine.split(";");
                if (credentials[0].equals(user))
                {
                    return true;
                    //userName already exists 
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
        return false;
    }

    /**
     * Check the user credentials
     */
    private Boolean areCredentialsCorrect(String filePathName, String uName, String pwd)
    {
        //check either seekerCredentials file or recruiterCredentials file to check the credentials
        try
        {
            readingFile = new FileReader(filePathName);
            readingBuffer = new BufferedReader(readingFile);
            String readLine = "";
            Boolean credentialsMatch = false;
            while ((readLine = readingBuffer.readLine()) != null) 
            {
                String[] credentials = readLine.split(";");
                if (credentials[0].equals(uName) && credentials[1].equals(pwd))
                {
                    return true;
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
        return false;
    }

    private void addCredentialsToFile(String UserName, String password, String path)
    {
        try
        {
            new FileOutputStream(path, true).close(); //create a new file if file doesnot exists
            writingFile = new FileWriter(path, true);
            writingBuffer = new BufferedWriter(writingFile);
            writingBuffer.write(userName + ";" + password);
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
}
