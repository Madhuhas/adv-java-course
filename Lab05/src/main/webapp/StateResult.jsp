<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.jspexample2.StateTutor" %>

<jsp:useBean id="State_Tutor" scope="session" class="com.example.jspexample2.StateTutor" />

<%
    boolean match = false;
    String capital = null;

    // Ensure StateTutor is initialized before using its properties
    StateTutor stateTutor = new StateTutor();

    String stateSearch = stateTutor.Random_State;
    String res = request.getParameter("result");

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        // Connecting to the database
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "root", "Spring@2024");

        // Fetching capital from database based on state
        String query = "SELECT capital FROM statecapital WHERE state=?";
        pstmt = con.prepareStatement(query);
        pstmt.setString(1, stateSearch);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            capital = rs.getString("capital");
        }
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    } finally {
        // Closing resources
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Check if the entered capital matches the retrieved capital
    match = capital != null && capital.equals(res);
%>
<html>
<head>
    <title>State and Capital Quiz Answer</title>
</head>
<body>
<% if (match) { %>
<h1>Yes, It is correct</h1>
<img src="trophy-joypixels.gif" style="float:right" alt="Trophy">
<% } else { %>
<h1>No!, The Capital of <%= stateSearch %> is <%= capital %></h1>
<h1>Better Luck Next Time!!!</h1>
<% } %>
</body>
</html>