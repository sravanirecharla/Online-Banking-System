/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: October, 2013													      *
*******************************************************************************/
package com.Recharla;

import java.lang.*; //including Java packages used by this program
import java.sql.*;
import com.Recharla.*;
import java.util.Date;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class Transactions
{   //Instance Variables
	private String TransactionNumber="", TransactionType, TransactionTime, TransactionDate, FromAccount, ToAccount, CustomerID;
	private float TransactionAmount = -1;
	private String Startdate, Enddate;
	private Vector TransStore = new Vector();


	public Transactions(String TAcc , String Cust_ID, String Bal) { //Constructor three parameters
		ToAccount = TAcc;
		TransactionAmount = Float.parseFloat(Bal);
		CustomerID = Cust_ID;
	}


	public Transactions(String TA_Num) { //Constructor Two with one parameter
			TransactionNumber = TA_Num;
	}


	public Transactions(String UName, String Tamt, String Facc, String Tacc){
		CustomerID = UName;
		TransactionAmount = Float.parseFloat(Tamt);
		FromAccount = Facc;
		ToAccount = Tacc;

		}

	public Transactions(String SD, String ED){

		Startdate = SD;
		Enddate = ED;
	}

	/*public Transactions(String FAcc, String TAcc, String UName, String Tamt, String TransType){
	ToAccount = TAcc;
	FromAccount = FAcc;
	CustomerID = UName;
	TransactionAmount = Float.parseFloat(Tamt);
	TransactionType = TransType;
	}*/


	public String TransactionsopenAcct() {
		int max = 10000;
		int min = 1000;
		     boolean done = true;
					try {
					    if (done) {
					        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
					        Connection DBConn = ToDB.openConn();
					        Statement Stmt = DBConn.createStatement();
					        String SQL_Command;
							//while(done)
							do {
								Random rand = new Random();
								int value = rand.nextInt(max - min) + min;
								TransactionNumber = Integer.toString(value);
								SQL_Command = "SELECT TransactionNumber FROM Transactions WHERE TransactionNumber ='"+TransactionNumber+"'"; //SQL query command
					        	ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username exsits.
					        	done = Rslt.next();
							} while (done);
					        	if (!done) {
								LocalTime todayTime = java.time.LocalTime.now();
								LocalDate todayDate = java.time.LocalDate.now();
								DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("HH:mm:ss");
								DateTimeFormatter  dateformat = DateTimeFormatter.ofPattern ("MM/dd/yyyy");
								TransactionTime = todayTime.format(timeformat);
								TransactionDate = todayDate.format(dateformat);

								TransactionType = "Deposit";
							     SQL_Command = "INSERT INTO Transactions(TransactionNumber, TransactionAmount, TransactionType, TransactionTime, TransactionDate, ToAccount, FromAccount, CustomerID)"+
								                  " VALUES ('"+TransactionNumber+"','"+TransactionAmount+"','"+TransactionType+"','"+TransactionTime+"', '"+TransactionDate+"','"+ToAccount+"', '"+FromAccount+"', '"+CustomerID+"')"; //Save the username, password and Name
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
		    return TransactionNumber;
	}

//*********************************   withrowal ********************************

public String WithrawTransactions() {
		int max = 10000;
		int min = 1000;
		     boolean done = true;
					try {
					    if (done) {
					        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
					        Connection DBConn = ToDB.openConn();
					        Statement Stmt = DBConn.createStatement();
					        String SQL_Command;
							//while(done)
							do {
								Random rand = new Random();
								int value = rand.nextInt(max - min) + min;
								TransactionNumber = Integer.toString(value);
								SQL_Command = "SELECT TransactionNumber FROM Transactions WHERE TransactionNumber ='"+TransactionNumber+"'"; //SQL query command
					        	ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username exsits.
					        	done = Rslt.next();
							} while (done);
					        	if (!done) {
								LocalTime todayTime = java.time.LocalTime.now();
								LocalDate todayDate = java.time.LocalDate.now();
								DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("HH:mm:ss");
								DateTimeFormatter  dateformat = DateTimeFormatter.ofPattern ("MM/dd/yyyy");
								TransactionTime = todayTime.format(timeformat);
								TransactionDate = todayDate.format(dateformat);

								TransactionType = "Withdraw";
							     SQL_Command = "INSERT INTO Transactions(TransactionNumber, TransactionAmount, TransactionType, TransactionTime, TransactionDate, ToAccount, FromAccount, CustomerID)"+
								                  " VALUES ('"+TransactionNumber+"','"+TransactionAmount+"','"+TransactionType+"','"+TransactionTime+"', '"+TransactionDate+"','"+ToAccount+"', '"+FromAccount+"', '"+CustomerID+"')"; //Save the username, password and Name
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
		    return TransactionNumber;
	}


	public boolean transferrecord()
	{
			int max = 10000;
			int min = 1000;
			     boolean done = true;
						try {
						    if (done) {
						        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
						        Connection DBConn = ToDB.openConn();
						        Statement Stmt = DBConn.createStatement();
						        String SQL_Command;

								do {
									Random rand = new Random();
									int value = rand.nextInt(max - min) + min;
									TransactionNumber = Integer.toString(value);
									SQL_Command = "SELECT TransactionNumber FROM Transactions WHERE TransactionNumber ='"+TransactionNumber+"'"; //SQL query command
						        	ResultSet Rslt = Stmt.executeQuery(SQL_Command);
						        	done = Rslt.next();
								} while (done);
						        if (!done) {

									LocalTime todayTime = java.time.LocalTime.now();
									LocalDate todayDate = java.time.LocalDate.now();
									DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("HH:mm:ss");
									DateTimeFormatter  dateformat = DateTimeFormatter.ofPattern ("MM/dd/yyyy");
									TransactionTime = todayTime.format(timeformat);
									TransactionDate = todayDate.format(dateformat);

									if (!FromAccount.equals("") && !ToAccount.equals(""))
										TransactionType = "Transfer";
									else if (!FromAccount.equals("") && ToAccount.equals(""))
										TransactionType = "Withdraw";
									else if (FromAccount.equals("") && !ToAccount.equals(""))
										TransactionType = "Deposite";
									else
										TransactionType = "Error";
										// inserting whole transacation....
								    SQL_Command = "INSERT INTO Transactions(TransactionNumber, TransactionAmount, TransactionType, TransactionTime, TransactionDate, ToAccount, FromAccount, CustomerID)"+
								                  " VALUES ('"+TransactionNumber+"','"+TransactionAmount+"','"+TransactionType+"','"+TransactionTime+"', '"+TransactionDate+"','"+ToAccount+"', '"+FromAccount+"', '"+CustomerID+"')";
								    Stmt.executeUpdate(SQL_Command);
								    done = !done;
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


	//      Search Transaction.

	public Vector searchTransaction(String UName){
				boolean done;

				try {
					done = !Startdate.equals("") && !Enddate.equals("");
			        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
			        Connection DBConn = ToDB.openConn();
			        Statement Stmt = DBConn.createStatement();
			        String SQL_Command = "SELECT * FROM Transactions WHERE TransactionDate BETWEEN '"+Startdate+ "' AND '"+Enddate+ "'"; //SQL query command
			        ResultSet Rslt = Stmt.executeQuery(SQL_Command);
			        while (Rslt.next()) {
						Vector single = new Vector();

							String TransNumber = Rslt.getString("TransactionNumber");
							String TransAmount = Rslt.getString("TransactionAmount");
							String TransType = Rslt.getString("TransactionType");
							String TransTime = Rslt.getString("TransactionTime");
							String TransDate = Rslt.getString("TransactionDate");
							String FromAcc = Rslt.getString("FromAccount");
							String ToAcc = Rslt.getString("ToAccount");
							single.add(0, TransNumber);
							single.add(1, TransAmount);
							single.add(2, TransType);
							single.add(3, TransTime);
							single.add(4, TransDate);
							single.add(5, FromAcc);
							single.add(6, ToAcc);
							TransStore.add(single);
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
			    return TransStore;
		}

}
