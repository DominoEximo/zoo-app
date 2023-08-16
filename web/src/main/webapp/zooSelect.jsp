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
      <h2>Select a Zoo</h2>
      <form action= "employees" method="GET">
        <div class="form-group">
          <label for="name">Name of Zoo:</label>
          <input type="text" class="form-control" id="name" placeholder="Name of Zoo" name="name">
        </div>


        <button type="submit" class="btn btn-default">Search</button>
      </form>
    </div>

</body>
</html>