import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.Vector;

import java.lang.*;
import java.lang.reflect.*;
import java.util.StringTokenizer;
import java.io.*;



class Staff
  implements SystemTypes
{
  private String staffId = ""; // internal
  private int costDay = 0; // internal
  private List assigned = new Vector(); // of Assignment

  public Staff()
  {
    this.staffId = "";
    this.costDay = 0;

  }



  public String toString()
  { String _res_ = "(Staff) ";
    _res_ = _res_ + staffId + ",";
    _res_ = _res_ + costDay;
    return _res_;
  }

  public void setstaffId(String staffId_x) { staffId = staffId_x;  }


    public static void setAllstaffId(List staffs,String val)
  { for (int i = 0; i < staffs.size(); i++)
    { Staff staffx = (Staff) staffs.get(i);
      Controller.inst().setstaffId(staffx,val); } }


  public void setcostDay(int costDay_x) { costDay = costDay_x;  }


    public static void setAllcostDay(List staffs,int val)
  { for (int i = 0; i < staffs.size(); i++)
    { Staff staffx = (Staff) staffs.get(i);
      Controller.inst().setcostDay(staffx,val); } }


  public void setassigned(List assignedxx) { assigned = assignedxx;
    }
 
  public void addassigned(Assignment assignedxx) { assigned.add(assignedxx);
    }
 
  public void removeassigned(Assignment assignedxx) { Vector _removedassignedassignedxx = new Vector();
  _removedassignedassignedxx.add(assignedxx);
  assigned.removeAll(_removedassignedassignedxx);
    }

  public static void setAllassigned(List staffs,List _val)
  { for (int _i = 0; _i < staffs.size(); _i++)
    { Staff staffx = (Staff) staffs.get(_i);
      Controller.inst().setassigned(staffx, _val); } }

  public static void addAllassigned(List staffs,Assignment _val)
  { for (int _i = 0; _i < staffs.size(); _i++)
    { Staff staffx = (Staff) staffs.get(_i);
      Controller.inst().addassigned(staffx, _val); } }


  public static void removeAllassigned(List staffs,Assignment _val)
  { for (int _i = 0; _i < staffs.size(); _i++)
    { Staff staffx = (Staff) staffs.get(_i);
      Controller.inst().removeassigned(staffx, _val); } }


  public static void unionAllassigned(List staffs, List _val)
  { for (int _i = 0; _i < staffs.size(); _i++)
    { Staff staffx = (Staff) staffs.get(_i);
      Controller.inst().unionassigned(staffx, _val); } }


  public static void subtractAllassigned(List staffs, List _val)
  { for (int _i = 0; _i < staffs.size(); _i++)
    { Staff staffx = (Staff) staffs.get(_i);
      Controller.inst().subtractassigned(staffx, _val); } }


    public String getstaffId() { return staffId; }

    public static List getAllstaffId(List staffs)
  { List result = new Vector();
    for (int i = 0; i < staffs.size(); i++)
    { Staff staffx = (Staff) staffs.get(i);
      if (result.contains(staffx.getstaffId())) { }
      else { result.add(staffx.getstaffId()); } }
    return result; }

    public static List getAllOrderedstaffId(List staffs)
  { List result = new Vector();
    for (int i = 0; i < staffs.size(); i++)
    { Staff staffx = (Staff) staffs.get(i);
      result.add(staffx.getstaffId()); } 
    return result; }

    public int getcostDay() { return costDay; }

    public static List getAllcostDay(List staffs)
  { List result = new Vector();
    for (int i = 0; i < staffs.size(); i++)
    { Staff staffx = (Staff) staffs.get(i);
      if (result.contains(new Integer(staffx.getcostDay()))) { }
      else { result.add(new Integer(staffx.getcostDay())); } }
    return result; }

    public static List getAllOrderedcostDay(List staffs)
  { List result = new Vector();
    for (int i = 0; i < staffs.size(); i++)
    { Staff staffx = (Staff) staffs.get(i);
      result.add(new Integer(staffx.getcostDay())); } 
    return result; }

  public List getassigned() { return (Vector) ((Vector) assigned).clone(); }

  public static List getAllassigned(List staffs)
  { List result = new Vector();
    for (int _i = 0; _i < staffs.size(); _i++)
    { Staff staffx = (Staff) staffs.get(_i);
      result = Set.union(result,staffx.getassigned()); }
    return result; }

  public static List getAllOrderedassigned(List staffs)
  { List result = new Vector();
    for (int _i = 0; _i < staffs.size(); _i++)
    { Staff staffx = (Staff) staffs.get(_i);
      result = Set.union(result,staffx.getassigned()); }
    return result; }


}


