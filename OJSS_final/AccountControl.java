import java.util.*;
import java.text.*;
import java.io.*;

public class AccountControl{

    private ArrayList<JobSeeker> listJobSeeker;
    private ArrayList<Recruiter> listRecruiter;

    public AccountControl(){
        listJobSeeker = new ArrayList<JobSeeker>();
        listRecruiter = new ArrayList<Recruiter>();
    }

    public AccountControl(ArrayList<JobSeeker> listJobSeeker, ArrayList<Recruiter> listRecruiter){
        this.listJobSeeker = listJobSeeker;
        this.listRecruiter = listRecruiter;
    }

    public void createJobSeeker(String userName){
        Scanner console =  new Scanner(System.in);
        JobSeeker jobSeeker = new JobSeeker();
        //DateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        jobSeeker.setType(1);
        jobSeeker.setUserName(userName);

        //Enter name
        String name = "";
        System.out.print("Please enter your name: ");
        name = console.nextLine().trim();
        while (name.equals("") || name.contains(";"))
        {
            enterAgain("a name");
            name = console.nextLine().trim();
        }
        jobSeeker.setName(name);
        
        //Enter phone number
        String phone = "";
        System.out.print("Please enter your phone number: ");
        phone = console.nextLine().trim();
        while (phone.equals("") || phone.contains(";"))
        {
            enterAgain("a phone number");
            phone = console.nextLine().trim();
        }
        jobSeeker.setPhoneNumber(phone);
        
        //Enter email
        String email = "";
        System.out.print("Please enter your email address: ");
        email = console.nextLine().trim();
        while (email.equals("") || email.contains(";") || !email.contains("@"))
        {
            System.out.println("An email address must contain \"@\" !!");
            enterAgain("an email address");
            email = console.nextLine().trim();
        }
        jobSeeker.setEmailAddress(email);
        
        //Enter date of birth
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("Please enter your date of birth (dd-mm-yyyy):");
        Date date = null;
        String line = "";
        while (date == null) {
            line = console.nextLine().trim();
            try {
                date = format.parse(line);
            } catch (ParseException e) {
                System.out.println("Sorry, that's not valid. Please try again.");
            }
        }
        jobSeeker.setDateOfBirth(line);
        
        //Enter first skill
        String skill1 = "";
        System.out.print("Please enter your first skill: ");
        skill1 = console.nextLine().trim();
        while (skill1.equals("") || skill1.contains(";"))
        {
            enterAgain("a skill");
            skill1 = console.nextLine().trim();
        }

        //Enter second skill
        String skill2 = "";
        System.out.print("Please enter your second skill: ");
        skill2 = console.nextLine().trim();
        while (skill2.contains(";"))
        {
            enterAgain("a skill");
            skill2 = console.nextLine().trim();
        }
        
        //Enter third skill
        String skill3 = "";
        System.out.print("Please enter your third skill: ");
        skill3 = console.nextLine().trim();
        while (skill3.contains(";"))
        {
            enterAgain("a skill");
            skill3 = console.nextLine().trim();
        }
        
        //Set skillset
        jobSeeker.setSkillset(skill1, skill2, skill3);
        
        //Enter experience
        String experience = "";
        System.out.print("Please enter your experience: ");
        experience = console.nextLine().trim();
        while (experience.equals("") || experience.contains(";"))
        {
            enterAgain("your experience");
            experience = console.nextLine().trim();
        }
        jobSeeker.setExperience(experience);
        
        //Enter education
        String education = "";
        System.out.print("Please enter your education: ");
        education = console.nextLine().trim();
        while (education.equals("") || education.contains(";"))
        {
            enterAgain("your education");
            education = console.nextLine().trim();
        }
        jobSeeker.setEducation(education);
        
        listJobSeeker.add(jobSeeker);
        System.out.println("New job seeker has been created!");
    }


