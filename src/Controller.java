import java.util.ArrayList;

/**
 * Created by Tahmidul on 28/11/2015.
 */
public class Controller {
    Schedule schedule;
    ArrayList<Story> stories;
    ArrayList<Staff> allStaff;
    ArrayList<Task> allTasks;

    public Controller(){
        schedule = new Schedule();
        allStaff = new ArrayList<>();
        allTasks = new ArrayList<>();
        stories = new ArrayList<>();
        //User loads in.txt
        //This should create all task object's
        //And create all staff objects



    }

    /**
     * This reads the in.txt file
     * And initialises all staff
     * and tasks and schedule and skills
     */
    public void initialise(ArrayList<String> fileData){
        for(String line:fileData){
            //If it is an object deceleration
            if(checkIfContains(line,": Task",": Staff",": Skill",": Schedule",": Story")){
                createObject(line);
            }else{
                //Adding attributes
                modifyObject(line);
            }
        }
        if(schedule == null){
            schedule = new Schedule();
        }
    }

    public static boolean checkIfContains(String subject,String...args){
        for (int i = 0;i < args.length;i++){
            if(subject.contains(args[i])){
                return true;
            }
        }
        return false;
    }

    public void createObject(String line){
        String id = line.split(" ")[0];
        if(checkIfContains(line,": Task")){
            Task task = new Task();
            task.setTaskId(id);
        }
        if(checkIfContains(line,": Staff")){
            Staff staff = new Staff();
            staff.setStaffId(id);
        }
        if(checkIfContains(line,": Skill")){
            Skill skill = new Skill();
            skill.setSkillId(id);
        }
        if(checkIfContains(line,": Schedule")){
            schedule = new Schedule();
        }
        if(checkIfContains(line,": Story")){

        }
    }

    /**
     * Identifies what object the line refers to
     * And then adds data to it's attribute
     * @param line - String from text file
     */
    public void modifyObject(String line){
        if(checkType(line) == ObjectType.TASK){
            if(checkIfContains(line,"taskId")){
                String[] breakdown= line.split(" ");
                String taskId = breakdown[breakdown.length - 1];
                taskId = taskId.replace("\"","");
                int taskId = Integer.parseInt(line.split("="))
            }
        }
        if(checkType(line) == ObjectType.STAFF){

        }
        if(checkType(line) == ObjectType.SKILL){

        }
    }

    /**
     * Checks what object type
     * The String is referencing
     * @param line - String from text
     * @return - ObjectType enum
     */
    public ObjectType checkType(String line){

        return ObjectType.TASK;
    }


    /**
     * For each unallocated task t,
     * (all whose dependsOn tasks have been allocated)
     * Find available unallocated staff who has all skills
     * Required by task t
     * And assign task to cheapest staff member.
     * Create new assignment for task t and staff s
     * And then add this to schedule
     */
    public void allocateStaff(){

    }

    /**
     * Prints out list of assignments
     * With info on staffId,costDay,taskId,duration
     */
    public void displaySchedule(){
        //Loop through each assignment
        //Provide info on staffId,costDay,taskId and duration
        ArrayList<Assignment> assignments = schedule.getAssignments();
        for (Assignment assignment:assignments){
            StringBuilder sb = new StringBuilder();
            sb.append(assignment.getStaff().getStaffId()+"\t");
            sb.append(assignment.getStaff().getCostDay()+"\t");
            sb.append(assignment.getTask().getTaskId()+"\t");
            sb.append(assignment.getTask().totalDuration());
            System.out.println(sb.toString());
        }
        calculateCost();
        System.out.println("Schedule Total Cost: "+schedule.getTotalCost());

    }

    /**
     * Breaks link between staff and assignment
     * So new iteration can task place
     * And assign tasks to staff
     */
    public void nextIteration(){
        //Break off link between staff and assignment
        for (Staff s:allStaff){
            s.setAssigned(null);
        }
    }

    /**
     * Adds up products
     * schedule.costDay * task.duration
     * And then assigns to schedule.totalCost
     */
    public void calculateCost(){
        ArrayList<Assignment> assignments = schedule.getAssignments();
        int totalCost = 0;
        for(Assignment assignment: assignments){
            int costDay = assignment.getStaff().getCostDay();
            int taskDuration = assignment.getTask().totalDuration();
            totalCost += costDay*taskDuration;
        }
        schedule.setTotalCost(totalCost);
    }
}
