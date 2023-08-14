<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags"%>
<%@page import="hu.neuron.mentoring.zooapp.service.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>


<html>
<head></head>
<body>
    <%
    Zoo zoo1 = new Zoo(new Director("Elemer",null,null,'m'));
    Zoo zoo2 = new Zoo(new Director("David",null,null,'m'));
    zoo1.addEmployee(new GondoZoo("Adam",null,null,'m',null));
    zoo1.addEmployee(new GondoZoo("Gabor",null,null,'m',null));
    zoo1.addEmployee(new GondoZoo("Mihály",null,null,'m',null));
    zoo1.addEmployee(new GondoZoo("Bela",null,null,'m',null));
    zoo2.addEmployee(new GondoZoo("Eva",null,null,'f',null));
    zoo2.addEmployee(new GondoZoo("Carl",null,null,'m',null));
    zoo2.addEmployee(new GondoZoo("Ed",null,null,'m',null));
    %>

    <form action= "listEmployee.jsp" method="POST">
    <% boolean isChecked = false;%>
    Zoo1 <input type="radio" value="zoo1" onclick="<%isChecked = true;%>" >
    <input type="submit" value="submit">
    <%if (isChecked) {request.getSession().setAttribute("zoo",zoo1);}
      else{request.getSession().setAttribute("zoo",zoo2);}
    %>
    </form>
</body>
</html>