<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: aursu
  Date: 26.12.22
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <title>Artists</title>
</head>
<body>
<jsp:useBean id="artistBean" type="com.github.aursu.chinook.webapp.beans.ArtistBean" scope="request"/>
<form action="add_artist" method="post">
    <label for="artistname">New artist:</label>
    <input type="text" name="artistname" id="artistname">
    <input type="submit" value="Add">
</form>
<table>
    <thead>
    <tr>
        <th>#</th>
        <th>Name</th>
        <th>X</th>
        <th>E</th>
        <th>Albums</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${artistBean.artists}" var="a">
        <tr>
            <td>${a.id}</td>
            <td>${a.name}</td>
            <td><a href="delete_artist?id=${a.id}">delete</a></td>
            <td><a href="edit_artist?id=${a.id}">edit</a></td>
            <td><a href="show_albums_of?id=${a.id}">show</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