class Assignment
  implements SystemTypes
{
  private Staff staff;
  private Task task;

  public Assignment(Staff staff,Task task)
  {
    this.staff = staff;
    this.task = task;

  }

  public Assignment() { }



  public void setstaff(Staff staffxx) { staff = staffxx;
  }

  public static void setAllstaff(List assignments,Staff _val)
  { for (int _i = 0; _i < assignments.size(); _i++)
    { Assignment assignmentx = (Assignment) assignments.get(_i);
      Controller.inst().setstaff(assignmentx, _val); } }

  public void settask(Task taskxx) { task = taskxx;
  }

  public static void setAlltask(List assignments,Task _val)
  { for (int _i = 0; _i < assignments.size(); _i++)
    { Assignment assignmentx = (Assignment) assignments.get(_i);
      Controller.inst().settask(assignmentx, _val); } }

  public Staff getstaff() { return staff; }

  public static List getAllstaff(List assignments)
  { List result = new Vector();
    for (int _i = 0; _i < assignments.size(); _i++)
    { Assignment assignmentx = (Assignment) assignments.get(_i);
      if (result.contains(assignmentx.getstaff())) {}
      else { result.add(assignmentx.getstaff()); }
 }
    return result; }

  public static List getAllOrderedstaff(List assignments)
  { List result = new Vector();
    for (int _i = 0; _i < assignments.size(); _i++)
    { Assignment assignmentx = (Assignment) assignments.get(_i);
      if (result.contains(assignmentx.getstaff())) {}
      else { result.add(assignmentx.getstaff()); }
 }
    return result; }

  public Task gettask() { return task; }

  public static List getAlltask(List assignments)
  { List result = new Vector();
    for (int _i = 0; _i < assignments.size(); _i++)
    { Assignment assignmentx = (Assignment) assignments.get(_i);
      if (result.contains(assignmentx.gettask())) {}
      else { result.add(assignmentx.gettask()); }
 }
    return result; }

  public static List getAllOrderedtask(List assignments)
  { List result = new Vector();
    for (int _i = 0; _i < assignments.size(); _i++)
    { Assignment assignmentx = (Assignment) assignments.get(_i);
      if (result.contains(assignmentx.gettask())) {}
      else { result.add(assignmentx.gettask()); }
 }
    return result; }

    public String toString()
  {   String result = "";
 
  result = task.gettaskId() + ", " + task.getduration() + ", " + staff.getstaffId() + ", " + staff.getcostDay();
    return result;
  }


    public void displayschedule1()
  {   System.out.println("" + this);

  }


}


class Task
  implements SystemTypes
{
  private String taskId = ""; // internal
  private int duration = 0; // internal
  private List assignment = new Vector(); // of Assignment
  private List dependsOn = new Vector(); // of Task

  public Task()
  {
    this.taskId = "";
    this.duration = 0;

  }



  public String toString()
  { String _res_ = "(Task) ";
    _res_ = _res_ + taskId + ",";
    _res_ = _res_ + duration;
    return _res_;
  }

  public void settaskId(String taskId_x) { taskId = taskId_x;  }


    public static void setAlltaskId(List tasks,String val)
  { for (int i = 0; i < tasks.size(); i++)
    { Task taskx = (Task) tasks.get(i);
      Controller.inst().settaskId(taskx,val); } }


  public void setduration(int duration_x) { duration = duration_x;  }


    public static void setAllduration(List tasks,int val)
  { for (int i = 0; i < tasks.size(); i++)
    { Task taskx = (Task) tasks.get(i);
      Controller.inst().setduration(taskx,val); } }


  public void setassignment(List assignmentxx) { assignment = assignmentxx;
    }
 
  public void addassignment(Assignment assignmentxx) { assignment.add(assignmentxx);
    }
 
  public void removeassignment(Assignment assignmentxx) { Vector _removedassignmentassignmentxx = new Vector();
  _removedassignmentassignmentxx.add(assignmentxx);
  assignment.removeAll(_removedassignmentassignmentxx);
    }

  public static void setAllassignment(List tasks,List _val)
  { for (int _i = 0; _i < tasks.size(); _i++)
    { Task taskx = (Task) tasks.get(_i);
      Controller.inst().setassignment(taskx, _val); } }

  public static void addAllassignment(List tasks,Assignment _val)
  { for (int _i = 0; _i < tasks.size(); _i++)
    { Task taskx = (Task) tasks.get(_i);
      Controller.inst().addassignment(taskx, _val); } }


  public static void removeAllassignment(List tasks,Assignment _val)
  { for (int _i = 0; _i < tasks.size(); _i++)
    { Task taskx = (Task) tasks.get(_i);
      Controller.inst().removeassignment(taskx, _val); } }


  public static void unionAllassignment(List tasks, List _val)
  { for (int _i = 0; _i < tasks.size(); _i++)
    { Task taskx = (Task) tasks.get(_i);
      Controller.inst().unionassignment(taskx, _val); } }


  public static void subtractAllassignment(List tasks, List _val)
  { for (int _i = 0; _i < tasks.size(); _i++)
    { Task taskx = (Task) tasks.get(_i);
      Controller.inst().subtractassignment(taskx, _val); } }


  public void setdependsOn(List dependsOnxx) { dependsOn = dependsOnxx;
    }
 
  public void adddependsOn(Task dependsOnxx) { dependsOn.add(dependsOnxx);
    }
 
  public void removedependsOn(Task dependsOnxx) { Vector _removeddependsOndependsOnxx = new Vector();
  _removeddependsOndependsOnxx.add(dependsOnxx);
  dependsOn.removeAll(_removeddependsOndependsOnxx);
    }

  public static void setAlldependsOn(List tasks,List _val)
  { for (int _i = 0; _i < tasks.size(); _i++)
    { Task taskx = (Task) tasks.get(_i);
      Controller.inst().setdependsOn(taskx, _val); } }

  public static void addAlldependsOn(List tasks,Task _val)
  { for (int _i = 0; _i < tasks.size(); _i++)
    { Task taskx = (Task) tasks.get(_i);
      Controller.inst().adddependsOn(taskx, _val); } }


  public static void removeAlldependsOn(List tasks,Task _val)
  { for (int _i = 0; _i < tasks.size(); _i++)
    { Task taskx = (Task) tasks.get(_i);
      Controller.inst().removedependsOn(taskx, _val); } }


  public static void unionAlldependsOn(List tasks, List _val)
  { for (int _i = 0; _i < tasks.size(); _i++)
    { Task taskx = (Task) tasks.get(_i);
      Controller.inst().uniondependsOn(taskx, _val); } }


  public static void subtractAlldependsOn(List tasks, List _val)
  { for (int _i = 0; _i < tasks.size(); _i++)
    { Task taskx = (Task) tasks.get(_i);
      Controller.inst().subtractdependsOn(taskx, _val); } }


    public String gettaskId() { return taskId; }

    public static List getAlltaskId(List tasks)
  { List result = new Vector();
    for (int i = 0; i < tasks.size(); i++)
    { Task taskx = (Task) tasks.get(i);
      if (result.contains(taskx.gettaskId())) { }
      else { result.add(taskx.gettaskId()); } }
    return result; }

    public static List getAllOrderedtaskId(List tasks)
  { List result = new Vector();
    for (int i = 0; i < tasks.size(); i++)
    { Task taskx = (Task) tasks.get(i);
      result.add(taskx.gettaskId()); } 
    return result; }

    public int getduration() { return duration; }

    public static List getAllduration(List tasks)
  { List result = new Vector();
    for (int i = 0; i < tasks.size(); i++)
    { Task taskx = (Task) tasks.get(i);
      if (result.contains(new Integer(taskx.getduration()))) { }
      else { result.add(new Integer(taskx.getduration())); } }
    return result; }

    public static List getAllOrderedduration(List tasks)
  { List result = new Vector();
    for (int i = 0; i < tasks.size(); i++)
    { Task taskx = (Task) tasks.get(i);
      result.add(new Integer(taskx.getduration())); } 
    return result; }

  public List getassignment() { return (Vector) ((Vector) assignment).clone(); }

  public static List getAllassignment(List tasks)
  { List result = new Vector();
    for (int _i = 0; _i < tasks.size(); _i++)
    { Task taskx = (Task) tasks.get(_i);
      result = Set.union(result,taskx.getassignment()); }
    return result; }

  public static List getAllOrderedassignment(List tasks)
  { List result = new Vector();
    for (int _i = 0; _i < tasks.size(); _i++)
    { Task taskx = (Task) tasks.get(_i);
      result = Set.union(result,taskx.getassignment()); }
    return result; }

  public List getdependsOn() { return (Vector) ((Vector) dependsOn).clone(); }

  public static List getAlldependsOn(List tasks)
  { List result = new Vector();
    for (int _i = 0; _i < tasks.size(); _i++)
    { Task taskx = (Task) tasks.get(_i);
      result = Set.union(result,taskx.getdependsOn()); }
    return result; }

  public static List getAllOrdereddependsOn(List tasks)
  { List result = new Vector();
    for (int _i = 0; _i < tasks.size(); _i++)
    { Task taskx = (Task) tasks.get(_i);
      result = Set.union(result,taskx.getdependsOn()); }
    return result; }

    public void allocatestaff1(Staff st)
  {   //  if ((!(assignment.size() == 0) || st.getassigned().size() != 0))) { return; } 
  Assignment a = new Assignment();
    Controller.inst().addAssignment(a);
    Controller.inst().settask(a,this);
    Controller.inst().setstaff(a,st);
  }

    public boolean allocatestaff1test(Staff st)
  {  boolean result;
Task taskx = this;
     if (taskx.getassignment().size() == 0 && st.getassigned().size() == 0) {   return true; }

    return false;

  }


    public static boolean allocatestaff1search()
  {  boolean result;
  List _range1 = Controller.inst().tasks;
  for (int _i0 = 0; _i0 < _range1.size(); _i0++)
  { Task taskx = (Task) _range1.get(_i0);
      List _range3 = Controller.inst().staffs;
  for (int _i2 = 0; _i2 < _range3.size(); _i2++)
  { Staff st = (Staff) _range3.get(_i2);
       if (taskx.allocatestaff1test(st)) {    Controller.inst().allocatestaff1(taskx,st);
    return true;
 }

  }
  }
    return false;

  }



}


