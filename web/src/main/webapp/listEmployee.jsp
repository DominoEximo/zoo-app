<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags"%>
<%@page import="hu.neuron.mentoring.zooapp.service.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <%@ include file="header.jsp"%>
</head>
<body>
<div class="container">
    <table class="table">
      <thead>
        <tr>
          <th scope="col">Name</th>
          <th scope="col">Gender</th>
          <th scope="col">Birth Date</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="employee" items="${currentZoo.eployees}">

                         <tr>
                            <td>
                                <c:out value="${employee.getName()}"></c:out>
                            </td>

                            <td>
                                <c:out value="${employee.getGender()}"></c:out>
                            </td>

                            <td>
                                <c:out value="${employee.getBirthDate()}"></c:out>
                            </td>


                         </tr>

        </c:forEach>
      </tbody>
    </table>
</div>

</body>
</html>