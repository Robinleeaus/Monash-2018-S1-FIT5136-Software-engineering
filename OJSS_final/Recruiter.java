public class Recruiter extends User{

    private String company;

    public Recruiter(){
        company = "";
    }
    
    public Recruiter(String company){
        this.company = company;
    }

    public void setCompany(String company){
        this.company = company;
    }

    public String getCompany(){
        return company;
    }
}