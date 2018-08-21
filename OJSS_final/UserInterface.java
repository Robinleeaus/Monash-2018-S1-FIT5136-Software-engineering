import java.util.*;
/**
 * Write a description of class UserInterface here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UserInterface
{
    // instance variables - replace the example below with your own
    private Scanner scan;
    /**
     * Constructor for objects of class UserInterface
     */
    public UserInterface()
    {
        // initialise instance variables
        scan = new Scanner(System.in);
    }

    public int startMenu()
    {
        int input = 0;
        System.out.println();
        System.out.println("Welcome to Online Job Seeking System");
        System.out.println("Would you like to login as:");
        System.out.println("1. JobSeeker");
        System.out.println("2. JobRecruiter");
        System.out.println("3. Register - If you are not an existing user create an account");
        System.out.println("4. Exit");
        while(input < 1 || input > 4)
        {
            try {
                input = scan.nextInt();
            } catch (InputMismatchException e){
                System.out.println("You should enter an integer.");
                scan.nextLine();
            }
            
            if (input < 1 || input > 4)
                System.out.println("Invalid input. Please enter the number between 1 and 4:\n");
        }
        return input;
    }
    
    public String[] loginMenu()
    {
        System.out.println();
        System.out.println("Enter the user name");
        String[] userNameAndPassword = new String[2];
        userNameAndPassword[0] = scan.next();
        System.out.println("Enter the password");
        userNameAndPassword[1] = scan.next();
        return userNameAndPassword;
    }
    
    public int jobSeekerMenu()
    {
        int input = 0;
        System.out.println();
        System.out.println("_______________________Main Menu_______________________");
        System.out.println("1. Modify account");
        System.out.println("2. Search job");
        System.out.println("3. View applied jobs");
        System.out.println("4. View invitation");
        System.out.println("5. Modify skillsets");
        System.out.println("6. Logout");
        while (input < 1 || input > 6)
        {
            try {
                input = scan.nextInt();
            } catch (InputMismatchException e){
                System.out.println("You should enter an integer.");
                scan.nextLine();
            }
            if (input < 1 || input > 6)
                System.out.println("Invalid input. Please enter the number between 1 and 5:\n");
        }
        return input;
    }
    
    public int jobRecruiterMenu()
    {
        int input = 0;
        System.out.println();
        System.out.println("_______________________Main Menu_______________________");
        System.out.println("1. View jobs created");
        System.out.println("2. Modify account");
        System.out.println("3. Create job");
        System.out.println("4. Match a job seeker");
        System.out.println("5. Send inviatation");
        System.out.println("6. Modify job skillsets");
        System.out.println("7. Logout");
        while (input < 1 || input > 7)
        {
            try {
                input = scan.nextInt();
            } catch (InputMismatchException e){
                System.out.println("You should enter an integer.");
                scan.nextLine();
            }
            if (input < 1 || input > 7)
                System.out.println("Invalid input. Please enter the number between 1 and 6:\n");
        }
        return input;
    }
}