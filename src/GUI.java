
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Tahmidul on 28/11/2015.
 */
public class GUI extends JFrame{
    Controller controller;
    JPanel jpanel;
    JPanel scheduleJPanel;
    public GUI(){
        super("Agile Development");
        controller = new Controller();
        initUi();

        setSize(500,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void initUi(){
        //Sets main layout to box layout
        jpanel = new JPanel();
        add(new JScrollPane(jpanel), BorderLayout.CENTER);
        jpanel.setLayout(new BoxLayout(jpanel,BoxLayout.PAGE_AXIS));



        JButton loadModel = new JButton("Load Model");
        loadModel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller = new Controller();
                controller.initialise();
            }
        });
        JButton saveModel = new JButton("Save Model");
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
        JButton allocateStaff = new JButton("Allocate Staff");
        allocateStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.allocateStaff();
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
            }
        });
        JButton calculateCost = new JButton("Calculate Cost");
        calculateCost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.calculateCost();
                JOptionPane.showMessageDialog(null, "Total Cost: "+controller.schedule.getTotalCost());
            }
        });
        addComponents(jpanel,loadModel,saveModel,allocateStaff,displaySchedule,nextIteration,calculateCost);

        scheduleJPanel = new JPanel();
        scheduleJPanel.setLayout(new BoxLayout(scheduleJPanel,BoxLayout.PAGE_AXIS));
        jpanel.add(scheduleJPanel);
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
        scheduleJPanel.add(titleLabel);
        scheduleJPanel.add(new JScrollPane(jTable));
        scheduleJPanel.add(totalCostLabel);
        scheduleJPanel.setMaximumSize(new Dimension(2000,200));
    }

}
