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
    List<Zoo> zooList = new ArrayList();
    pageContext.setAttribute("zooList",zooList);
    zooList.add(zoo1);
    zooList.add(zoo2);
    %>
<h2>

<%String name= request.getParameter("director");%>
Director:
<%out.write(name.trim());%>


<br />
<%
    for(Zoo zoo : zooList)
    {
        if (name.equals(zoo.getDirector().getName())) {
        pageContext.setAttribute("currentZoo",zoo);
        }

    }


%>


<br />

<c:forEach var="employee" items="${currentZoo.getEployees()}">


            <table>
                 <tr>
                     <c:out value="${employee}"></c:out>
                 </tr>
            </table>

</c:forEach>
</h2>

</body>
</html>