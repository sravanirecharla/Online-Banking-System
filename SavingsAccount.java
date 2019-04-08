/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: October, 2013													      *
*******************************************************************************/
package com.Recharla;

import java.lang.*; //including Java packages used by this program
import java.sql.*;
import com.Recharla.*;

public class SavingsAccount
{   //Instance Variables
	private String SavingsAccountNumber, CustomerName, CustomerID;
	private float Balance = -1, TransactionAmount = -1;
	private double InterestRate = 0.1;

	public SavingsAccount(String SA_Num, String Cust_Name, String Cust_ID, String Bal) { //Constructor One with three parameters
		SavingsAccountNumber = SA_Num;
		CustomerName = Cust_Name;
		CustomerID = Cust_ID;
		Balance = Float.parseFloat(Bal);
	}

	public SavingsAccount(String SA_Num) { //Constructor Two with one parameter
		SavingsAccountNumber = SA_Num;
	}

	public SavingsAccount() { //Constructor Two with one parameter

	}
	public SavingsAccount(String SA_Num, String Cust_ID, String Tamt ){
		SavingsAccountNumber = SA_Num;
		CustomerID = Cust_ID;
		TransactionAmount = Float.parseFloat(Tamt);

	}

        public String getSavingsAccountNumber(){
            return SavingsAccountNumber;
        }

        public float getBalanceValue(){
            return Balance;
        }

 	public boolean openAcct() {

	     boolean done = false;
				try {
				    if (!done) {
				        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
				        Connection DBConn = ToDB.openConn();
				        Statement Stmt = DBConn.createStatement();
				        String SQL_Command = "SELECT SavingsAccountNumber FROM SavingsAccount WHERE SavingsAccountNumber ='"+SavingsAccountNumber+"'"; //SQL query command
				        ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username exsits.
				        done = !Rslt.next();
				        //System.out.println("done =" + done);
				        if (done) {
						    SQL_Command = "INSERT INTO SavingsAccount(SavingsAccountNumber, CustomerName, Balance, CustomerID)"+
						                  " VALUES ('"+SavingsAccountNumber+"','"+CustomerName+"',"+Balance+", '"+CustomerID+"')"; //Save the username, password and Name
						    Stmt.executeUpdate(SQL_Command);
						   // System.out.println("done =" + done);
					    }
					    Stmt.close();
					    ToDB.closeConn();
					}
				}
			    catch(java.sql.SQLException e)
			    {         done = false;
						 System.out.println("SQLException: " + e);
						 while (e != null)
						 {   System.out.println("SQLState: " + e.getSQLState());
							 System.out.println("Message: " + e.getMessage());
							 System.out.println("Vendor: " + e.getErrorCode());
							 e = e.getNextException();
							 System.out.println("");
						 }
			    }
			    catch (java.lang.Exception e)
			    {         done = false;
						 System.out.println("Exception: " + e);
						 e.printStackTrace ();
			    }
	    return done;
	}

/*public boolean openAcct() {
	     boolean done = false;
				try {
				    if (!done) {
				        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
				        Connection DBConn = ToDB.openConn();
				        Statement Stmt = DBConn.createStatement();
				        String SQL_Command = "SELECT SavingsAccountNumber FROM SavingsAccount WHERE SavingsAccountNumber ='"+SavingsAccountNumber+"'"; //SQL query command
				        ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username exsits.
				        done = !Rslt.next();
				        if (done) {
						    SQL_Command = "INSERT INTO SavingsAccount(SavingsAccountNumber, CustomerName, Balance, CustomerID)"+
						                  " VALUES ('"+SavingsAccountNumber+"','"+CustomerName+"',"+Balance+", '"+CustomerID+"')"; //Save the username, password and Name
						    Stmt.executeUpdate(SQL_Command);
					    }
					    Stmt.close();
					    ToDB.closeConn();
					}
				}
			    catch(java.sql.SQLException e)
			    {         done = false;
						 System.out.println("SQLException: " + e);
						 while (e != null)
						 {   System.out.println("SQLState: " + e.getSQLState());
							 System.out.println("Message: " + e.getMessage());
							 System.out.println("Vendor: " + e.getErrorCode());
							 e = e.getNextException();
							 System.out.println("");
						 }
			    }
			    catch (java.lang.Exception e)
			    {         done = false;
						 System.out.println("Exception: " + e);
						 e.printStackTrace ();
			    }
	    return done;
	}*/

