<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags"%>
<%@page import="hu.neuron.mentoring.zooapp.service.*"%>


<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <%@ include file="header.jsp"%>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <script>
      $( function() {
        $( "#birthDate" ).datepicker();
        $( "#appointmentDate" ).datepicker();
      } );
    </script>
</head>
<body>
    <div class="container">
      <h2>Cleaner</h2>
      <form action= "AddCleaner" method="GET">
        <div class="form-group">
          <label for="id">Name</label>
          <input type="text" class="form-control" id="id" placeholder="Name" name="id">
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
        <div class="form-group">
          <label for="cleanedAreas">Cleaned Areas</label>
          <input type="text" class="form-control" id="cleanedAreas" placeholder="Cleaned Areas" name="cleanedAreas">
        </div>
        <input type="hidden" name="name" value="${currentZoo.name}">

        <button type="submit" class="btn btn-default">Add</button>
      </form>
    </div>

</body>
</html>