<!-- RAHUL -->
<%@page import="java.sql.*"%>
<%@ page import="com.mvc.util.DBConnection"%>
<%
String country=request.getParameter("count");  
 String buffer="<select name='state' onchange='showCity(this.value);'><option value='-1'>Select</option>";  
 try{
 Connection con = DBConnection.createConnection();  
 Statement stmt = con.createStatement();  
 ResultSet rs = stmt.executeQuery("Select distinct p_city from parking where p_state='"+country+"' ");  
   while(rs.next()){
   buffer=buffer+"<option value='"+rs.getString("p_city")+"'>"+rs.getString("p_city")+"</option>";  
   }  
 buffer=buffer+"</select>";  
 response.getWriter().println(buffer); 
 }
 catch(Exception e){
     System.out.println(e);
 }

 %>