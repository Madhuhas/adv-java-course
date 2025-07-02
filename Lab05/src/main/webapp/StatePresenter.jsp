<!--Jsp file to represent the State and Capital quiz-->
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 03-08-2022
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id = "StateTutor" scope = "session"
             class = "com.example.jspexample2.StateTutor">
</jsp:useBean>
<html>
<head>
    <title>
        States and Capitals Quiz
    </title>
</head>
<body>
<form method = "post"  action = "StateResult.jsp">
    <% StateTutor.refresh(); %>
    <table>
        <td>
            What is the capital of <%=StateTutor.Random_State %> ?
        </td>
        <td>
            <input name = "<%= "result"%>" size = 4/>
        </td>
    </table>
    <br />
    <input type = "submit" name = "Submit"
           value = "Submit" /> Click the browser's Refresh button to get a new quiz
</form>
</body>
</html>
