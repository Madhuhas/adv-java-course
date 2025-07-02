<jsp:useBean id = "MultiplicationTutor" scope = "session"
             class = "com.example.jspexample2.MultiplicationTutor">
</jsp:useBean>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Addition Quiz
    </title>
</head>
<body>
<form method = "post"  action = "MultiplicationResult.jsp">
    <% MultiplicationTutor.refresh(); %>
    <table>
        <% for (int i = 0; i < 10; i++){ %>
        <tr>
            <td>
                <%= MultiplicationTutor.number1[i]%>
            </td>
            <td>
                *
            </td>
            <td><%= MultiplicationTutor.number2[i]%>
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