class AllocateStaff
  implements SystemTypes
{

  public AllocateStaff()
  {

  }



  public String toString()
  { String _res_ = "(AllocateStaff) ";
    return _res_;
  }


}


class DisplaySchedule
  implements SystemTypes
{

  public DisplaySchedule()
  {

  }



  public String toString()
  { String _res_ = "(DisplaySchedule) ";
    return _res_;
  }


}



public class Controller implements SystemTypes, ControllerInterface
{
  Vector staffs = new Vector();
  Map staffstaffIdindex = new HashMap(); // String --> Staff

  Vector assignments = new Vector();
  Vector tasks = new Vector();
  Map tasktaskIdindex = new HashMap(); // String --> Task

  Vector allocatestaffs = new Vector();
  Vector displayschedules = new Vector();
  private static Controller uniqueInstance; 


  private Controller() { } 


  public static Controller inst() 
    { if (uniqueInstance == null) 
    { uniqueInstance = new Controller(); }
    return uniqueInstance; } 


  public static void loadModel(String file)
  {
    try
    { BufferedReader br = null;
      File f = new File(file);
      try 
      { br = new BufferedReader(new FileReader(f)); }
      catch (Exception ex) 
      { System.err.println("No file: " + file); return; }
      Class cont = Class.forName("Controller");
      java.util.Map objectmap = new java.util.HashMap();
      while (true)
      { String line1;
        try { line1 = br.readLine(); }
        catch (Exception e)
        { return; }
        if (line1 == null)
        { return; }
        line1 = line1.trim();

        if (line1.length() == 0) { continue; }
        String left;
        String op;
        String right;
        if (line1.charAt(line1.length() - 1) == '"')
        { int eqind = line1.indexOf("="); 
          if (eqind == -1) { continue; }
          else 
          { left = line1.substring(0,eqind-1).trim();
            op = "="; 
            right = line1.substring(eqind+1,line1.length()).trim();
          }
        }
        else
        { StringTokenizer st1 = new StringTokenizer(line1);
          Vector vals1 = new Vector();
          while (st1.hasMoreTokens())
          { String val1 = st1.nextToken();
            vals1.add(val1);
          }
          if (vals1.size() < 3)
          { continue; }
          left = (String) vals1.get(0);
          op = (String) vals1.get(1);
          right = (String) vals1.get(2);
        }
        if (":".equals(op))
        { int i2 = right.indexOf(".");
          if (i2 == -1)
          { Class cl;
            try { cl = Class.forName("" + right); }
            catch (Exception _x) { System.err.println("No entity: " + right); continue; }
            Object xinst = cl.newInstance();
            objectmap.put(left,xinst);
            Class[] cargs = new Class[] { cl };
            Method addC = cont.getMethod("add" + right,cargs);
            if (addC == null) { continue; }
            Object[] args = new Object[] { xinst };
            addC.invoke(Controller.inst(),args);
          }
          else
          { String obj = right.substring(0,i2);
            String role = right.substring(i2+1,right.length());
            Object objinst = objectmap.get(obj); 
            if (objinst == null) 
            { continue; }
            Object val = objectmap.get(left);
            if (val == null) 
            { continue; }
            Class objC = objinst.getClass();
            Class typeclass = val.getClass(); 
            Object[] args = new Object[] { val }; 
            Class[] settypes = new Class[] { typeclass };
            Method addrole = Controller.findMethod(objC,"add" + role);
            if (addrole != null) 
            { addrole.invoke(objinst, args); }
            else { System.err.println("Error: cannot add to " + role); }
          }
        }
        else if ("=".equals(op))
        { int i1 = left.indexOf(".");
          if (i1 == -1) 
          { continue; }
          String obj = left.substring(0,i1);
          String att = left.substring(i1+1,left.length());
          Object objinst = objectmap.get(obj); 
          if (objinst == null) 
          { continue; }
          Class objC = objinst.getClass();
          Class typeclass; 
          Object val; 
          if (right.charAt(0) == '"' &&
              right.charAt(right.length() - 1) == '"')
          { typeclass = String.class;
            val = right.substring(1,right.length() - 1);
          } 
          else if ("true".equals(right) || "false".equals(right))
          { typeclass = boolean.class;
            if ("true".equals(right))
            { val = new Boolean(true); }
            else
            { val = new Boolean(false); }
          }
          else 
          { val = objectmap.get(right);
            if (val != null)
            { typeclass = val.getClass(); }
            else 
            { int i;
              long l; 
              double d;
              try 
              { i = Integer.parseInt(right);
                typeclass = int.class;
                val = new Integer(i); 
              }
              catch (Exception ee)
              { try 
                { l = Long.parseLong(right);
                  typeclass = long.class;
                  val = new Long(l); 
                }
                catch (Exception eee)
                { try
                  { d = Double.parseDouble(right);
                    typeclass = double.class;
                    val = new Double(d);
                  }
                  catch (Exception ff)
                  { continue; }
                }
              }
            }
          }
          Object[] args = new Object[] { val }; 
          Class[] settypes = new Class[] { typeclass };
          Method setatt = Controller.findMethod(objC,"set" + att);
          if (setatt != null) 
          { setatt.invoke(objinst, args); }
          else { System.err.println("No attribute: " + att); }
        }
      }
    } catch (Exception e) { }
  }

