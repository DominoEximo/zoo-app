<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Case</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="header.jsp"%>
</head>
<body>
<div class="container">
      <h2>Select a Zoo</h2>
      <form action= "animals" method="GET">
        <div class="form-group">
          <label for="name">Name of Zoo:</label>
          <input type="text" class="form-control" id="name" placeholder="Name of Zoo" name="name">
        </div>


        <button type="submit" class="btn btn-default">Search</button>
      </form>
    </div>

</body>
</html>