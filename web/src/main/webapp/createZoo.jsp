<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags"%>
<%@page import="hu.neuron.mentoring.zooapp.service.*"%>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <%@ include file="header.jsp"%>
</head>
<body>
    <div class="container">
      <h2>Zoo</h2>
      <form action= "AddZoo" method="GET">
        <div class="form-group">
          <label for="name">Name</label>
          <input type="text" class="form-control" id="name" placeholder="Name" name="name">
        </div>
        <div class="form-group">
          <label for="directorName">Director</label>
          <input type="text" class="form-control" id="directorName" placeholder="Director" name="directorName">
        </div>
        <div class="form-group">
          <label for="birthDate">Birth Date</label>
          <input type="text" class="form-control" id="birthDate" placeholder="dd/MM/yyyy" name="birthDate">
        </div>
        <div class="form-group">
          <label for="appointmentDate">Appointment Date</label>
          <input type="text" class="form-control" id="appointmentDate" placeholder="dd/MM/yyyy" name="appointmentDate">
        </div>
        <div class="form-group">
          <label for="gender">Gender</label>
          <input type="text" class="form-control" id="gender" placeholder="Gender" name="gender">
        </div>
        <button type="submit" class="btn btn-default">Add</button>
      </form>
    </div>

</body>
</html>