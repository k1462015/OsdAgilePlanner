import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Vector;

public interface ControllerInterface
{
  public void addStaff(Staff oo);
    public Staff createStaff(String staffIdx);
  public void killStaff(Staff staffxx);
 public void setassigned(Staff staffx,List assignedxx);
   public void addassigned(Staff staffx,Assignment assignedxx);
   public void removeassigned(Staff staffx,Assignment assignedxx);
    public void addAssignment(Assignment oo);
    public Assignment createAssignment(Staff staffx,Task taskx);
  public void killAssignment(Assignment assignmentxx);
 public void setstaff(Assignment assignmentx,Staff staffxx);
 public void settask(Assignment assignmentx,Task taskxx);
  public void addTask(Task oo);
    public Task createTask(String taskIdx);
  public void killTask(Task taskxx);
 public void setassignment(Task taskx,List assignmentxx);
   public void addassignment(Task taskx,Assignment assignmentxx);
   public void removeassignment(Task taskx,Assignment assignmentxx);
   public void setdependsOn(Task taskx,List dependsOnxx);
   public void adddependsOn(Task taskx,Task dependsOnxx);
   public void removedependsOn(Task taskx,Task dependsOnxx);
    public void addAllocateStaff(AllocateStaff oo);
    public AllocateStaff createAllocateStaff();
  public void killAllocateStaff(AllocateStaff allocatestaffxx);
  public void addDisplaySchedule(DisplaySchedule oo);
    public DisplaySchedule createDisplaySchedule();
  public void killDisplaySchedule(DisplaySchedule displayschedulexx);
}