    public float getBalance() {  //Method to return a CheckingAccount balance
		try {
		        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
		        Connection DBConn = ToDB.openConn();
		        Statement Stmt = DBConn.createStatement();
		        String SQL_Command = "SELECT Balance FROM SavingsAccount WHERE SavingsAccountNumber ='"+SavingsAccountNumber+"'"; //SQL query command for Balance
		        ResultSet Rslt = Stmt.executeQuery(SQL_Command);

		        while (Rslt.next()) {
					Balance = Rslt.getFloat(1);
			    }
			    Stmt.close();
			    ToDB.closeConn();
		}
	    catch(java.sql.SQLException e)
	    {
				 System.out.println("SQLException: " + e);
				 while (e != null)
				 {   System.out.println("SQLState: " + e.getSQLState());
					 System.out.println("Message: " + e.getMessage());
					 System.out.println("Vendor: " + e.getErrorCode());
					 e = e.getNextException();
					 System.out.println("");
				 }
	    }
	    catch (java.lang.Exception e)
	    {
				 System.out.println("Exception: " + e);
				 e.printStackTrace ();
	    }
	    return Balance;
	}

    public float getBalance(String SavAcctNumber) {  //Method to return a CheckingAccount balance
		try {
		        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
		        Connection DBConn = ToDB.openConn();
		        Statement Stmt = DBConn.createStatement();
		        String SQL_Command = "SELECT Balance FROM SavingsAccount WHERE SavingsAccountNumber ='"+SavAcctNumber+"'"; //SQL query command for Balance
		        ResultSet Rslt = Stmt.executeQuery(SQL_Command);

		        while (Rslt.next()) {
					Balance = Rslt.getFloat(1);
			    }
			    Stmt.close();
			    ToDB.closeConn();
		}
	    catch(java.sql.SQLException e)
	    {
				 System.out.println("SQLException: " + e);
				 while (e != null)
				 {   System.out.println("SQLState: " + e.getSQLState());
					 System.out.println("Message: " + e.getMessage());
					 System.out.println("Vendor: " + e.getErrorCode());
					 e = e.getNextException();
					 System.out.println("");
				 }
	    }
	    catch (java.lang.Exception e)
	    {
				 System.out.println("Exception: " + e);
				 e.printStackTrace ();
	    }
	    return Balance;
	}

	public String getAccount(String Cus_ID) {  //Method to return a CheckingAccount balance
			try {
			        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
			        Connection DBConn = ToDB.openConn();
			        Statement Stmt = DBConn.createStatement();
			        String SQL_Command = "SELECT SavingsAccountNumber FROM SavingsAccount WHERE CustomerID ='"+Cus_ID+"'"; //SQL query command for Balance
			        ResultSet Rslt = Stmt.executeQuery(SQL_Command);

			        while (Rslt.next()) {
						SavingsAccountNumber = Rslt.getString("SavingsAccountNumber");
				    }
				    Stmt.close();
				    ToDB.closeConn();
			}
		    catch(java.sql.SQLException e)
		    {
					 System.out.println("SQLException: " + e);
					 while (e != null)
					 {   System.out.println("SQLState: " + e.getSQLState());
						 System.out.println("Message: " + e.getMessage());
						 System.out.println("Vendor: " + e.getErrorCode());
						 e = e.getNextException();
						 System.out.println("");
					 }
		    }
		    catch (java.lang.Exception e)
		    {
					 System.out.println("Exception: " + e);
					 e.printStackTrace ();
		    }
		    return SavingsAccountNumber;
	}

	public boolean withdraw() {
			//private double Balance = -1;
			     boolean done = true;
						try {
							DBConnection ToDB = new DBConnection(); //Have a connection to the DB
						    Connection DBConn = ToDB.openConn();
						    Statement Stmt = DBConn.createStatement();
						    String SQL_Command = "SELECT Balance FROM SavingsAccount WHERE SavingsAccountNumber ='"+SavingsAccountNumber+"'"; //SQL query command
						    ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username exsits.

						    while (Rslt.next()) {
								Balance = Rslt.getFloat(1);
							}
						    if(Balance >= TransactionAmount) {
								Balance -= TransactionAmount;
								SQL_Command = "UPDATE SavingsAccount SET Balance='"+Balance+"' WHERE SavingsAccountNumber ='"+SavingsAccountNumber+"'"; //Save the username, password and Name
					    		Stmt.executeUpdate(SQL_Command);
								Stmt.close();
							    ToDB.closeConn();
							    done = true;
							} else
								done = false;
						}
						catch(java.sql.SQLException e)
					    {         done = false;
								 System.out.println("SQLException: " + e);
								 while (e != null)
								 {   System.out.println("SQLState: " + e.getSQLState());
									 System.out.println("Message: " + e.getMessage());
									 System.out.println("Vendor: " + e.getErrorCode());
									 e = e.getNextException();
									 System.out.println("");
								 }
					    }
					    catch (java.lang.Exception e)
					    {         done = false;
								 System.out.println("Exception: " + e);
								 e.printStackTrace ();
					    }
			    return done;
		}

