<%@ page import="com.example.jspexample2.SubtractionTutor" %>
<jsp:useBean id = "SubtractionTutor" scope = "session"
             class = "com.example.jspexample2.SubtractionTutor">
</jsp:useBean>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Subtraction Quiz
    </title>
</head>
<body>
<form method = "post"  action = "SubtractionResult.jsp">
    <% SubtractionTutor.refresh(); %>
    <table>
        <% for (int i = 0; i < 10; i++){ %>
        <tr>
            <td>
                <%= SubtractionTutor.number1[i]%>
            </td>
            <td>
                -
            </td>
            <td><%= SubtractionTutor.number2[i]%>
            </td>
            <td>
                =
            </td>
            <td>
                <input name = "<%= "result" + i%>" size = 2/>
            </td>
        </tr>
        <% } %>
    </table>
    <br />
    <input type = "submit" name = "Submit"
           value = "Submit" /> Click the browser's Refresh button to get a new quiz
</form>
</body>
</html>