  public static Method findMethod(Class c, String name)
  { Method[] mets = c.getMethods(); 
    for (int i = 0; i < mets.length; i++)
    { Method m = mets[i];
      if (m.getName().equals(name))
      { return m; }
    } 
    return null;
  }


  public void checkCompleteness()
  {   for (int _i = 0; _i < staffs.size(); _i++)
  { Staff staff_x = (Staff) staffs.get(_i);
    Staff staff_obj = (Staff) staffstaffIdindex.get(staff_x.getstaffId());
    if (staff_obj == staff_x) { }
    else if (staff_obj == null)
    { staffstaffIdindex.put(staff_x.getstaffId(),staff_x); }
    else
    { System.out.println("Error: multiple objects with staffId = " + staff_x.getstaffId()); }
  }
  for (int _i = 0; _i < tasks.size(); _i++)
  { Task task_x = (Task) tasks.get(_i);
    Task task_obj = (Task) tasktaskIdindex.get(task_x.gettaskId());
    if (task_obj == task_x) { }
    else if (task_obj == null)
    { tasktaskIdindex.put(task_x.gettaskId(),task_x); }
    else
    { System.out.println("Error: multiple objects with taskId = " + task_x.gettaskId()); }
  }
  for (int _i = 0; _i < assignments.size(); _i++)
  { Assignment assigned_assignmentx1 = (Assignment) assignments.get(_i);
    for (int _j = 0; _j < staffs.size(); _j++)
    { Staff staff_staffx2 = (Staff) staffs.get(_j);
      if (assigned_assignmentx1.getstaff() == staff_staffx2)
      { if (staff_staffx2.getassigned().contains(assigned_assignmentx1)) { }
        else { staff_staffx2.addassigned(assigned_assignmentx1); }
      }
      else if (staff_staffx2.getassigned().contains(assigned_assignmentx1))
      { assigned_assignmentx1.setstaff(staff_staffx2); } 
    }
  }
  for (int _i = 0; _i < assignments.size(); _i++)
  { Assignment assignment_assignmentx1 = (Assignment) assignments.get(_i);
    for (int _j = 0; _j < tasks.size(); _j++)
    { Task task_taskx2 = (Task) tasks.get(_j);
      if (assignment_assignmentx1.gettask() == task_taskx2)
      { if (task_taskx2.getassignment().contains(assignment_assignmentx1)) { }
        else { task_taskx2.addassignment(assignment_assignmentx1); }
      }
      else if (task_taskx2.getassignment().contains(assignment_assignmentx1))
      { assignment_assignmentx1.settask(task_taskx2); } 
    }
  }
  }


