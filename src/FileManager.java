import java.io.*;
import java.util.ArrayList;
import java.util.NavigableMap;
import java.util.Set;

/**
 * Created by Tahmidul on 28/11/2015.
 */
public class FileManager {

    public static ArrayList<String> readInTxt(){
        String operatingSystem = System.getProperty("os.name").toLowerCase();
        String fileSeperator = "\\";    //Default file seperator for windows
        if(operatingSystem.contains("mac")){
            fileSeperator = "//";
        }
        String filePath = System.getProperty("user.dir")+fileSeperator+"test.txt";
        ArrayList<String> data = new ArrayList<>();
        BufferedReader br;
        try{
            br = new BufferedReader(new java.io.FileReader(filePath));
            String line = br.readLine();
            while(line != null){
                data.add(line);
                line = br.readLine();
            }
            br.close();
            return data;
        }catch (IOException e){
            System.out.println("IO Exception");
        }
        return null;
    }

    public static void outputText(Controller controller) throws IOException {
        File file = new File("out.txt");
        // creates the file
        file.createNewFile();
        // creates a FileWriter Object
        FileWriter writer = new FileWriter(file);

        //Print out schedule declaration
        writer.write("sch   :   Schedule"+"\n");
        //Print out all skills
        NavigableMap<String,Skill> allSkills = controller.allSkills;
        Set<String> skillObjectNames = allSkills.keySet();
        for (String skillObject:skillObjectNames){
            writer.write("skillx_"+skillObject+"  :   "+"Skill"+"\n");
            String skillId = allSkills.get(skillObject).getSkillId();
            writer.write("skillx_"+skillObject+".skillId  =   \""+skillId+"\"\n");
        }
        //Print out all staff
        ArrayList<Staff> allStaff = controller.allStaff;
        for (Staff staff:allStaff){
            String staffId = staff.getStaffId();
            int costDay = staff.getCostDay();
            writer.write("staffx_"+allStaff.indexOf(staff)+"  :"+"    Staff"+"\n");
            writer.write("staffx_"+allStaff.indexOf(staff)+".staffId  =   "+"\""+staffId+"\""+"\n");
            writer.write("staffx_"+allStaff.indexOf(staff)+".costDay  =   "+costDay+"\n");
            ArrayList<Skill> has = staff.getHas();
            for (Skill skill:has){
                String skillObjectNumber = "NOT FOUND";
                for (String skillObject:skillObjectNames){
                    if(skill.equals(allSkills.get(skillObject))){
                        skillObjectNumber = skillObject;
                    }
                }
                writer.write("staffx_"+allStaff.indexOf(staff)+".has    =   skillx_"+skillObjectNumber+"\"\n");
            }
        }
        //Print out all tasks
        ArrayList<Task> allTasks = controller.allTasks;
        for (Task task:allTasks){
            String taskId = task.getTaskId();
            int taskDuration = task.getDuration();
            int objectIndex = allTasks.indexOf(task);
            writer.write("taskx_"+objectIndex+" :   "+"Task\n");
            writer.write("taskx_"+objectIndex+".taskId  =   \""+taskId+"\"\n");
            writer.write("taskx_"+objectIndex+".duration  =   "+taskDuration+"\n");
            ArrayList<Skill> needs = task.getNeeds();
            for (Skill skill:needs){
                String skillObjectNumber = "NOT FOUND";
                for (String skillObject:skillObjectNames){
                    if(skill.equals(allSkills.get(skillObject))){
                        skillObjectNumber = skillObject;
                    }
                }
                writer.write("taskx_"+objectIndex+".needs    =   skillx_"+skillObjectNumber+"\"\n");
            }
            ArrayList<Task> dependsOn = task.getDependsOn();
            for (Task dependTask:dependsOn){
                int dependTaskIndex = allTasks.indexOf(dependTask);
                writer.write("taskx_"+objectIndex+".dependsOn    =   taskx_"+dependTaskIndex+"\n");
            }

        }
        //Print out all assignments
        ArrayList<Assignment> assignments = controller.schedule.getAssignments();
        for(int i = 0;i < assignments.size();i++){
            Assignment assignment = assignments.get(i);
            String taskObjectName = allTasks.indexOf(assignment.getTask())+"";
            String staffObjectName = allStaff.indexOf(assignment.getStaff())+"";
            writer.write("assignmentx_"+i+" :   "+"Assignment\n");
            writer.write("assignmentx_"+i+".task    =   "+"taskx_"+taskObjectName+"\n");
            writer.write("assignmentx_"+i+".staff    =   "+"staffx_"+staffObjectName+"\n");
        }
        writer.flush();
        writer.close();
        System.out.println("File written to out.txt");
    }

//    public static void main(String[] args){
//        BufferedReader br;
//        try{
//            br = new BufferedReader(new java.io.FileManager("C:\\Users\\Tahmidul\\Desktop\\in.txt"));
//            StringBuilder sb = new StringBuilder();
//            String line = br.readLine();
//            while(line != null){
//                sb.append(line);
//                sb.append(System.lineSeparator());
//                //First identify if it is a object deceleration
//                if(checkIfContains(line,": Task",": Staff")){
//                    System.out.println(line+" Contains object deceleration");
//                }
//                line = br.readLine();
//            }
//            String everything = sb.toString();
//            br.close();
//        }catch (IOException e){
//            System.out.println("IO Exception");
//        }
//    }
//
//    public static boolean checkIfContains(String subject,String...args){
//        for (int i = 0;i < args.length;i++){
//            if(subject.contains(args[i])){
//                return true;
//            }
//        }
//        return false;
//    }
}
