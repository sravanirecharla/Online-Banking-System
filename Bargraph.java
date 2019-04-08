import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class barPanel extends JPanel{
   private double[] monthlyProfit;
   private String[] month = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
                             "Aug", "Sep", "Oct", "Nov", "Dec"};
   public barPanel(double[] profit){
      monthlyProfit = profit;
   }

   private double maxMonth(){
      double max = monthlyProfit[0];
      for (int i=1; i<12; i++)
          if(monthlyProfit[i] > max) max=monthlyProfit[i];
      return max;
   public void paintComponent(Graphics g){
      int i;
      super.paintComponent(g); //set graphics context by parent class
      setBackground(Color.white);
      double Max = maxMonth();
      g.setColor(Color.black);
      for (i=0; i<12; i++){
          int y=50 + (int)(Max-monthlyProfit[i]);
          g.fillRect(40+i*40, y, 20, (int)monthlyProfit[i]);
      }
      g.drawString("Profit", 10, 20);
      g.drawLine(20, 30, 20, 50+(int)Max);
      g.drawLine(20, 50+(int)Max, 520, 50+(int)Max);
      g.drawString("Month", 540, 50+(int)Max);
      for (i=0; i<12; i++)
          g.drawString(month[i], 40+i*40, 80+(int)Max);
   }
}
}
class barThread extends Thread{
   public barThread(double[] profit, JFrame parent){
      MonthlyProfit = profit;
      frame = parent;
   }
   //override method run() and include what to be done in this thread
   public void run(){
      barChart = new barPanel(MonthlyProfit);
      frame.getContentPane().add(barChart);
   }

   private double[] MonthlyProfit;
   private JFrame frame;
   private JPanel barChart;
}
class tablePanel extends JPanel{
   private Vector rowData = new Vector();
   private String[] month = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
                                             "Aug", "Sep", "Oct", "Nov", "Dec"};
   private Vector columnNames = new Vector();
   public tablePanel(double[] profit){
      setBackground(Color.white);
      columnNames.addElement("Month");
      Vector row = new Vector();
      row.addElement("Profit");
      for (int i=0; i<profit.length; i++){
         columnNames.addElement(month[i]);
         Double temp = new Double(profit[i]);
         row.addElement(temp.toString());
      }
      rowData.addElement(row);
      JTable table = new JTable(rowData, columnNames);
      table.setPreferredScrollableViewportSize(new Dimension(600, 40));
      JScrollPane scrollPane = new JScrollPane(table);
      JPanel p = new JPanel();
      p.add(scrollPane);
      add(p);
   }
}
class tableThread extends Thread{
   public tableThread(double[] profit, JFrame parent){
      MonthlyProfit = profit;
      frame = parent;
   }

   public void run(){
      try{
         Thread.sleep((int)(Math.random()*5000));
      }
      catch (InterruptedException e){
         System.err.println(e.toString());
      }
      tableChart = new tablePanel(MonthlyProfit);
      frame.getContentPane().add(tableChart, "South");
   }
   private double[] MonthlyProfit;
   private JFrame frame;
   private JPanel tableChart;
}
class barFrame extends JFrame{
   public barFrame(){
      setTitle("Multithreading Demo");
      setSize(640, 300);
      getContentPane().setLayout(new BorderLayout(0, 5));
      //get screen size and set the location of the frame
      Toolkit tk = Toolkit.getDefaultToolkit();
      Dimension d = tk.getScreenSize();
      int screenHeight = d.height;
      int screenWidth = d.width;
      setLocation( screenWidth / 2 - 320, screenHeight / 4);

      addWindowListener (new WindowAdapter()  //handle window closing event
         {  public void windowClosing (WindowEvent e)
            { System.exit(0);
            }
         });
      computeMonthlyProfit();
     //declare and initialize two threads
      Thread drawBarThread = new barThread(monthlyProfit, this);
      Thread addTableThread = new tableThread(monthlyProfit, this);
      drawBarThread.setDaemon(true); //set a thread to be daemon thread
      addTableThread.setPriority(8); //set priority for a thread
      drawBarThread.start();  //start a thread
      addTableThread.start();
      show();
   }

   private void computeMonthlyProfit(){
      monthlyProfit = new double[12];
      for (int i=0; i<12; i++)
          monthlyProfit[i] = Math.random()*100;
   }
   private double[] monthlyProfit;
}
  public class testThread{  //main class of this program
      public static void main(String [] args){
            JFrame frame = new barFrame(); //initialize a JFrame object
      }
}
