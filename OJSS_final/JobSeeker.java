import java.util.*;

public class JobSeeker extends User {

    private String[] skillset;
    private String experience;
    private String education;

    public JobSeeker(){
        skillset = new String[3];
        experience = "";
        education = "";
    }

    public JobSeeker(String skill1, String skill2, String skill3, String experience, String education){
        skillset = new String[1];
        skillset[0] = skill1;
        skillset[1] = skill2;
        skillset[2] = skill3;
        this.experience = experience;
        this.education = education;
    }

    public void setSkillset(String skill1, String skill2, String skill3){
        skillset[0] = skill1;
        skillset[1] = skill2;
        skillset[2] = skill3;
    }

    public String[] getSkillSet(){
        return skillset;
    }

    public void setExperience(String experience){
        this.experience = experience;
    }

    public String getExperience(){
        return experience;
    }

    public void setEducation(String education){
        this.education = education;
    }

    public String getEducation(){
        return education;
    }

}