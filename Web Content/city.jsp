<!-- RAHUL -->
<%@page import="java.sql.*"%>
<%@ page import="com.mvc.util.DBConnection"%>
<%
String state=request.getParameter("count");  
 String buffer="<select name='city'><option value='-1'>Select</option>";  
 try{
 Connection con = DBConnection.createConnection();  
 Statement stmt = con.createStatement();  
 ResultSet rs = stmt.executeQuery("Select * from parking where p_state='"+state+"' ");  
   while(rs.next()){
   buffer=buffer+"<option value='"+rs.getString(2)+"'>"+rs.getString(3)+"</option>";  
   }  
 buffer=buffer+"</select>";  
 response.getWriter().println(buffer); 
 }
 catch(Exception e){
     System.out.println(e);
 }
 %>