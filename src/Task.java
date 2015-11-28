import java.util.ArrayList;

/**
 * Created by Tahmidul on 28/11/2015.
 */
public class Task {
    private String taskId;
    private int duration;
    private ArrayList<Task> dependsOn;
    private ArrayList<Skill> needs;
    private Assignment assignment;

    public Task(){
        dependsOn = new ArrayList<>();
        needs = new ArrayList<>();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ArrayList<Task> getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(ArrayList<Task> dependsOn) {
        this.dependsOn = dependsOn;
    }

    public ArrayList<Skill> getNeeds() {
        return needs;
    }

    public void setNeeds(ArrayList<Skill> needs) {
        this.needs = needs;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public int totalDuration(){
        int totalDuration = 0;
        //Loop through all dependsOn Task
        for (Task task:dependsOn){
            int taskDuration = task.totalDuration();
            totalDuration += taskDuration;
        }
        //Add to this tasks duration
        totalDuration += duration;
        //And return
        return totalDuration;
    }
}
