import java.util.ArrayList;

/**
 * Created by Tahmidul on 28/11/2015.
 */
public class Schedule {
    private int totalCost;
    private int duration;
    private ArrayList<Assignment> assignments;

    public Schedule(){
        assignments = new ArrayList<>();
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
    }
}
