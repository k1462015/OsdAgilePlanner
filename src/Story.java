import java.util.ArrayList;

/**
 * Created by Tahmidul on 28/11/2015.
 */
public class Story {
    private String storyId;
    private ArrayList<Task> subTasks;


    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public ArrayList<Task> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(ArrayList<Task> subTasks) {
        this.subTasks = subTasks;
    }

    public void addSubTask(Task subTask){
        subTasks.add(subTask);
    }
}
