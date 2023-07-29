import java.text.SimpleDateFormat ;
import java.util.Date;
import java.util.Scanner;
import java.io.*;
import java.sql.*;

class booking
{
Scanner sc=new Scanner(System.in);

void booking_tickets()throws Exception
{
System.out.println("enter ur details to book the bus");
System.out.println("enter ur name");
String name=sc.nextLine();
System.out.println("enter the date");
String date=sc.nextLine();
System.out.println("enter bus no");
int b=sc.nextInt();


SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
Date date1=sdf.parse(date);
java.sql.Date sqd=new java.sql.Date(date1.getTime());

Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
PreparedStatement st=con.prepareStatement("insert into passenger_details values(?,?,?)");
st.setInt(1,b);
st.setDate(2,sqd);
st.setString(3,name);

st.executeUpdate();
System.out.println("ur booking done!");
}

void passenger_details()throws Exception
{
int bus_id;
Scanner sc=new Scanner(System.in);
System.out.println("enter busid");
bus_id=sc.nextInt();

String query="select booked_date,name from passenger_details where bus_no= ?";
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
PreparedStatement st=con.prepareStatement(query);
st.setInt(1,bus_id);
ResultSet rs=st.executeQuery();
while(rs.next())
{
System.out.println("date"+" - " + rs.getDate(1)+" "+"name"+ " - "+rs.getString(2));
}
}

void bus_details()throws Exception
{

String query="select id,ac,capacity from bus_details";
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
PreparedStatement st=con.prepareStatement(query);
ResultSet rs=st.executeQuery();
while(rs.next())
{
System.out.println("bus_id"+" - "+rs.getInt(1)+"  "+"ac"+" - " + rs.getString(2)+" "+"capacity"+ " - "+rs.getInt(3));
}
}

void ticket_cancellation()throws Exception
{
int bus_id;
Scanner sc=new Scanner(System.in);
System.out.println("enter busid");
bus_id=sc.nextInt();

String query="delete from passenger_details where bus_no=?";
String query1="select bus_no from passenger_details";
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
PreparedStatement st=con.prepareStatement(query);
st.setInt(1,bus_id);
ResultSet rs=st.executeQuery();

System.out.println("your booking cancelled");
}

}

public class bus_ticket_reservation
{
public static void main(String args[])throws Exception
{
Scanner sc=new Scanner(System.in);
booking b=new booking();
int choice;
System.out.println("enter ur choice");
System.out.println("1 : booking");
System.out.println("2 : view passenger details");
System.out.println("3 : view bus details");
System.out.println("4 : cancellation");
choice=sc.nextInt();

while(choice!=0)
{
switch(choice)
{

case 1:
b.booking_tickets();
break;

case 2:
b.passenger_details();
break;

case 3:
b.bus_details();
break;

case 4:
b.ticket_cancellation();
break;

default:
System.out.println("keep an eye on ur choice!");

}


System.out.println("enter your choice or press 0 to exit");
choice=sc.nextInt();
}

}

