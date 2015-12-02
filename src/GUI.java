
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

        setSize(700,600);
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
                long startTime = System.nanoTime();
                controller.allocateStaff();
                long endTime = System.nanoTime();
                //Show what allocations have been made
                String message = "";
                ArrayList<Assignment> assignments = controller.schedule.getAssignments();
                for (Assignment assignment:assignments){
                    message += "Staff: "+assignment.getStaff().getStaffId()
                            +" has been assigned to Task: "+assignment.getTask().getTaskId()+"\n";
                }
                if(assignments.size() == 0){
                    message = "No assignments could be made.";
                }else{
                    message += "\nTime Taken: "+(endTime - startTime)+" ns";
                }

                JOptionPane.showMessageDialog(null,message);
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
                JOptionPane.showMessageDialog(null, message+"Total Cost: "+controller.schedule.getTotalCost());
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
        if(scheduleJPanel.getComponentCount() > 0){
            scheduleJPanel.removeAll();
        }
        scheduleJPanel.removeAll();   //Removes previous table
        String[] columnNames = {"Staff Id","Cost per/day","Staff Has","Task Id","Task Duration","Task needs"};
        Object[][] data = new Object[controller.schedule.getAssignments().size()][];
        int row = 0;
        for (Assignment assignment:controller.schedule.getAssignments()){
            data[row] = new Object[]{assignment.getStaff().getStaffId(),assignment.getStaff().getCostDay(),assignment.getStaff().getHas().toString(),
                    assignment.getTask().getTaskId(),assignment.getTask().getDuration(),assignment.getTask().getNeeds().toString()};
            row++;
        }
        JTable jTable = new JTable(data,columnNames);
        JLabel totalCostLabel = new JLabel("Total Schedule Cost: "+controller.schedule.getTotalCost());
        JLabel titleLabel = new JLabel("Schedule");
        titleLabel.setFont(new Font("Courier New", Font.BOLD, 40));
        scheduleJPanel.add(titleLabel);
        scheduleJPanel.add(new JScrollPane(jTable));
        scheduleJPanel.add(totalCostLabel);
        scheduleJPanel.setMaximumSize(new Dimension(2000,200));
    }

}
