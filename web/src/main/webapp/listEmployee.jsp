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
    <link rel="stylesheet" type="text/css" href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.0/css/jquery.dataTables.css">
    <link rel="stylesheet" type="text/css" href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.0/css/jquery.dataTables_themeroller.css">
    <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.1.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.0/jquery.dataTables.min.js"></script>

    <style>
    .container {
      text-align: center;
      background-color: orange;
    }
    </style>

</head>
<body>
<div id="stage" class="container">
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
        <c:forEach var="employee" items="${employees}">

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
                                    <input type="hidden" name="id" value="${employee.id}">
                                    <input id="data" type="hidden" name="zooID" value="${id}">
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
                <form id="cleanerForm" action= "ZooTransfer" method="GET">
                    <input id="source" type="hidden" name="source" value="cleaner">
                    <input id="data" type="hidden" name="zooID" value="${id}">
                    <button type="submit" id="addCleaner" class="btn btn-lg btn-secondary">Add Cleaner</button>
                </form>
                <form id="gondoZooForm" action= "ZooTransfer" method="GET">
                    <input id="source" type="hidden" name="source" value="gondozoo">
                    <input id="data" type="hidden" name="zooID" value="${id}">
                    <button type="submit" id="addGondoZoo" name="gomb" class="btn btn-lg btn-secondary">Add GondoZoo</button>
                </form>
            </div>
      </main>
</div>
</body>
</html>