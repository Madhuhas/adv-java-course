<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id = "MultiplicationTutor" scope="session"
             class = "com.example.jspexample2.MultiplicationTutor">
</jsp:useBean>

<html>
<head>
    <title>
        Multiplication Quiz Answer
    </title>
</head>
<body>
<table>
    <% int correctCount = 0;
        for (int i = 0; i < 10; i++){
            String s = request.getParameter("result" + i);
            int result;
            try{
                result = Integer.parseInt(s);
            }
            catch (Exception ex){
                result = 0;
            }%>
    <tr>
        <td><%= MultiplicationTutor.number1[i] %>  </td> <td>* </td> <td><%=
    MultiplicationTutor.number2[i] %></td><td> =</td>
        <td><%= result %>   </td>

        <td>
            <% if (result == MultiplicationTutor.number1[i] *
                    MultiplicationTutor.number2[i]){
                correctCount++; %>
            Correct
            <% } else { %>
            Wrong
            <% } %>
        </td>
    </tr>
    <% } %>

</table>
The total correct count is <%= correctCount %>
<%  if (correctCount == 10)
{ %> <img src="trophy-joypixels.gif" style="float:right" >
<% } else { %> <h1>Better Luck Next Time !! </h1> <% } %>
</body>
</html>