  public void saveModel(String file)
  { File outfile = new File(file); 
    PrintWriter out; 
    try { out = new PrintWriter(new BufferedWriter(new FileWriter(outfile))); }
    catch (Exception e) { return; } 
  for (int _i = 0; _i < staffs.size(); _i++)
  { Staff staffx_ = (Staff) staffs.get(_i);
    out.println("staffx_" + _i + " : Staff");
    out.println("staffx_" + _i + ".staffId = \"" + staffx_.getstaffId() + "\"");
    out.println("staffx_" + _i + ".costDay = " + staffx_.getcostDay());
  }

  for (int _i = 0; _i < assignments.size(); _i++)
  { Assignment assignmentx_ = (Assignment) assignments.get(_i);
    out.println("assignmentx_" + _i + " : Assignment");
  }

  for (int _i = 0; _i < tasks.size(); _i++)
  { Task taskx_ = (Task) tasks.get(_i);
    out.println("taskx_" + _i + " : Task");
    out.println("taskx_" + _i + ".taskId = \"" + taskx_.gettaskId() + "\"");
    out.println("taskx_" + _i + ".duration = " + taskx_.getduration());
  }

  for (int _i = 0; _i < allocatestaffs.size(); _i++)
  { AllocateStaff allocatestaffx_ = (AllocateStaff) allocatestaffs.get(_i);
    out.println("allocatestaffx_" + _i + " : AllocateStaff");
  }

  for (int _i = 0; _i < displayschedules.size(); _i++)
  { DisplaySchedule displayschedulex_ = (DisplaySchedule) displayschedules.get(_i);
    out.println("displayschedulex_" + _i + " : DisplaySchedule");
  }

  for (int _i = 0; _i < staffs.size(); _i++)
  { Staff staffx_ = (Staff) staffs.get(_i);
    List staff_assigned_Assignment = staffx_.getassigned();
    for (int _j = 0; _j < staff_assigned_Assignment.size(); _j++)
    { out.println("assignmentx_" + assignments.indexOf(staff_assigned_Assignment.get(_j)) + " : staffx_" + _i + ".assigned");
    }
  }
  for (int _i = 0; _i < assignments.size(); _i++)
  { Assignment assignmentx_ = (Assignment) assignments.get(_i);
    out.println("assignmentx_" + _i + ".staff = staffx_" + staffs.indexOf(((Assignment) assignments.get(_i)).getstaff()));
    out.println("assignmentx_" + _i + ".task = taskx_" + tasks.indexOf(((Assignment) assignments.get(_i)).gettask()));
  }
  for (int _i = 0; _i < tasks.size(); _i++)
  { Task taskx_ = (Task) tasks.get(_i);
    List task_assignment_Assignment = taskx_.getassignment();
    for (int _j = 0; _j < task_assignment_Assignment.size(); _j++)
    { out.println("assignmentx_" + assignments.indexOf(task_assignment_Assignment.get(_j)) + " : taskx_" + _i + ".assignment");
    }
    List task_dependsOn_Task = taskx_.getdependsOn();
    for (int _j = 0; _j < task_dependsOn_Task.size(); _j++)
    { out.println("taskx_" + tasks.indexOf(task_dependsOn_Task.get(_j)) + " : taskx_" + _i + ".dependsOn");
    }
  }
    out.close(); 
  }


  public void saveXSI(String file)
  { File outfile = new File(file); 
    PrintWriter out; 
    try { out = new PrintWriter(new BufferedWriter(new FileWriter(outfile))); }
    catch (Exception e) { return; } 
    out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    out.println("<My:model xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\">");
    for (int _i = 0; _i < staffs.size(); _i++)
    { Staff staffx_ = (Staff) staffs.get(_i);
       out.print("<staffs xsi:type=\"My:Staff\"");
    out.print(" staffId=\"" + staffx_.getstaffId() + "\" ");
    out.print(" costDay=\"" + staffx_.getcostDay() + "\" ");
    out.print(" assigned = \"");
    List staff_assigned = staffx_.getassigned();
    for (int _j = 0; _j < staff_assigned.size(); _j++)
    { out.print(" //@assignments." + assignments.indexOf(staff_assigned.get(_j)));
    }
    out.print("\"");
    out.println(" />");
  }

    for (int _i = 0; _i < assignments.size(); _i++)
    { Assignment assignmentx_ = (Assignment) assignments.get(_i);
       out.print("<assignments xsi:type=\"My:Assignment\"");
    out.print(" staff=\"");
    out.print("//@staffs." + staffs.indexOf(((Assignment) assignments.get(_i)).getstaff()));
    out.print("\"");
    out.print(" task=\"");
    out.print("//@tasks." + tasks.indexOf(((Assignment) assignments.get(_i)).gettask()));
    out.print("\"");
    out.println(" />");
  }

    for (int _i = 0; _i < tasks.size(); _i++)
    { Task taskx_ = (Task) tasks.get(_i);
       out.print("<tasks xsi:type=\"My:Task\"");
    out.print(" taskId=\"" + taskx_.gettaskId() + "\" ");
    out.print(" duration=\"" + taskx_.getduration() + "\" ");
    out.print(" assignment = \"");
    List task_assignment = taskx_.getassignment();
    for (int _j = 0; _j < task_assignment.size(); _j++)
    { out.print(" //@assignments." + assignments.indexOf(task_assignment.get(_j)));
    }
    out.print("\"");
    out.print(" dependsOn = \"");
    List task_dependsOn = taskx_.getdependsOn();
    for (int _j = 0; _j < task_dependsOn.size(); _j++)
    { out.print(" //@tasks." + tasks.indexOf(task_dependsOn.get(_j)));
    }
    out.print("\"");
    out.println(" />");
  }

    for (int _i = 0; _i < allocatestaffs.size(); _i++)
    { AllocateStaff allocatestaffx_ = (AllocateStaff) allocatestaffs.get(_i);
       out.print("<allocatestaffs xsi:type=\"My:AllocateStaff\"");
    out.println(" />");
  }

    for (int _i = 0; _i < displayschedules.size(); _i++)
    { DisplaySchedule displayschedulex_ = (DisplaySchedule) displayschedules.get(_i);
       out.print("<displayschedules xsi:type=\"My:DisplaySchedule\"");
    out.println(" />");
  }

    out.println("</My:model>");
    out.close(); 
  }



