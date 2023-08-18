<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Zoos</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="header.jsp"%>
</head>
<body>
<div class="container">
    <table class="table">
      <thead>
        <tr>
          <th scope="col">Name</th>
          <th scope="col">Director</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="zoo" items="${zoos}">

                         <tr>
                            <td>
                                <c:out value="${zoo.name}"></c:out>
                            </td>

                            <td>
                                <c:out value="${zoo.director.getName()}"></c:out>
                            </td>
                         </tr>

        </c:forEach>
      </tbody>
    </table>
</div>

</body>
</html>