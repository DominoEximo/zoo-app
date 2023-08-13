<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags"%>
<%@page import="hu.neuron.mentoring.zooapp.service.*"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<html>
<head></head>
<body>
<%
Zoo zoo1 = new Zoo();
zoo1.addEmployee(new GondoZoo(null,null,null,'f',null));
List<Employee> zoo1Employees = zoo1.getEployees();
pageContext.setAttribute("list1",zoo1Employees);
%>
<c:forEach var="Employee" items="${list1}">
    <c:out value="${Employee}"/>
</c:forEach>

</body>
</html>