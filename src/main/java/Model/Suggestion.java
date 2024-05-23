package Model;

public class Suggestion {
    private String suggestedDate;
    private String suggestedTime;
    private String studentEmail;
    private String lenda;

    public Suggestion(String suggestedDate, String suggestedTime, String lenda) {
        this.suggestedDate = suggestedDate;
        this.suggestedTime = suggestedTime;
        this.lenda = lenda;
    }

    // Getters and Setters

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

    public String getLenda() {
        return lenda;
    }

    public void setLenda(String lenda) {
        this.lenda = lenda;
    }
}
