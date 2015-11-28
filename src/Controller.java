import java.util.*;

/**
 * Created by Tahmidul on 28/11/2015.
 */
public class Controller {
    Schedule schedule;
    ArrayList<Story> allStories;
    ArrayList<Staff> allStaff;
    ArrayList<Task> allTasks;
    NavigableMap<String,Skill> allSkills;

    public Controller(){
        schedule = new Schedule();
        allStaff = new ArrayList<>();
        allTasks = new ArrayList<>();
        allStories = new ArrayList<>();
        allSkills = new TreeMap<>();
    }

    /**
     * This reads the in.txt file
     * And initialises all staff
     * and tasks and schedule and skills
     */
    public void initialise(){
        ArrayList<String> fileData = FileReader.readInTxt();
        if(fileData != null){
            for(String line:fileData){
                String trimmedLine = line.trim();
                if(trimmedLine.length() > 0){
                    //If it is an object deceleration
                    if(checkIfContains(trimmedLine,": Task",": Staff",": Skill",": Schedule",": Story")){
                        System.out.println("Creating object for line "+trimmedLine);
                        createObject(trimmedLine);
                    }else{
                        System.out.println("Modifying object for line "+trimmedLine);
                        //Adding attributes
                        modifyObject(trimmedLine);
                    }
                }

            }
            if(schedule == null){
                schedule = new Schedule();
                System.out.println("Creating new schedule since schedule couldn't be found in text file");
            }
            //Display all data collected
            System.out.println("Total Tasks found: "+allTasks.size());
            System.out.println("Total Staff found: "+allStaff.size());
            System.out.println("Total Skills found: "+allSkills.size());
            System.out.println("Total Stories found: "+allStories.size());
        }else{
            System.out.println("Couldn't find in.txt");
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
            allTasks.add(task);
            System.out.println("Created new Task");
        }
        if(checkIfContains(line,": Staff")){
            Staff staff = new Staff();
            allStaff.add(staff);
            System.out.println("Created new staff");
        }
        if(checkIfContains(line,": Skill")){
            Skill skill = new Skill();
            allSkills.put(id,skill);
            System.out.println("Created new skill");
        }
        if(checkIfContains(line,": Schedule")){
            schedule = new Schedule();
            System.out.println("Created new schedule");
        }
        if(checkIfContains(line,": Story")){
            Story story = new Story();
            story.setStoryId(id);
            allStories.add(story);
        }
    }

    /**
     * Identifies what object the line refers to
     * And then adds data to it's attribute
     * @param line - String from text file
     */
    public void modifyObject(String line){
        //Check if is an attribute or association
        if(!checkIfContains(line,":")){
            //Is an attribute
            modifyAttribute(line);
        }else{
            //Is an association
            modifyAssociation(line);
        }
    }

    public void modifyAssociation(String line){
        System.out.println("Adding association");
        String[] split = line.split(" ");
        String firstId = split[0];
        String secondId = split[split.length - 1].split("\\.")[0];
        if(checkType(line) == ObjectType.TASK){
            if(checkIfContains(line,"subtasks")){
                //This is a Task subTasks association
                String storyId = secondId;
                Story story = findStory(secondId);
                Task task = findTask(firstId);
                if(story != null && task != null){
                    story.addSubTask(task);
                    System.out.println("Assigned story "+secondId+" to task "+firstId);
                }else{
                    if(story == null){
                        System.out.println("Story not found with id "+secondId);
                    }else{
                        System.out.println("Task not found with id "+firstId);
                    }
                }
            }else{
                //This is a Task dependsOn association
                Task task1 = findTask(firstId);
                Task task2 = findTask(secondId);
                if(task1 != null && task2 != null){
                    task2.getDependsOn().add(task1);
                    System.out.println("Made task "+secondId+" depend on "+firstId);
                }else{
                    if(task1 == null){
                        System.out.println("Couldn't find task"+"with id "+firstId+" in modify association");
                    }
                    if(task2 == null){
                        System.out.println("Couldn't find task"+"with id "+secondId+" in modify association");
                    }
                }
            }
        }
        if(checkType(line) == ObjectType.SKILL){
            Skill skill = findSkill(firstId);
            if(skill != null){
                if(checkIfContains(line,"needs")){
                    //This is a Skill needs association
                    Task task = findTask(secondId);
                    if(skill != null && task != null){
                        task.getNeeds().add(skill);
                        System.out.println("Added skill needs "+firstId+" to task "+secondId);
                    }else{
                        if(task == null){
                            System.out.println("Task not found with id: "+secondId);
                        }else{
                            System.out.println("Skill not found with id "+firstId);
                        }
                    }
                }else{
                    //This is a Skill has association
                    Staff staff = findStaff(secondId);
                    staff.getHas().add(skill);
                }
            }else{
                System.out.println("Couldn't find skill with id: "+firstId);
            }


        }
    }

    public Task findTask(String taskId){
        for(Task task:allTasks){
            if(task.getTaskId().equals(taskId)){
                return task;
            }
        }
        return null;
    }

    public Story findStory(String storyId){
        for(Story story:allStories){
            if(story.getStoryId().equals(storyId)){
                return story;
            }
        }
        return null;
    }

    public Skill findSkill(String skillId){
        Skill skill = allSkills.get(skillId);
        return skill;
    }

    public Staff findStaff(String staffId){
        for(Staff staff:allStaff){
            if(staff.getStaffId().equals(staffId)){
                return staff;
            }
        }
        return null;
    }

    public void modifyAttribute(String line){
        //Then is an attribute
        if(checkType(line) == ObjectType.TASK){
            String[] split = line.split(" ");
            Task task = allTasks.get(allTasks.size() - 1);
            if(checkIfContains(line,"taskId")){
                //Assign taskId
                String taskId = split[split.length - 1];
                taskId = taskId.replace("\"","");
                task.setTaskId(taskId);
            }else{
                //Assign task duration
                int duration;
                try{
                    duration = Integer.parseInt(split[split.length - 1]);
                    task.setDuration(duration);
                }catch (NumberFormatException e){
                    System.out.println("Number format exception for line: "+line);
                }
            }
        }
        if(checkType(line) == ObjectType.STAFF){
            String[] split = line.split(" ");
            Staff staff = allStaff.get(allStaff.size() - 1);
            if(checkIfContains(line,"staffId")){
                //Assign staffId
                String staffId = split[split.length - 1];
                staffId = staffId.replace("\"","");
                staff.setStaffId(staffId);
            }else{
                //Assign task duration
                int costDay = Integer.parseInt(split[split.length - 1]);
                staff.setCostDay(costDay);
            }
        }
        if(checkType(line) == ObjectType.SKILL){
            String[] split = line.split(" ");
            Map.Entry<String,Skill> lastEntry = allSkills.lastEntry();
            Skill skill = lastEntry.getValue();
            if(checkIfContains(line,"skillId") && skill != null){
                //Assign staffId
                String skillId = split[split.length - 1];
                skillId = skillId.replace("\"","");
                skill.setSkillId(skillId);
            }else{
                System.out.println("Couldn't retrieve last skill");
            }
        }
    }

    /**
     * Checks what object type
     * The String is referencing
     * @param line - String from text
     * @return - ObjectType enum
     */
    public ObjectType checkType(String line){
        if(checkIfContains(line,"taskId","duration","subtasks")){
            return ObjectType.TASK;
        }
        if(checkIfContains(line,"staffId","costDay")){
            return ObjectType.STAFF;
        }
        if(checkIfContains(line,"skillId","needs","has")){
            return ObjectType.SKILL;
        }
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
