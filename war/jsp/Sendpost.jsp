<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


 <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   <c:forEach items="${it.posts}" var="post" >
   
  
  <p> post :${post}</p>
   </c:forEach>
   
  <form action="/social/Likeresponse" method="post">
    <select name="posted"  >
   <c:forEach items="${it.posts}" var="post" >
    <option value="${post}">${post}</option>
   </c:forEach>
   </select>
   
   <input type="submit" value="Like">
   </form>
   
   
   
     <form action="/social/shareresponse" method="post">
    <select name="posted"  >
   <c:forEach items="${it.posts}" var="post" >
    <option value="${post}">${post}</option>
   </c:forEach>
   </select>
   
   <input type="submit" value="share">
   </form>
   
   
   
   
    
     <form action="/social/postresponse" method="post">
     <br><br>
   post : <input type="text" name="content" /> <br><br>
   <input name="privacy" type="text" 
   value=<%="public"%>>
   <br>    
    <select name="feeling"  >
    <option value="no">SelectFeeling</option>
    <option value="happy">happy  </option>
    <option value="sad">sad</option>
    
    <option value="love">love</option>
    <option value="illness">illness</option>
  </select>
  <input type="submit" value="post">
   </form>
   
   
</body>
</html>