/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: October, 2013													      *
*******************************************************************************/
package com.Recharla;

import java.lang.*; //including Java packages used by this program
import java.sql.*;
import com.Recharla.*;

public class CheckingAccount
{   //Instance Variables
	private String CheckingAccountNumber, CustomerName, CustomerID;
	private float Balance = -1, TransactionAmount = -1;

	public CheckingAccount(String CA_Num, String Cust_Name, String Cust_ID, String Bal) { //Constructor One with three parameters
		CheckingAccountNumber = CA_Num;
		CustomerName = Cust_Name;
		CustomerID = Cust_ID;
		Balance = Float.parseFloat(Bal);
	}

	public CheckingAccount(String CA_Num) { //Constructor Two with one parameter
		CheckingAccountNumber = CA_Num;
	}



        public CheckingAccount() { //Constructor Two with one parameter

	}
	public CheckingAccount(String CA_Num, String Cust_ID, String Amt) {
		CheckingAccountNumber = CA_Num;
		CustomerID = Cust_ID;
		TransactionAmount = Float.parseFloat(Amt);
	}

        public String getAccountNumber(){
            return CheckingAccountNumber;
        }

        public float getBalanceValue(){
            return Balance;
        }

/*	public String getCANum()
		{
			return CheckingAccountNumber;
	}
*/


/*public void getCName(String CName)
{
			CustomerName = CName;
	} */


public boolean openAcct() {

	     boolean done = false;
				try {
				    if (!done) {
				        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
				        Connection DBConn = ToDB.openConn();
				        Statement Stmt = DBConn.createStatement();
				        String SQL_Command = "SELECT CheckingAccountNumber FROM CheckingAccount WHERE CheckingAccountNumber ='"+CheckingAccountNumber+"'"; //SQL query command
				        ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username exsits.
				        done = !Rslt.next();
				        //System.out.println("done =" + done);
				        if (done) {
						    SQL_Command = "INSERT INTO CheckingAccount(CheckingAccountNumber, CustomerName, Balance, CustomerID)"+
						                  " VALUES ('"+CheckingAccountNumber+"','"+CustomerName+"',"+Balance+", '"+CustomerID+"')"; //Save the username, password and Name
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




    public float getBalance() {  //Method to return a CheckingAccount balance
		try {
		        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
		        Connection DBConn = ToDB.openConn();
		        Statement Stmt = DBConn.createStatement();
		        String SQL_Command = "SELECT Balance FROM CheckingAccount WHERE CheckingAccountNumber ='"+CheckingAccountNumber+"'"; //SQL query command for Balance
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

    public float getBalance(String ChkAcctNumber) {  //Method to return a CheckingAccount balance
		try {
		        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
		        Connection DBConn = ToDB.openConn();
		        Statement Stmt = DBConn.createStatement();
		        String SQL_Command = "SELECT Balance FROM CheckingAccount WHERE CheckingAccountNumber ='"+ChkAcctNumber+"'"; //SQL query command for Balance
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
			        //CheckingAccountNumber
			        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
			        Connection DBConn = ToDB.openConn();
			        Statement Stmt = DBConn.createStatement();
			        String SQL_Command = "SELECT CheckingAccountNumber FROM CheckingAccount WHERE CustomerID ='"+Cus_ID+"'"; //SQL query command for Balance
			        ResultSet Rslt = Stmt.executeQuery(SQL_Command);

			        while (Rslt.next()) {
						CheckingAccountNumber = Rslt.getString("CheckingAccountNumber");
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
		    return CheckingAccountNumber;
	}

	public boolean withdraw() {
		//private double Balance = -1;
		     boolean done = true; /* !CheckingAccountNumber.equals("")  && !CustomerID.equals("");*/
					try {
						DBConnection ToDB = new DBConnection(); //Have a connection to the DB
					    Connection DBConn = ToDB.openConn();
					    Statement Stmt = DBConn.createStatement();
					    String SQL_Command = "SELECT Balance FROM CheckingAccount WHERE CheckingAccountNumber ='"+CheckingAccountNumber+"'"; //SQL query command
					    ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username exsits.

					    while (Rslt.next()) {
							Balance = Rslt.getFloat(1);
						}
					    if(Balance >= TransactionAmount) {
							Balance -= TransactionAmount;
							SQL_Command = "UPDATE CheckingAccount SET Balance='"+Balance+"' WHERE CheckingAccountNumber ='"+CheckingAccountNumber+"'"; // withdraw
				    		Stmt.executeUpdate(SQL_Command);
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


public boolean deposite() {
	     boolean done = false; /* !CheckingAccountNumber.equals("") && !CustomerID.equals("");*/
				try {

				        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
				        Connection DBConn = ToDB.openConn();
				        Statement Stmt = DBConn.createStatement();
				        String SQL_Command = "SELECT Balance FROM CheckingAccount WHERE CheckingAccountNumber ='"+CheckingAccountNumber+"'"; //SQL query command
				        ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username exsits.

 						while (Rslt.next()) {
						Balance = Rslt.getFloat(1);
						}

						Balance = Balance + TransactionAmount;
						SQL_Command = "UPDATE CheckingAccount SET Balance='"+Balance+"' WHERE CheckingAccountNumber ='"+CheckingAccountNumber+"'";//Save the username, password and Name
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

	/********************************  for Account overview    ******************************/

	public CheckingAccount getAccountInfo(String UName){
			CheckingAccount CAcc = new CheckingAccount();
			try {
		        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
		        Connection DBConn = ToDB.openConn();
		        Statement Stmt = DBConn.createStatement();
		        String SQL_Command = "SELECT * FROM CheckingAccount WHERE CustomerID ='"+UName + "'"; //SQL query command
		        ResultSet Rslt = Stmt.executeQuery(SQL_Command);
		        while (Rslt.next()) {
						CheckingAccountNumber = Rslt.getString("CheckingAccountNumber");
						Balance = Float.parseFloat(Rslt.getString("Balance"));

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
		    return CAcc;
		}



}