  public void addStaff(Staff oo) { staffs.add(oo); }

  public Staff getStaffByPK(String staffIdx)
  {  return (Staff) staffstaffIdindex.get(staffIdx); }

  public List getStaffByPK(List staffIdx)
  { Vector res = new Vector(); 
    for (int _i = 0; _i < staffIdx.size(); _i++)
    { Staff staffx = getStaffByPK((String) staffIdx.get(_i));
      if (staffx != null) { res.add(staffx); }
    }
    return res; 
  }

  public void addAssignment(Assignment oo) { assignments.add(oo); }

  public void addTask(Task oo) { tasks.add(oo); }

  public Task getTaskByPK(String taskIdx)
  {  return (Task) tasktaskIdindex.get(taskIdx); }

  public List getTaskByPK(List taskIdx)
  { Vector res = new Vector(); 
    for (int _i = 0; _i < taskIdx.size(); _i++)
    { Task taskx = getTaskByPK((String) taskIdx.get(_i));
      if (taskx != null) { res.add(taskx); }
    }
    return res; 
  }

  public void addAllocateStaff(AllocateStaff oo) { allocatestaffs.add(oo); }

  public void addDisplaySchedule(DisplaySchedule oo) { displayschedules.add(oo); }



  public void createAllStaff(List staffx)
  { for (int i = 0; i < staffx.size(); i++)
    { Staff staffx_x = (Staff) staffx.get(i);
      if (staffx_x == null) { staffx_x = new Staff(); }
      staffx.set(i,staffx_x);
      addStaff(staffx_x);
    }
  }


  public Staff createStaff(String staffIdx)
  { 
    if (staffstaffIdindex.get(staffIdx) != null) { return null; }
    Staff staffx = new Staff();
    addStaff(staffx);
    setstaffId(staffx,staffIdx);
    setcostDay(staffx,0);
    setassigned(staffx,new Vector());
    staffstaffIdindex.put(staffIdx,staffx);

    return staffx;
  }

  public void createAllAssignment(List assignmentx)
  { for (int i = 0; i < assignmentx.size(); i++)
    { Assignment assignmentx_x = (Assignment) assignmentx.get(i);
      if (assignmentx_x == null) { assignmentx_x = new Assignment(); }
      assignmentx.set(i,assignmentx_x);
      addAssignment(assignmentx_x);
    }
  }


  public Assignment createAssignment(Staff staffx,Task taskx)
  { 
    Assignment assignmentx = new Assignment(staffx,taskx);
    addAssignment(assignmentx);
    setstaff(assignmentx,staffx);
    settask(assignmentx,taskx);

    return assignmentx;
  }

  public void createAllTask(List taskx)
  { for (int i = 0; i < taskx.size(); i++)
    { Task taskx_x = (Task) taskx.get(i);
      if (taskx_x == null) { taskx_x = new Task(); }
      taskx.set(i,taskx_x);
      addTask(taskx_x);
    }
  }


  public Task createTask(String taskIdx)
  { 
    if (tasktaskIdindex.get(taskIdx) != null) { return null; }
    Task taskx = new Task();
    addTask(taskx);
    settaskId(taskx,taskIdx);
    setduration(taskx,0);
    setassignment(taskx,new Vector());
    setdependsOn(taskx,new Vector());
    tasktaskIdindex.put(taskIdx,taskx);

    return taskx;
  }

  public void createAllAllocateStaff(List allocatestaffx)
  { for (int i = 0; i < allocatestaffx.size(); i++)
    { AllocateStaff allocatestaffx_x = (AllocateStaff) allocatestaffx.get(i);
      if (allocatestaffx_x == null) { allocatestaffx_x = new AllocateStaff(); }
      allocatestaffx.set(i,allocatestaffx_x);
      addAllocateStaff(allocatestaffx_x);
    }
  }


  public AllocateStaff createAllocateStaff()
  { 
    AllocateStaff allocatestaffx = new AllocateStaff();
    addAllocateStaff(allocatestaffx);

    return allocatestaffx;
  }

  public void createAllDisplaySchedule(List displayschedulex)
  { for (int i = 0; i < displayschedulex.size(); i++)
    { DisplaySchedule displayschedulex_x = (DisplaySchedule) displayschedulex.get(i);
      if (displayschedulex_x == null) { displayschedulex_x = new DisplaySchedule(); }
      displayschedulex.set(i,displayschedulex_x);
      addDisplaySchedule(displayschedulex_x);
    }
  }


  public DisplaySchedule createDisplaySchedule()
  { 
    DisplaySchedule displayschedulex = new DisplaySchedule();
    addDisplaySchedule(displayschedulex);

    return displayschedulex;
  }


public void setstaffId(Staff staffx, String staffId_x) 
  { if (staffstaffIdindex.get(staffId_x) != null) { return; }
  staffstaffIdindex.remove(staffx.getstaffId());
  staffx.setstaffId(staffId_x);
  staffstaffIdindex.put(staffId_x,staffx);
    }


public void setcostDay(Staff staffx, int costDay_x) 
  { staffx.setcostDay(costDay_x);
    }


  public void setassigned(Staff staffx, List assignedxx) 
  {   List _oldassignedxx = staffx.getassigned();
    for (int _j = 0; _j < _oldassignedxx.size(); _j++)
    { Assignment _yy = (Assignment) _oldassignedxx.get(_j);
      if (assignedxx.contains(_yy)) { }
      else { _yy.setstaff(null); }
    }
  for (int _i = 0; _i < assignedxx.size(); _i++)
  { Assignment _xx = (Assignment) assignedxx.get(_i);
    if (_oldassignedxx.contains(_xx)) { }
    else { if (_xx.getstaff() != null) { _xx.getstaff().removeassigned(_xx); }  }
    _xx.setstaff(staffx);
  }
    staffx.setassigned(assignedxx);
      }


