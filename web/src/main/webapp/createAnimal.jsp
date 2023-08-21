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
      <h2>Animal</h2>
      <form action= "AddAnimal" method="GET">
        <div class="form-group">
          <label for="sepcies">Species</label>
          <input type="text" class="form-control" id="species" placeholder="Species" name="species">
        </div>
        <div class="form-group">
          <label for="nickname">Nickname</label>
          <input type="text" class="form-control" id="nickname" placeholder="Nickname" name="nickname">
        </div>
        <div class="form-group">
          <label for="birthDate">Birth Date</label>
          <input type="text" class="form-control" id="birthDate" placeholder="Birth Date" name="birthDate">
        </div>
        <div class="form-group">
          <label for="gender">Gender</label>
          <input type="text" class="form-control" id="gender" placeholder="Gender" name="gender">
        </div>
        <input type="hidden" name="name" value="${currentZoo.name}">

        <button type="submit" class="btn btn-default">Add</button>
      </form>
    </div>

</body>
</html>