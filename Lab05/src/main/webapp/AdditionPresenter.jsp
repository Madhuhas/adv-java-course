<jsp:useBean id = "additionTutor" scope = "session"
             class = "com.example.jspexample2.AdditionTutor">
</jsp:useBean>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Addition Quiz
    </title>
</head>
<body>
<form method = "post"  action = "AdditionResult.jsp">
    <% additionTutor.refresh(); %>
    <table>
        <% for (int i = 0; i < 10; i++){ %>
        <tr>
            <td>
                <%= additionTutor.number1[i]%>
            </td>
            <td>
                +
            </td>
            <td><%= additionTutor.number2[i]%>
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