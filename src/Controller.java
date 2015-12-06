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
    public void initialise(String filePath){
        ArrayList<String> fileData = FileManager.readInTxt(filePath);
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
                    if(staff != null){
                        staff.getHas().add(skill);
                    }else{
                        System.out.println("Couldn't add association as not staff found with id: "+secondId+" ("+line+")");
                    }
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
        String[] split = line.split(" ");
        //Then is an attribute
        if(checkType(line) == ObjectType.TASK){
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
        if(checkType(line) == ObjectType.STORY){
            Story story = allStories.get(allStories.size() - 1);
            if(checkIfContains(line,"storyId")){
                //Assign storyId
                String storyId = split[split.length - 1];
                storyId = storyId.replace("\"","");
                story.setStoryId(storyId);
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
        if(checkIfContains(line,"taskId","duration","subtasks","dependsOn")){
            return ObjectType.TASK;
        }
        if(checkIfContains(line,"staffId","costDay")){
            return ObjectType.STAFF;
        }
        if(checkIfContains(line,"skillId","needs","has")){
            return ObjectType.SKILL;
        }
        if(checkIfContains(line,"storyId")){
            return ObjectType.STORY;
        }
        return null;
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
        ArrayList<Task> unallocatedTask = new ArrayList<>();
        ArrayList<Task> incompleteTasks = new ArrayList<>();
        //Find all unallocated task
        System.out.println("Total tasks: "+allTasks.size());
        for(Task task:allTasks){
            if(task.getAssignment() == null){
                if(task.getDependsOn().size() > 0){
                    ///Check if their dependsOn Task has been allocated
                    ArrayList<Task> dependsOn = task.getDependsOn();
                    boolean taskDependsComplete = true;
                    for (Task dependsTask:dependsOn){
                        if(dependsTask.getAssignment() == null){
                            taskDependsComplete = false;
                        }
                    }
                    if(taskDependsComplete){
                        unallocatedTask.add(task);
                    }else{
                        incompleteTasks.add(task);
                        System.out.println("Task "+task.getTaskId()+" has incompleted dependent tasks");
                    }
                }else{
                    System.out.println("Task "+task.getTaskId()+" has no depends on");
                    unallocatedTask.add(task);
                }
            }else{
                System.out.println(task.getTaskId()+" has already been allocated");
            }
        }
        System.out.println("Total unallocated tasks: "+unallocatedTask.size()+" "+unallocatedTask.toString());
        //Order unallocated tasks from longest duration to shortest duration
        Collections.sort(unallocatedTask, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                if(t1.totalDuration() == t2.totalDuration()){
                    return 0;
                }
                if(t1.totalDuration() > t2.totalDuration()){
                    return -1;
                }else{
                    return 1;
                }
            }
        });
        System.out.println("All unallocated tasks (ordered from longest to shortest duration): "+unallocatedTask.toString());
        ///Check staff member has all required skills
        for (Task task:unallocatedTask){
            //Get tasks skills
            ArrayList<Skill> taskNeeds = task.getNeeds();

            //Find staff who has required skills
            ArrayList<Staff> compatibleStaffs = findCompatibleStaff(task);

            //Find cheapest staff
            if(compatibleStaffs.size() > 0){
                Staff chosenStaff = compatibleStaffs.get(0);
                ///Create new assignment linking task and staff
                Assignment assignment = new Assignment();
                System.out.println("Created new Assignment: Staff "+chosenStaff.getStaffId()+" allocated to "+task.getTaskId());
                assignment.setTask(task);
                assignment.setStaff(chosenStaff);
                task.setAssignment(assignment);
                chosenStaff.setAssigned(assignment);
                schedule.getAssignments().add(assignment);
            }else{
                System.out.println("No compatible staff found for "+task.getTaskId());
            }

        }
        if(incompleteTasks.size() > 0){
            boolean compatibleStaffAvailable = false;
            for (Task task:incompleteTasks){
                if(findCompatibleStaff(task).size() > 0){
                    ArrayList<Task> dependsOnTasks = task.getDependsOn();
                    boolean allDependsOnCompleted = true;
                    for (Task dependTask:dependsOnTasks){
                        if(dependTask.getAssignment() == null){
                            allDependsOnCompleted = false;
                        }
                    }
                    if(allDependsOnCompleted){
                        compatibleStaffAvailable = true;
                    }
                }
            }
            if(compatibleStaffAvailable){
                System.out.println("Recursively calling allocate staff");
                allocateStaff();
            }
        }
    }

    public ArrayList<Staff> findCompatibleStaff(Task task){
        ArrayList<Staff> compatibleStaff = new ArrayList<>();
        ArrayList<Skill> taskNeeds = task.getNeeds();
        //Find unallocated staff
        ArrayList<Staff> unallocatedStaff = new ArrayList<>();
        for (Staff staff:allStaff){
            if(staff.getAssigned() == null){
                unallocatedStaff.add(staff);
            }
        }
        for (Staff staff:unallocatedStaff){
            ArrayList<Skill> staffHas = staff.getHas();
            if(staffHas.containsAll(taskNeeds)){
                compatibleStaff.add(staff);
            }else{
                System.out.println("Staff "+staff.getStaffId()+
                        " has skills "+staffHas.toString()+" is not sufficient for Task "
                        +task.getTaskId()+" needs "+taskNeeds.toString());
            }
        }
        Collections.sort(compatibleStaff, new Comparator<Staff>() {
            @Override
            public int compare(Staff s1, Staff s2) {
                if(s1.getCostDay() == s2.getCostDay()){
                    return 0;
                }
                if(s1.getCostDay() > s2.getCostDay()){
                    return 1;
                }else{
                    return -1;
                }
            }
        });
        System.out.println("Task: "+task.getTaskId()+"  Compatible staff: "+compatibleStaff.toString());
        return compatibleStaff;
    }

    /**
     * Prints out list of assignments
     * With info on staffId,costDay,taskId,duration
     */
    public void displaySchedule(){
        //Loop through each assignment
        //Provide info on staffId,costDay,taskId and duration
        ArrayList<Assignment> assignments = schedule.getAssignments();
        System.out.println("Staff Id\tCost p/day\tTask Id\tTask duration\t");
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
        int totalDuration = 0;
        for(Assignment assignment: assignments){
            int costDay = assignment.getStaff().getCostDay();
            int taskTotalDuration = assignment.getTask().totalDuration();
            int taskDuration = assignment.getTask().getDuration();
            totalCost += costDay*taskDuration;
            totalDuration += taskTotalDuration;
        }
        schedule.setTotalCost(totalCost);
        schedule.setDuration(totalDuration);
        System.out.println("Total cost of all assignments: "+totalCost);
    }
}