    public void createRecruiter(String userName){
        Scanner console = new Scanner(System.in);
        Recruiter recruiter = new Recruiter();
        //DateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        recruiter.setType(2);
        recruiter.setUserName(userName);
        
        //Enter name
        String name = "";
        System.out.print("Please enter your name: ");
        name = console.nextLine().trim();
        while (name.equals("") || name.contains(";"))
        {
            enterAgain("a name");
            name = console.nextLine().trim();
        }
        recruiter.setName(name);
        
        //Enter phone number
        String phone = "";
        System.out.print("Please enter your phone number: ");
        phone = console.nextLine().trim();
        while (phone.equals("") || phone.contains(";"))
        {
            enterAgain("a phone number");
            phone = console.nextLine().trim();
        }
        recruiter.setPhoneNumber(phone);
        
        //Enter email
        String email = "";
        System.out.print("Please enter your email address: ");
        email = console.nextLine().trim();
        while (email.equals("") || email.contains(";") || !email.contains("@"))
        {
            System.out.println("An email address must contain \"@\" !!");
            enterAgain("an email address");
            email = console.nextLine().trim();
        }
        recruiter.setEmailAddress(email);
        
        //Enter date of birth
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("Please enter your date of birth (dd-mm-yyyy):");
        Date date = null;
        String line = "";
        while (date == null) {
            line = console.nextLine().trim();
            try {
                date = format.parse(line);
            } catch (ParseException e) {
                System.out.println("Sorry, that's not valid. Please try again.");
            }
        }
        recruiter.setDateOfBirth(line);
        
        //Enter company name
        String company = "";
        System.out.print("Please enter your company name: ");
        company = console.nextLine().trim();
        while (company.equals("") || company.contains(";"))
        {
            enterAgain("a company name");
            company = console.nextLine().trim();
        }
        recruiter.setCompany(company);
        
        listRecruiter.add(recruiter);
        System.out.println("New recruiter has been created! ");
    }

    public void viewJobSeeker(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the job seeker's user name: ");
        JobSeeker jobSeeker = searchJobSeeker(sc.nextLine());
        if (jobSeeker == null)
            System.out.println("We can't find the job seeker!");
        else
        {
            System.out.println(jobSeeker.getUserName() + ";" +
                                jobSeeker.getPhoneNumber() + ";" +
                                jobSeeker.getEmailAddress() + ";" +
                                jobSeeker.getDateOfBirth() + ";" +
                                jobSeeker.getSkillSet()[0] + ";" +
                                jobSeeker.getSkillSet()[1] + ";" +
                                jobSeeker.getSkillSet()[2] + ";" +
                                jobSeeker.getExperience() + ";" +
                                jobSeeker.getEducation());
        }
        
    }

    public void viewRecruiter(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the recruiter's user name: ");
        Recruiter recruiter = searchRecruiter(sc.nextLine());
        if (recruiter == null)
            System.out.println("We can't find the recruiter!");
        else
        {
            System.out.println(recruiter.getUserName() + ";" +
                                recruiter.getPhoneNumber() + ";" +
                                recruiter.getEmailAddress() + ";" +
                                recruiter.getDateOfBirth() + ";" +
                                recruiter.getCompany());
        }
    }

    public void modifyJobSeeker(String userName){
        Scanner console =  new Scanner(System.in);
        //DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        JobSeeker jobSeeker = searchJobSeeker(userName);
        if (jobSeeker == null)
            System.out.println("We can't find the job seeker!");
        else
        {
            //Enter name
            String name = "";
            System.out.print("Please enter your name: ");
            name = console.nextLine().trim();
            while (name.equals("") || name.contains(";"))
            {
                enterAgain("a name");
                name = console.nextLine().trim();
            }
            jobSeeker.setName(name);
            
            //Enter phone number
            String phone = "";
            System.out.print("Please enter your phone number: ");
            phone = console.nextLine().trim();
            while (phone.equals("") || phone.contains(";"))
            {
                enterAgain("a phone number");
                phone = console.nextLine().trim();
            }
            jobSeeker.setPhoneNumber(phone);
            
            //Enter email
            String email = "";
            System.out.print("Please enter your email address: ");
            email = console.nextLine().trim();
            while (email.equals("") || email.contains(";") || !email.contains("@"))
            {
                System.out.println("An email address must contain \"@\" !!");
                enterAgain("an email address");
                email = console.nextLine().trim();
            }
            jobSeeker.setEmailAddress(email);
            
            //Enter date of birth
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            System.out.println("Please enter your date of birth (dd-mm-yyyy):");
            Date date = null;
            String line = "";
            while (date == null) {
                line = console.nextLine().trim();
                try {
                    date = format.parse(line);
                } catch (ParseException e) {
                    System.out.println("Sorry, that's not valid. Please try again.");
                }
            }
            jobSeeker.setDateOfBirth(line);
            
            //Enter first skill
            String skill1 = "";
            System.out.print("Please enter your first skill: ");
            skill1 = console.nextLine().trim();
            while (skill1.equals("") || skill1.contains(";"))
            {
                enterAgain("a skill");
                skill1 = console.nextLine().trim();
            }
    
            //Enter second skill
            String skill2 = "";
            System.out.print("Please enter your second skill: ");
            skill2 = console.nextLine().trim();
            while (skill2.contains(";"))
            {
                enterAgain("a skill");
                skill2 = console.nextLine().trim();
            }
            
            //Enter third skill
            String skill3 = "";
            System.out.print("Please enter your third skill: ");
            skill3 = console.nextLine().trim();
            while (skill3.contains(";"))
            {
                enterAgain("a skill");
                skill3 = console.nextLine().trim();
            }
            
            //Set skillset
            jobSeeker.setSkillset(skill1, skill2, skill3);
            
            //Enter experience
            String experience = "";
            System.out.print("Please enter your experience: ");
            experience = console.nextLine().trim();
            while (experience.equals("") || experience.contains(";"))
            {
                enterAgain("your experience");
                experience = console.nextLine().trim();
            }
            jobSeeker.setExperience(experience);
            
            //Enter education
            String education = "";
            System.out.print("Please enter your education: ");
            education = console.nextLine().trim();
            while (education.equals("") || education.contains(";"))
            {
                enterAgain("your education");
                education = console.nextLine().trim();
            }
            jobSeeker.setEducation(education);
            
            System.out.println("Changes have been saved!! ");
        }
    }

