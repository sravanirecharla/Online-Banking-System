import java.awt.*;     //including Java packages used by this program
import java.awt.event.*;
import javax.swing.*;
import com.Recharla.*;


class EnquireTransactionPanel extends JPanel implements ActionListener {

  //private class SearchDialog extends JDialog {
	  //public SearchDialog(JFrame parent)
	  //{
		 private JButton EnquireTransaction;
         private JTextField UserNameField, NameField, StartDateField, EndDateField;
         //private JDialog EnquireTransaction;
         private final JTable table;
         private String UName, Name, StartDate, EndDate;
         //private String CA_Number, SA_Number;


         public tablePanel() {
			 Object[] columnNames =  { "TransactionNumber", "TransactionAmount", "TransactionTime", "TransactionDate", "FromAccount", "ToAccount", "TransactionType"};
			 Object [][][][][][][] componentNames =


		 }

         public EnquireTransactionPanel(String U_Name, String Customer_Name)
         {
     		UName = U_Name;
     		UserNameField = new JTextField(15);
     		UserNameField.setText(UName);
     		StartDateField = new JTextField(10);
     		StartDateField.setText(StartDate);
     		EndDateField = new JTextField(10);
     		EndDateField.setText(EndDate);
     		EnquireTransaction = new JButton("Enquire Transaction");
     		JLabel NameLabel = new JLabel("Customer Name:");
            JLabel UserNameLabel = new JLabel("Username: ");
            JLabel StartDateLabel = new JLabel("Start Date");
            JLabel EndDateLabel = new JLabel("End Date");




			JPanel UserNamePanel = new JPanel();
			JPanel NamePanel = new JPanel();
			JPanel StartDatePanel= new JPanel();
			JPanel EndDatePanel= new JPanel();


			UserNamePanel.add(UserNameLabel);
			UserNamePanel.add(UserNameField);
			NamePanel.add(NameLabel);
			NamePanel.add(NameField);
			StartDatePanel.add(StartDateLabel);
			StartDatePanel.add(StartDateField);
			EndDatePanel.add(EndDateLabel);
			EndDatePanel.add(EndDateLabel);

			add(UserNamePanel);
			add(NamePanel);
			add(StartDatePanel);
			add(EndDatePanel);
			add(EnquireTransaction);

			EnqiureTransaction.addActionListener(this);
	}

      public void actionPerformed(ActionEvent evt)  //event handling
         {
             //Object source = evt.getSource(); //get who generates this event
             String arg = evt.getActionCommand();
             if (arg.equals("EnquireTransaction")) { //determine which button is clicked
                 UName = UserNameField.getText(); //take actions
                 Name = NameField.getText();
                 StartDate = StartDateField.getText();
                 EndDate = EndDateField.getText();
                 if (StartDate.equals(""))
                     JOptionPane.showMessageDialog(null, "Please enter start date", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                 if (EndDate.equals("") )
                          JOptionPane.showMessageDialog(null, "Please enter end date", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                 else {
					 EnquireTransactionControl ETC = new EnquireTransactionControl(UName, StartDate, EndDate);
				 }
			 }
		 }


     public class EnquireTransactionBO extends JFrame
     {
         private EnquireTransactionPanel ETB_Panel;

         public EnquireTransactionBO(String UName, String CustomerName)
         {
             setTitle("Enquire Transaction");
             setSize(350, 260);

              //get screen size and set the location of the frame
              Toolkit tk = Toolkit.getDefaultToolkit();
              Dimension d = tk.getScreenSize();
              int screenHeight = d.height;
              int screenWidth = d.width;
              setLocation( screenWidth / 3, screenHeight / 4);

              addWindowListener (new WindowAdapter()  //handle window event
                 {
     		       public void windowClosing (WindowEvent e)
     			                  { System.exit(0);
                    }
                 });

              Container contentPane = getContentPane(); //add a panel to a frame
              ETB_Panel = new EnquireTransactionPanel(UName, CustomerName);
              contentPane.add(ETB_Panel);
              show();
         }
     }
 }