  public void addassigned(Staff staffx, Assignment assignedxx) 
  { if (staffx.getassigned().contains(assignedxx)) { return; }
    if (assignedxx.getstaff() != null) { assignedxx.getstaff().removeassigned(assignedxx); }
  assignedxx.setstaff(staffx);
    staffx.addassigned(assignedxx);
    }


  public void removeassigned(Staff staffx, Assignment assignedxx) 
  { staffx.removeassigned(assignedxx);
    assignedxx.setstaff(null);
  }


 public void unionassigned(Staff staffx,List assignedx)
  { for (int _i = 0; _i < assignedx.size(); _i++)
    { Assignment assignmentxassigned = (Assignment) assignedx.get(_i);
      addassigned(staffx,assignmentxassigned);
     } } 


 public void subtractassigned(Staff staffx,List assignedx)
  { for (int _i = 0; _i < assignedx.size(); _i++)
    { Assignment assignmentxassigned = (Assignment) assignedx.get(_i);
      removeassigned(staffx,assignmentxassigned);
     } } 


  public void setstaff(Assignment assignmentx, Staff staffxx) 
  {   if (assignmentx.getstaff() == staffxx) { return; }
    if (assignmentx.getstaff() != null)
    { Staff old_value = assignmentx.getstaff();
      old_value.removeassigned(assignmentx); } 
    staffxx.addassigned(assignmentx);
    assignmentx.setstaff(staffxx);
      }


  public void settask(Assignment assignmentx, Task taskxx) 
  {   if (assignmentx.gettask() == taskxx) { return; }
    if (assignmentx.gettask() != null)
    { Task old_value = assignmentx.gettask();
      old_value.removeassignment(assignmentx); } 
    taskxx.addassignment(assignmentx);
    assignmentx.settask(taskxx);
      }


public void settaskId(Task taskx, String taskId_x) 
  { if (tasktaskIdindex.get(taskId_x) != null) { return; }
  tasktaskIdindex.remove(taskx.gettaskId());
  taskx.settaskId(taskId_x);
  tasktaskIdindex.put(taskId_x,taskx);
    }


public void setduration(Task taskx, int duration_x) 
  { taskx.setduration(duration_x);
    }


  public void setassignment(Task taskx, List assignmentxx) 
  {   List _oldassignmentxx = taskx.getassignment();
    for (int _j = 0; _j < _oldassignmentxx.size(); _j++)
    { Assignment _yy = (Assignment) _oldassignmentxx.get(_j);
      if (assignmentxx.contains(_yy)) { }
      else { _yy.settask(null); }
    }
  for (int _i = 0; _i < assignmentxx.size(); _i++)
  { Assignment _xx = (Assignment) assignmentxx.get(_i);
    if (_oldassignmentxx.contains(_xx)) { }
    else { if (_xx.gettask() != null) { _xx.gettask().removeassignment(_xx); }  }
    _xx.settask(taskx);
  }
    taskx.setassignment(assignmentxx);
      }


  public void addassignment(Task taskx, Assignment assignmentxx) 
  { if (taskx.getassignment().contains(assignmentxx)) { return; }
    if (assignmentxx.gettask() != null) { assignmentxx.gettask().removeassignment(assignmentxx); }
  assignmentxx.settask(taskx);
    taskx.addassignment(assignmentxx);
    }


  public void removeassignment(Task taskx, Assignment assignmentxx) 
  { taskx.removeassignment(assignmentxx);
    assignmentxx.settask(null);
  }


 public void unionassignment(Task taskx,List assignmentx)
  { for (int _i = 0; _i < assignmentx.size(); _i++)
    { Assignment assignmentxassignment = (Assignment) assignmentx.get(_i);
      addassignment(taskx,assignmentxassignment);
     } } 


 public void subtractassignment(Task taskx,List assignmentx)
  { for (int _i = 0; _i < assignmentx.size(); _i++)
    { Assignment assignmentxassignment = (Assignment) assignmentx.get(_i);
      removeassignment(taskx,assignmentxassignment);
     } } 


  public void setdependsOn(Task taskx, List dependsOnxx) 
  {     taskx.setdependsOn(dependsOnxx);
      }


  public void adddependsOn(Task taskx, Task dependsOnxx) 
  { if (taskx.getdependsOn().contains(dependsOnxx)) { return; }
      taskx.adddependsOn(dependsOnxx);
    }


  public void removedependsOn(Task taskx, Task dependsOnxx) 
  { taskx.removedependsOn(dependsOnxx);
    }


 public void uniondependsOn(Task taskx,List dependsOnx)
  { for (int _i = 0; _i < dependsOnx.size(); _i++)
    { Task taskxdependsOn = (Task) dependsOnx.get(_i);
      adddependsOn(taskx,taskxdependsOn);
     } } 


 public void subtractdependsOn(Task taskx,List dependsOnx)
  { for (int _i = 0; _i < dependsOnx.size(); _i++)
    { Task taskxdependsOn = (Task) dependsOnx.get(_i);
      removedependsOn(taskx,taskxdependsOn);
     } } 



  public  List AllAssignmenttoString(List assignmentxs)
  { 
    List result = new Vector();
    for (int _i = 0; _i < assignmentxs.size(); _i++)
    { Assignment assignmentx = (Assignment) assignmentxs.get(_i);
      result.add(assignmentx.toString());
    }
    return result; 
  }

  public void displayschedule1(Assignment assignmentx)
  {   assignmentx.displayschedule1();
   }

  public void allocatestaff1(Task taskx,Staff st)
  {   //  if (!(taskx.getassignment().size() == 0 && st.getassigned().size() == 0)) { return; } 
    taskx.allocatestaff1(st);
   }

