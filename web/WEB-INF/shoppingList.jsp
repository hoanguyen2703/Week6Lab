<%-- 
    Document   : shoppingList
    Created on : Feb 24, 2023, 8:50:49 PM
    Author     : khanhhoanguyen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List - Home</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>
            Hello, ${username} 
            <a href="ShoppingList?action=logout">Logout</a>
        </p>
        
        <form action="" method="POST">
            <h2>Add Items</h2>
            <input type="text" name="item">
            <input type="submit" value="Add Item">
            <input type="hidden" name="action" value="add">
        </form>
        
        <form action="" method="POST">
            <ul>
                <c:forEach var="item" items="${items}">
                    <li> 
                        <input type="radio" name="${item}" value="${item}">${item}
                    </li>
                </c:forEach>
            </ul>
            <input type="submit" value="Delete">
            <input type="hidden" name="action" value="delete">
        </form>
        
        <style>
            li {             
            list-style-type: none;
            }
        </style>
    </body>
</html>