    public void modifyRecruiter(String userName){
        Scanner console = new Scanner(System.in);
        //DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Recruiter recruiter = searchRecruiter(userName);
        if (recruiter == null)
            System.out.println("We can't find the recruiter!");
        else
        {
            //Enter name
            String name = "";
            System.out.print("Please enter your name: ");
            name = console.nextLine().trim();
            while (name.equals("") || name.contains(";"))
            {
                enterAgain("a name");
                name = console.nextLine().trim();
            }
            recruiter.setName(name);
            
            //Enter phone number
            String phone = "";
            System.out.print("Please enter your phone number: ");
            phone = console.nextLine().trim();
            while (phone.equals("") || phone.contains(";"))
            {
                enterAgain("a phone number");
                phone = console.nextLine().trim();
            }
            recruiter.setPhoneNumber(phone);
            
            //Enter email
            String email = "";
            System.out.print("Please enter your email address: ");
            email = console.nextLine().trim();
            while (email.equals("") || email.contains(";") || !email.contains("@"))
            {
                System.out.println("An email address must contain \"@\" !!");
                enterAgain("an email address");
                email = console.nextLine().trim();
            }
            recruiter.setEmailAddress(email);
            
            //Enter date of birth
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            System.out.println("Please enter your date of birth (dd-mm-yyyy):");
            Date date = null;
            String line = "";
            while (date == null) {
                line = console.nextLine().trim();
                try {
                    date = format.parse(line);
                } catch (ParseException e) {
                    System.out.println("Sorry, that's not valid. Please try again.");
                }
            }
            recruiter.setDateOfBirth(line);
            
            //Enter company name
            String company = "";
            System.out.print("Please enter your company name: ");
            company = console.nextLine().trim();
            while (company.equals("") || company.contains(";"))
            {
                enterAgain("a company name");
                company = console.nextLine().trim();
            }
            recruiter.setCompany(company);
            
            System.out.println("Changes have been saved!! ");
        }
    }

    public JobSeeker searchJobSeeker(String userName){
        JobSeeker js = new JobSeeker();
        for (JobSeeker jobSeeker : listJobSeeker)
            if (jobSeeker.getUserName().equalsIgnoreCase(userName))
                js = jobSeeker;
        return js;
    }

    public Recruiter searchRecruiter(String userName){
        Recruiter rec = new Recruiter();
        for ( Recruiter recruiter : listRecruiter )
            if (recruiter.getUserName().equalsIgnoreCase(userName))
                return recruiter;
        return rec;
    }

    public void deleteJobSeeker(String name){
        JobSeeker jobSeeker = searchJobSeeker(name);
        if (jobSeeker == null)
            System.out.println("We can't find the job seeker!");
        else
        {
            listJobSeeker.remove(jobSeeker);
            System.out.println("Succeed!");
        }
    }

    public void deleteRecruiter(String name){
        Recruiter recruiter = searchRecruiter(name);
        if (recruiter == null)
            System.out.println("We can't find the recruiter!");
        else
        {
            listRecruiter.remove(recruiter);
            System.out.println("Succeed!");
        }
    }

