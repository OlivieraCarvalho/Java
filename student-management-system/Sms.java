package files.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public class StdDbOp extends JFrame implements ActionListener
{
JButton su; //Submit Button on the login page
JTextField usr; //User name field on login page
JPasswordField pwd; //Password field on login page
JLabel user,pw,wel,inc; //User name and password labels
JFrame frm;
JPanel p1,p2,p3,p4; //four panels on the login page
String u,p,cn;
JButton fes,mks,std,edt; //Three JButtons
JPanel c1,c2,c3,c4;
JPanel l1,l2,l3,l4;
JButton add,del,upd,bak;
JLabel st_roll,st_name,st_mrks,st_fdue,st_doj,bn;
JPanel a1,a2,a3,a4;
JTextField w1,w2,w3,w4,w5;
JLabel ret;
JButton cont;
JPanel f1,f2;
JLabel we1,we2,we3,we4,we5,we6,we7,we8;


static Connection con=null;
static Statement st;
static ResultSet rs;
public StdDbOp()
{
frm=new JFrame("Welcome");
frm.setLayout(new GridLayout(2,1));
f1=new JPanel(new FlowLayout());
we1=new JLabel("Welcome to the Student");
we2=new JLabel("Database Operations ");
we3=new JLabel("Application. This application");
we4=new JLabel("was designed to facilitate a ");
we5=new JLabel("Graphical User Interface");
we6=new JLabel("method to perform insertion,");
we7=new JLabel("deletion and alteration of ");
we8=new JLabel("tables in a DataBase.");
f1.add(we1);
f1.add(we2);
f1.add(we3);
f1.add(we4);
f1.add(we5);
f1.add(we6);
f1.add(we7);
f1.add(we8);

f2=new JPanel(new FlowLayout());
cont=new JButton("Continue");
cont.addActionListener(this);
f2.add(cont);

frm.add(f1);
frm.add(f2);
frm.setVisible(true);
frm.setSize(350,350);
//To close the window
addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent w)
{
System.exit(0);
}
});
}
public void actionPerformed(ActionEvent e)
{
if(e.getSource()==cont)
{
frm.remove(f1);
frm.remove(f2);
frm.setTitle("Student Database Operations");
frm.setLayout(new GridLayout(4,3,3,3));
p3=new JPanel(new FlowLayout());
wel=new JLabel("Please enter your account details to begin : ");
wel.setBorder(BorderFactory.createLoweredBevelBorder());
p3.add(wel);
frm.add(p3);

//First panel containing pwd and usr fields
p1=new JPanel(new GridLayout(3,3,5,5));
user=new JLabel("                  User Name");
p1.add(user);
usr=new JTextField(25);
usr.setBorder(BorderFactory.createLineBorder(Color.blue,2));
p1.add(usr);
pw=new JLabel("                  Password");
p1.add(pw);
pwd=new JPasswordField(25);
pwd.setBorder(BorderFactory.createLineBorder(Color.blue,2));
p1.add(pwd);
frm.add(p1);

//Second Panel containing submit button on login page
p2=new JPanel(new FlowLayout());
su=new JButton("SUBMIT");
su.setToolTipText("Press to submit account details");
p2.add(su);
su.addActionListener(this);
frm.add(p2);

p4=new JPanel(new FlowLayout());
inc=new JLabel("");
p4.add(inc);
frm.add(p4);

frm.setVisible(true);
frm.setSize(365,350);
}
if(e.getSource()==su)
{
  if((usr.getText().equalsIgnoreCase("amadeus") && pwd.getText().equalsIgnoreCase("student")) || (usr.getText().equalsIgnoreCase("Admin") && pwd.getText().equalsIgnoreCase("teacher")))
  {
frm.remove(p1);
frm.remove(p2);
frm.remove(p3);
frm.remove(p4);
frm.setLayout(new GridLayout(4,1,0,0));
frm.setTitle("Main Menu");
//First Panel
c1=new JPanel(new GridLayout());
fes=new JButton("View Fee Details");
fes.setToolTipText("Press to view 'FEES' Table");
c1.add(fes);
fes.addActionListener(this);

//Second Panel
c2=new JPanel(new GridLayout());
mks=new JButton("View Mark Details");
mks.setToolTipText("Press to view 'MARKS' Table");
c2.add(mks);
mks.addActionListener(this);

//Third Panel
c3=new JPanel(new GridLayout());
std=new JButton("View All Student Details");
std.setToolTipText("Press to view 'STD_DET' Table");
std.addActionListener(this);
c3.add(std);

//Fourth Panel
c4=new JPanel(new GridLayout());
edt=new JButton("Edit Student DataBase");
edt.setToolTipText("Press to edit 'STD_DET' Table");
edt.addActionListener(this);
c4.add(edt);

//Adding the Panels to the frame
frm.add(c1);
frm.add(c2);
frm.add(c3);
frm.add(c4);
frm.setVisible(true);
frm.setSize(365,350);

//To close the window
addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent w)
{
System.exit(0);
}
});
  }
  else inc.setText("Incorrect Username and Password entered !");
}
if(e.getSource()==fes) { TableDisplay("FEES"); }
if(e.getSource()==mks) { TableDisplay("MARKS"); }
if(e.getSource()==std) { TableDisplay("STD_DET");}
if(e.getSource()==edt)
{
frm.remove(c1);
frm.remove(c2);
frm.remove(c3);
frm.remove(c4);
frm.setLayout(new GridLayout(4,1));
frm.setTitle("Edit DataBase");
a4=new JPanel(new GridLayout());
bn=new JLabel("        Enter the required details to continue");
a4.add(bn);

a1=new JPanel(new GridLayout(5,2,5,5));
st_roll=new JLabel("    Roll Number");
a1.add(st_roll);
w1=new JTextField(25);
w1.setBorder(BorderFactory.createLineBorder(Color.blue,2));
a1.add(w1);
st_name=new JLabel("    Name");
a1.add(st_name);
w2=new JTextField(25);
w2.setBorder(BorderFactory.createLineBorder(Color.blue,2));
a1.add(w2);
st_mrks=new JLabel("    Total Marks");
a1.add(st_mrks);
w3=new JTextField(25);
w3.setBorder(BorderFactory.createLineBorder(Color.blue,2));
a1.add(w3);
st_fdue=new JLabel("    Fees Due");
a1.add(st_fdue);
w4=new JTextField(25);
w4.setBorder(BorderFactory.createLineBorder(Color.blue,2));
a1.add(w4);
st_doj=new JLabel("    Date of Joining");
a1.add(st_doj);
w5=new JTextField(25);
w5.setBorder(BorderFactory.createLineBorder(Color.blue,2));
a1.add(w5);

a2=new JPanel(new FlowLayout());
add=new JButton("Add");
add.setToolTipText("Press to insert the record");
add.addActionListener(this);
a2.add(add);
del=new JButton("Delete");
del.setToolTipText("Press to delete the record");
del.addActionListener(this);
a2.add(del);
upd=new JButton("Update");
upd.setToolTipText("Press to update the record");
upd.addActionListener(this);
a2.add(upd);
bak=new JButton("Back");
bak.setToolTipText("Press to go back to the main menu");
bak.addActionListener(this);
a2.add(bak);

a3=new JPanel(new FlowLayout()); //trial
ret=new JLabel();  //trial
a3.add(ret);
frm.add(a4);
frm.add(a1);
frm.add(a2);
frm.add(a3);
frm.setVisible(true);
frm.setSize(365,550);
}
if(e.getSource()==add) { DataAdd(); }
if(e.getSource()==del) { DataDelete(); }
if(e.getSource()==upd) { DataUpdate(); }
if(e.getSource()==bak)
{
frm.remove(a1);
frm.remove(a2);
frm.remove(a3);
frm.remove(a4);
frm.setLayout(new GridLayout(4,1,0,0));
frm.setTitle("Main Menu");
//First Panel
c1=new JPanel(new GridLayout());
fes=new JButton("View Fee Details");
fes.setToolTipText("Press to view 'FEES' Table");
c1.add(fes);
fes.addActionListener(this);

//Second Panel
c2=new JPanel(new GridLayout());
mks=new JButton("View Mark Details");
mks.setToolTipText("Press to view 'MARKS' Table");
c2.add(mks);
mks.addActionListener(this);

//Third Panel
c3=new JPanel(new GridLayout());
std=new JButton("View All Student Details");
std.setToolTipText("Press to view 'STD_DET' Table");
std.addActionListener(this);
c3.add(std);

//Fourth Panel
c4=new JPanel(new GridLayout());
edt=new JButton("Edit Student DataBase");
edt.setToolTipText("Press to edit 'STD_DET' Table");
edt.addActionListener(this);
c4.add(edt);

//Adding the Panels to the frame
frm.add(c1);
frm.add(c2);
frm.add(c3);
frm.add(c4);
frm.setVisible(true);
frm.setSize(365,350);

//To close the window
addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent w)
{
System.exit(0);
}
});
}
}
public void TableDisplay(String k)   // Method to display any table
{
if(k.equalsIgnoreCase("FEES"))
{
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
cn="jdbc:odbc:STDPROJ";
con=DriverManager.getConnection(cn);
st=con.createStatement();
rs=st.executeQuery("SELECT SlNo,Roll_Number,SName,Fees_Due FROM STD_DET");
ResultSetMetaData rm=rs.getMetaData();
int col=rm.getColumnCount();
Vector<String>colNames=new Vector<String>();
Vector<Object> data=new Vector<Object>();
for(int m=1;m<=col;m++)
{
colNames.addElement(rm.getColumnName(m));
}
while(rs.next())
{
Vector<Object>row=new Vector<Object>();
for(int m=1;m<=col;m++)
{
row.addElement(rs.getObject(m));
}
  data.addElement(row);
}
DefaultTableModel tm=new DefaultTableModel(data,colNames);
JFrame frame=new JFrame("View "+k);
JTable table=new JTable(tm);
table.getTableHeader().setBackground(Color.orange);
JScrollPane scrollPane=new JScrollPane(table);
frame.getContentPane().add(scrollPane);
frame.setSize(365,350);
frame.setVisible(true);
rs.close();
st.close();
}
catch(Exception e)
{
System.out.println("Error ..... "+e.toString());
System.exit(1);
}
}
else if(k.equalsIgnoreCase("MARKS"))
{
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
cn="jdbc:odbc:STDPROJ";
con=DriverManager.getConnection(cn);
st=con.createStatement();
rs=st.executeQuery("SELECT SlNo,Roll_Number,SName,Tot_Marks FROM STD_DET");
ResultSetMetaData rm=rs.getMetaData();
int col=rm.getColumnCount();
Vector<String>colNames=new Vector<String>();
Vector<Object> data=new Vector<Object>();
for(int m=1;m<=col;m++)
{
colNames.addElement(rm.getColumnName(m));
}
while(rs.next())
{
Vector<Object>row=new Vector<Object>();
for(int m=1;m<=col;m++)
{
row.addElement(rs.getObject(m));
}
  data.addElement(row);
}
DefaultTableModel tm=new DefaultTableModel(data,colNames);
JFrame frame=new JFrame("View "+k);
JTable table=new JTable(tm);
table.getTableHeader().setBackground(Color.orange);
JScrollPane scrollPane=new JScrollPane(table);
frame.getContentPane().add(scrollPane);
frame.setSize(365,350);
frame.setVisible(true);
rs.close();
st.close();
}
catch(Exception e)
{
System.out.println("Error ..... "+e.toString());
System.exit(1);
}
}
else if(k.equalsIgnoreCase("STD_DET"))
{
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
cn="jdbc:odbc:STDPROJ";
con=DriverManager.getConnection(cn);
st=con.createStatement();
rs=st.executeQuery("SELECT * FROM STD_DET");
ResultSetMetaData rm=rs.getMetaData();
int col=rm.getColumnCount();
Vector<String>colNames=new Vector<String>();
Vector<Object> data=new Vector<Object>();
for(int m=1;m<=col;m++)
{
colNames.addElement(rm.getColumnName(m));
}
while(rs.next())
{
Vector<Object>row=new Vector<Object>();
for(int m=1;m<=col;m++)
{
row.addElement(rs.getObject(m));
}
  data.addElement(row);
}
DefaultTableModel tm=new DefaultTableModel(data,colNames);
JFrame frame=new JFrame("View "+k);
JTable table=new JTable(tm);
table.getTableHeader().setBackground(Color.orange);
JScrollPane scrollPane=new JScrollPane(table);
frame.getContentPane().add(scrollPane);
frame.setSize(365,350);
frame.setVisible(true);
rs.close();
st.close();
}
catch(Exception e)
{
System.out.println("Error ..... "+e.toString());
System.exit(1);
}
}
}
public void DataAdd()
{
String sql_query="";
try
{
ret.setText("");
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
String str="jdbc:odbc:STDPROJ";
con=DriverManager.getConnection(str);
st=con.createStatement();
int sl=0;
try
{
rs=st.executeQuery("SELECT MAX(SlNo) FROM STD_DET");
if(rs.next()) {sl=rs.getInt(1);}
sl=sl+1;
sql_query="INSERT INTO STD_DET VALUES("+sl+","+w1.getText()+",'"+w2.getText()+"',"+w3.getText()+","+w4.getText()+",'"+w5.getText()+"')";
int i=st.executeUpdate(sql_query);
w1.setText("");
w2.setText("");
w3.setText("");
w4.setText("");
w5.setText("");
ret.setText("Added "+i+" record(s)");
}
catch(Exception e)
{
System.out.println("Error ..... "+e.toString());
}
//Cannot Add ! Record already exists !
}
catch(Exception e)
{
System.out.println("Error ..... "+e.toString());
}
}
public void DataDelete()
{
String sql_query="";
ret.setText("");
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
String str="jdbc:odbc:STDPROJ";
con=DriverManager.getConnection(str);
st=con.createStatement();
sql_query="DELETE FROM STD_DET WHERE Roll_Number="+w1.getText();
int i=st.executeUpdate(sql_query);
w1.setText("");
w2.setText("");
w3.setText("");
w4.setText("");
w5.setText("");
ret.setText("Deleted "+i+" record(s)");
con.close();
}
catch(Exception e)
{
System.out.println("Error ..... "+e.toString());
}
}
public void DataUpdate()
{
String sql_query="";
ret.setText("");
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
String str="jdbc:odbc:STDPROJ";
con=DriverManager.getConnection(str);
st=con.createStatement();
sql_query="UPDATE STD_DET SET Roll_Number="+w1.getText()+",SName='"+w2.getText()+"',Tot_Marks="+w3.getText()+",Fees_Due="+w4.getText()+",DOJ='"+w5.getText()+"' WHERE Roll_Number="+w1.getText();
int i=st.executeUpdate(sql_query);
w1.setText("");
w2.setText("");
w3.setText("");
w4.setText("");
w5.setText("");
ret.setText("Updated "+i+" record(s)");
con.close();
}
catch(Exception e)
{
System.out.println("Error ..... "+sql_query+""+e.toString());
}
}
public static void main(String args[])
{
StdDbOp g=new StdDbOp();
}
}