  public  List AllTaskallocatestaff1test(List taskxs,Staff st)
  { 
    List result = new Vector();
    for (int _i = 0; _i < taskxs.size(); _i++)
    { Task taskx = (Task) taskxs.get(_i);
      result.add(new Boolean(taskx.allocatestaff1test(st)));
    }
    return result; 
  }

 public static boolean allocatestaff1search()
 { return Task.allocatestaff1search(); }



  public void killAllStaff(List staffxx)
  { for (int _i = 0; _i < staffxx.size(); _i++)
    { killStaff((Staff) staffxx.get(_i)); }
  }

  public void killStaff(Staff staffxx)
  { if (staffxx == null) { return; }
   staffs.remove(staffxx);
    Vector _1removedstaffAssignment = new Vector();
    Vector _1qrangestaffAssignment = new Vector();
    _1qrangestaffAssignment.addAll(staffxx.getassigned());
    for (int _i = 0; _i < _1qrangestaffAssignment.size(); _i++)
    { Assignment assignmentx = (Assignment) _1qrangestaffAssignment.get(_i);
      if (assignmentx != null && staffxx.equals(assignmentx.getstaff()))
      { _1removedstaffAssignment.add(assignmentx);
        assignmentx.setstaff(null);
      }
    }
    staffstaffIdindex.remove(staffxx.getstaffId());
    for (int _i = 0; _i < _1removedstaffAssignment.size(); _i++)
    { killAssignment((Assignment) _1removedstaffAssignment.get(_i)); }
  }



  public void killAllAssignment(List assignmentxx)
  { for (int _i = 0; _i < assignmentxx.size(); _i++)
    { killAssignment((Assignment) assignmentxx.get(_i)); }
  }

  public void killAssignment(Assignment assignmentxx)
  { if (assignmentxx == null) { return; }
   assignments.remove(assignmentxx);
    Vector _2qrangeassignedStaff = new Vector();
    _2qrangeassignedStaff.add(assignmentxx.getstaff());
    for (int _i = 0; _i < _2qrangeassignedStaff.size(); _i++)
    { Staff staffx = (Staff) _2qrangeassignedStaff.get(_i);
      if (staffx != null && staffx.getassigned().contains(assignmentxx))
      { removeassigned(staffx,assignmentxx); }
    }
    Vector _2qrangeassignmentTask = new Vector();
    _2qrangeassignmentTask.add(assignmentxx.gettask());
    for (int _i = 0; _i < _2qrangeassignmentTask.size(); _i++)
    { Task taskx = (Task) _2qrangeassignmentTask.get(_i);
      if (taskx != null && taskx.getassignment().contains(assignmentxx))
      { removeassignment(taskx,assignmentxx); }
    }
  }



  public void killAllTask(List taskxx)
  { for (int _i = 0; _i < taskxx.size(); _i++)
    { killTask((Task) taskxx.get(_i)); }
  }

  public void killTask(Task taskxx)
  { if (taskxx == null) { return; }
   tasks.remove(taskxx);
    Vector _1removedtaskAssignment = new Vector();
    Vector _1qrangetaskAssignment = new Vector();
    _1qrangetaskAssignment.addAll(taskxx.getassignment());
    for (int _i = 0; _i < _1qrangetaskAssignment.size(); _i++)
    { Assignment assignmentx = (Assignment) _1qrangetaskAssignment.get(_i);
      if (assignmentx != null && taskxx.equals(assignmentx.gettask()))
      { _1removedtaskAssignment.add(assignmentx);
        assignmentx.settask(null);
      }
    }
    Vector _1qrangedependsOnTask = new Vector();
    _1qrangedependsOnTask.addAll(tasks);
    for (int _i = 0; _i < _1qrangedependsOnTask.size(); _i++)
    { Task taskx = (Task) _1qrangedependsOnTask.get(_i);
      if (taskx != null && taskx.getdependsOn().contains(taskxx))
      { removedependsOn(taskx,taskxx); }
    }
    tasktaskIdindex.remove(taskxx.gettaskId());
    for (int _i = 0; _i < _1removedtaskAssignment.size(); _i++)
    { killAssignment((Assignment) _1removedtaskAssignment.get(_i)); }
  }



  public void killAllAllocateStaff(List allocatestaffxx)
  { for (int _i = 0; _i < allocatestaffxx.size(); _i++)
    { killAllocateStaff((AllocateStaff) allocatestaffxx.get(_i)); }
  }

  public void killAllocateStaff(AllocateStaff allocatestaffxx)
  { if (allocatestaffxx == null) { return; }
   allocatestaffs.remove(allocatestaffxx);
  }



  public void killAllDisplaySchedule(List displayschedulexx)
  { for (int _i = 0; _i < displayschedulexx.size(); _i++)
    { killDisplaySchedule((DisplaySchedule) displayschedulexx.get(_i)); }
  }

  public void killDisplaySchedule(DisplaySchedule displayschedulexx)
  { if (displayschedulexx == null) { return; }
   displayschedules.remove(displayschedulexx);
  }




  
    public void allocateStaff() 
  { 

    boolean allocatestaff1_running;
  allocatestaff1_running = true;
    while (allocatestaff1_running) 
  { allocatestaff1_running = Task.allocatestaff1search(); }


  }



    public void displaySchedule() 
  { 

       List assignmentdisplayschedule1x = new Vector();
  assignmentdisplayschedule1x.addAll(Controller.inst().assignments);
  for (int assignmentdisplayschedule1x_ind4 = 0; assignmentdisplayschedule1x_ind4 < assignmentdisplayschedule1x.size(); assignmentdisplayschedule1x_ind4++)
  { Controller.inst().displayschedule1((Assignment) assignmentdisplayschedule1x.get(assignmentdisplayschedule1x_ind4)); }


  }


 
}