    public void readJobSeeker(){
        String filename = "JobSeeker.txt";
        try {
            //if file not found create a new file
            new FileOutputStream(filename, true).close(); //aaded by Sharat
            
            FileReader fileReader = new FileReader("JobSeeker.txt");
            Scanner sc = new Scanner(fileReader);

            while (sc.hasNext()){
                JobSeeker jobSeeker = new JobSeeker();
                String[] stringArray = sc.nextLine().split(";");
                jobSeeker.setType(1);
                jobSeeker.setUserName(stringArray[0]);
                jobSeeker.setName(stringArray[1]);
                jobSeeker.setPhoneNumber(stringArray[2]);
                jobSeeker.setEmailAddress(stringArray[3]);
                jobSeeker.setDateOfBirth(stringArray[4]);
                jobSeeker.setSkillset(stringArray[5], stringArray[6], stringArray[7]);
                jobSeeker.setExperience(stringArray[8]);
                jobSeeker.setEducation(stringArray[9]);
                listJobSeeker.add(jobSeeker);
            }
        } catch (FileNotFoundException e){
            System.out.println("Error! File not found!");
        }
        catch(IOException ioe)
        {
            System.out.println("Unexpected I/O error occured");
        }
    }

    public void writeJobSeeker(){
        String filename = "JobSeeker.txt";
        try{
            PrintWriter outputFile = new PrintWriter(filename);
            for(JobSeeker jobSeeker : listJobSeeker )
                outputFile.println( jobSeeker.getUserName() + ";" +
                                    jobSeeker.getName() + ";" +
                                    jobSeeker.getPhoneNumber() + ";" +
                                    jobSeeker.getEmailAddress() + ";" +
                                    jobSeeker.getDateOfBirth() + ";" +
                                    jobSeeker.getSkillSet()[0] + ";" +
                                    jobSeeker.getSkillSet()[1] + ";" +
                                    jobSeeker.getSkillSet()[2] + ";" +
                                    jobSeeker.getExperience() + ";" +
                                    jobSeeker.getEducation());
            outputFile.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Error! File not found!");
        }
    }

    public void readRecruiter(){
        String filename = "Recruiter.txt";
        try {
            //if file not found create a new file
            new FileOutputStream(filename, true).close(); //aaded by Sharat
            
            FileReader fileReader = new FileReader(filename);
            Scanner sc = new Scanner(fileReader);

            while (sc.hasNext()) {
                Recruiter recruiter = new Recruiter();
                String[] stringArray = sc.next().split(";");
                recruiter.setType(2);
                recruiter.setUserName(stringArray[0]);
                recruiter.setName(stringArray[1]);
                recruiter.setPhoneNumber(stringArray[2]);
                recruiter.setEmailAddress(stringArray[3]);
                recruiter.setDateOfBirth(stringArray[4]);
                recruiter.setCompany(stringArray[5]);
                listRecruiter.add(recruiter);
            }
        } catch(FileNotFoundException e){
            System.out.println("Error! File not found! ");
        }
        catch(IOException ioe)
        {
            System.out.println("Unexpected I/O error occured");
        }
    }

    public void writeRecruiter(){
        String filename = "Recruiter.txt";
        try{
            PrintWriter outputFile = new PrintWriter(filename);
            for (Recruiter recruiter : listRecruiter)
                outputFile.println(recruiter.getUserName() + ";" +
                                    recruiter.getName() + ";" +
                                    recruiter.getPhoneNumber() + ";" +
                                    recruiter.getEmailAddress() + ";" +
                                    recruiter.getDateOfBirth() + ";" +
                                    recruiter.getCompany());
            outputFile.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Error! File not found!");
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
    
    public void modifyJobSeekerSkill(String userName){
        Scanner console =  new Scanner(System.in);
        //DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        JobSeeker jobSeeker = searchJobSeeker(userName);
        if (jobSeeker == null)
            System.out.println("We can't find the job seeker!");
        else
        {    
            //Enter first skill
            String skill1 = "";
            System.out.print("Please enter your first skill: ");
            skill1 = console.nextLine().trim();
            while (skill1.equals("") || skill1.contains(";"))
            {
                enterAgain("a skill");
                skill1 = console.nextLine().trim();
            }
    
            //Enter second skill
            String skill2 = "";
            System.out.print("Please enter your second skill: ");
            skill2 = console.nextLine().trim();
            while (skill2.contains(";"))
            {
                enterAgain("a skill");
                skill2 = console.nextLine().trim();
            }
            
            //Enter third skill
            String skill3 = "";
            System.out.print("Please enter your third skill: ");
            skill3 = console.nextLine().trim();
            while (skill3.contains(";"))
            {
                enterAgain("a skill");
                skill3 = console.nextLine().trim();
            }
            
            //Set skillset
            jobSeeker.setSkillset(skill1, skill2, skill3);

            System.out.println("Skills have been changed!! ");
        }
    }
}