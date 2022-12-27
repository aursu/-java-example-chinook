<%--
  Created by IntelliJ IDEA.
  User: aursu
  Date: 27.12.22
  Time: 05:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Artist</title>
</head>
<body>
<jsp:useBean id="artist" type="com.github.aursu.chinook.webapp.data.Artist" scope="request"/>
<form action="edit_artist" method="post">
    <label for="artistname">Artist name:</label>
    <input type="text" name="artistname" id="artistname" value="${artist.name}">
    <input type="hidden" name="artistId"  value="${artist.id}">
    <input type="submit" value="Update">
</form>

</body>
</html>