		public boolean deposite() {
			     boolean done = false;
						try {

						        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
						        Connection DBConn = ToDB.openConn();
						        Statement Stmt = DBConn.createStatement();
						        String SQL_Command = "SELECT Balance FROM SavingsAccount WHERE SavingsAccountNumber ='"+SavingsAccountNumber+"'"; //SQL query command
						        ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username exsits.

								   while (Rslt.next())
								   {
								   		Balance = Rslt.getFloat(1);
								   }
								   Balance = Balance + TransactionAmount;
								   SQL_Command = "UPDATE SavingsAccount SET Balance='"+Balance+"' WHERE SavingsAccountNumber ='"+SavingsAccountNumber+"'";//Save the username, password and Name
								   Stmt.executeUpdate(SQL_Command);
								   Stmt.close();
					    		   ToDB.closeConn();
					    		   done = true;
						}
					    catch(java.sql.SQLException e)
					    {         done = false;
								 System.out.println("SQLException: " + e);
								 while (e != null)
								 {   System.out.println("SQLState: " + e.getSQLState());
									 System.out.println("Message: " + e.getMessage());
									 System.out.println("Vendor: " + e.getErrorCode());
									 e = e.getNextException();
									 System.out.println("");
								 }
					    }
					    catch (java.lang.Exception e)
					    {         done = false;
								 System.out.println("Exception: " + e);
								 e.printStackTrace ();
					    }
			    return done;
	}


public SavingsAccount getAccountInfo(String UName){
			SavingsAccount SAcc = new SavingsAccount();
			try {
		        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
		        Connection DBConn = ToDB.openConn();
		        Statement Stmt = DBConn.createStatement();
		        String SQL_Command = "SELECT * FROM SavingsAccount WHERE CustomerID ='"+UName + "'"; //SQL query command
		        ResultSet Rslt = Stmt.executeQuery(SQL_Command);
		        while (Rslt.next()) {
						SavingsAccountNumber = Rslt.getString("SavingsAccountNumber");
						Balance = Float.parseFloat(Rslt.getString("Balance"));
						CustomerID = UName;
				}
			}
		    catch(SQLException e)
		    {
					 System.out.println("SQLException: " + e);
					 while (e != null)
					 {   System.out.println("SQLState: " + e.getSQLState());
						 System.out.println("Message: " + e.getMessage());
						 System.out.println("Vendor: " + e.getErrorCode());
						 e = e.getNextException();
						 System.out.println("");
					 }
		    }
		    catch (Exception e)
		    {
					 System.out.println("Exception: " + e);
					 e.printStackTrace ();
		    }
		    return SAcc;
		}


		/*****************************************   Calculate intrest rate *******************************************/

		public String calculateInterests(String SavingsAccountNumber) {
				//boolean done = false;
					try {
						DBConnection ToDB = new DBConnection(); //Have a connection to the DB
						Connection DBConn = ToDB.openConn();
						Statement Stmt = DBConn.createStatement();
						String SQL_Command = "SELECT SavingsAccountNumber, Balance FROM SavingsAccount WHERE CustomerID ='"+CustomerID+"'";
						ResultSet Rslt = Stmt.executeQuery(SQL_Command);
						while (Rslt.next()) {
							Balance = Rslt.getFloat("Balance");
							String SavingAccNum = Rslt.getString("SavingsAccountNumber");
							Balance = (float) (Balance + (Balance * (InterestRate/100)));
							SQL_Command = "UPDATE SavingsAccount SET Balance='"+Balance+"' WHERE SavingsAccountNumber ='"+SavingsAccountNumber+"'";
							Stmt.executeUpdate(SQL_Command);
							//done = !done;
						}
						Stmt.close();
						ToDB.closeConn();
					} catch(java.sql.SQLException e) {
						System.out.println("SQLException: " + e);
						while (e != null) {
							System.out.println("SQLState: " + e.getSQLState());
							System.out.println("Message: " + e.getMessage());
							System.out.println("Vendor: " + e.getErrorCode());
							e = e.getNextException();
							System.out.println("");
						}
					} catch (java.lang.Exception e) {
						System.out.println("Exception: " + e);
						e.printStackTrace ();
					}
				return SavingsAccountNumber;
			}


}