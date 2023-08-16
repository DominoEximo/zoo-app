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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</head>
<body>

<table class="table">
  <thead class="table-dark">
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


</body>
</html>