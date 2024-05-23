package Model;

public class Suggestion {
    private String suggestedDate;
    private String suggestedTime;
    private String studentEmail;

    public Suggestion(String suggestedDate, String suggestedTime) {
        this.suggestedDate = suggestedDate;
        this.suggestedTime = suggestedTime;
        this.studentEmail = studentEmail;
    }

    public String getSuggestedDate() {
        return suggestedDate;
    }

    public void setSuggestedDate(String suggestedDate) {
        this.suggestedDate = suggestedDate;
    }

    public String getSuggestedTime() {
        return suggestedTime;
    }

    public void setSuggestedTime(String suggestedTime) {
        this.suggestedTime = suggestedTime;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
}
