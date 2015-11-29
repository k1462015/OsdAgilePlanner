
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Tahmidul on 28/11/2015.
 */
public class GUI extends JFrame{
    Controller controller;
    JPanel jpanel;
    public GUI(){
        super("Agile Development");
        controller = new Controller();
        initUi();

        setSize(1000,1000);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void initUi(){
        //Sets main layout to box layout
        jpanel = new JPanel();
        add(jpanel, BorderLayout.NORTH);
        jpanel.setLayout(new BoxLayout(jpanel,BoxLayout.PAGE_AXIS));
//        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JButton loadModel = new JButton("Load Model");
        loadModel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller = new Controller();
                controller.initialise();
            }
        });
        JButton saveModel = new JButton("Save Model");
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
                JPanel tempJPanel = new JPanel();
                JLabel jLabel = new JLabel("Schedule");
                tempJPanel.setLayout(new BoxLayout(tempJPanel,BoxLayout.PAGE_AXIS));
                tempJPanel.add(jLabel);
                tempJPanel.add(new JScrollPane(createSchedule()));
                JLabel totalCostLabel = new JLabel("Total Schedule Cost: "+controller.schedule.getTotalCost()+"");
                tempJPanel.add(totalCostLabel);
                jpanel.add(tempJPanel);
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
            }
        });
        addComponents(jpanel,loadModel,saveModel,allocateStaff,displaySchedule,nextIteration,calculateCost);
    }

    public void addComponents(JPanel panel,Component... components){
        for (int i = 0;i < components.length;i++){
            panel.add(components[i]);
        }
    }

    public JTable createSchedule(){
        String[] columnNames = {"Staff Id","Cost per/day","Task Id","Task Duration"};
        Object[][] data = new Object[controller.schedule.getAssignments().size()][];
        int row = 0;
        for (Assignment assignment:controller.schedule.getAssignments()){
            data[row] = new Object[]{assignment.getStaff().getStaffId(),assignment.getStaff().getCostDay(),assignment.getTask().getTaskId(),assignment.getTask().getDuration()};
            row++;
        }
        JTable jTable = new JTable(data,columnNames);
        if(jTable != null){
            return jTable;
        }else{
            return new JTable();
        }
    }

}
