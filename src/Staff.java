import java.util.ArrayList;

/**
 * Created by Tahmidul on 28/11/2015.
 */
public class Staff {
    private String staffId;
    private int costDay;
    private ArrayList<Skill> has;
    private Assignment assigned;

    public Staff(){
        has = new ArrayList<>();
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public int getCostDay() {
        return costDay;
    }

    public void setCostDay(int costDay) {
        this.costDay = costDay;
    }

    public ArrayList<Skill> getHas() {
        return has;
    }

    public void setHas(ArrayList<Skill> has) {
        this.has = has;
    }

    public Assignment getAssigned() {
        return assigned;
    }

    public void setAssigned(Assignment assigned) {
        this.assigned = assigned;
    }

    public String toString(){
        return staffId+"("+costDay+")";
    }
}
