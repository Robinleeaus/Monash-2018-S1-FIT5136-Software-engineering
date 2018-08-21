import java.util.*;

public class JobMatchingController {

    private JobSeeker jobSeeker;
    private Job job;
    private int rank;

    public JobMatchingController(){
        jobSeeker = new JobSeeker();
        job = new Job();
        rank = 0;
    }

    public JobMatchingController(JobSeeker jobSeeker, Job job){
        this.jobSeeker = jobSeeker;
        this.job = job;
    }

    public int matchJobSeeker(Job job, JobSeeker jobSeeker){
        int rank = 0;
        
        this.job = job;
        this.jobSeeker = jobSeeker;

        String[] seekSkillset = jobSeeker.getSkillSet();
        ArrayList<String> jobSkillset = job.getSkillset();

        for (String string: jobSkillset)
            for (int i = 0;i < 3;i++)
                if (string.equalsIgnoreCase(seekSkillset[i]))
                    rank++;
        return rank;
    }
}