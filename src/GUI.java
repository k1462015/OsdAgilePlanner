
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Tahmidul on 28/11/2015.
 */
public class GUI extends JFrame{
    Controller controller;
    JPanel optionJPanel;
    JPanel scheduleJPanel;
    public GUI(){
        super("Agile Development");
        controller = new Controller();
        initUi();

        setSize(1000,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void initUi(){
        //Sets option layout to box layout
        optionJPanel = new JPanel();
        add(new JScrollPane(optionJPanel), BorderLayout.NORTH);
        optionJPanel.setLayout(new BoxLayout(optionJPanel,BoxLayout.PAGE_AXIS));

        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("File");
        jMenuBar.add(jMenu);
        JMenuItem loadModel = new JMenuItem("Load Model");
        loadModel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller = new Controller();
                controller.initialise();
            }
        });
        JMenuItem saveModel = new JMenuItem("Save Model");
        saveModel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileManager.outputText(controller);
                    JOptionPane.showMessageDialog(null, "Output saved to out.txt");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        jMenu.add(loadModel);
        jMenu.add(saveModel);
        setJMenuBar(jMenuBar);
        JButton allocateStaff = new JButton("Allocate Staff");
        allocateStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Show what allocations have already been made
                String message = "";
                ArrayList<Assignment> oldAssignments = new ArrayList<Assignment>();
                controller.schedule.getAssignments();
                if(controller.schedule.getAssignments().size() > 0){
                    message += "----Old Assignments---\n";
                }
                for (Assignment assignment:controller.schedule.getAssignments()){
                    message += "Staff: "+assignment.getStaff().getStaffId()
                            +" has already been assigned to Task: "+assignment.getTask().getTaskId()+"\n";
                    oldAssignments.add(assignment);
                }

                long startTime = System.nanoTime();
                controller.allocateStaff();
                long endTime = System.nanoTime();

                //Shows new assignments
                ArrayList<Assignment> newAssignments = controller.schedule.getAssignments();
                message += "----New Assignments---\n";
                for (int i  = 0;i < newAssignments.size();i++){
                    Assignment assignment = newAssignments.get(i);
                    if(!oldAssignments.contains(assignment)){
                        message += "New Assignment --> Staff: "+assignment.getStaff().getStaffId()
                                +" has been assigned to Task: "+assignment.getTask().getTaskId()+"\n";
                    }
                }

                if(newAssignments.size() == 0){
                    message = "No assignments could be made.";
                }else{
                    message += "\nTime Taken: "+(endTime - startTime)+" ns";
                }

                JOptionPane.showMessageDialog(null,message);
                createSchedule();
            }
        });
        JButton displaySchedule = new JButton("Display Schedule");
        displaySchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.displaySchedule();
                createSchedule();
                setVisible(true);
            }
        });
        JButton nextIteration = new JButton("Next Iteration");
        nextIteration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.nextIteration();
                JOptionPane.showMessageDialog(null, "All staff have been made unallocated. \n" +
                        "Tasks can now be assigned to corresponding staff members."
                        +"\nPlease click allocate staff.");
            }
        });
        JButton calculateCost = new JButton("Calculate Cost");
        calculateCost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.calculateCost();
                //Show breakdown of costs
                String message = "";
                ArrayList<Assignment> assignments = controller.schedule.getAssignments();
                for (Assignment assignment:assignments){
                    int costDay = assignment.getStaff().getCostDay();
                    int taskDuration = assignment.getTask().totalDuration();
                    int totalCost = costDay*taskDuration;
                    message += "Assignment - Staff: "+assignment.getStaff().getStaffId()
                            +" |Task: "+assignment.getTask().getTaskId()+
                            " --> Total:  "+costDay+"*"+taskDuration+" = "+totalCost+"\n";
                }
                JOptionPane.showMessageDialog(null, message+"Total Cost: "+controller.schedule.getTotalCost()+" Total Duration: "+controller.schedule.getDuration());
            }
        });
        addComponents(optionJPanel,allocateStaff,displaySchedule,nextIteration,calculateCost);

        scheduleJPanel = new JPanel();
        scheduleJPanel.setLayout(new BoxLayout(scheduleJPanel,BoxLayout.PAGE_AXIS));
        add(scheduleJPanel,BorderLayout.CENTER);
    }

    public void addComponents(JPanel panel,Component... components){
        for (int i = 0;i < components.length;i++){
            panel.add(components[i]);
        }
    }

    public void createSchedule(){
        controller.calculateCost();
        if(scheduleJPanel.getComponentCount() > 0){
            scheduleJPanel.removeAll();
        }
        scheduleJPanel.removeAll();   //Removes previous table
        String[] columnNames = {"Assignment Number","Staff Id","Cost per/day","Staff Has","Task Id","Task Duration","Task needs","Task Depends"};
        Object[][] data = new Object[controller.schedule.getAssignments().size()][];
        int row = 0;
        for (Assignment assignment:controller.schedule.getAssignments()){
            Task task = assignment.getTask();
            String taskDependsOn = "";
            ArrayList<Task> dependsOn = task.getDependsOn();
            for (Task t:dependsOn){
                taskDependsOn += "["+t.getTaskId()+"]";
            }
            data[row] = new Object[]{row,assignment.getStaff().getStaffId(),assignment.getStaff().getCostDay(),assignment.getStaff().getHas().toString(),
                    task.getTaskId(),task.getDuration(),task.getNeeds().toString(),taskDependsOn};
            row++;
        }
        JTable jTable = new JTable(data,columnNames);
        JLabel totalCostLabel = new JLabel("Total Schedule Cost: "+controller.schedule.getTotalCost());
        JLabel titleLabel = new JLabel("Schedule");
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 40));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel gridPanel = new JPanel(new GridLayout(1,2));
        Font font = new Font("Calibri",Font.BOLD,20);
        JLabel scheduleCost = new JLabel("Schedule Cost: "+controller.schedule.getTotalCost());
        scheduleCost.setFont(font);
        JLabel scheduleDuration = new JLabel("Schedule Duration: "+controller.schedule.getDuration());
        scheduleDuration.setFont(font);
        gridPanel.add(scheduleCost);
        gridPanel.add(scheduleDuration);

        scheduleJPanel.add(titleLabel);
        scheduleJPanel.add(new JScrollPane(jTable));
        scheduleJPanel.add(gridPanel);
        scheduleJPanel.setMaximumSize(new Dimension(2000,200));
        setVisible(true);
    }

}
