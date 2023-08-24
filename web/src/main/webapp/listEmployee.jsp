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
    <link rel="stylesheet" type="text/css" href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.0/css/jquery.dataTables.css">
    <link rel="stylesheet" type="text/css" href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.0/css/jquery.dataTables_themeroller.css">
    <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.0/jquery.dataTables.min.js"></script>
    <%@ include file="header.jsp"%>
</head>
<body>
<div class="container">
    <table id="employees" class="table">
      <thead>
        <tr>
          <th scope="col">Name</th>
          <th scope="col">Gender</th>
          <th scope="col">Birth Date</th>
          <th scope="col"></th>
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

                            <td>
                                <form action="removeEmployee" method="GET">
                                    <input type="hidden" name="zoo" value="${currentZoo.name}">
                                    <input type="hidden" name="name" value="${employee.name}">
                                    <button type="submit" class="btn btn-lg btn-secondary">Remove</button>
                                </form>
                            </td>

                         </tr>

        </c:forEach>
      </tbody>
    </table>
    <script>
            $(document).ready(function () {
                $('#employees').DataTable();
            });
    </script>
</div>
<div class="after">
      <main class="inner cover">
            <div class=" text-center">
                <form action= "ZooTransfer" method="GET">
                    <input type="hidden" name="source" value="cleaner">
                    <input type="hidden" name="name" value="${currentZoo.name}">
                    <button type="submit" class="btn btn-lg btn-secondary">Add Cleaner</button>
                </form>
                <form action= "ZooTransfer" method="GET">
                    <input type="hidden" name="source" value="gondozoo">
                    <input type="hidden" name="name" value="${currentZoo.name}">
                    <button type="submit" class="btn btn-lg btn-secondary">Add GondoZoo</button>
                </form>
            </div>
      </main>
</div>
</body>
</html>