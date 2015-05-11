

<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
<body>
 <div id="main">
  <h1>Welcome ${it.name} to Our website</h1>
  <h2 style="color:blue">Web Site Main Ingredients:</h2>

  <p>Add friends</p>
  <p> create groups </p>
  <p> Register</p>
  <p>Live Data (Files and Databases)</p>
</div>

</head>
<style>
body{
font-family: verdina;
background-color: #ABC;
padding: 50px;
}
form {
    text-align: center;
    color: black;
}
</style>
<form method ="get" action="/social/Addfriend/">
<input type ="submit" value = "addfriend">
</form>
<br>
<a href="/social/signout/">Signout</a>

<br>
<form method ="get" action="/social/request/">
<input type ="submit" value ="request">
</form>
<br>
<br>
<form method ="get" action="/social/groupManager/">
<input type ="submit" value ="group">
</form>
<br>

</form>
<br>
<form method ="get" action="/social/search/">
<input type ="submit" value ="search">
</form>
<br>
<form method ="get" action="/social/sendmessage/">
<input type ="submit" value ="sendmessage">
</form>
<br>

<form method ="get" action="/social/news/">
<input type ="submit" value ="myNews">
</form>
<br>

<form method ="get" action="/social/postFriend/">
<input type ="submit" value ="friendsPost">
</form>
<br>

<form method ="get" action="/social/Page/">
<input type ="submit" value ="Create Page">
</form>
<br>

<form method ="get" action="/social/Like/">
<input type ="submit" value ="Like Page">
</form>
<br>

<form method ="get" action="/social/PagePost/">
<input type ="submit" value ="Page Post">
</form>
<br>

</body>
</html>
