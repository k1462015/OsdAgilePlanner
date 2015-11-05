import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.io.*;
import java.util.StringTokenizer;

public class GUI extends JFrame implements ActionListener
{ JPanel panel = new JPanel();
  Controller cont = Controller.inst();
  JButton loadModelButton = new JButton("loadModel");
  JButton saveModelButton = new JButton("saveModel");
  JButton allocateStaffButton = new JButton("allocateStaff");
  JButton displayScheduleButton = new JButton("displaySchedule");

 public GUI()
  { super("Select use case to execute");
    setContentPane(panel);
    addWindowListener(new WindowAdapter() 
    { public void windowClosing(WindowEvent e)
      { System.exit(0); } });
  panel.add(loadModelButton);
  loadModelButton.addActionListener(this);
  panel.add(saveModelButton);
  saveModelButton.addActionListener(this);
  panel.add(allocateStaffButton);
  allocateStaffButton.addActionListener(this);
  panel.add(displayScheduleButton);
  displayScheduleButton.addActionListener(this);
  }

  public void actionPerformed(ActionEvent e)
  { if (e == null) { return; }
    String cmd = e.getActionCommand();
    if ("loadModel".equals(cmd))
    { Controller.loadModel("in.txt");
      cont.checkCompleteness();
      System.err.println("Model loaded");
      return; } 
    if ("saveModel".equals(cmd))
    { cont.saveModel("out.txt");  
      cont.saveXSI("xsi.txt"); 
      return; } 
    if ("allocateStaff".equals(cmd))
    {  cont.allocateStaff() ;  return; } 
    if ("displaySchedule".equals(cmd))
    {  cont.displaySchedule() ;  return; } 
  }

  public static void main(String[] args)
  {  GUI gui = new GUI();
    gui.setSize(400,400);
    gui.setVisible(true);
  }
 }
