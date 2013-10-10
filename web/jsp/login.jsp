<%-- 
    Document   : login
    Created on : Oct 5, 2013, 12:06:39 PM
    Author     : qz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log In</title>
    </head>
    <body>
        <h1>Hello!</h1>
        <FORM action="Controller" method="POST">
            <TABLE>
                <TR>
                    <TD>Login:</TD>
                    <TD>
                        <INPUT type="text" size="30" name="login"> </TD>
                </TR>
                <TR>
                <TD>Password:</TD>
                <TD>
                    <INPUT type="password" size="30" name="password"> </TD>
                </TR>
                <TR><INPUT type="hidden" name="command" value="login"></TR>
            </TABLE>
            <INPUT type="submit" value="Log In">
        </FORM>
    </body>
</html>
